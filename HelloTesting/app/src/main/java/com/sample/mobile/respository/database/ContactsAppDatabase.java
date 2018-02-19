package com.sample.mobile.respository.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sample.mobile.resultObjects.Contact;

@Database(entities = Contact.class, version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {
    public abstract ContactDao provideContactDao();
}
