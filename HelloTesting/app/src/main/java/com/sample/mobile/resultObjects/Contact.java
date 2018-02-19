package com.sample.mobile.resultObjects;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "contacts")
public class Contact implements Parcelable {

    @SerializedName("email")
    @PrimaryKey
    @NonNull
    public String email;

    @SerializedName("name")
    @Embedded
    public Name name;

    @SerializedName("location")
    @Embedded
    public Location location;

    @SerializedName("login")
    @Embedded
    public Login login;

    @SerializedName("dob")
    public String birthDate;

    @SerializedName("phone")
    public String phoneNumber;

    @SerializedName("cell")
    public String cellNumber;

    @SerializedName("picture")
    @Embedded
    public Picture picture;

    public Contact() {
        name = new Name();
        location = new Location();
        login = new Login();
    }

    private Contact(Parcel in) {
        name = in.readParcelable(Name.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
        email = in.readString();
        login = in.readParcelable(Login.class.getClassLoader());
        birthDate = in.readString();
        phoneNumber = in.readString();
        cellNumber = in.readString();
        picture = in.readParcelable(Picture.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(name, i);
        parcel.writeParcelable(location, i);
        parcel.writeString(email);
        parcel.writeParcelable(login, i);
        parcel.writeString(birthDate);
        parcel.writeString(phoneNumber);
        parcel.writeString(cellNumber);
        parcel.writeParcelable(picture, i);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        @Override
        public Contact[] newArray(int i) {
            return new Contact[i];
        }
    };
}
