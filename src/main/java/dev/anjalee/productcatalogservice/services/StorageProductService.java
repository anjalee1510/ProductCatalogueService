package dev.anjalee.productcatalogservice.services;

import dev.anjalee.productcatalogservice.models.Product;
import dev.anjalee.productcatalogservice.models.State;
import dev.anjalee.productcatalogservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service("storageProductService")
//@Primary
public class StorageProductService implements IProductService{

    private ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
//        return List.of();
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> optionalProduct=productRepository.findById(product.getId());
        if(optionalProduct.isEmpty()){
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product replaceProduct(Product input, Long productId) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            return null;
        }else{
            input.setId(productId);
            input.setCreatedAt(optionalProduct.get().getCreatedAt());
//            input.setLastUpdatedAt((String)LocalDateTime.now());
            return productRepository.save(input);
        }

        }

        public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            return false;
        }
        else{
            Product product=optionalProduct.get();
            if(product.getState().equals(State.ACTIVE)) {
                product.setState(State.INACTIVE);
                productRepository.save(product);
                return true;
            }
            return  false;
        }
        }

}
