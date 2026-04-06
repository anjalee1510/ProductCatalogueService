package dev.anjalee.productcatalogservice.controllers;

import dev.anjalee.productcatalogservice.services.StorageProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static java.lang.reflect.Array.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StorageProductService productService;
    @Test
    public void testGetAllProducts_RunSuccessfully() throws Exception{
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

}
