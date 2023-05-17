package org.paradigmadigital.ecommerce.domain.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
  private Long id;

  private Long userId;

  private Items items = new Items();

}
