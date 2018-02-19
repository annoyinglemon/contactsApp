package com.sample.mobile;


import android.app.Application;

import com.sample.mobile.contactDetailPage.injection.ContactInfoComponent;
import com.sample.mobile.contactListPage.injection.ContactListComponent;
import com.sample.mobile.contactSearchPage.injection.ContactSearchComponent;
import com.sample.mobile.globalModules.AppContextModule;

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
