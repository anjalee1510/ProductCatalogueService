package dev.anjalee.productcatalogservice.repositories;

import dev.anjalee.productcatalogservice.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository  categoryRepository;

    @Test
    @Transactional
   public void findFetchTypes(){
        Optional<Category> categoryOpt=categoryRepository.findById(3L);
        if (categoryOpt.isPresent()) {

            Category category=categoryOpt.get();
            System.out.println(category.getName());

        }
    }

}