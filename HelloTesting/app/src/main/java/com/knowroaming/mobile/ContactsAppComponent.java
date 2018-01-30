package com.knowroaming.mobile;


import com.knowroaming.mobile.contactDetailPage.injection.ContactInfoComponent;
import com.knowroaming.mobile.contactListPage.injection.ContactListComponent;
import com.knowroaming.mobile.contactSearchPage.injection.ContactSearchComponent;
import com.knowroaming.mobile.globalModules.AppContextModule;
import com.knowroaming.mobile.globalModules.DatabaseModule;
import com.knowroaming.mobile.globalModules.NetworkModule;

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
