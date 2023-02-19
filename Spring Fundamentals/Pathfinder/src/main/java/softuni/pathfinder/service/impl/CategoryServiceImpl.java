package softuni.pathfinder.service.impl;

import org.springframework.stereotype.Service;
import softuni.pathfinder.model.entity.Category;
import softuni.pathfinder.model.enums.CategoryName;
import softuni.pathfinder.repository.CategoryRepository;
import softuni.pathfinder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(CategoryName categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }
}
