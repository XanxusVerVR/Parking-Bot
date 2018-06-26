package javabean.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Query {

    @SerializedName("coordinate")
    @Expose
    private Coordinate coordinate;
    @SerializedName("landmark")
    @Expose
    private String landmark;

    public Query() {
    }

    public Query(String landmark) {
        super();
        this.landmark = landmark;
    }

    public Query(Coordinate coordinate, String landmark) {
        super();
        this.coordinate = coordinate;
        this.landmark = landmark;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("landmark", landmark).append("coordinate", coordinate).toString();
    }

}
