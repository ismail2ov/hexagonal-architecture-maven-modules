package org.paradigmadigital.ecommerce.controller;

import java.util.List;
import org.mapstruct.Mapper;
import org.paradigmadigital.ecommerce.domain.Product;

@Mapper
public interface ProductDtoMapper {

  List<ProductDto> map(List<Product> products);
}
