package com.thanhbt.product.controller;

import com.thanhbt.product.model.Product;
import com.thanhbt.product.model.request.ProductRequest;
import com.thanhbt.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Product> getProductById(
      @PathVariable String productId
  ) {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @PostMapping
  public ResponseEntity<Product> addProduct(
      @RequestBody Product request
  ) {
    return ResponseEntity.ok(productService.addProduct(request));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable String productId,
      @RequestBody ProductRequest request
  ) {
    return ResponseEntity.ok(productService.updateProduct(request, productId));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteProduct(
      @PathVariable String productId
  ) {
    productService.deleteProduct(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
