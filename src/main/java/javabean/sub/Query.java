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
    @SerializedName("parkName")
    @Expose
    private String parkName;

    public Query() {
    }

    public Query(String landmark, String parkName) {
        super();
        this.landmark = landmark;
        this.parkName = parkName;
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

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("landmark", landmark).append("coordinate", coordinate).append("parkName", parkName).toString();
    }

}
