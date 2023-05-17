package org.paradigmadigital.ecommerce.domain;

import java.util.List;
import lombok.Value;

@Value
public class ProductPage {

  Product product;

  List<Product> crossSelling;
}
