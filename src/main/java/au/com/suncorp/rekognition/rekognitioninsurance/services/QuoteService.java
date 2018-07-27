package au.com.suncorp.rekognition.rekognitioninsurance.services;

import au.com.suncorp.rekognition.rekognitioninsurance.model.QuoteDetails;
import au.com.suncorp.rekognition.rekognitioninsurance.rest.RekognitionResponse;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class QuoteService {
    public List<QuoteDetails> getQuoteDetails(RekognitionResponse faceDetails) {
        return Arrays.asList(getLifeQuoteDetails(faceDetails),
                                getHealthQuoteDetails(faceDetails),
                                getTravelQuoteDetails(faceDetails));
    }

    private QuoteDetails getLifeQuoteDetails(RekognitionResponse faceDetails) {
        double basePremium = 154.1237943;

        basePremium = basePremium * faceDetails.getEstimatedAge();
        if (faceDetails.getGender().getValue().equals("Female")) {
            basePremium = basePremium * 0.80;
        }

        DecimalFormat df = new DecimalFormat("####0.00");
        return new QuoteDetails("life", new Double(df.format(basePremium)));
    }

    private QuoteDetails getHealthQuoteDetails(RekognitionResponse faceDetails) {
        double basePremium = 53.1237943;

        basePremium = basePremium * faceDetails.getEstimatedAge();
        if (faceDetails.getGender().getValue().equals("Female")) {
            basePremium = basePremium * 0.90;
        }

        DecimalFormat df = new DecimalFormat("####0.00");
        return new QuoteDetails("health", new Double(df.format(basePremium)));
    }

    private QuoteDetails getTravelQuoteDetails(RekognitionResponse faceDetails) {
        double basePremium = 10.1237943;

        basePremium = basePremium * faceDetails.getEstimatedAge();

        DecimalFormat df = new DecimalFormat("####0.00");
        return new QuoteDetails("travel", new Double(df.format(basePremium)));
    }
}
