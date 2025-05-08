package com.example.babmeognyangserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST(400, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(404, HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
