package dev.anjalee.productcatalogservice.repositories;

import dev.anjalee.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    public List<Product> findByPriceBetween(Double low,Double high);

    @Query("SELECT p.description FROM Product p WHERE p.id= :id") // Hibernate Query Language
    String findDescriptionWhereIdIs(@Param("id") Long id);
}
