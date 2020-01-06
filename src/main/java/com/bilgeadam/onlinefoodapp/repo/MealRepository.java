package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends CrudRepository<Meal, Long> {
    List<Meal> findAll();
    void deleteById(Long code);
    Optional<Meal> findById(Long code);
    List<Meal> findAllByCampaignTrue();
}
