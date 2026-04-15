package Restful.exception;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handle(RuntimeException ex) {
        Map<String, String> res = new HashMap<>();
        res.put("error", ex.getMessage());
        return res;
    }
}