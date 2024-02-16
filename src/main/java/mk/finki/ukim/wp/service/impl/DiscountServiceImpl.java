package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Discount;
import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.dto.DiscountDto;
import mk.finki.ukim.wp.model.exceptions.DiscountNotFoundException;
import mk.finki.ukim.wp.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wp.repository.DiscountRepository;
import mk.finki.ukim.wp.repository.UserRepository;
import mk.finki.ukim.wp.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository, UserRepository userRepository) {
        this.discountRepository = discountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Discount> findAll() {
        return this.discountRepository.findAll();
    }

    @Override
    public Optional<Discount> findById(Long id) {
        return this.discountRepository.findById(id);
    }

    @Override
    public Optional<Discount> save(DiscountDto discountDto) {
        return Optional.of(this.discountRepository.save(new Discount(discountDto.getDateExpired())));

    }

    @Override
    public Optional<Discount> edit(Long id, DiscountDto discountDto) {
        Discount discount = this.discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));

        discount.setDateExpired(discountDto.getDateExpired());

        return Optional.of(this.discountRepository.save(discount));
    }

    @Override
    public void deleteById(Long id) {
        this.discountRepository.deleteById(id);
    }

    @Override
    public Optional<Discount> assignDiscount(String username, Long discountId) {
        User user = this.userRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Discount discount = this.discountRepository.findById(discountId)
                .orElseThrow(() -> new DiscountNotFoundException(discountId));

        discount.getUsers().add(user);

        return Optional.of(this.discountRepository.save(discount));
    }
}
