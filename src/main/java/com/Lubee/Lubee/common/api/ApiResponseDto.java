package com.Lubee.Lubee.common.api;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponseDto<T>  {

    private boolean success;
    private T response;
    private ErrorResponse success_or_error_code;


    @Builder
    ApiResponseDto(boolean success, T response, ErrorResponse error, String jwt)
    {
        this.success = success;
        this.response = response;
        this.success_or_error_code = error;
    }
}