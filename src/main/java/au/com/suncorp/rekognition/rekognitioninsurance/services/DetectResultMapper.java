package au.com.suncorp.rekognition.rekognitioninsurance.services;

import au.com.suncorp.rekognition.rekognitioninsurance.exceptions.LowConfidenceException;
import au.com.suncorp.rekognition.rekognitioninsurance.exceptions.NoMatchException;
import au.com.suncorp.rekognition.rekognitioninsurance.rest.RekognitionResponse;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhezhao on 2018/7/27.
 */
@Component
public class DetectResultMapper {

    public RekognitionResponse mapToRekognitionResponse(DetectFacesResult detectFacesResult) {
        RekognitionResponse response = new RekognitionResponse();
        List<FaceDetail> faceDetails = detectFacesResult.getFaceDetails();
        if(faceDetails.size() == 0 )
            throw new NoMatchException();

        FaceDetail firstMatch = faceDetails.get(0);
        if(firstMatch.getConfidence() <50 )
            throw new LowConfidenceException();

        response.setAgeRange(firstMatch.getAgeRange());
        response.setGender(firstMatch.getGender());
        response.setConfidence(firstMatch.getConfidence());

        return response;
    }
}
