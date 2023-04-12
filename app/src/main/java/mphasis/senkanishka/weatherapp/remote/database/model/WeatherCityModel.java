package mphasis.senkanishka.weatherapp.remote.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "region_table")
public class WeatherCityModel {

    //below line is to auto increment id for each city.
    @PrimaryKey(autoGenerate = true)
    //variable for our id.
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "sys")
    private String sys;

    @ColumnInfo(name = "humidity")
    private int humidity;

    @ColumnInfo(name = "windSpeed")
    private double windSpeed;

    @ColumnInfo(name = "tempMax")
    private Float tempMax;

    @ColumnInfo(name = "tempMin")
    private Float tempMin;

    @ColumnInfo(name = "temp")
    private Float temp;

    @ColumnInfo(name = "sunriseTime")
    private Long sunriseTime;

    @ColumnInfo(name = "sunsetTime")
    private Long sunsetTime;

    @ColumnInfo(name = "pressure")
    private Float pressure;

    @ColumnInfo(name = "imageURL")
    private String imageURL;

    @ColumnInfo(name="modified_date")
    private String modified_date;

    public WeatherCityModel(String name, String sys, int humidity, double windSpeed, Float tempMax, Float tempMin, Float temp,
                            Long sunriseTime, Long sunsetTime, Float pressure, String imageURL, String modified_date) {
        this.name = name;
        this.sys = sys;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.temp = temp;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.pressure = pressure;
        this.imageURL = imageURL;
        this.modified_date = modified_date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Float getTempMax() {
        return tempMax;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    public Float getTempMin() {
        return tempMin;
    }

    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public Long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }
}
