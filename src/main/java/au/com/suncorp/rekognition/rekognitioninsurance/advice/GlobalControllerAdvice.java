package au.com.suncorp.rekognition.rekognitioninsurance.advice;

import au.com.suncorp.rekognition.rekognitioninsurance.exceptions.LowConfidenceException;
import au.com.suncorp.rekognition.rekognitioninsurance.exceptions.NoMatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhezhao on 2018/7/27.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({LowConfidenceException.class, NoMatchException.class})
    @ResponseBody
    public ResponseEntity<String> handleLogConfidenceException(HttpServletRequest request, Exception exception) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>("{\"error\": \"" + exception.getMessage() + "\"}", headers, HttpStatus.OK);
    }

}
