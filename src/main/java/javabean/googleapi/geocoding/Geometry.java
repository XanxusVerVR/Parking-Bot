
package javabean.googleapi.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Geometry {

    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Geometry() {
    }

    /**
     * 
     * @param bounds
     * @param viewport
     * @param location
     * @param locationType
     */
    public Geometry(Bounds bounds, Location location, String locationType, Viewport viewport) {
        super();
        this.bounds = bounds;
        this.location = location;
        this.locationType = locationType;
        this.viewport = viewport;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bounds", bounds).append("location", location).append("locationType", locationType).append("viewport", viewport).toString();
    }

}
