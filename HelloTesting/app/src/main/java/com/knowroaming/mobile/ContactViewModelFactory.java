package com.knowroaming.mobile;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.knowroaming.mobile.contactDetailPage.viewmodel.ContactInfoActivityViewModel;
import com.knowroaming.mobile.contactListPage.viewmodel.ContactListActivityViewModel;
import com.knowroaming.mobile.contactSearchPage.viewmodel.ContactSearchActivityViewModel;
import com.knowroaming.mobile.respository.ContactsRepository;

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
