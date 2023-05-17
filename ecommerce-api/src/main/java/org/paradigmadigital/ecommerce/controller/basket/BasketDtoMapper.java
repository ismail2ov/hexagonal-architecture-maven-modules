package org.paradigmadigital.ecommerce.controller.basket;

import org.mapstruct.Mapper;
import org.paradigmadigital.ecommerce.controller.product.ProductDto;
import org.paradigmadigital.ecommerce.domain.basket.Basket;
import org.paradigmadigital.ecommerce.domain.product.Product;

@Mapper
public interface BasketDtoMapper {

  BasketDto map(Basket basket);

  Product from(ProductDto productDto);
}
