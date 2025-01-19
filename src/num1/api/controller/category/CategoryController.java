package num1.api.controller.category;

import num1.api.ApiResponse;
import num1.api.CustomApiException;
import num1.api.dto.CreateCategoryDTO;
import num1.api.dto.SearchCategoryDTO;
import num1.api.service.category.CategoryService;
import num1.entity.category.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            displayCategoryMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();
            ApiResponse response;
            switch (choice) {
                case 1 -> {
                    // 카테고리 추가
                    System.out.println("--------------카테고리 추가-----------");
                    System.out.print("추가할 카테고리명: ");
                    String categoryName = scanner.nextLine();
                    printAllCategories();
                    System.out.print("부모 카테고리 ID (없으면 0 입력): ");
                    Long parentId = scanner.nextLong();
                    if (parentId == 0) {
                        parentId = null;
                    }
                    try {
                        Category saveCategory = categoryService.addCategory(categoryName, parentId);
                        CreateCategoryDTO createCategoryDTO = CreateCategoryDTO.from(saveCategory);
                        response = ApiResponse.create(true, createCategoryDTO, "카테고리 추가가 되었습니다.");
                    } catch (CustomApiException e) {
                        response = ApiResponse.create(false, null, e.getMessage());
                    } catch (Exception e) {
                        response = ApiResponse.create(false, null, "카테고리 추가 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    System.out.println(response.toJson());
                }
                case 2 -> {
                    // 카테고리 ID로 검색
                    printAllCategories();
                    System.out.print("검색할 카테고리 ID: ");
                    String searchCategoryIdInput = scanner.nextLine();
                    try {
                        Long searchCategoryId = Long.parseLong(searchCategoryIdInput);
                        Category category = categoryService.searchCategoryId(searchCategoryId);
                        SearchCategoryDTO searchCategoryDTO = SearchCategoryDTO.from(category);
                        response = ApiResponse.create(true, searchCategoryDTO, "검색된 카테고리입니다.");
                    } catch (NumberFormatException e) {
                        response = ApiResponse.create(false, null, "잘못된 ID 형식입니다. 숫자를 입력하세요.");
                    } catch (CustomApiException e) {
                        response = ApiResponse.create(false, null, e.getMessage());
                    } catch (Exception e) {
                        response = ApiResponse.create(false, null, "카테고리 검색 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    System.out.println(response.toJson());
                }
                case 3 -> {
                    // 카테고리명으로 검색
                    printAllCategories();
                    System.out.print("검색할 카테고리명: ");
                    String searchCategoryName = scanner.nextLine();
                    try {
                        Category category = categoryService.searchCategoryName(searchCategoryName);
                        SearchCategoryDTO searchCategoryDTO = SearchCategoryDTO.from(category);
                        response = ApiResponse.create(true, searchCategoryDTO, "검색된 카테고리입니다.");
                    } catch (CustomApiException e) {
                        response = ApiResponse.create(false, null, e.getMessage());
                    } catch (Exception e) {
                        response = ApiResponse.create(false, null, "카테고리 검색 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    System.out.println(response.toJson());
                }
                case 4 -> {
                    // 종료
                    System.out.println("-> 카테고리 컨트롤러를 종료합니다.");
                    return;
                }
                default -> System.out.println("-> 잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private static void displayCategoryMenu() {
        System.out.println("--------------카테고리 관리-----------");
        System.out.println("1. 카테고리 추가");
        System.out.println("2. 카테고리 ID로 검색");
        System.out.println("3. 카테고리명으로 검색");
        System.out.println("4. 카테고리 컨트롤러 종료");
        System.out.print("선택: ");
    }

    private void printAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        System.out.println("---------------현재 등록된 카테고리 목록----------------");
        System.out.printf("%-15s %-20s %-20s%n", "ID", "이름", "부모 카테고리");
        System.out.println("-------------------------------------------------");

        for (Category allCategory : allCategories) {
            String parentCategoryName = "없음";
            if (allCategory.getParentId() != null) {
                Category parentCategory = categoryService.searchCategoryId(allCategory.getParentId());
                if (parentCategory != null) {
                    parentCategoryName = parentCategory.getCategoryName();
                }
            }
            System.out.printf("%-15d %-20s %-20s%n", allCategory.getCategoryId(), allCategory.getCategoryName(), parentCategoryName);
        }

        System.out.println("-------------------------------------------------");
    }
}
