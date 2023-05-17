package org.paradigmadigital.ecommerce.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.paradigmadigital.ecommerce.application.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  private final ProductDtoMapper mapper;

  @GetMapping
  public List<ProductDto> getAll() {
    return mapper.map(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ProductPageDto getById(@PathVariable("id") Long id) {
    return mapper.map(productService.getProductBy(id));
  }

}
