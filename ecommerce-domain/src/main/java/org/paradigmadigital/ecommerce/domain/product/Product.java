package org.paradigmadigital.ecommerce.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private Long id;

  private String name;

  private String price;

  public Product(String name, String price) {
    this.name = name;
    this.price = price;
  }
}
