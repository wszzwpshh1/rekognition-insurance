package au.com.suncorp.rekognition.rekognitioninsurance.exceptions;

/**
 * Created by zhezhao on 2018/7/27.
 */
public class NoMatchException extends RuntimeException {

    public NoMatchException() {
        super("No Match");
    }
}
