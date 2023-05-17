package org.paradigmadigital.ecommerce.persistence;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.paradigmadigital.ecommerce.domain.product.Product;
import org.paradigmadigital.ecommerce.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpaRepository jpaRepository;

  private final ProductEntityMapper mapper;

  @Override
  public List<Product> findAll() {
    return mapper.map(jpaRepository.findAll());
  }

  @Override
  public Optional<Product> findById(long id) {
    Optional<ProductEntity> productEntity = jpaRepository.findById(id);
    return productEntity.map(mapper::from);
  }

  @Override
  public List<Product> getCrossSellProducts(long id) {
    return mapper.map(jpaRepository.getCrossSellProducts(id));
  }
}
