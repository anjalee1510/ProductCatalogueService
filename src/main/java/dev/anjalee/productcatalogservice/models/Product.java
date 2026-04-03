package dev.anjalee.productcatalogservice.models;

import dev.anjalee.productcatalogservice.dtos.CategoryDTO;
import dev.anjalee.productcatalogservice.dtos.FakestoreProductDTO;
import dev.anjalee.productcatalogservice.dtos.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategoryDTO(categoryDTO);

        }
        return productDTO;
    }

    public FakestoreProductDTO productToFakestoreProductDTO(Product product) {
        FakestoreProductDTO fakestoreProductDTO = new FakestoreProductDTO();
        fakestoreProductDTO.setId(product.getId());
        fakestoreProductDTO.setTitle(product.getName());
        fakestoreProductDTO.setDescription(product.getDescription());
        fakestoreProductDTO.setPrice(product.getPrice());
        fakestoreProductDTO.setImage(product.getImageUrl());
        if(product.getCategory() != null) {

            fakestoreProductDTO.setCategory(product.getCategory().getName());

        }
        return fakestoreProductDTO;
    }
}
