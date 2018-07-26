package au.com.suncorp.rekognition.rekognitioninsurance.rest;

import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Gender;

/**
 * Created by zhezhao on 2018/7/26.
 */
public class RekognitionResponse {
    AgeRange ageRange;
    Gender gender;
    Float confidence;

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public int getEstimatedAge() {
        return Math.round((ageRange.getHigh() + ageRange.getLow()) / 2);
    }

}
