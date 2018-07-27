package au.com.suncorp.rekognition.rekognitioninsurance.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by zhezhao on 2018/7/26.
 */
@XmlRootElement
public class RekognitionRequest {
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
