package publisher.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import publisher.dto.OrderResponse;
import publisher.dto.Status;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static publisher.dto.Status.FAILED;

@RestControllerAdvice
@Slf4j
public class OrderControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<OrderResponse> exceptionHandler(Exception exception) {

        log.error("Ошибка при обработке запроса: {}", exception.getMessage());

        var response = new OrderResponse()
                .setStatus(FAILED)
                .setErrorDescription(exception.getMessage());

        log.info("Ответ сервиса: {}", response);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }
}