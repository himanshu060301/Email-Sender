package com.mail.mailSender.DTO;

import org.springframework.http.HttpStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse {
    private String msg;
    private HttpStatus httpStatus;
    private boolean success=false;

    // Private constructor
    private CustomResponse(Builder builder) {
        this.msg = builder.msg;
        this.httpStatus = builder.httpStatus;
        this.success = builder.success;
    }

    // Getters
    public String getMsg() {
        return msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    // Builder class
    public static class Builder {
        private String msg;
        private HttpStatus httpStatus;
        private boolean success;

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public CustomResponse build() {
            return new CustomResponse(this);
        }
    }

    // Static method to start the builder
    public static Builder builder() {
        return new Builder();
    }
}
