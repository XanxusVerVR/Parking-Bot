package javabean.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TainanParkingRemainder implements Comparable<TainanParkingRemainder> {

    @SerializedName("typeId")
    @Expose
    private String typeId;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("zoneId")
    @Expose
    private String zoneId;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("largeCar")
    @Expose
    private int largeCar;
    @SerializedName("car")
    @Expose
    private int car;
    @SerializedName("carDis")
    @Expose
    private int carDis;
    @SerializedName("carWoman")
    @Expose
    private int carWoman;
    @SerializedName("carGreen")
    @Expose
    private int carGreen;
    @SerializedName("moto")
    @Expose
    private int moto;
    @SerializedName("motoDis")
    @Expose
    private int motoDis;
    @SerializedName("largeCar_total")
    @Expose
    private int largeCarTotal;
    @SerializedName("car_total")
    @Expose
    private int carTotal;
    @SerializedName("carDis_total")
    @Expose
    private int carDisTotal;
    @SerializedName("carWoman_total")
    @Expose
    private int carWomanTotal;
    @SerializedName("carGreen_total")
    @Expose
    private int carGreenTotal;
    @SerializedName("moto_total")
    @Expose
    private int motoTotal;
    @SerializedName("motoDis_total")
    @Expose
    private int motoDisTotal;
    @SerializedName("chargeTime")
    @Expose
    private String chargeTime;
    @SerializedName("chargeFee")
    @Expose
    private String chargeFee;
    @SerializedName("update_time")
    @Expose
    private String updateTime;
    @SerializedName("lnglat")
    @Expose
    private String lnglat;
    @SerializedName("distance")
    @Expose
    private String distance;

    /**
     * No args constructor for use in serialization
     *
     */
    public TainanParkingRemainder() {
    }

    /**
     *
     * @param car
     * @param carWoman
     * @param motoDis
     * @param carDisTotal
     * @param motoTotal
     * @param id
     * @param carWomanTotal
     * @param carDis
     * @param carTotal
     * @param moto
     * @param name
     * @param chargeFee
     * @param chargeTime
     * @param updateTime
     * @param code
     * @param zoneId
     * @param typeName
     * @param largeCarTotal
     * @param carGreenTotal
     * @param address
     * @param carGreen
     * @param largeCar
     * @param lnglat
     * @param motoDisTotal
     * @param typeId
     * @param zone
     */
    public TainanParkingRemainder(String typeId, String typeName, String id, String code, String name, String zoneId, String zone, String address, int largeCar, int car, int carDis, int carWoman, int carGreen, int moto, int motoDis, int largeCarTotal, int carTotal, int carDisTotal, int carWomanTotal, int carGreenTotal, int motoTotal, int motoDisTotal, String chargeTime, String chargeFee, String updateTime, String lnglat, String distance) {
        super();
        this.typeId = typeId;
        this.typeName = typeName;
        this.id = id;
        this.code = code;
        this.name = name;
        this.zoneId = zoneId;
        this.zone = zone;
        this.address = address;
        this.largeCar = largeCar;
        this.car = car;
        this.carDis = carDis;
        this.carWoman = carWoman;
        this.carGreen = carGreen;
        this.moto = moto;
        this.motoDis = motoDis;
        this.largeCarTotal = largeCarTotal;
        this.carTotal = carTotal;
        this.carDisTotal = carDisTotal;
        this.carWomanTotal = carWomanTotal;
        this.carGreenTotal = carGreenTotal;
        this.motoTotal = motoTotal;
        this.motoDisTotal = motoDisTotal;
        this.chargeTime = chargeTime;
        this.chargeFee = chargeFee;
        this.updateTime = updateTime;
        this.lnglat = lnglat;
        this.distance = distance;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
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

    public int getLargeCar() {
        return largeCar;
    }

    public void setLargeCar(int largeCar) {
        this.largeCar = largeCar;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getCarDis() {
        return carDis;
    }

    public void setCarDis(int carDis) {
        this.carDis = carDis;
    }

    public int getCarWoman() {
        return carWoman;
    }

    public void setCarWoman(int carWoman) {
        this.carWoman = carWoman;
    }

    public int getCarGreen() {
        return carGreen;
    }

    public void setCarGreen(int carGreen) {
        this.carGreen = carGreen;
    }

    public int getMoto() {
        return moto;
    }

    public void setMoto(int moto) {
        this.moto = moto;
    }

    public int getMotoDis() {
        return motoDis;
    }

    public void setMotoDis(int motoDis) {
        this.motoDis = motoDis;
    }

    public int getLargeCarTotal() {
        return largeCarTotal;
    }

    public void setLargeCarTotal(int largeCarTotal) {
        this.largeCarTotal = largeCarTotal;
    }

    public int getCarTotal() {
        return carTotal;
    }

    public void setCarTotal(int carTotal) {
        this.carTotal = carTotal;
    }

    public int getCarDisTotal() {
        return carDisTotal;
    }

    public void setCarDisTotal(int carDisTotal) {
        this.carDisTotal = carDisTotal;
    }

    public int getCarWomanTotal() {
        return carWomanTotal;
    }

    public void setCarWomanTotal(int carWomanTotal) {
        this.carWomanTotal = carWomanTotal;
    }

    public int getCarGreenTotal() {
        return carGreenTotal;
    }

    public void setCarGreenTotal(int carGreenTotal) {
        this.carGreenTotal = carGreenTotal;
    }

    public int getMotoTotal() {
        return motoTotal;
    }

    public void setMotoTotal(int motoTotal) {
        this.motoTotal = motoTotal;
    }

    public int getMotoDisTotal() {
        return motoDisTotal;
    }

    public void setMotoDisTotal(int motoDisTotal) {
        this.motoDisTotal = motoDisTotal;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(String chargeFee) {
        this.chargeFee = chargeFee;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(TainanParkingRemainder t) {
        return this.distance.compareTo(t.distance);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("typeId", typeId).append("typeName", typeName).append("id", id).append("code", code).append("name", name).append("zoneId", zoneId).append("zone", zone).append("address", address).append("largeCar", largeCar).append("car", car).append("carDis", carDis).append("carWoman", carWoman).append("carGreen", carGreen).append("moto", moto).append("motoDis", motoDis).append("largeCarTotal", largeCarTotal).append("carTotal", carTotal).append("carDisTotal", carDisTotal).append("carWomanTotal", carWomanTotal).append("carGreenTotal", carGreenTotal).append("motoTotal", motoTotal).append("motoDisTotal", motoDisTotal).append("chargeTime", chargeTime).append("chargeFee", chargeFee).append("updateTime", updateTime).append("lnglat", lnglat).append("distance", distance).toString();
    }

}
