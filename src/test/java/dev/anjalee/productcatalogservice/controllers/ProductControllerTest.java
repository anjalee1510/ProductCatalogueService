package dev.anjalee.productcatalogservice.controllers;

import dev.anjalee.productcatalogservice.dtos.ProductDTO;
import dev.anjalee.productcatalogservice.models.Product;
import dev.anjalee.productcatalogservice.services.IProductService;
import dev.anjalee.productcatalogservice.services.StorageProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockitoBean
    private StorageProductService productService;

    /*
    happy case for getProductById
    Arrange,Act,Assert
     */
    @Test
    public void testGetProductById_WithValidId_RunSuccessfully(){
        //Arrange
        Product product=new Product();
        product.setId(1L);
        product.setName("iPhone 14");
        product.setDescription("Apple iPhone 14 with A15 Bionic chip");
        product.setPrice(499.99);

        when(productService.getProductById(1L)).thenReturn(product);

        //Act
        ResponseEntity<ProductDTO> productDTOResponseEntity=productController.getProductById(1L);

        //Assert
        assertNotNull(productDTOResponseEntity);
        assertNotNull(productDTOResponseEntity.getBody());
        assertEquals(product.getId(),productDTOResponseEntity.getBody().getId());
        assertEquals(product.getName(),productDTOResponseEntity.getBody().getName());
        assertEquals(product.getDescription(),productDTOResponseEntity.getBody().getDescription());

        verify(productService,times(1)).getProductById(1L);
        /*
        the verify() method is used to check
         */
    }

    /*
    Sad Scenario
    assertThrows(ExpectedException.class,()->{
    //code that should throw the exception
    });
     */

    @Test
    public void testGetProductById_WithNegativeId_ThrowsIllegalArgumentException(){
        Exception exception=assertThrows(IllegalArgumentException.class,()->{
            productController.getProductById((-1L));
        });


        verify(productService,times(0)).getProductById(-1L);
        assertEquals("Product Id not found",exception.getMessage());


    }

}