package au.com.suncorp.rekognition.rekognitioninsurance.services;

import au.com.suncorp.rekognition.rekognitioninsurance.rest.RekognitionResponse;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by zhezhao on 2018/7/26.
 */
@Service
public class DetectFaceService {
    private AmazonRekognition client;
    private DetectResultMapper detectResultMapper;

    @Autowired
    public DetectFaceService(DetectResultMapper detectResultMapper) {
        this.detectResultMapper = detectResultMapper;

        setupRekognitionClient();
    }

    public RekognitionResponse detect(ByteBuffer imageBytes) {
        DetectFacesResult detectFacesResult = displayFaceDetail(imageBytes);

        return detectResultMapper.mapToRekognitionResponse(detectFacesResult);
    }

    private void setupRekognitionClient() {
        AWSCredentials credentials;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location (/Usersuserid.aws/credentials), and is in a valid format.", e);
        }

        client = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private DetectFacesResult displayFaceDetail(ByteBuffer imageBytes) {
        DetectFacesRequest dfRequest = new DetectFacesRequest()
                .withImage(new Image()
                        .withBytes(imageBytes))
                .withAttributes(Attribute.ALL);

        DetectFacesResult result = null;
        try {
            result = client.detectFaces(dfRequest);
            List<FaceDetail> faceDetails = result.getFaceDetails();

            for (FaceDetail facedetail: faceDetails) {
                System.out.println("Face Detail: " + facedetail.toString());
            }

        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }

        return result;
    }
}
