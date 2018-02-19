package com.sample.mobile.contactListPage.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.sample.mobile.contactDetailPage.view.ContactInfoActivity;
import com.sample.mobile.respository.ContactsRepository;
import com.sample.mobile.respository.State;
import com.sample.mobile.resultObjects.Contact;

import java.util.List;


public class ContactListActivityViewModel extends ViewModel {

    private final ContactsRepository contactsRepository;
    private MutableLiveData<State> stateLiveData;

    public ContactListActivityViewModel(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
        stateLiveData = new MutableLiveData<>();
    }

    public LiveData<State> getDataState() {
        return stateLiveData;
    }

    public LiveData<List<Contact>> getContactsLiveData() {
        return this.contactsRepository.getContactsLiveData();
    }

    public void refreshContacts(boolean forceRefresh) {
        this.contactsRepository.refreshContacts(forceRefresh, stateLiveData);
    }

    public void onAddContactButtonClicked(View view) {
        view.getContext().startActivity(ContactInfoActivity.createContactDetailsIntent(view.getContext()));
    }
}
