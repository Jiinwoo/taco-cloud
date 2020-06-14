package me.jung.tacocloud.data;

import me.jung.tacocloud.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
