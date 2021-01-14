package com.example.database_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btn_insert_data,btn_dataView,btn_Updatedata,btn_deleteData;
    EditText text_Name,text_Surname,text_Mobile,text_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        myDb = new DatabaseHelper(this);
        btn_insert_data = (Button)findViewById(R.id.bt1);
        btn_dataView = (Button)findViewById(R.id.bt4);
        btn_Updatedata = (Button)findViewById(R.id.bt3);
        btn_deleteData = (Button)findViewById(R.id.bt2);

         text_Name = (EditText)findViewById(R.id.et1);
         text_Surname = (EditText)findViewById(R.id.et2);
         text_Mobile = (EditText)findViewById(R.id.et3);
         text_id = (EditText)findViewById(R.id.et0);





         btn_insert_data.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 boolean isinserted = myDb.insertData(text_Name.getText().toString(),text_Surname.getText().toString(),text_Mobile.getText().toString());
                 String t_name = text_Name.getText().toString();
                 String t_Sname = text_Surname.getText().toString();
                 String t_mob = text_Mobile.getText().toString();

                 if (TextUtils.isEmpty(t_name)) {
                     Toast.makeText(MainActivity.this, "Please fill Name", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(t_Sname)) {
                     Toast.makeText(MainActivity.this, "Please fill SurName", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(t_mob)) {
                     Toast.makeText(MainActivity.this, "Please fill Mobile no.", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 if(isinserted == true){
                     Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                     //part 3 tk
                 }
             }
         });
         btn_dataView.setOnClickListener(new View.OnClickListener() {
             //part 4 vedio m hai data ko view kaise krna h
             @Override
             public void onClick(View v) {
                 Cursor cursor = myDb.getAllData();
                 if (cursor.getCount() == 0){
                     showMessage("Error","No Data Found");
                     return;
                 }

                 StringBuffer buffer = new StringBuffer();
                 while (cursor.moveToNext()){
                     buffer.append("ID: "+ cursor.getString(0)+"\n");
                     buffer.append("NAME: "+ cursor.getString(1)+"\n");
                     buffer.append("SURNAME: "+ cursor.getString(2)+"\n");
                     buffer.append("MOBILE: "+ cursor.getString(3)+"\n");
                 }
                 showMessage("Data",buffer.toString());


             }
         });

         btn_Updatedata.setOnClickListener(new View.OnClickListener() {
             //vedio k PART 5 mai data update h
             @Override
             public void onClick(View v) {
                 boolean isUpdate= myDb.updateData(text_id.getText().toString(),text_Name.getText().toString(),text_Surname.getText().toString(),text_Mobile.getText().toString());
                 if (isUpdate == true){
                     Toast.makeText(MainActivity.this,"Successfully updated",Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Toast.makeText(MainActivity.this,"Failure to update",Toast.LENGTH_SHORT).show();
                 }
             }
         });
         btn_deleteData.setOnClickListener(new View.OnClickListener() {
             // vedio k 6 part m delete krna bataya h
             @Override
             public void onClick(View v) {
                 Integer deleted = myDb.deleteData(text_id.getText().toString());
                 if (deleted>0){
                     Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                 }
                 else {
                     Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
