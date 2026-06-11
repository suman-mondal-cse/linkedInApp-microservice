package com.suman.linkedin.posts_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException exception){
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException exception){
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Handle Authentication Exception
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.FORBIDDEN)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

    //    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .message(exception.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

//    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
//        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
//    }

}
