package com.thanhbt.product.service;

import com.thanhbt.product.model.Product;
import com.thanhbt.product.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
  List<Product> findAll();

  Product getProductById(String productId);

  Product addProduct(Product request);

  Product updateProduct(ProductRequest request, String productId);

  void deleteProduct(String productId);
}
