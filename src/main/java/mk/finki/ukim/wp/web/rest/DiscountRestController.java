package mk.finki.ukim.wp.web.rest;

import mk.finki.ukim.wp.model.Discount;
import mk.finki.ukim.wp.model.dto.DiscountDto;
import mk.finki.ukim.wp.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class DiscountRestController {

    private final DiscountService discountService;

    public DiscountRestController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public List<Discount> findAll() {
        return this.discountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> findById(@PathVariable Long id) {
        return this.discountService.findById(id)
                .map(discount -> ResponseEntity.ok().body(discount))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Discount> save(@RequestBody DiscountDto discountDto) {
        return this.discountService.save(discountDto)
                .map(discount -> ResponseEntity.ok().body(discount))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Discount> edit(@PathVariable Long id, @RequestBody DiscountDto discountDto) {
        return this.discountService.edit(id, discountDto)
                .map(discount -> ResponseEntity.ok().body(discount))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.discountService.deleteById(id);
        if (this.discountService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/assign/{id}")
    public ResponseEntity<Discount> assignDiscount(@PathVariable Long id, @RequestParam String username) {
        return this.discountService.assignDiscount(username, id)
                .map(discount -> ResponseEntity.ok().body(discount))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
