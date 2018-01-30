package com.knowroaming.mobile.contactDetailPage.viewmodel;


import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.view.View;
import android.widget.Toast;

import com.knowroaming.mobile.respository.ContactsRepository;
import com.knowroaming.mobile.resultObjects.Contact;

public class ContactInfoActivityViewModel extends ViewModel {

    private final ContactsRepository contactsRepository;
    private ContactViewModel contactViewModel;

    public ContactInfoActivityViewModel(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public void fetchContactDetails(String email) {
        Contact contact = contactsRepository.getContactDetails(email);
        contactViewModel = new ContactViewModel(contact);
    }

    public ContactViewModel getContactViewModel() {
        if (contactViewModel == null) {
            contactViewModel = new ContactViewModel();
        }
        return contactViewModel;
    }

    public void onContactEditClick(View view) {
        int result = contactsRepository.updateContact(contactViewModel.contact);
        if (result == 1) {
            Toast.makeText(view.getContext(), "Contact successfully updated!", Toast.LENGTH_SHORT).show();
            ((Activity) view.getContext()).finish();
        } else {
            Toast.makeText(view.getContext(), "Contact update failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void onContactSaveClick(View view) {
        long result = contactsRepository.insertContact(contactViewModel.contact);
        if (result == -1) {
            Toast.makeText(view.getContext(), "Failed to save contact", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "Contact saved", Toast.LENGTH_SHORT).show();
            ((Activity) view.getContext()).finish();
        }
    }

    public void onContactDisabledButtonClick(View view) {
        Toast.makeText(view.getContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show();
    }


}
