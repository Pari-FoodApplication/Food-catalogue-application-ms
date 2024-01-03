package com.pari.foodApp.foodcatalogueapplication.mapper;

import com.pari.foodApp.foodcatalogueapplication.dto.FoodItemDto;
import com.pari.foodApp.foodcatalogueapplication.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDto foodItemDTO);

    FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);


}