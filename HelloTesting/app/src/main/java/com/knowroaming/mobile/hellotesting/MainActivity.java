package com.knowroaming.mobile.hellotesting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.knowroaming.mobile.esimprov.EsimprovActivity;
import com.knowroaming.mobile.esimprov.LpaActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private Toolbar toolbar;
    private String mImeiOne = null;
    private String mImeiTwo = null;
    private String mImsiOne;
    private String mImsiTwo;
    private String mIccid;
    private TextView mTitleText;
    private TextView vImeiOneText;
    private TextView vImeiTwoText;
    private TextView vImsiOneText;
    private TextView vImsiTwoText;
    private TextView vIccidTextView;

    FloatingActionButton mFabRefresh;
    private static final int PERMISSION_READ_STATE = 999;
    private String mOperatorISO3;
    TelephonyManager mTel;
    SubscriptionManager mSub= null;
    LottieAnimationView vAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        vAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
        vImeiOneText = (TextView) findViewById(R.id.imei_one);
        vImeiTwoText = (TextView) findViewById(R.id.imei_two);
        vImsiOneText = (TextView) findViewById(R.id.imsi_one);
        vImsiTwoText = (TextView) findViewById(R.id.imsi_two);
        vIccidTextView = (TextView) findViewById(R.id.iccid_text);
        mFabRefresh = (FloatingActionButton) findViewById(R.id.fab_refresh);


        mTitleText = (TextView) findViewById(R.id.title_text);

        setSupportActionBar(toolbar);

        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
        vIccidTextView.setVisibility(View.GONE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // We do not have this permission. Let's ask the user
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
        }

        mTel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT > 21) {
            mSub = SubscriptionManager.from(mContext);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        vAnimationView.setAnimation("PinJump.json");
        vAnimationView.loop(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lpa:
                final Intent lpaIntent = new Intent(this, LpaActivity.class);
                this.startActivity(lpaIntent);
                return true;
            case R.id.action_esimprov:
                final Intent settingsIntent = new Intent(this, EsimprovActivity.class);
                this.startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_0:
                initSectionZero();
                break;
            case R.id.nav_1:
                initSectionOne();
                break;
            case R.id.nav_2:
                initSectionTwo();
                break;
            case R.id.nav_3:
                initSectionThree();
                break;
            case R.id.nav_4:
                initSectionFour();
                break;
            case R.id.nav_5:
                initSectionFive();
                break;
            case R.id.nav_6:
                initSectionSix();
                break;
            case R.id.nav_7:
                initSectionSeven();
                break;
            default:
                initSectionZero();
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initSectionZero() {
        vImeiOneText.setVisibility(View.VISIBLE);
        vImeiTwoText.setVisibility(View.VISIBLE);
        vImsiOneText.setVisibility(View.VISIBLE);
        vImsiTwoText.setVisibility(View.VISIBLE);
        vIccidTextView.setVisibility(View.VISIBLE);

        mTitleText.setText(getString(R.string.title_section_0));
        mFabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Retrieve", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                try {
                    mImeiOne = mTel.getDeviceId(0);
                    mImeiTwo = mTel.getDeviceId(1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT > 21 && mSub != null) {
                    try {
                        List<SubscriptionInfo> subscriptionList = mSub.getActiveSubscriptionInfoList();
                        // getting first SubscriptionInfo
                        SubscriptionInfo si = subscriptionList.get(0);

                        mIccid = si.getIccId();
                        if (subscriptionList != null && subscriptionList.size() > 0) {
                            for (SubscriptionInfo s : subscriptionList) {
                                int subIndex = s.getSimSlotIndex();
                                if (subIndex == 0) {
                                    final int mccOne = s.getMcc();
                                    final int mncOne = s.getMnc();
                                    mImsiOne = mccOne + "...";

                                } else if (subIndex == 1) {
                                    final int mccTwo = s.getMcc();
                                    final int mncTwo = s.getMnc();
                                    mImsiTwo = mccTwo + "...";
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Log.e(TAG, ex.toString());
                    }

                }

                Snackbar.make(view, " Retrieved IMSI as " + mTel.getSubscriberId(), Snackbar.LENGTH_LONG)
                        .setAction("Info", null).show();

                vImeiOneText.setText("IMEI 1 : " + mImeiOne);
                vImeiTwoText.setText("IMEI 2 : " + mImeiTwo);
                vImsiOneText.setText("IMSI 1 :" + mImsiOne);
                vImsiTwoText.setText("IMSI 2 : " + mImsiTwo);
                vIccidTextView.setText("ICCID : " + mIccid);
            }
        });
        vAnimationView.setAnimation("PinJump.json");
        vAnimationView.loop(true);
    }

    private void initSectionOne() {
        mTitleText.setText(getString(R.string.title_section_1));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
        // vAnimationView.setAnimation("EmptyState.json");
        vAnimationView.loop(true);
    }

    private void initSectionTwo() {
        mTitleText.setText(getString(R.string.title_section_2));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
        // vAnimationView.setAnimation("TwitterHeart.json");
        vAnimationView.loop(true);
    }

    private void initSectionThree() {
        mTitleText.setText(getString(R.string.title_section_3));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
    }
    private void initSectionFour() {
        mTitleText.setText(getString(R.string.title_section_4));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
    }
    private void initSectionFive() {
        mTitleText.setText(getString(R.string.title_section_5));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
    }

    private void initSectionSix() {
        mTitleText.setText(getString(R.string.title_section_6));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
    }

    private void initSectionSeven() {
        mTitleText.setText(getString(R.string.title_section_7));
        vImeiOneText.setVisibility(View.GONE);
        vImeiTwoText.setVisibility(View.GONE);
        vImsiOneText.setVisibility(View.GONE);
        vImsiTwoText.setVisibility(View.GONE);
    }
}
