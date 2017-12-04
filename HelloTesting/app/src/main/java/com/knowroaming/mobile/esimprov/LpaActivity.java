package com.knowroaming.mobile.esimprov;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.knowroaming.mobile.hellotesting.R;

/**
 * Created by Das on 9/1/17.
 */

public class LpaActivity extends Activity {
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_lpa);
    }
}
