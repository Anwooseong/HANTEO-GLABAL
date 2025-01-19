package api.dto;

import entity.board.Board;

public record SaveBoardDTO(Long id, String title, String content) {
    public static SaveBoardDTO from(Board board) {
        return new SaveBoardDTO(board.getBoardId(), board.getTitle(), board.getContent());
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %d, \"title\": \"%s\", \"content\": \"%s\"}",
                id, title, content);
    }
}
