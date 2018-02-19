package com.sample.mobile.resultObjects;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchResult {

    @SerializedName("results")
    public List<Contact> results;

}
