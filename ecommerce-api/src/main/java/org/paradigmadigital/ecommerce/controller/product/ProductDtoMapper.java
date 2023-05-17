package org.paradigmadigital.ecommerce.controller.product;

import java.util.List;
import org.mapstruct.Mapper;
import org.paradigmadigital.ecommerce.domain.product.Product;
import org.paradigmadigital.ecommerce.domain.product.ProductPage;

@Mapper
public interface ProductDtoMapper {

  List<ProductDto> map(List<Product> products);

  ProductPageDto map(ProductPage productPage);
}
