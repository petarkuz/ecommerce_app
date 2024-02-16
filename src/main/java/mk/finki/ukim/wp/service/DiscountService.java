package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Discount;
import mk.finki.ukim.wp.model.dto.DiscountDto;

import java.util.List;
import java.util.Optional;

public interface DiscountService {
    List<Discount> findAll();

    Optional<Discount> findById(Long id);

    Optional<Discount> save(DiscountDto discountDto);

    Optional<Discount> edit(Long id, DiscountDto discountDto);

    void deleteById(Long id);

    Optional<Discount> assignDiscount(String username, Long discountId);
}
