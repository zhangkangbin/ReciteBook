package com.zkb.recitebook.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;


import com.zkb.recitebook.bean.SubjectBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author z
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final static String table = "book";
    /*建表语句定义成了一个字符串常量*/
    public static final String CREATE_BANK =
            "create table " + table + "("
                    + "id integer primary key autoincrement,"
                    + "subject text,"
                    + "answer text,"
                    + "subjectType text,"
                    + "tips text,"
                    + "time text,"
                    + "memoryType integer,"
                    + "forget integer,"
                    + "remember integer,"
                    + "collect integer,"
                    + "bookId text)";


    private Context mContext;

    public static int dbVersion = 3;

    public MyDatabaseHelper(Context context) {
        super(context, table + ".db", null, dbVersion);
        mContext = context;
    }

    private static MyDatabaseHelper myDatabaseHelper;

    public static synchronized MyDatabaseHelper get(Context context) {
        if (myDatabaseHelper == null) {

            myDatabaseHelper = new MyDatabaseHelper(context);
        //    myDatabaseHelper.onUpgrade(myDatabaseHelper.getWritableDatabase(),2,3);
        }
        return myDatabaseHelper;
    }

    /*数据库管理器构造方法*/
    public MyDatabaseHelper(Context context, int version) {
        super(context, table + ".db", null, version);
        mContext = context;
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public synchronized void insertData(SubjectBean info) {
        Map<String, String> map = new HashMap<>();
        map.put("subject", info.getSubject());
        map.put("answer", info.getAnswer());
        map.put("tips", info.getTips());
        map.put("time", info.getTime());
        map.put("bookId", info.getBookId());
        map.put("memoryType", info.getMemoryType() + "");
        map.put("subjectType", info.getSubjectType() + "");
        insertData(map);
    }

    public synchronized void insertData(Map<String, String> map) {
        SQLiteDatabase db = getWritableDatabase();
        /*用contentValues来组装要插入的数据 未赋值的自动生成默认值*/
        ContentValues values = new ContentValues();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            values.put(mapKey, mapValue);
        }

        Log.d("mytest", "insert");
        db.insert(table, null, values);
    }

    /*创建数据库，同时创建表*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BANK);
        Log.d("mytest", "onCreate");
        // Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_LONG).show();
    }

    public synchronized List<SubjectBean> getAllData() {

        return getAllData("", 0);
    }

    public synchronized SubjectBean getById(String id) {
    //    Log.d("mytest", "getAllData is " + queryData + "page" + page);

        SQLiteDatabase db = getReadableDatabase();
        //查询sql数据
        Cursor cursor;

        cursor = db.query(table, null, "id = ?", new String[]{id},
                null, null, null, null);

        List<SubjectBean> list = setData(cursor);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public synchronized List<SubjectBean> getAllData(String queryData, int page) {
        Log.d("mytest", "getAllData is " + queryData + "page" + page);


        SQLiteDatabase db = getReadableDatabase();
        //查询sql数据
        Cursor cursor;
        if (TextUtils.isEmpty(queryData)) {
            cursor = db.query(table, null, null, null,
                    null, null, "time DESC", page + ",20");
        } else {
            cursor = db.query(table, null, "bookId = ?", new String[]{queryData},
                    null, null, "time DESC", page + ",20");
        }

        return setData(cursor);
    }

    private List<SubjectBean> setData(Cursor cursor) {
        List<SubjectBean> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象 取出数据并打印
                /**
                 *id int
                 * subject text
                 * subjectType int
                 * tips text,"
                 * time text,"
                 * memoryType int
                 * bookId text
                 */
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String subject = cursor.getString(cursor.getColumnIndex("subject"));
                int subjectType = cursor.getInt(cursor.getColumnIndex("subjectType"));
                String tips = cursor.getString(cursor.getColumnIndex("tips"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                int memoryType = cursor.getInt(cursor.getColumnIndex("memoryType"));
                String bookId = cursor.getString(cursor.getColumnIndex("bookId"));
                String answer = cursor.getString(cursor.getColumnIndex("answer"));
                int remember = cursor.getInt(cursor.getColumnIndex("remember"));
                int forget = cursor.getInt(cursor.getColumnIndex("forget"));
                int collect = cursor.getInt(cursor.getColumnIndex("collect"));

                SubjectBean info = new SubjectBean();
                info.setId(id);
                info.setSubject(subject);
                info.setSubjectType(subjectType);
                info.setTips(tips);
                info.setTime(time);
                info.setMemoryType(memoryType);
                info.setBookId(bookId);
                info.setAnswer(answer);
                info.setRemember(remember);
                info.setForget(forget);
                info.setCollect(collect);
                data.add(info);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return data;
    }


    /**
     * 查询数据库中的总条数.
     *
     * @return
     */
    public synchronized long getAllPhoneNum(String phone) {
        // String sql = "select count(*) from "+table+" where phone="+phone+" and type="+type_fail;
        long count = 0;
        try {
            // limit 0,5;
            String type_fail = "发送失败";
            String sql = "select count(*) from " + table + " where send_tel=" + phone + " and type='" + type_fail + "'";
            Cursor cursor = getReadableDatabase().rawQuery(sql, null);
            cursor.moveToFirst();
            count = cursor.getLong(0);
            cursor.close();
        } catch (Exception e) {

        }

        return count;
    }

    public synchronized boolean delete(int id) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            db.execSQL("delete from " + table + " where id=?", new Object[]{id});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 4 是为重新发送成功
     *
     * @param id
     * @param value
     * @return
     */
    public synchronized boolean updateType(int id, String value) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            ContentValues newValues = new ContentValues();
            newValues.put("type", value);

            String[] args = new String[]{id + ""};
            db.update(table, newValues, "id=?", args);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 4 是为重新发送成功
     *
     * @param id
     * @return
     */
    public synchronized boolean updateRemember(int id) {
        try {
            SQLiteDatabase db = getReadableDatabase();

            SubjectBean info = getById(id+"");

            int remember=info.getRemember()+1;
    /*
            ContentValues newValues = new ContentValues();
            newValues.put("remember", value);

            String[] args = new String[]{id + ""};
            db.update(table, newValues, "id=?", args);*/
            //db.update(table, null, "id=?", args)

            db.execSQL("update "+table+" set remember="+remember+" where id = "+id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public synchronized boolean updateForget(int id) {
        try {
            SQLiteDatabase db = getReadableDatabase();

            SubjectBean info = getById(id+"");

            int forget=info.getForget()+1;
    /*
            ContentValues newValues = new ContentValues();
            newValues.put("remember", value);

            String[] args = new String[]{id + ""};
            db.update(table, newValues, "id=?", args);*/
            //db.update(table, null, "id=?", args)

            db.execSQL("update "+table+" set forget="+forget+" where id = "+id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public synchronized boolean updateCollect(int id,int type) {
        try {
            SQLiteDatabase db = getReadableDatabase();

            db.execSQL("update "+table+" set collect="+type+" where id = "+id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    /*升级数据库*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(CREATE_BANK);
    }
}