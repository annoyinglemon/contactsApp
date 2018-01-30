package com.knowroaming.mobile.contactListPage.viewmodel;


import android.content.Context;
import android.view.View;

import com.knowroaming.mobile.ContactUtil;
import com.knowroaming.mobile.contactDetailPage.view.ContactInfoActivity;
import com.knowroaming.mobile.resultObjects.Contact;

public class ContactListItemViewModel {

    private final Contact contact;

    public ContactListItemViewModel(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return ContactUtil.formatName(contact.name);
    }

    public Contact getContact() {
        return contact;
    }

    public void onContactItemClicked(View view) {
        Context context = view.getContext();
        context.startActivity(ContactInfoActivity.createContactDetailsIntent(context, contact.email));
    }

    public int getImageVisibility() {
        if (contact.picture != null && contact.picture.largeUrl != null){
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }

    public int getInitialsVisibility() {
        if (contact.picture != null && contact.picture.largeUrl != null){
            return View.GONE;
        } else {
            return View.VISIBLE;
        }
    }

    public String getNameInitials() {
        if (contact.name.firstName == null || contact.name.lastName == null ||
                contact.name.lastName.isEmpty() || contact.name.firstName.isEmpty()) {
            return "";
        }
        return contact.name.firstName.substring(0, 1).toUpperCase().concat(contact.name.lastName.substring(0, 1).toUpperCase());
    }
}
