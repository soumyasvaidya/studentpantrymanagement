package com.student.pantry.studentPantry.response;

public class OrderHistoryResponse {
    private String message;
    private Object data;

    public OrderHistoryResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
