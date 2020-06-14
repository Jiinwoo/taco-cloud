package me.jung.tacocloud;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Taco {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdAt;
    @NotNull
    @Size(min=5, message="이름은 최소 5글자 이상이여야 합니다.")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1,message = "최소 1개 이상의 재료를 선택해야 합니다.")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }
}
