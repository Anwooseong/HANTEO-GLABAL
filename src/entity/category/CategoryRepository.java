package entity.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepository {
    private static List<Category> categories = new ArrayList<>(); // 카테고리 저장을 위한 리스트
    private static Map<Long, Category> store = new HashMap<>(); // 카테고리 저장소
    private static Long sequenceId = 0L;

    public Category save(Category category) {
        category.setCategoryId(++sequenceId);
        store.put(category.getCategoryId(), category);
        return category;
    }

    public Category findById(Long id) {
        return store.get(id);
    }

    public List<Category> findAll() {
        return new ArrayList<>(store.values());
    }

    public Category findByCategoryName(String categoryName) {
        for (Category category : store.values()) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
            for (Category subCategory : category.getSubCategories()) {
                if (subCategory.getCategoryName().equals(categoryName)) {
                    return subCategory;
                }
            }
        }
        return null;
    }

    public void addSubCategory(Category parentCategory, Category newCategory) {
        // 부모 ID를 통해 해당 부모 카테고리를 찾아서 하위 카테고리 추가
        parentCategory.addSubCategory(newCategory);
        // 하위 카테고리도 저장
    }
}
