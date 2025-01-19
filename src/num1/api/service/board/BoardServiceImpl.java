package num1.api.service.board;

import num1.api.CustomApiException;
import num1.entity.board.Board;
import num1.entity.board.BoardRepository;
import num1.entity.category.Category;
import num1.entity.category.CategoryRepository;

public class BoardServiceImpl implements BoardService{
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    public BoardServiceImpl(CategoryRepository categoryRepository, BoardRepository boardRepository) {
        this.categoryRepository = categoryRepository;
        this.boardRepository = boardRepository;
    }


    @Override
    public Board addBoard(Long categoryId, String title, String content) {
        if (categoryId == null || categoryId <= 0) {
            throw new CustomApiException("유효한 카테고리 ID를 입력하세요.");
        }

        Board newBoard = new Board(categoryId, title, content);

        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new CustomApiException("존재하지 않는 카테고리 ID입니다.");
        }

        Board saveBoard;
        try {
            saveBoard = boardRepository.save(newBoard);
            category.addBoardId(saveBoard.getBoardId());
        } catch (Exception e) {
            throw new CustomApiException("게시글 저장 중 오류가 발생했습니다: " + e.getMessage());
        }

        return saveBoard;
    }


    @Override
    public Board searchBoard(Long boardId) {
        if (boardId == null || boardId <= 0) {
            throw new CustomApiException("유효한 게시글 ID를 입력하세요.");
        }

        Board board = boardRepository.findById(boardId);
        if (board == null) {
            throw new CustomApiException("존재하지 않는 게시글 ID입니다.");
        }

        return board;
    }
}
