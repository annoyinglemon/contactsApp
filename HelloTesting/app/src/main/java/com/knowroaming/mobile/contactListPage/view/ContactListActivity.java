package com.knowroaming.mobile.contactListPage.view;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.knowroaming.mobile.ContactsApplication;
import com.knowroaming.mobile.GlideApp;
import com.knowroaming.mobile.contactListPage.viewmodel.ContactListActivityViewModel;
import com.knowroaming.mobile.ContactViewModelFactory;
import com.knowroaming.mobile.contactSearchPage.view.ContactSearchActivity;
import com.knowroaming.mobile.hellotesting.R;
import com.knowroaming.mobile.hellotesting.databinding.ActivityContactListBinding;
import com.knowroaming.mobile.respository.State;
import com.knowroaming.mobile.resultObjects.Contact;

import java.util.List;

import javax.inject.Inject;

public class ContactListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ContactViewModelFactory contactViewModelFactory;

    private ContactListActivityViewModel contactListActivityViewModel;

    private ActivityContactListBinding binding;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ContactsApplication) getApplication()).createContactListComponent().inject(this);
        contactListActivityViewModel = ViewModelProviders.of(this, contactViewModelFactory).get(ContactListActivityViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_list);
        setSupportActionBar(binding.toolbarContactList);
        binding.setViewModel(contactListActivityViewModel);

        contactsAdapter = new ContactsAdapter(GlideApp.with(this));

        binding.swiperefreshContactsActivity.setOnRefreshListener(this);
        binding.recyclerviewContactsActivity.setAdapter(contactsAdapter);
        binding.recyclerviewContactsActivity.setLayoutManager(new LinearLayoutManager(this));

        contactListActivityViewModel.getDataState().observe(this, createStateChangeObserver());
        contactListActivityViewModel.getContactsLiveData().observe(this, createContactListChange());

        contactListActivityViewModel.refreshContacts(false);

    }

    private Observer<State> createStateChangeObserver() {
        return state -> {
            if (state != null) {
                switch (state) {
                    case LOADING:
                        binding.linearLayoutContactListSyncing.setVisibility(View.VISIBLE);
                        binding.linearLayoutContactListSyncFailed.setVisibility(View.GONE);
                        break;
                    case SUCCESS:
                        binding.linearLayoutContactListSyncing.setVisibility(View.GONE);
                        binding.linearLayoutContactListSyncFailed.setVisibility(View.GONE);
                        binding.swiperefreshContactsActivity.setRefreshing(false);
                        break;
                    default:
                    case FAILED:
                        binding.linearLayoutContactListSyncing.setVisibility(View.GONE);
                        binding.linearLayoutContactListSyncFailed.setVisibility(View.VISIBLE);
                        binding.swiperefreshContactsActivity.setRefreshing(false);
                        break;
                }
            }
        };
    }

    private Observer<List<Contact>> createContactListChange() {
        return contacts -> {
            if (contacts != null) {
                if (contacts.isEmpty()) {
                    binding.recyclerviewContactsActivity.setVisibility(View.GONE);
                    binding.linearLayoutContactListEmpty.setVisibility(View.VISIBLE);
                } else {
                    contactsAdapter.setContactList(contacts);
                    binding.recyclerviewContactsActivity.setVisibility(View.VISIBLE);
                    binding.linearLayoutContactListEmpty.setVisibility(View.GONE);
                }
            }
        };
    }

    @Override
    public void onRefresh() {
        contactListActivityViewModel.refreshContacts(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search:
                Bundle options = ActivityOptions.makeSceneTransitionAnimation(this, binding.toolbarContactList, binding.toolbarContactList.getTransitionName()).toBundle();
                startActivity(ContactSearchActivity.createContactSearchIntent(this), options);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
