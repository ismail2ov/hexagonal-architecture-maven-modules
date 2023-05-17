package org.paradigmadigital.ecommerce.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.paradigmadigital.ecommerce.application.BasketService;
import org.paradigmadigital.ecommerce.controller.basket.BasketController;
import org.paradigmadigital.ecommerce.controller.basket.BasketDtoMapperImpl;
import org.paradigmadigital.ecommerce.domain.basket.Basket;
import org.paradigmadigital.ecommerce.domain.basket.Items;
import org.paradigmadigital.ecommerce.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BasketController.class)
@Import(BasketDtoMapperImpl.class)
public class BasketControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BasketService basketService;

  @Test
  void return_basket_without_items() throws Exception {

    Basket basket = new Basket(1L, 1L, new Items());

    when(basketService.getBy(1L)).thenReturn(basket);

    this.mockMvc
        .perform(get("/api/users/1/basket"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.userId").value(1))
        .andExpect(jsonPath("$.items.products.size()").value(0));
  }

  @Test
  void add_product_to_basket() throws Exception {
    Product product = new Product(3L, "Logitech Wireless Mouse M185", "10,78 €");
    Basket basket = new Basket(1L, 1L, new Items(List.of(product)));

    when(basketService.addProductToBasket(1L, product)).thenReturn(basket);

    this.mockMvc
        .perform(post("/api/users/1/basket")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(product)))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.userId").value(1))
        .andExpect(jsonPath("$.items.size()").value(1));
  }

  private String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
