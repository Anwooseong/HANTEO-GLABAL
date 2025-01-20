package num1.api.service.category;

import num1.api.CustomApiException;
import num1.entity.board.BoardRepository;
import num1.entity.category.Category;
import num1.entity.category.CategoryRepository;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(String name, Long parentId) {
        if (name == null || name.trim().isEmpty()) {
            throw new CustomApiException("카테고리 이름은 필수입니다.");
        }

        // 부모 카테고리 유효성 검사
        if (parentId != null && parentId <= 0) {
            throw new CustomApiException("유효한 부모 카테고리 ID를 입력하세요.");
        }

        Category newCategory = new Category(name, parentId);
        categoryRepository.save(newCategory);

        if (parentId != null) {
            Category parentCategory = categoryRepository.findById(parentId);
            if (parentCategory != null) {
                categoryRepository.addSubCategory(parentCategory, newCategory);
            } else {
                throw new CustomApiException("존재하지 않는 부모 카테고리 ID입니다.");
            }
        }
        return newCategory;
    }

    @Override
    public Category searchCategoryId(Long categoryId) {
        if (categoryId == null || categoryId <= 0) {
            throw new CustomApiException("유효한 카테고리 ID를 입력하세요.");
        }

        Category category = categoryRepository.findById(categoryId); // ID로 카테고리 검색
        if (category == null) {
            throw new CustomApiException("존재하지 않는 카테고리 ID입니다.");
        }
        return category;
    }

    @Override
    public Category searchCategoryName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new CustomApiException("카테고리 이름은 필수입니다.");
        }

        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new CustomApiException("존재하지 않는 카테고리 이름입니다.");
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
