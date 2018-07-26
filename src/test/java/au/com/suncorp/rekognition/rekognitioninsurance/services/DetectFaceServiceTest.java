package au.com.suncorp.rekognition.rekognitioninsurance.services;

import com.amazonaws.util.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * Created by zhezhao on 2018/7/27.
 */
public class DetectFaceServiceTest {

    @Test
    public void shouldGetFaceDetails() throws Exception {
        DetectFaceService detectFaceService = new DetectFaceService(new DetectResultMapper());

        String photo="photo1.jpg";

        ByteBuffer imageBytes = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(photo);
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        detectFaceService.detect(imageBytes);
    }
}