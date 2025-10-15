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
    private T error;

    public ApiResponse(int status, T response, T error) {
        this.status = status;
        this.response = response;
        this.data = TimeFormatUtils.getDateTime();
        this.error = error;
    }

    public static <T> ApiResponse<T> ok(T response) {
        return new ApiResponse<>(200, response, null);
    }

    public static <T> ApiResponse<T> error(int status, T error) {
        return new ApiResponse<>(status, null, error);
    }

}
