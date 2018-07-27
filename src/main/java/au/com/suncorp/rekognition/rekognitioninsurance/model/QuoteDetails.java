package au.com.suncorp.rekognition.rekognitioninsurance.model;

public class QuoteDetails {
    private String quoteType;
    private Double premium;

    public QuoteDetails(String quoteType, Double premium) {
        this.quoteType = quoteType;
        this.premium = premium;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    @Override
    public String toString() {
        return "QuoteDetails{" +
                "quoteType='" + quoteType + '\'' +
                ", premium=" + premium +
                '}';
    }
}
