package org.paradigmadigital.ecommerce.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

  @Query(value = "SELECT *  FROM products WHERE id IN (SELECT xsell_id FROM cross_sales WHERE product_id = :productId)", nativeQuery = true)
  List<ProductEntity> getCrossSellProducts(long productId);

}
