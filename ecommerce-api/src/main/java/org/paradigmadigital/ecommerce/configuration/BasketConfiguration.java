package org.paradigmadigital.ecommerce.configuration;

import org.paradigmadigital.ecommerce.application.BasketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasketConfiguration {

  @Bean
  public BasketService basketService() {
    return new BasketService();
  }
}
