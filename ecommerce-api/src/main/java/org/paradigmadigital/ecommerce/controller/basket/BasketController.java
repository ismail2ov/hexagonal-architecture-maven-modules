package org.paradigmadigital.ecommerce.controller.basket;

import lombok.RequiredArgsConstructor;
import org.paradigmadigital.ecommerce.application.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BasketController {

  private final BasketService basketService;

  private final BasketDtoMapper mapper;

  @GetMapping("/users/{userId}/basket")
  public BasketDto getByUserId(@PathVariable("userId") Long userId) {
    return mapper.map(basketService.getBy(userId));
  }

}
