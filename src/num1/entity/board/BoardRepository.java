package num1.entity.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardRepository {

    private static final Map<Long, Board> store = new HashMap<>();
    private static Long sequenceId = 0L;

    public Board save(Board board) {
        board.setBoardId(++sequenceId);
        store.put(board.getBoardId(), board);
        return board;
    }

    public Board findById(Long boardId) {
        return store.get(boardId);
    }

    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }
}
