package num1.config;

import num1.api.controller.board.BoardController;
import num1.api.controller.category.CategoryController;
import num1.api.service.board.BoardService;
import num1.api.service.board.BoardServiceImpl;
import num1.api.service.category.CategoryService;
import num1.api.service.category.CategoryServiceImpl;
import num1.entity.board.BoardRepository;
import num1.entity.category.CategoryRepository;

public class AppConfig {

    public BoardController boardController() {
        return new BoardController(categoryService(), boardService());
    }

    public CategoryController categoryController() {
        return new CategoryController(categoryService());
    }

    public BoardService boardService() {
        return new BoardServiceImpl(categoryRepository(), boardRepository());
    }

    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository());
    }

    public CategoryRepository categoryRepository() {
        return new CategoryRepository();
    }

    public BoardRepository boardRepository() {
        return new BoardRepository();
    }
}
