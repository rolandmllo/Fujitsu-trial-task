package app.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrors handleValidationError(
            MethodArgumentNotValidException exception) {

        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        System.out.println(errors);

        app.validation.ValidationErrors result = new app.validation.ValidationErrors();

        for (FieldError error : errors) {
            result.addFieldError(error);
        }

        return result;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrors handleInputError(
            IllegalArgumentException exception) {

        //List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        System.out.println(exception);

        app.validation.ValidationErrors result = new app.validation.ValidationErrors();
        result.addErrorMessage(exception.getMessage());

//        for (FieldError error : errors) {
//            result.addFieldError(error);
//        }

        return result;
    }
}