package org.paradigmadigital.ecommerce.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.paradigmadigital.ecommerce.domain.product.Product;
import org.paradigmadigital.ecommerce.domain.product.ProductNotFoundException;
import org.paradigmadigital.ecommerce.domain.product.ProductPage;
import org.paradigmadigital.ecommerce.domain.product.ProductRepository;

@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public ProductPage getProductBy(long id) {
    Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    List<Product> crossSellProducts = productRepository.getCrossSellProducts(id);

    return new ProductPage(product, crossSellProducts);
  }
}
