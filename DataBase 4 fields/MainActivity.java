package com.example.akshay.database;


import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText Efname,Elname,Eemail,Ecity,Eid;
    String First,Last,Email,City;
    int ID;
    Button reg,retrive,updateB,deleteB;
    DataBaseHelper db = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Efname = findViewById(R.id.edit_fname_id);
        Elname = findViewById(R.id.edit_lname_id);
        Eemail = findViewById(R.id.edit_email_id);
        Ecity = findViewById(R.id.edit_city_id);
        Eid = findViewById(R.id.edit_id);

        reg = findViewById(R.id.register_button);
        retrive = findViewById(R.id.getData_button);
        updateB = findViewById(R.id.update_button);
        deleteB = findViewById(R.id.delete_button);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllData();
            }
        });

        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.getAllDatabaseData();
                if(c.getCount() == 0) {
                    showMessage("Error", "No Data Found");
                }
                StringBuffer bf = new StringBuffer();
                while (c.moveToNext())
                {
                    bf.append("ID : "+c.getString(0)+"\n");
                    bf.append("Name : "+c.getString(1)+" "+c.getString(2)+"\n");
                    bf.append("Email ID  : "+c.getString(3)+"\n");
                    bf.append("City : "+c.getString(4)+"\n");
                }
                showMessage("Data",bf.toString());
            }
        });


        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = Integer.parseInt(Eid.getText().toString());
                First = Efname.getText().toString();
                Last = Elname.getText().toString();
                Email = Eemail.getText().toString();
                City = Ecity.getText().toString();

                boolean isupdate = db.updateData(ID,First,Last,Email,City);
                if(isupdate == true)
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rowsAffect = db.deleteData(Integer.parseInt(Eid.getText().toString()));
                if (rowsAffect > 0)
                    Toast.makeText(MainActivity.this,"Data Successfully Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Error to Delete Data",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getAllData()
    {
        First = Efname.getText().toString();
        Last = Elname.getText().toString();
        Email = Eemail.getText().toString();
        City = Ecity.getText().toString();

        boolean isInsert = db.insertData(First,Last,Email,City);

        if(isInsert == true)
            Toast.makeText(this,"Data Successfully Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
    }





    private void showMessage(String title,String msg)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle(title);
        b.setMessage(msg);
        b.show();
    }


}
