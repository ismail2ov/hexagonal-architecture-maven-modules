package org.paradigmadigital.ecommerce.domain.basket;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.paradigmadigital.ecommerce.domain.product.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {

  List<Product> products = new ArrayList<>();

  public void addItem(Product product) {
    if (!products.contains(product)) {
      products.add(product);
    }
  }
}
