package com.knowroaming.mobile.contactDetailPage.viewmodel;


import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public class ContactBindingAdapter {

    @BindingAdapter("errorMessage")
    public static void setErrorMessage(TextInputLayout textInputLayout, CharSequence errorMessage) {
        textInputLayout.setError(errorMessage);
    }

}
