package com.sample.mobile;


import com.sample.mobile.contactDetailPage.injection.ContactInfoComponent;
import com.sample.mobile.contactListPage.injection.ContactListComponent;
import com.sample.mobile.contactSearchPage.injection.ContactSearchComponent;
import com.sample.mobile.globalModules.AppContextModule;
import com.sample.mobile.globalModules.DatabaseModule;
import com.sample.mobile.globalModules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppContextModule.class,
        DatabaseModule.class,
        NetworkModule.class
        })
public interface ContactsAppComponent {

    ContactListComponent getContactListComponent();

    ContactInfoComponent getContactInfoComponent();

    ContactSearchComponent getContactSearchComponent();

}
