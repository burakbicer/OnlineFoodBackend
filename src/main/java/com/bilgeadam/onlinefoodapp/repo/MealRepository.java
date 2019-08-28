package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MealRepository extends CrudRepository<Meal, String> {
    List<Meal> findAll();
    void deleteById(String code);
}
