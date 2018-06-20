package javabean.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import javabean.sub.Result;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PullServiceResponse {

    @SerializedName("Result")
    @Expose
    private List< Result> result = null;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private String status;

    public PullServiceResponse() {
    }

    /**
     *
     * @param message
     * @param result
     * @param status
     */
    public PullServiceResponse(List< Result> result, String message, String status) {
        super();
        this.result = result;
        this.message = message;
        this.status = status;
    }

    @JsonProperty("Result")
    public List< Result> getResult() {
        return result;
    }

    public void setResult(List< Result> result) {
        this.result = result;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("result", result).append("message", message).append("status", status).toString();
    }

}
