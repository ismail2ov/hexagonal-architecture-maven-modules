package org.paradigmadigital.ecommerce.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.paradigmadigital.ecommerce.application.BasketService;
import org.paradigmadigital.ecommerce.controller.basket.BasketController;
import org.paradigmadigital.ecommerce.controller.basket.BasketDtoMapperImpl;
import org.paradigmadigital.ecommerce.domain.basket.Basket;
import org.paradigmadigital.ecommerce.domain.basket.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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

}
