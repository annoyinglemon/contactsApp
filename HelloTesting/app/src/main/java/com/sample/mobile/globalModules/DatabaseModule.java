package com.sample.mobile.globalModules;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.sample.mobile.respository.database.ContactDao;
import com.sample.mobile.respository.database.ContactsAppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    ContactsAppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, ContactsAppDatabase.class, "contacts-storage").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    ContactDao provideContactDao(ContactsAppDatabase contactsAppDatabase) {
        return contactsAppDatabase.provideContactDao();
    }

}
