package com.thanhbt.product.service.impl;

import com.thanhbt.product.exception.ProductNotFoundException;
import com.thanhbt.product.model.Product;
import com.thanhbt.product.model.request.ProductRequest;
import com.thanhbt.product.repository.ProductRepository;
import com.thanhbt.product.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> findAll() {
    List<Product> products = productRepository.findAll();

    if (products.isEmpty()) {
      return List.of();
    }

    return products;
  }

  @Override
  public Product getProductById(String productId) {
    Optional<Product> optional = productRepository.findById(productId);

    if (optional.isEmpty()) {
      throw new ProductNotFoundException("Product not found by ID: " + productId);
    }

    return optional.get();
  }

  @Override
  public Product addProduct(Product request) {
    return productRepository.save(request);
  }

  @Override
  public Product updateProduct(ProductRequest request, String productId) {
    Product product = getProductById(productId);
    product.setName(request.getName());
    product.setPrice(request.getPrice());

    return productRepository.save(product);
  }

  @Override
  public void deleteProduct(String productId) {
    productRepository.deleteById(productId);
  }
}
