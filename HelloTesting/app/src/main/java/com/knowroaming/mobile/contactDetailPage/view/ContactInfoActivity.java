package com.knowroaming.mobile.contactDetailPage.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.knowroaming.mobile.ContactViewModelFactory;
import com.knowroaming.mobile.ContactsApplication;
import com.knowroaming.mobile.GlideApp;
import com.knowroaming.mobile.contactDetailPage.viewmodel.ContactInfoActivityViewModel;
import com.knowroaming.mobile.hellotesting.R;
import com.knowroaming.mobile.hellotesting.databinding.ActivityContactDetailBinding;
import com.knowroaming.mobile.resultObjects.Contact;

import javax.inject.Inject;

public class ContactInfoActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";

    @Inject
    ContactViewModelFactory contactViewModelFactory;

    private ActivityContactDetailBinding binding;

    public static Intent createContactDetailsIntent(Context context) {
        return new Intent(context, ContactInfoActivity.class);
    }

    public static Intent createContactDetailsIntent(Context context, String email) {
        final Intent intent = new Intent(context, ContactInfoActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ContactsApplication) getApplication()).createContactInfoComponent().inject(this);
        ContactInfoActivityViewModel contactActivityViewModel = ViewModelProviders.of(this, contactViewModelFactory).get(ContactInfoActivityViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_detail);

        setSupportActionBar(binding.toolbarContactDetails);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String email = getIntent().getStringExtra(EXTRA_EMAIL);

        if (email != null) {
            contactActivityViewModel.fetchContactDetails(email);

            if (contactActivityViewModel.getContactViewModel().contact != null) {
                loadImage(contactActivityViewModel.getContactViewModel().contact);

            } else {
                Toast.makeText(this, "Unable to load contact details", Toast.LENGTH_SHORT).show();
            }

        } else {
            getSupportActionBar().setTitle("Add Contact");
        }

        binding.setActivityViewModel(contactActivityViewModel);
        binding.setViewModel(contactActivityViewModel.getContactViewModel());
    }

    private void loadImage(Contact contact) {
        if (contact.picture != null && contact.picture.largeUrl != null) {
            GlideApp.with(this)
                    .load(contact.picture.largeUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.imageviewContactDetail);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        ((ContactsApplication) getApplication()).discardContactInfoComponent();
        super.onDestroy();
    }
}
