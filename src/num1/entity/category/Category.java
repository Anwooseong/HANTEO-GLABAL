package num1.entity.category;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Long categoryId;
    private String categoryName;
    private Long parentId;
    private List<Category> subCategories = new ArrayList<>();
    private List<Long> boardIds = new ArrayList<>();

    public Category(String categoryName, Long parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getParentId() {
        return parentId;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(Category category) {
        subCategories.add(category);
    }

    public void addBoardId(Long boardId) {
        boardIds.add(boardId);
    }

    public List<Long> getBoardIds() {
        return boardIds;
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %d, \"name\": \"%s\", \"boardIds\": %s, \"subCategories\": %s}",
                categoryId, categoryName, boardIds, subCategories);
    }
}
