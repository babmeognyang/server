package com.example.babmeognyangserver.global.common;

import com.example.babmeognyangserver.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorDetail error;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .error(null)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .data(null)
                .error(new ErrorDetail(errorCode.getCode(), errorCode.getMessage()))
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorDetail {
        private final int code;
        private final String message;
    }
}
