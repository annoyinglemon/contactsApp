package com.knowroaming.mobile.respository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.knowroaming.mobile.ContactUtil;
import com.knowroaming.mobile.respository.database.ContactDao;
import com.knowroaming.mobile.resultObjects.Contact;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ContactsRepository {

    private final ContactService contactService;
    private final ContactDao contactDao;
    private LiveData<List<Contact>> contactsLiveData;

    @Inject
    ContactsRepository(ContactService contactService, ContactDao contactDao) {
        this.contactService = contactService;
        this.contactDao = contactDao;
    }

    public LiveData<List<Contact>> getContactsLiveData() {
        if (contactsLiveData == null) {
            contactsLiveData = contactDao.getAllContacts();
        }
        return contactsLiveData;
    }

    public void refreshContacts(boolean forceRefresh, MutableLiveData<State> stateLiveData) {
        if ((contactsLiveData.getValue() == null || contactsLiveData.getValue().isEmpty() || forceRefresh)) {
            fetchContactsFromServer(forceRefresh, stateLiveData);
        }
    }

    private void fetchContactsFromServer(boolean forceRefresh, MutableLiveData<State> stateLiveData) {
        contactService.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(fetchResult -> {
                    for (Contact contact : fetchResult.results) {
                        contact.name.title = ContactUtil.capitalizeWord(contact.name.title);
                        contact.name.firstName = ContactUtil.capitalizeWord(contact.name.firstName);
                        contact.name.lastName = ContactUtil.capitalizeWord(contact.name.lastName);
                        contact.location.street = ContactUtil.capitalizeWords(contact.location.street);
                        contact.location.city = ContactUtil.capitalizeWords(contact.location.city);
                        contact.location.state = ContactUtil.capitalizeWords(contact.location.state);
                        contact.birthDate = ContactUtil.formatBirthDate(contact.birthDate);
                    }
                    return fetchResult.results;
                })
                .subscribe(new SingleObserver<List<Contact>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!forceRefresh) {
                            stateLiveData.setValue(State.LOADING);
                        }
                    }

                    @Override
                    public void onSuccess(List<Contact> contacts) {
                        contactDao.insertContacts(contacts);
                        stateLiveData.setValue(State.SUCCESS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        stateLiveData.setValue(State.FAILED);
                    }
                });
    }

    public Contact getContactDetails(String email) {
        return contactDao.getContactDetails(email);
    }

    public int updateContact(Contact contact) {
        return contactDao.updateContact(contact);
    }

    public long insertContact(Contact contact) {
        return contactDao.insertContact(contact);
    }

    public List<Contact> searchContact(String searchQuery) {
        return contactDao.searchContact(searchQuery);
    }

}
