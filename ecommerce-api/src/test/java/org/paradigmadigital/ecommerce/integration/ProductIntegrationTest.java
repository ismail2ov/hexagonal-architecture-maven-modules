package org.paradigmadigital.ecommerce.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.paradigmadigital.ecommerce.controller.product.ProductPageDto;
import org.paradigmadigital.ecommerce.domain.product.Product;
import org.paradigmadigital.ecommerce.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest extends BaseTestcontainers {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  public TestRestTemplate testRestTemplate;

  @AfterEach
  void tearDown() {
    productRepository.deleteAll();
  }

  @Test
  public void when_try_to_get_product_that_not_exists_then_returns_not_found() {

    ResponseEntity<ProductPageDto> result = testRestTemplate.getForEntity("/api/products/1", ProductPageDto.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void when_get_products_then_return_all_products() {
    productRepository.save(new Product("Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €"));
    productRepository.save(new Product("Samsonite Airglow Laptop Sleeve 13.3", "41,34 €"));
    productRepository.save(new Product("Logitech Wireless Mouse M185", "10,78 €"));
    productRepository.save(new Product("Fellowes Mouse Pad Black", "1,34 €"));

    ResponseEntity<Product[]> result = testRestTemplate.getForEntity("/api/products", Product[].class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result).isNotNull();
    assertThat(result.getBody()).hasSize(4);
  }

  @Test
  public void when_get_product_by_id_then_return_also_cross_sell_products() {
    Product product1 = new Product("Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €");
    productRepository.save(product1);
    productRepository.save(new Product("Samsonite Airglow Laptop Sleeve 13.3", "41,34 €"));
    productRepository.save(new Product("Logitech Wireless Mouse M185", "10,78 €"));

    productRepository.addCrossSellProducts(1L, 2L);
    productRepository.addCrossSellProducts(1L, 3L);

    ResponseEntity<ProductPageDto> result = testRestTemplate.getForEntity("/api/products/1", ProductPageDto.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result).isNotNull();
    assertThat(result.getBody().getProduct().getName()).isEqualTo(product1.getName());
    assertThat(result.getBody().getCrossSelling()).hasSize(2);
  }

}
