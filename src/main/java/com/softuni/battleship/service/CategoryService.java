package com.softuni.battleship.service;

import com.softuni.battleship.model.CategoryEntity;
import com.softuni.battleship.model.enums.CategoryEnum;
import com.softuni.battleship.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    private void intiCategories() {
        if (categoryRepository.count() == 0) {
            List<CategoryEntity> categories = Arrays.stream(CategoryEnum.values())
                    .map(value -> new CategoryEntity().setName(value))
                    .collect(Collectors.toList());
            categoryRepository.saveAll(categories);
        }
    }

    public CategoryEntity findCategoryEntity(CategoryEnum name){
        Optional<CategoryEntity> categoryOpt = this.categoryRepository.findByName(name);
        return categoryOpt.get();
    }
}
