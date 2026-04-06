package dev.anjalee.productcatalogservice.controllers;

import dev.anjalee.productcatalogservice.dtos.ProductDTO;
import dev.anjalee.productcatalogservice.models.Product;
import dev.anjalee.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
     private IProductService  productService;

     public ProductController(@Qualifier("storageProductService") IProductService productService) {
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
     ProductDTO createProduct(@RequestBody ProductDTO productDTO){
          Product product=productDTO.productDTOToProduct(productDTO);

          Product product1=productService.addProduct(product);
          /*
          call the service layer to save the product
           */
          if(product1!=null){
               return product1.productToProductDTO(product1);
          }
          return null;
     }
     @GetMapping("products/{id}")
     ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id){

          if (id < 0) {
               throw new IllegalArgumentException("Product Id not found");
          } else if(id == 0) {
               throw new IllegalArgumentException("Products exist with positive id");
          }

          //RestTemplate
        /*
        call the service layer to get the product by id
         */

//        if(id < 1){
//            throw new IllegalArgumentException("Invalid Product ID(zero or negative)");
//        }



          Product product = productService.getProductById(id);


          if(product == null){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }



        /*
        product
        to productDTO

        obj.from(obj) -> newObj
         */

          ProductDTO productDTO = product.productToProductDTO(product);

          return new ResponseEntity<>(productDTO,HttpStatus.OK);
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
