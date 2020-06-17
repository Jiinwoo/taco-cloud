package me.jung.tacocloud.data;

import me.jung.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco,Long> {

}
