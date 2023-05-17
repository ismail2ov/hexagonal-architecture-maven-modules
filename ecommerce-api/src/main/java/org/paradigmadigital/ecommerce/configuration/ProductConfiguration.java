package org.paradigmadigital.ecommerce.configuration;

import org.paradigmadigital.ecommerce.application.ProductService;
import org.paradigmadigital.ecommerce.domain.product.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductService(productRepository);
  }
}
