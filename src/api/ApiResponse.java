package api;

public class ApiResponse {

    private boolean success; // 성공 여부
    private Object data; // 데이터
    private String message; // 메시지

    public ApiResponse() {
    }

    public static ApiResponse create(boolean success, Object data, String message) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(success);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    // JSON 형태로 변환하는 메서드
    public String toJson() {
        String dataString = (data != null) ? data.toString() : "null";
        return String.format(
                "{\"success\": %b, \"message\": \"%s\", \"data\": %s}",
                success,
                message,
                dataString
        );
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
