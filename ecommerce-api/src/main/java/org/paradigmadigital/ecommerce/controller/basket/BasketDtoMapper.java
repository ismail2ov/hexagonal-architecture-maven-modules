package org.paradigmadigital.ecommerce.controller.basket;

import org.mapstruct.Mapper;
import org.paradigmadigital.ecommerce.domain.basket.Basket;

@Mapper
public interface BasketDtoMapper {

  BasketDto map(Basket basket);
}
