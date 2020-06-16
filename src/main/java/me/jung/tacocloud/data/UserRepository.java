package me.jung.tacocloud.data;

import me.jung.tacocloud.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
