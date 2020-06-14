package me.jung.tacocloud.data;

import me.jung.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
