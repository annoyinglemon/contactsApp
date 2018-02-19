package com.sample.mobile.contactSearchPage.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sample.mobile.respository.ContactsRepository;
import com.sample.mobile.resultObjects.Contact;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ContactSearchActivityViewModel extends ViewModel {

    private final ContactsRepository contactsRepository;
    private MutableLiveData<List<Contact>> contactsLiveData;
    private final SearchQueryViewModel searchQueryViewModel;
    private final Disposable disposable;

    public ContactSearchActivityViewModel(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
        contactsLiveData = new MutableLiveData<>();
        this.searchQueryViewModel = new SearchQueryViewModel();

        this.disposable = searchQueryViewModel.getSearchQueryObservable().subscribe(this::searchContact);
    }

    public SearchQueryViewModel getSearchQueryViewModel() {
        return searchQueryViewModel;
    }

    public LiveData<List<Contact>> getContactsLiveData() {
        return this.contactsLiveData;
    }

    private void searchContact(String searchQuery) {
        if (searchQuery != null && !searchQuery.isEmpty() && searchQuery.length() >= 2) {
            String newSearchQuery = "%".concat(searchQuery).concat("%");
            contactsLiveData.setValue(this.contactsRepository.searchContact(newSearchQuery));
        }
    }

    @Override
    protected void onCleared() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
