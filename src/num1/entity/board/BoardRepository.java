package num1.entity.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardRepository {

    private static final Map<Long, Board> store = new HashMap<>();
    private static Long sequenceId = 0L;

    // 게시판 저장
    public Board save(Board board) {
        board.setBoardId(++sequenceId);
        store.put(board.getBoardId(), board);
        return board;
    }

    // ID로 게시판 검색
    public Board findById(Long boardId) {
        return store.get(boardId);
    }

    // 카테고리 ID로 게시판 검색
    public List<Board> findByCategoryId(Long categoryId) {
        List<Board> result = new ArrayList<>();
        for (Board board : store.values()) {
            if (board.getCategoryId().equals(categoryId)) {
                result.add(board);
            }
        }
        return result;
    }

    // 모든 게시판 목록 반환
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }
}
