package com.sample.mobile;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.sample.mobile.contactDetailPage.viewmodel.ContactInfoActivityViewModel;
import com.sample.mobile.contactListPage.viewmodel.ContactListActivityViewModel;
import com.sample.mobile.contactSearchPage.viewmodel.ContactSearchActivityViewModel;
import com.sample.mobile.respository.ContactsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactViewModelFactory implements ViewModelProvider.Factory {

    private final ContactsRepository contactsRepository;

    @Inject
    ContactViewModelFactory(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ContactListActivityViewModel.class)) {
            return (T) new ContactListActivityViewModel(contactsRepository);

        } else if (modelClass.isAssignableFrom(ContactInfoActivityViewModel.class)) {
            return (T) new ContactInfoActivityViewModel(contactsRepository);

        } else if (modelClass.isAssignableFrom(ContactSearchActivityViewModel.class)) {
            return (T) new ContactSearchActivityViewModel(contactsRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
