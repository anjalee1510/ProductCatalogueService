package dev.anjalee.productcatalogservice.services;

import dev.anjalee.productcatalogservice.clients.FakeStoreAPIClient;
import dev.anjalee.productcatalogservice.dtos.FakestoreProductDTO;
import dev.anjalee.productcatalogservice.models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class FakestoreProductService implements IProductService {

    private FakeStoreAPIClient fakeStoreAPIClient;



    public FakestoreProductService(FakeStoreAPIClient fakeStoreAPIClient) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }

    @Override
    public Product getProductById(Long id) {
//       FakestoreProductDTO fakestoreProductDTO= restTemplate.getForObject("https://fakestoreapi.com/products/{id}", FakestoreProductDTO.class,id);
        ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity=fakeStoreAPIClient.getForEntity("https://fakestoreapi.com/products/{id}", FakestoreProductDTO.class,id);
        if(fakeStoreAPIClient.validateResponse(fakestoreProductDTOResponseEntity)){
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
        List<Product> products=new ArrayList<>();
        ResponseEntity<FakestoreProductDTO[]> response=fakeStoreAPIClient.getForEntity("https://fakestoreapi.com/products",  FakestoreProductDTO[].class);
        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            FakestoreProductDTO[] fakestoreProductDTOS=response.getBody();
            for(FakestoreProductDTO fakestoreProductDTO: fakestoreProductDTOS){
                products.add(fakestoreProductDTO.fakestoreDTOToProduct(fakestoreProductDTO));
            }
            return products;
        }

        return null;
    }

//    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
//        return (restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables));
//    }

    public Product replaceProduct(Product product,Long id){
        FakestoreProductDTO  fakestoreProductDTO=product.productToFakestoreProductDTO(product);
        ResponseEntity<FakestoreProductDTO>response=fakeStoreAPIClient.putForEntity("http://fakestoreapi.com/products/{id}",fakestoreProductDTO,FakestoreProductDTO.class,id);
        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return response.getBody().fakestoreDTOToProduct(fakestoreProductDTO);
        }
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }
}
