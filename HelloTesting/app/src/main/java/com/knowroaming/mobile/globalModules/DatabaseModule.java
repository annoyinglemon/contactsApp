package com.knowroaming.mobile.globalModules;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.knowroaming.mobile.respository.database.ContactDao;
import com.knowroaming.mobile.respository.database.ContactsAppDatabase;

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
