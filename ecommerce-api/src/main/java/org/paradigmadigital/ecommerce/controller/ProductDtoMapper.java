package org.paradigmadigital.ecommerce.controller;

import java.util.List;
import org.mapstruct.Mapper;
import org.paradigmadigital.ecommerce.domain.Product;
import org.paradigmadigital.ecommerce.domain.ProductPage;

@Mapper
public interface ProductDtoMapper {

  List<ProductDto> map(List<Product> products);

  ProductPageDto map(ProductPage productPage);
}
