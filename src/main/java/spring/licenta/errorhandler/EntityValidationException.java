package spring.licenta.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Entity validation failed.")
public class EntityValidationException extends RuntimeException {
    List<String> validationErrors;
    private static final long serialVersionUID = 1L;

    public EntityValidationException(String msg, List<String> errors) {
        super(msg);
        this.validationErrors = errors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
