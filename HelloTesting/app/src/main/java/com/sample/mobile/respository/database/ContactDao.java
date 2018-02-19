package com.sample.mobile.respository.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sample.mobile.resultObjects.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertContacts(List<Contact> contacts);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertContact(Contact contact);

    @Update
    int updateContact(Contact contact);

    @Query("SELECT * FROM contacts WHERE email = :email")
    Contact getContactDetails(String email);

    @Query("SELECT email, title, firstName, lastName, largeUrl FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT email, title, firstName, lastName, largeUrl FROM contacts WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery")
    List<Contact> searchContact(String searchQuery);
}
