package api.dto;

import entity.board.Board;

public record SearchBoardDTO(Long id, String title, String content) {

    public static SearchBoardDTO from(Board board) {
        return new SearchBoardDTO(
                board.getBoardId(),
                board.getTitle(),
                board.getContent()
        );
    }

    @Override
    public String toString() {
        return String.format("{\"id\": %d, \"title\": \"%s\", \"content\": \"%s\"}",
                id, title, content);
    }
}
