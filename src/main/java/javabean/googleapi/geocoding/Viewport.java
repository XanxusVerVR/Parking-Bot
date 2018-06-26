
package javabean.googleapi.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Viewport {

    @SerializedName("northeast")
    @Expose
    private Northeast_ northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest_ southwest;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Viewport() {
    }

    /**
     * 
     * @param southwest
     * @param northeast
     */
    public Viewport(Northeast_ northeast, Southwest_ southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast_ getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast_ northeast) {
        this.northeast = northeast;
    }

    public Southwest_ getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest_ southwest) {
        this.southwest = southwest;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("northeast", northeast).append("southwest", southwest).toString();
    }

}
