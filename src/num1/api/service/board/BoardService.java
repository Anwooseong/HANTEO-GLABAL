package num1.api.service.board;

import num1.entity.board.Board;

public interface BoardService {

    Board addBoard(Long categoryId, String title, String content);
    Board searchBoard(Long boardId);

}
