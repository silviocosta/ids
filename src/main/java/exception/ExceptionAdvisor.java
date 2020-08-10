package exception;

import com.challenge.ids.controller.StockController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(StockController.class);

    // Place holder for proper and standardized error message
    private static final String ERROR = "Internal Server, please contact support.";

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {
        LOG.warn("Request is missing parameters {}", ex.getLocalizedMessage(), ex);
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        LOG.error("Unhandled error happened {}", ex.getLocalizedMessage(), ex);
        return super.handleExceptionInternal(ex, ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
