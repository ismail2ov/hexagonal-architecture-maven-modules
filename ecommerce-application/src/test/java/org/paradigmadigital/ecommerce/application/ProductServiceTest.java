package org.paradigmadigital.ecommerce.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.paradigmadigital.ecommerce.domain.product.Product;
import org.paradigmadigital.ecommerce.domain.product.ProductNotFoundException;
import org.paradigmadigital.ecommerce.domain.product.ProductPage;
import org.paradigmadigital.ecommerce.domain.product.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  ProductRepository productRepository;

  ProductService productService;

  @BeforeEach
  void setUp() {
    productService = new ProductService(productRepository);
  }

  @Test
  void return_products_list() throws Exception {
    Product product1 = new Product(1L, "Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €");
    Product product2 = new Product(2L, "Samsonite Airglow Laptop Sleeve 13.3", "41,34 €");

    when(productRepository.findAll()).thenReturn(List.of(product1, product2));

    List<Product> actual = productService.getAllProducts();

    assertThat(actual).containsExactly(product1, product2);
  }

  @Test
  void return_product_by_id_and_cross_sell_products() {
    Product product1 = new Product(1L, "Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €");
    Product product2 = new Product(2L, "Samsonite Airglow Laptop Sleeve 13.3", "41,34 €");
    Product product3 = new Product(3L, "Logitech Wireless Mouse M185", "10,78 €");

    ProductPage expected = new ProductPage(product1, List.of(product2, product3));

    when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
    when(productRepository.getCrossSellProducts(1L)).thenReturn(List.of(product2, product3));

    ProductPage actual = productService.getProductBy(1L);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void throw_exception_when_product_does_not_exists() {
    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    Throwable thrown = catchThrowable(() -> productService.getProductBy(1L));

    assertThat(thrown).isInstanceOf(ProductNotFoundException.class);
  }
}