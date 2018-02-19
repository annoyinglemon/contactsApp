package com.sample.mobile.resultObjects;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Login implements Parcelable {

    @SerializedName("username")
    public String username;

    public Login() {}

    private Login(Parcel in) {
        username = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel parcel) {
            return new Login(parcel);
        }

        @Override
        public Login[] newArray(int i) {
            return new Login[i];
        }
    };

}
