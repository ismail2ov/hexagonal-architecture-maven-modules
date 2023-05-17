package org.paradigmadigital.ecommerce.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.paradigmadigital.ecommerce.application.ProductService;
import org.paradigmadigital.ecommerce.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@Import(ProductDtoMapperImpl.class)
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @Test
  void return_products_list() throws Exception {
    Product product1 = new Product(1L, "Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €");
    Product product2 = new Product(2L, "Samsonite Airglow Laptop Sleeve 13.3", "41,34 €");
    Product product3 = new Product(3L, "Logitech Wireless Mouse M185", "10,78 €");
    Product product4 = new Product(4L, "Fellowes Mouse Pad Black", "1,34 €");

    when(productService.getAllProducts()).thenReturn(List.of(product1, product2, product3, product4));

    this.mockMvc
        .perform(get("/api/products"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(4))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].name").value("Samsonite Airglow Laptop Sleeve 13.3"))
        .andExpect(jsonPath("$[1].price").value("41,34 €"));
  }

}
