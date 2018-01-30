package com.knowroaming.mobile.respository;


import com.knowroaming.mobile.resultObjects.FetchResult;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface ContactService {

    @GET("api/?seed=knowroaming&results=15&nat=ca&inc=name,location,email,login,dob,phone,cell,picture&noinfo")
    Single<FetchResult> getContacts();

}
