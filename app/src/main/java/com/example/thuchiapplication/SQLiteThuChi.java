package com.example.thuchiapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteThuChi extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ThuChi.db";
    private static final String TABLE_NAME = "TableThuChi";
    private static final String COLUMN_ID = "Ma";
    private static final String COLUMN_NAME = "TaiKhoan";
    private static final String COLUMN_MONEY = "Tien";
    private static final String COLUMN_DATE = "NgayThang";
    private static final String COLUMN_THUCHI = "ThuChi";
    public SQLiteThuChi(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTaxi = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_MONEY + " DOUBLE , " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_THUCHI + " INTEGER )";
        db.execSQL(createTableTaxi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableSP = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableSP);
        onCreate(db);
    }
    public List<ThuChi> getAllSP() {
        List<ThuChi> listTaxi = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME +"  ORDER BY " +COLUMN_MONEY +" DESC " ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(0);
                String name = cursor.getString(1);
                double tien = cursor.getDouble(2);
                String date = cursor.getString(3);
                int thuchi=cursor.getInt(4);
                boolean tc;
                if (thuchi==1)
                    tc=true;
                else
                    tc=false;
                ThuChi Khoantien= new ThuChi(name, tien,date,tc);
                listTaxi.add(Khoantien);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listTaxi;
    }
    public void addKhoanTien(ThuChi tc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, tc.getTaikhoan());
        values.put(COLUMN_MONEY, tc.getSotien());
        values.put(COLUMN_DATE,tc.getNgayThang());
        values.put(COLUMN_THUCHI, tc.isThuChi());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public List<ThuChi> getAllSP(double tiensearch) {
        List<ThuChi> listTaxi = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME +"  WHERE  "+ COLUMN_MONEY + "<=" + tiensearch + " ORDER BY " +COLUMN_MONEY +" DESC " ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(0);
                String name = cursor.getString(1);
                double tien = cursor.getDouble(2);
                String date = cursor.getString(3);
                int thuchi=cursor.getInt(4);
                boolean tc;
                if (thuchi==1)
                    tc=true;
                else
                    tc=false;
                ThuChi Khoantien= new ThuChi(name, tien,date,tc);
                listTaxi.add(Khoantien);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listTaxi;
    }
}
