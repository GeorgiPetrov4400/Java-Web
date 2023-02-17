package bg.softuni.battleships.service;

import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.entity.CategoryNameEnum;

public interface CategoryService {
    void seedCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
