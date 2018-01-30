package com.knowroaming.mobile.contactSearchPage.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.knowroaming.mobile.ContactViewModelFactory;
import com.knowroaming.mobile.ContactsApplication;
import com.knowroaming.mobile.GlideApp;
import com.knowroaming.mobile.contactListPage.view.ContactsAdapter;
import com.knowroaming.mobile.contactSearchPage.viewmodel.ContactSearchActivityViewModel;
import com.knowroaming.mobile.hellotesting.R;
import com.knowroaming.mobile.hellotesting.databinding.ActivityContactSearchBinding;
import com.knowroaming.mobile.resultObjects.Contact;

import java.util.List;

import javax.inject.Inject;

public class ContactSearchActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    public ContactViewModelFactory contactViewModelFactory;

    private ActivityContactSearchBinding binding;

    private ContactsAdapter contactsAdapter;

    public static Intent createContactSearchIntent(Context context) {
        return new Intent(context, ContactSearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ContactsApplication) getApplication()).createContactSearchComponent().inject(this);
        ContactSearchActivityViewModel contactSearchActivityViewModel = ViewModelProviders.of(this, contactViewModelFactory).get(ContactSearchActivityViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_search);
        binding.setViewModel(contactSearchActivityViewModel);
        binding.buttonContactSearchClear.setOnClickListener(this);

        setSupportActionBar(binding.toolbarContactSearch);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }

        contactsAdapter = new ContactsAdapter(GlideApp.with(this));

        binding.recyclerviewContactSearchActivity.setAdapter(contactsAdapter);
        binding.recyclerviewContactSearchActivity.setLayoutManager(new LinearLayoutManager(this));

        contactSearchActivityViewModel.getContactsLiveData().observe(this, createContactSearchChange());

    }

    private Observer<List<Contact>> createContactSearchChange() {
        return contacts -> {
            if (contacts != null) {
                contactsAdapter.setContactList(contacts);
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_contactSearch_clear) {
            binding.edittextContactSearch.setText("");
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        ((ContactsApplication) getApplication()).discardContactSearchComponent();
        super.onDestroy();
    }
}
