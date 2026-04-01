package dev.anjalee.productcatalogservice.clients;

import dev.anjalee.productcatalogservice.dtos.FakestoreProductDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreAPIClient {
    private RestTemplate restTemplate;

    public FakeStoreAPIClient(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables));
    }


    public <T> ResponseEntity<T> getForEntity(String url,  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables));
    }

    public Boolean validateResponse(ResponseEntity<FakestoreProductDTO> fakestoreProductDTOResponseEntity){
        if(fakestoreProductDTOResponseEntity.hasBody() &&
                fakestoreProductDTOResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return true;
        }
        return false;
    }


}
