package dev.anjalee.productcatalogservice.services;

import dev.anjalee.productcatalogservice.dtos.FakestoreProductDTO;
import dev.anjalee.productcatalogservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakestoreProductService implements IProductService {

    private RestTemplate restTemplate;

    public FakestoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
//       FakestoreProductDTO fakestoreProductDTO= restTemplate.getForObject("https://fakestoreapi.com/products/{id}", FakestoreProductDTO.class,id);
        ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakestoreProductDTO.class,id);
        if(fakestoreProductDTOResponseEntity.hasBody() && fakestoreProductDTOResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return fakestoreProductDTOResponseEntity.getBody().fakestoreDTOToProduct(fakestoreProductDTOResponseEntity.getBody());
        }
//        Product product = new Product();
//        product=fakestoreProductDTO.fakestoreDTOToProduct(fakestoreProductDTO);
//
//       return product;
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }
}
