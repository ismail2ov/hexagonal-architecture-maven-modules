package org.paradigmadigital.ecommerce.domain.product;

import java.util.List;
import lombok.Value;

@Value
public class ProductPage {

  Product product;

  List<Product> crossSelling;
}
