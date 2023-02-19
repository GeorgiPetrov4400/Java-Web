package softuni.pathfinder.service;

import softuni.pathfinder.model.entity.Category;
import softuni.pathfinder.model.enums.CategoryName;

public interface CategoryService {

    Category findCategoryByName(CategoryName categoryName);
}
