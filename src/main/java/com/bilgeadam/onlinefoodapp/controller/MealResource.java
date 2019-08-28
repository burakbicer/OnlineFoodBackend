package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/meals")
public class MealResource {

    private final MealService mealService;

    @Autowired
    public MealResource(MealService mealService) {
        this.mealService = mealService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<Meal> getAllMeals(){
        return mealService.getAllMeals();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code){
        try{
            mealService.delete(code);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
