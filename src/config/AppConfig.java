package config;

import api.controller.board.BoardController;
import api.controller.category.CategoryController;
import api.service.board.BoardService;
import api.service.board.BoardServiceImpl;
import api.service.category.CategoryService;
import api.service.category.CategoryServiceImpl;
import entity.board.BoardRepository;
import entity.category.CategoryRepository;

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
        return new CategoryServiceImpl(categoryRepository(), boardRepository());
    }

    public CategoryRepository categoryRepository() {
        return new CategoryRepository();
    }

    public BoardRepository boardRepository() {
        return new BoardRepository();
    }
}
