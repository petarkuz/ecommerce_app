package mk.finki.ukim.wp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscountDto {
    private LocalDateTime dateExpired;

    public DiscountDto() {
    }

    public DiscountDto(LocalDateTime dateExpired) {
        this.dateExpired = dateExpired;
    }
}
