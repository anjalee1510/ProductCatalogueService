package dev.anjalee.productcatalogservice.dtos;

import dev.anjalee.productcatalogservice.models.Category;
import dev.anjalee.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakestoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product fakestoreDTOToProduct(FakestoreProductDTO fakestoreProductDTO){
        Product product = new Product();
        product.setId(fakestoreProductDTO.getId());
        product.setName(fakestoreProductDTO.getTitle());
        product.setDescription(fakestoreProductDTO.getDescription());
        product.setPrice(fakestoreProductDTO.getPrice());
        product.setImageUrl(fakestoreProductDTO.getImage());
        Category category = new Category();
        category.setName(fakestoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
