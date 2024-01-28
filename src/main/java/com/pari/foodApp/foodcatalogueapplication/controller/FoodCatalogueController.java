package com.pari.foodApp.foodcatalogueapplication.controller;

import com.pari.foodApp.foodcatalogueapplication.dto.FoodCataloguePage;
import com.pari.foodApp.foodcatalogueapplication.dto.FoodItemDto;
import com.pari.foodApp.foodcatalogueapplication.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    public FoodCatalogueService foodCatalogueService;
    @GetMapping(path = "foodItem/id/{id}")
    public ResponseEntity<FoodItemDto> getFoodItemById(@PathVariable Integer id){
        return foodCatalogueService.getFoodItemById(id);
    }

    @PostMapping(path = "foodItem")
    public ResponseEntity<FoodItemDto> addUser(@RequestBody FoodItemDto foodItemDto){
        FoodItemDto addedFoodItem = foodCatalogueService.addFoodItem(foodItemDto);
        return new ResponseEntity<>(addedFoodItem, HttpStatus.OK);
    }
    @GetMapping(path = "foodCatalog/restaurantId/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getFoodCataloguePageByRestaurantId(@PathVariable Integer restaurantId){
        return new ResponseEntity<>(foodCatalogueService.getFoodCataloguePageByRestaurantId(restaurantId),HttpStatus.OK);
    }
}
