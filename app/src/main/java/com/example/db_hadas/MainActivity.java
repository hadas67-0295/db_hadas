package com.example.db_hadas;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etFirstName;
    EditText etLastName;
    EditText etAddress;
    EditText etPhone;

    Button btnAddContact;

    Database database;
    ContactDAO contactDao;

    ArrayList<String> contactIds;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    List<Contact> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        listView = findViewById(R.id.listContact);
        contactIds = new ArrayList<>();

        btnAddContact = findViewById(R.id.btnAddContact);

        database = Database.getInstance(this);
        contactDao = database.contactDAO();
        contactList = contactDao.getAllContact();
        for(Contact contact: contactList) {
            contactIds.add(String.valueOf(contact.getId()));
        }
        arrayAdapter =new ArrayAdapter<>(MainActivity.this,R.layout.contact_item,contactIds);
        listView.setAdapter(arrayAdapter);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();

                Contact contact = new Contact(firstName,lastName,phone,address);
                contactDao.insert(contact);

            }
        });





        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact tamp = new Contact(etFirstName.getText().toString(), etLastName.getText().toString(),etPhone.getText().toString(),etAddress.getText().toString());
                contactDao.insert(tamp);
            }
        });
    }
}