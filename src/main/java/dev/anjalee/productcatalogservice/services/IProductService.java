package dev.anjalee.productcatalogservice.services;

import dev.anjalee.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
}
