import api.controller.board.BoardController;
import api.controller.category.CategoryController;
import api.service.board.BoardService;
import api.service.category.CategoryService;
import config.AppConfig;
import entity.category.Category;

import java.util.Scanner;

public class Main {

    static AppConfig appConfig = new AppConfig();

    public static void main(String[] args) {

        initData();

        // BoardController 시작
        Scanner scanner = new Scanner(System.in);
        CategoryController categoryController = appConfig.categoryController();
        BoardController boardController = appConfig.boardController();

        while (true) {

            displayMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 ->
                    // 카테고리 관리
                        categoryController.start();
                case 2 ->
                    // 게시판 관리
                        boardController.start();
                case 3 -> {
                    // 종료
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("--------------Dispatcher Servlet-----------");
        System.out.println("1. 카테고리 관리");
        System.out.println("2. 게시판 관리");
        System.out.println("3. 프로그램 종료");
        System.out.print("선택: ");
    }

    private static void initData() {
        CategoryService categoryService = appConfig.categoryService();
        BoardService boardService = appConfig.boardService();

        // 카테고리 추가
        Category MAN = categoryService.addCategory("남자", null);
        Category EXO = categoryService.addCategory("엑소", MAN.getCategoryId());
        Category EXOAnnouncement = categoryService.addCategory("공지사항", EXO.getCategoryId());
        Category CHEN = categoryService.addCategory("첸", EXO.getCategoryId());
        Category BAEKHYUN = categoryService.addCategory("백현", EXO.getCategoryId());
        Category XIUMIN = categoryService.addCategory("시우민", EXO.getCategoryId());
        Category BTS = categoryService.addCategory("BTS", MAN.getCategoryId());
        Category BTSEXOAnnouncement = categoryService.addCategory("공지사항", BTS.getCategoryId());
        Category BTSAnonymousBoardCategory = categoryService.addCategory("익명게시판", BTS.getCategoryId());
        Category V = categoryService.addCategory("뷔", BTS.getCategoryId());
        Category WOMAN = categoryService.addCategory("여자", null);
        Category BLACKPINK = categoryService.addCategory("블랙핑크", WOMAN.getCategoryId());
        Category BLACKPINKAnnouncement = categoryService.addCategory("공지사항", BLACKPINK.getCategoryId());
        Category BLACKPINKAnonymousBoardCategory = categoryService.addCategory("익명게시판", BLACKPINK.getCategoryId());
        Category ROSE = categoryService.addCategory("로제", BLACKPINK.getCategoryId());


        // 게시판 추가
        boardService.addBoard(EXOAnnouncement.getCategoryId(), "엑소 공지사항1", "엑소 공지사항1 content"); // 엑소의 공지사항
        boardService.addBoard(CHEN.getCategoryId(),"첸 게시판1", "첸 게시판1 content"); // 엑소의 첸
        boardService.addBoard(BAEKHYUN.getCategoryId(),"백현 게시판1", "백현 게시판1 content"); // 엑소의 백현
        boardService.addBoard(XIUMIN.getCategoryId(),"시우민 게시판1", "시우민 게시판1 content"); // 엑소의 시우민
        boardService.addBoard(BTSEXOAnnouncement.getCategoryId(),"BTS 공지사항1", "BTS 공지사항1 content"); // 엑소의 시우민
        boardService.addBoard(BTSAnonymousBoardCategory.getCategoryId(),"BTS 익명게시판1", "BTS 익명게시판1 content"); // 엑소의 시우민
        boardService.addBoard(V.getCategoryId(),"뷔 게시판1", "뷔 게시판1 content"); // 엑소의 시우민
        boardService.addBoard(BLACKPINKAnnouncement.getCategoryId(),"BLACKPINK 공지사항1", "BLACKPINK 공지사항1 content"); // 엑소의 시우민
        boardService.addBoard(BLACKPINKAnonymousBoardCategory.getCategoryId(),"BLACKPINK 익명게시판1", "BLACKPINK 익명게시판1 content"); // 엑소의 시우민
        boardService.addBoard(ROSE.getCategoryId(),"로제 게시판1", "로제 게시판1 content"); // 엑소의 시우민
    }
}