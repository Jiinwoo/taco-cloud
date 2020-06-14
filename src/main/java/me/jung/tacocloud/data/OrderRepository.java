package me.jung.tacocloud.data;

import me.jung.tacocloud.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {
//    List<Order> findByDeliveryZip(String deliveryZip);
}
