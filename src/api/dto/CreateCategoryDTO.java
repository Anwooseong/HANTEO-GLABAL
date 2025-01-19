package api.dto;

import entity.category.Category;

public record CreateCategoryDTO(Long id, String name) {
    public static CreateCategoryDTO from(Category category) {
        return new CreateCategoryDTO(category.getCategoryId(), category.getCategoryName());
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %d, \"name\": \"%s\"}",
                id, name);
    }
}
