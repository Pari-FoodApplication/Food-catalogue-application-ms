package com.pari.foodApp.foodcatalogueapplication.service;

import com.pari.foodApp.foodcatalogueapplication.dto.FoodCataloguePage;
import com.pari.foodApp.foodcatalogueapplication.dto.FoodItemDto;
import com.pari.foodApp.foodcatalogueapplication.dto.Restaurant;
import com.pari.foodApp.foodcatalogueapplication.entity.FoodItem;
import com.pari.foodApp.foodcatalogueapplication.mapper.FoodItemMapper;
import com.pari.foodApp.foodcatalogueapplication.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCatalogueService {

    @Autowired
    public FoodItemRepo foodItemRepo;

    @Autowired
    public RestTemplate restTemplate;

    public ResponseEntity<FoodItemDto> getFoodItemById(Integer id){
        Optional<FoodItem> foodItem = foodItemRepo.findById(id);
        if(foodItem.isPresent()){
            FoodItemDto userDto = FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItem.get());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto){
        FoodItem addedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDto));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(addedFoodItem);
    }
    public FoodCataloguePage getFoodCataloguePageByRestaurantId(Integer restaurantId){
        List<FoodItem> foodItems = getFoodItemByRestaurantId(restaurantId);
        Restaurant restaurant = getRestaurantDetails(restaurantId);
        return createFoodCataloguePage(foodItems, restaurant);
    }

    private List<FoodItem> getFoodItemByRestaurantId(Integer restaurantId){
        return foodItemRepo.findFoodItemsByRestaurantId(restaurantId);
    }

    private Restaurant getRestaurantDetails(Integer restaurantId){
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/id/" + restaurantId, Restaurant.class);

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItems, Restaurant restaurant){
        return new FoodCataloguePage(foodItems,restaurant);
    }
}
