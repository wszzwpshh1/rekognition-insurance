package au.com.suncorp.rekognition.rekognitioninsurance.services;

import au.com.suncorp.rekognition.rekognitioninsurance.model.QuoteDetails;
import au.com.suncorp.rekognition.rekognitioninsurance.rest.RekognitionResponse;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.Gender;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class QuoteServiceTest {
    @Test
    public void shouldGetPremium() {
        QuoteService quoteService = new QuoteService();
        RekognitionResponse faceDetails = new RekognitionResponse();
        faceDetails.setAgeRange(new AgeRange().withLow(20).withHigh(37));
        Gender gender = new Gender();
        gender.setValue("Female");
        faceDetails.setGender(gender);
        List<QuoteDetails> quoteDetails = quoteService.getQuoteDetails(faceDetails);
        System.out.println(quoteDetails);
    }
}