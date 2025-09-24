package it.back.back_app.board.dto;

import it.back.back_app.common.utils.TimeFormatUtils;
import lombok.Data;

/**
 * API가 항상 동일한 요소를 가지게 해줌
 */
@Data
public class ApiResponse<T> {

    private String data;
    private int status;
    private T response;

    public ApiResponse(int status, T response) {
        this.status = status;
        this.response = response;
        this.data = TimeFormatUtils.getDateTime();
    }

    public static <T> ApiResponse<T> ok(T response) {
        return new ApiResponse<>(200, response);
    }

}
