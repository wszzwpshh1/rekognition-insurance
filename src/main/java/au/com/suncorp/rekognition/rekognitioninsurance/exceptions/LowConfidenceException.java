package au.com.suncorp.rekognition.rekognitioninsurance.exceptions;

/**
 * Created by zhezhao on 2018/7/27.
 */
public class LowConfidenceException extends RuntimeException {

    public LowConfidenceException() {
        super("Confidence lower than 50%");
    }
}
