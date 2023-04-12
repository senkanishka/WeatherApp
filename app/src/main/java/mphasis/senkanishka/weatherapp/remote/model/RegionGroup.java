package mphasis.senkanishka.weatherapp.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RegionGroup {

    @Expose
    @SerializedName("cnt")
    private Integer cnt;

    @Expose
    @SerializedName("list")
    private ArrayList<Region1> list;

    public Integer getCnt() {
        return cnt;
    }

    public ArrayList<Region1> getList() {
        return list;
    }
}
