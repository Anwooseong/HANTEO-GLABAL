package api.service.board;

import entity.board.Board;

public interface BoardService {

    Board addBoard(Long categoryId, String title, String content);
    Board searchBoard(Long boardId);

}
