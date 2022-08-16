package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_CUSTOMER_NAME = "COLUMN_CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "COLUMN_CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "COLUMN_ACTIVE_CUSTOMER";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " Text, " + COLUMN_CUSTOMER_AGE + " Int, " + COLUMN_ACTIVE_CUSTOMER + " BOOL )";
        db.execSQL(createtableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<CustomerModel>getEveryOne(){
        List<CustomerModel> returnList=new ArrayList<>();

        //get data from the database
        String queryString=" SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do{
                int customerId=cursor.getInt(0);
                String customerName=cursor.getString(1);
                int customerAge=cursor.getInt(2);
                boolean customerActive=cursor.getInt(3)==1? true:false;
                CustomerModel newCustomer=new CustomerModel(customerId,customerName,customerAge,customerActive);
                returnList.add(newCustomer);

            }while(cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return returnList;
    }


}