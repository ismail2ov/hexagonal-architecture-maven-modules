package org.paradigmadigital.ecommerce.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  List<Product> findAll();

  Optional<Product> findById(long id);

  List<Product> getCrossSellProducts(long id);

  void deleteAll();

  void save(Product product);

  void addCrossSellProducts(long productId, long xsellId);
}
