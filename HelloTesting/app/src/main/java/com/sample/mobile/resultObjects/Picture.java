package com.sample.mobile.resultObjects;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Picture implements Parcelable {

    @SerializedName("large")
    public String largeUrl;
    @SerializedName("medium")
    public String mediumUrl;
    @SerializedName("thumbnail")
    public String thumbnailUrl;

    public Picture() {}

    private Picture(Parcel in) {
        largeUrl = in.readString();
        mediumUrl = in.readString();
        thumbnailUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(largeUrl);
        parcel.writeString(mediumUrl);
        parcel.writeString(thumbnailUrl);
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel parcel) {
            return new Picture(parcel);
        }

        @Override
        public Picture[] newArray(int i) {
            return new Picture[i];
        }
    };

}
