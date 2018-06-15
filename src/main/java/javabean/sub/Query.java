package javabean.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Query {

    @SerializedName("coordinate")
    @Expose
    private Coordinate coordinate;

    public Query() {
    }

    public Query(Coordinate coordinate) {
        super();
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("coordinate", coordinate).toString();
    }

}
