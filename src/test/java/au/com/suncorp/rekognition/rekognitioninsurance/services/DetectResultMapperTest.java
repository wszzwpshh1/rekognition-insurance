package au.com.suncorp.rekognition.rekognitioninsurance.services;

import au.com.suncorp.rekognition.rekognitioninsurance.rest.RekognitionResponse;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by zhezhao on 2018/7/27.
 */
public class DetectResultMapperTest {
    public static final int AGE_HIGH = 30;
    public static final int AGE_LOW = 25;
    private DetectResultMapper detectResultMapper;

    @Before
    public void setUp() {
        detectResultMapper = new DetectResultMapper();
    }

    @Test
    public void mapToRekognitionResponse() throws Exception {
        RekognitionResponse response = detectResultMapper.mapToRekognitionResponse(buildDetectFaseResult());

        assertThat(response.getEstimatedAge(), is(Math.round((AGE_HIGH + AGE_LOW) / 2)));
    }

    private DetectFacesResult buildDetectFaseResult() {
        DetectFacesResult result = new DetectFacesResult();

        FaceDetail faceDetail = new FaceDetail();
        AgeRange ageRange = new AgeRange();
        ageRange.setHigh(AGE_HIGH);
        ageRange.setLow(AGE_LOW);
        faceDetail.setAgeRange(ageRange);
        faceDetail.setConfidence(95f);
        result.setFaceDetails(Arrays.asList(faceDetail));

        return result;
    }

}