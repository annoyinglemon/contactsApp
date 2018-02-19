package com.sample.mobile.resultObjects;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable {

    @SerializedName("street")
    public String street;
    @SerializedName("city")
    public String city;
    @SerializedName("state")
    public String state;
    @SerializedName("postcode")
    public long postalCode;

    public Location() {}

    private Location(Parcel in) {
        street = in.readString();
        city = in.readString();
        state = in.readString();
        postalCode = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(street);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeLong(postalCode);
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel parcel) {
            return new Location(parcel);
        }

        @Override
        public Location[] newArray(int i) {
            return new Location[i];
        }
    };

}
