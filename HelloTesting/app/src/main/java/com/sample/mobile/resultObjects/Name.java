package com.sample.mobile.resultObjects;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Name implements Parcelable {

    @SerializedName("title")
    public String title;
    @SerializedName("first")
    public String firstName;
    @SerializedName("last")
    public String lastName;

    public Name() {}

    private Name(Parcel in) {
        title = in.readString();
        firstName = in.readString();
        lastName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel parcel) {
            return new Name(parcel);
        }

        @Override
        public Name[] newArray(int i) {
            return new Name[i];
        }
    };
}
