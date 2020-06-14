package me.jung.tacocloud.data;

import me.jung.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco,Long>  {

}
