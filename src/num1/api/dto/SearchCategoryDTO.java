package num1.api.dto;

import num1.entity.category.Category;

import java.util.List;

public record SearchCategoryDTO(Long categoryId, String categoryName, List<Category> subCategories, List<Long> boardIds) {

    public static SearchCategoryDTO from(Category category) {
        return new SearchCategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getSubCategories(),
                category.getBoardIds()
        );
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %d, \"name\": \"%s\", \"boardIds\": %s, \"subCategories\": %s}",
                categoryId, categoryName, boardIds, subCategories);

    }
}
