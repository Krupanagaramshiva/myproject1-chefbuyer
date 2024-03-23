package com.food.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.food.model.Chef;
@Repository
public interface ChefRepository extends CrudRepository<Chef, String> {

	Chef findByGmail(String gmail);

	List<Chef> findByLocation(String location);

}
