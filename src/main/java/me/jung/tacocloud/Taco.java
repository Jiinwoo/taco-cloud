package me.jung.tacocloud;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Taco {
    private String name;
    private List<String> ingredients;
}
