package au.com.suncorp.rekognition.rekognitioninsurance.rest;

import au.com.suncorp.rekognition.rekognitioninsurance.services.DetectFaceService;
import au.com.suncorp.rekognition.rekognitioninsurance.services.QuoteService;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Map;

/**
 * Created by zhezhao on 2018/7/26.
 */

@RestController
@RequestMapping("/rekognition")
public class RekognitionReourse {
    private DetectFaceService detectFaceService;
    private QuoteService quoteService;

    @Autowired
    public RekognitionReourse(DetectFaceService detectFaceService, QuoteService quoteService) {
        this.detectFaceService = detectFaceService;
        this.quoteService = quoteService;
    }

    @GetMapping("/sample")
    public ResponseEntity<RekognitionResponse> getSampleDetectResponse() {
        String photo="photo1.jpg";

        ByteBuffer imageBytes = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(photo);
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        RekognitionResponse rekognitionResponse = detectFaceService.detect(imageBytes);
        ResponseEntity<RekognitionResponse> response = new ResponseEntity<RekognitionResponse>(rekognitionResponse, HttpStatus.OK);

        return response;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/detect")
    public ResponseEntity<RekognitionResponse> getDetectResponse(@RequestBody RekognitionRequest request) {
        String webCamImage = request.getImage();
        byte[] imageByteArray = Base64.getDecoder().decode(webCamImage);

        RekognitionResponse rekognitionResponse = detectFaceService.detect(ByteBuffer.wrap(imageByteArray));
        rekognitionResponse.setQuoteDetailsList(quoteService.getQuoteDetails(rekognitionResponse));
        return new ResponseEntity<RekognitionResponse>(rekognitionResponse, HttpStatus.OK);
    }
}
