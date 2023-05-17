package org.paradigmadigital.ecommerce.persistence;

import java.util.List;
import org.mapstruct.Mapper;
import org.paradigmadigital.ecommerce.domain.product.Product;

@Mapper
public interface ProductEntityMapper {

  List<Product> map(List<ProductEntity> products);

  Product from(ProductEntity productEntity);

  ProductEntity map(Product product);
}
