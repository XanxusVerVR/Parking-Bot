package javabean.googleapi.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GeocodingAPIResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public GeocodingAPIResponse() {
    }

    /**
     *
     * @param results
     * @param status
     */
    public GeocodingAPIResponse(List<Result> results, String status) {
        super();
        this.results = results;
        this.status = status;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("results", results).append("status", status).toString();
    }

}
