package javabean.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("surplusCar")
    @Expose
    private int surplusCar;
    @SerializedName("distance")
    @Expose
    private int distance;

    public Result() {
    }

    /**
     *
     * @param surplusCar
     * @param address
     * @param name
     * @param zone
     * @param distance
     */
    public Result(String name, String zone, String address, int surplusCar, int distance) {
        super();
        this.name = name;
        this.zone = zone;
        this.address = address;
        this.surplusCar = surplusCar;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSurplusCar() {
        return surplusCar;
    }

    public void setSurplusCar(int surplusCar) {
        this.surplusCar = surplusCar;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("zone", zone).append("address", address).append("car", surplusCar).append("distance", distance).toString();
    }
}
