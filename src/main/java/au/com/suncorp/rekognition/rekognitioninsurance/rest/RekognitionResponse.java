package au.com.suncorp.rekognition.rekognitioninsurance.rest;

import au.com.suncorp.rekognition.rekognitioninsurance.model.QuoteDetails;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Gender;

import java.util.List;

/**
 * Created by zhezhao on 2018/7/26.
 */
public class RekognitionResponse {
    AgeRange ageRange;
    Gender gender;
    Float confidence;
    List<QuoteDetails> quoteDetailsList;

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

    public List<QuoteDetails> getQuoteDetailsList() {
        return quoteDetailsList;
    }

    public void setQuoteDetailsList(List<QuoteDetails> quoteDetailsList) {
        this.quoteDetailsList = quoteDetailsList;
    }
}
