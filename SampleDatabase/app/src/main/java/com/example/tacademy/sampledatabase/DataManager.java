package com.example.tacademy.sampledatabase;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2016-04-29.
 */
public class DataManager extends SQLiteOpenHelper { //db 다루는 매니저
    private static DataManager instance;
    public static DataManager getInstance() { //싱글톤
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    private static final String DB_NAME = "address";
    private static final int DB_VERSION = 1;

    private DataManager() {

        super(MyApplication.getContext(), DB_NAME, null,  DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //디비 테이블 생성
        String sql = "CREATE TABLE "+ DataConstant.PersonTable.TABLE_NAME+"(" +
                DataConstant.PersonTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataConstant.PersonTable.COLUMN_NAME+" TEXT," +
                DataConstant.PersonTable.COLUMN_PHONE+" TEXT," +
                DataConstant.PersonTable.COLUMN_ADDRESS+" TEXT," +
                DataConstant.PersonTable.COLUMN_OFFICE+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    ContentValues values = new ContentValues();
    public void insert(Person p) { //삽입
        values.clear();
        values.put(DataConstant.PersonTable.COLUMN_NAME, p.name);
        values.put(DataConstant.PersonTable.COLUMN_PHONE, p.phone);
        values.put(DataConstant.PersonTable.COLUMN_ADDRESS, p.address);
        values.put(DataConstant.PersonTable.COLUMN_OFFICE, p.office);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(DataConstant.PersonTable.TABLE_NAME, null, values);
    }

    public void update(Person p) { //수정
        values.clear();
        values.put(DataConstant.PersonTable.COLUMN_NAME, p.name);
        values.put(DataConstant.PersonTable.COLUMN_PHONE, p.phone);
        values.put(DataConstant.PersonTable.COLUMN_ADDRESS, p.address);
        values.put(DataConstant.PersonTable.COLUMN_OFFICE, p.office);

        String where = DataConstant.PersonTable._ID + " = ?"; //조건
        String[] args = {""+p.id};

        SQLiteDatabase db = getWritableDatabase();
        db.update(DataConstant.PersonTable.TABLE_NAME, values, where, args);
    }

    public void delete(Person p) { //삭제
        String where = DataConstant.PersonTable._ID + " = ?";
        String[] args = {""+p.id};

        SQLiteDatabase db = getWritableDatabase();
        db.delete(DataConstant.PersonTable.TABLE_NAME, where, args);
    }

    public Cursor selectCursor(String keyword) { //검색
        String[] columns = {DataConstant.PersonTable._ID,
                DataConstant.PersonTable.COLUMN_NAME,
                DataConstant.PersonTable.COLUMN_PHONE,
                DataConstant.PersonTable.COLUMN_ADDRESS,
                DataConstant.PersonTable.COLUMN_OFFICE};
        String where = null;
        String[] whereArgs = null;
        if (!TextUtils.isEmpty(keyword)) {
            where = DataConstant.PersonTable.TABLE_NAME + " LIKE ?";
            whereArgs = new String[]{"%" + keyword + "%"};
        }
        String groupBy = null;
        String having = null;
        String orderBy = DataConstant.PersonTable.COLUMN_NAME + " COLLATE LOCALIZED ASC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DataConstant.PersonTable.TABLE_NAME, columns, where, whereArgs, groupBy, having, orderBy);
        return c;
    }

    public List<Person> search(String keyword) { //검색
        Cursor c = selectCursor(keyword);
        List<Person> list = new ArrayList<Person>();
        while(c.moveToNext()) {
            Person p = new Person();
            p.id = c.getLong(c.getColumnIndex(DataConstant.PersonTable._ID));
            p.name = c.getString(c.getColumnIndex(DataConstant.PersonTable.COLUMN_NAME));
            p.phone = c.getString(c.getColumnIndex(DataConstant.PersonTable.COLUMN_PHONE));
            p.address = c.getString(c.getColumnIndex(DataConstant.PersonTable.COLUMN_ADDRESS));
            p.office = c.getString(c.getColumnIndex(DataConstant.PersonTable.COLUMN_OFFICE));
            list.add(p);
        }
        c.close();
        return list;
    }
}