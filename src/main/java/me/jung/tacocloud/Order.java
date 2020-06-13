package me.jung.tacocloud;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

@Data
public class Order {
    @NotBlank(message = "Name 필수 항목 입니다.")
    private String name;
    @NotBlank(message = "street 필수 항목 입니다.")
    private String street;
    @NotBlank(message = "city 필수 항목 입니다.")
    private String city;
    @NotBlank(message = "state 필수 항목 입니다.")
    private String state;
    @NotBlank(message = "zip 필수 항목 입니다.")
    private String zip;
    @CreditCardNumber(message = "유효한 카드 번호가 아닙니다.")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
}
