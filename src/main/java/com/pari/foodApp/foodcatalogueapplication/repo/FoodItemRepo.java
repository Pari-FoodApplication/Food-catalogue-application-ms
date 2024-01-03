package com.pari.foodApp.foodcatalogueapplication.repo;

import com.pari.foodApp.foodcatalogueapplication.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,Integer> {
    List<FoodItem> findFoodItemsByRestaurantId(Integer restaurantId);
}
