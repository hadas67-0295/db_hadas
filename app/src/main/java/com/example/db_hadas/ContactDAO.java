package com.example.db_hadas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    void insert(Contact contact);

    @Delete
    void delet(Contact contact);

    @Query("SELECT*FROM contact")
    List<Contact> getAllContact();

    @Query("SELECT*FROM contact WHERE id = :id")
    Contact getContactByID(int id);
}
