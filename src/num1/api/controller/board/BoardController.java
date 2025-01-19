package num1.api.controller.board;

import num1.api.ApiResponse;
import num1.api.CustomApiException;
import num1.api.dto.SaveBoardDTO;
import num1.api.dto.SearchBoardDTO;
import num1.api.service.board.BoardService;
import num1.api.service.category.CategoryService;
import num1.entity.board.Board;
import num1.entity.category.Category;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    private final CategoryService categoryService;
    private final BoardService boardService;

    public BoardController(CategoryService categoryService, BoardService boardService) {
        this.categoryService = categoryService;
        this.boardService = boardService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            displayBoardMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();
            ApiResponse response;
            switch (choice) {
                case 1 -> {
                    // 게시판 추가
                    printAllCategories();
                    try {
                        System.out.print("카테고리 ID: ");
                        Long categoryId = scanner.nextLong();
                        scanner.nextLine();

                        System.out.print("게시글 제목: ");
                        String title = scanner.nextLine();
                        System.out.println("게시글 내용: ");
                        String content = scanner.nextLine();

                        // 게시글 추가
                        Board saveBoard = boardService.addBoard(categoryId, title, content);
                        SaveBoardDTO saveBoardDTO = SaveBoardDTO.from(saveBoard);
                        response = ApiResponse.create(true, saveBoardDTO, "게시글이 성공적으로 추가되었습니다.");
                    } catch (InputMismatchException e) {
                        response = ApiResponse.create(false, null, "숫자만 입력할 수 있습니다.");
                        scanner.nextLine();
                    } catch (CustomApiException e) {
                        response = ApiResponse.create(false, null, e.getMessage());
                    } catch (Exception e) {
                        response = ApiResponse.create(false, null, "게시글 추가 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    System.out.println(response.toJson());
                }
                case 2 -> {
                    // 게시판 검색
                    try {
                        System.out.print("검색할 게시판 ID: ");
                        Long searchBoardId = scanner.nextLong();
                        scanner.nextLine();

                        // 게시글 검색
                        Board board = boardService.searchBoard(searchBoardId);
                        SearchBoardDTO searchBoardDTO = SearchBoardDTO.from(board);
                        response = ApiResponse.create(true, searchBoardDTO, "검색 성공");

                    } catch (InputMismatchException e) {
                        response = ApiResponse.create(false, null, "숫자만 입력할 수 있습니다.");
                        scanner.nextLine();
                    } catch (CustomApiException e) {
                        response = ApiResponse.create(false, null, e.getMessage());
                    } catch (Exception e) {
                        response = ApiResponse.create(false, null, "게시판 검색 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    System.out.println(response.toJson());
                }
                case 3 -> {
                    // 종료
                    System.out.println("게시판 컨트롤러를 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private static void displayBoardMenu() {
        System.out.println("--------------게시판 관리-----------");
        System.out.println("1. 게시판 추가");
        System.out.println("2. 게시판 검색");
        System.out.println("3. 게시판 컨트롤러 종료");
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
