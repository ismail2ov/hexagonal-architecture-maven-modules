package org.paradigmadigital.ecommerce.controller.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.paradigmadigital.ecommerce.domain.basket.Items;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {

  private Long id;

  private Long userId;

  private Items items = new Items();

}
