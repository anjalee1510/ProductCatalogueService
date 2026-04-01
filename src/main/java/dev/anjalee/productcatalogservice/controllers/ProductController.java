package dev.anjalee.productcatalogservice.controllers;

import dev.anjalee.productcatalogservice.dtos.ProductDTO;
import dev.anjalee.productcatalogservice.models.Product;
import dev.anjalee.productcatalogservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
     private IProductService  productService;

     public ProductController(IProductService productService) {
          this.productService = productService;
     }
     /*
     1.create product
     2.get product by id
     3. get all product
      */

     /*
     Create product ("/products"), POST
     get product by id ("/products/{id}"),GET
     get all products ("/products"),GET
      */
     @PostMapping("/products")
     ProductDTO createProduct(@RequestBody ProductDTO product){
          ProductDTO productDTO =new ProductDTO();

          /*
          call the service layer to save the product
           */
          return productDTO;
     }
     @GetMapping("products/{id}")
     ProductDTO getProductById(@PathVariable("id") Long id){
          ProductDTO productDTO=new ProductDTO();

          if(id<1){
               throw new IllegalArgumentException("Invalid product ID(0 or negative");
          }
          Product product=productService.getProductById(id);
          productDTO=product.productToProductDTO(product);
          return productDTO;
     }

     @GetMapping("/products")
     List<ProductDTO> getAllProduct(){

          List<ProductDTO> productDTOs=new ArrayList<>();
          List<Product> products=productService.getAllProducts();
          for(Product product:products){
               productDTOs.add(product.productToProductDTO(product));
          }
          return productDTOs;
     }

     @PutMapping("/products/{id}")
     ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
          Product product=productDTO.productDTOToProduct(productDTO);

          Product product1=productService.replaceProduct(product,id);
          if(product1 != null){
               return product1.productToProductDTO(product1);
          }
          return null;
     }
}
