package com.edw.operatingdbremoteservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


/**
 * **************************************************************************************************
 * Project Name:    OperatingDBClient
 * <p>
 * Date:            2021-05-24
 * <p>
 * Author：         EdwardWMD
 * <p>
 * Github:          https://github.com/Edwardwmd
 * <p>
 * Blog:            https://edwardwmd.github.io/
 * <p>
 * Description：    城市
 * <p>
 * **************************************************************************************************
 */

public class City implements Parcelable {
    private static final String TAG = "City";
    private int id;
    private String cityName;
    private String cityPinyin;
    private String cityCode;
    private String cityProvince;

    public City() {

    }

    public City(int id, String cityName, String cityPinyin, String cityCode, String cityProvince) {
        this.id = id;
        this.cityName = cityName;
        this.cityPinyin = cityPinyin;
        this.cityCode = cityCode;
        this.cityProvince = cityProvince;
    }

    public City(String cityName, String cityPinyin, String cityCode, String cityProvince) {
        this.cityName = cityName;
        this.cityPinyin = cityPinyin;
        this.cityCode = cityCode;
        this.cityProvince = cityProvince;
    }

    protected City(Parcel in) {
        id = in.readInt();
        cityName = in.readString();
        cityPinyin = in.readString();
        cityCode = in.readString();
        cityProvince = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            Log.e(TAG,"拆解数据");
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityProvince() {
        return cityProvince;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityProvince(String cityProvince) {
        this.cityProvince = cityProvince;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.e(TAG,"打包数据");
        dest.writeInt(id);
        dest.writeString(cityName);
        dest.writeString(cityPinyin);
        dest.writeString(cityCode);
        dest.writeString(cityProvince);
    }
}
