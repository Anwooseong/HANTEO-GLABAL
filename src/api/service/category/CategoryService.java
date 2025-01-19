package api.service.category;

import entity.category.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(String name, Long parentId);
    Category searchCategoryId(Long categoryId);
    Category searchCategoryName(String categoryName);
    List<Category> getAllCategories();

}
