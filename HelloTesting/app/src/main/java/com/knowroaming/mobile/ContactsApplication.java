package com.knowroaming.mobile;


import android.app.Application;

import com.knowroaming.mobile.contactDetailPage.injection.ContactInfoComponent;
import com.knowroaming.mobile.contactListPage.injection.ContactListComponent;
import com.knowroaming.mobile.contactSearchPage.injection.ContactSearchComponent;
import com.knowroaming.mobile.globalModules.AppContextModule;

public class ContactsApplication extends Application {

    private ContactsAppComponent appComponent;
    private ContactListComponent contactListComponent;
    private ContactInfoComponent contactInfoComponent;
    private ContactSearchComponent contactSearchComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerContactsAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
    }

    public ContactListComponent createContactListComponent() {
        if (contactListComponent == null) {
            contactListComponent = appComponent.getContactListComponent();
        }
        return contactListComponent;
    }

    public ContactInfoComponent createContactInfoComponent() {
        if (contactInfoComponent == null) {
            contactInfoComponent = appComponent.getContactInfoComponent();
        }
        return contactInfoComponent;
    }

    public ContactSearchComponent createContactSearchComponent() {
        if (contactSearchComponent == null) {
            contactSearchComponent = appComponent.getContactSearchComponent();
        }
        return contactSearchComponent;
    }

    public void discardContactInfoComponent() {
        contactInfoComponent = null;
    }

    public void discardContactSearchComponent() {
        contactSearchComponent = null;
    }
}
