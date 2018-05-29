package com.coderfolk.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Mydbhelper extends SQLiteOpenHelper {
    public Mydbhelper(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY AUTOINCREMENT, note TEXT, date TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    public void insertdata(Mymodel mymodel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note", mymodel.note);
        values.put("date", mymodel.date);
        db.insert("notes", null, values);
        values.clear();
        db.close();
    }

    public List<Mymodel> showdata() {
        List<Mymodel> list=new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from notes", null);
            Log.d("count",String.valueOf(cursor.getCount()));

            if(cursor.moveToFirst()){
                do{
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    String note=cursor.getString(cursor.getColumnIndex("note"));
                    String date=cursor.getString(cursor.getColumnIndex("date"));
                    list.add(new Mymodel(id,note,date));
                    Log.d("Cursorid",cursor.getString(cursor.getColumnIndex("id")) );
                    Log.d("Cursor note",cursor.getString(cursor.getColumnIndex("note")));
                    Log.d("cursor date",cursor.getString(cursor.getColumnIndex("date")));
                }while (cursor.moveToNext());
            }
            db.close();
            return list ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Mymodel> deletedata(int uid){
        List<Mymodel> list=new ArrayList<>();
        try {
            SQLiteDatabase db=getWritableDatabase();
            db.execSQL("delete from notes where id="+uid);
          //  db.execSQL("delete from sqlite_sequence where name= notes");
          //  int stat=db.delete("notes","id=?",new String[]{String.valueOf(id)});
      //      Log.d("Deleted status",String.valueOf(stat));
            SQLiteDatabase db2 = getReadableDatabase();
            Cursor cursor = db2.rawQuery("select * from notes", null);
            Log.d("count",String.valueOf(cursor.getCount()));

            if(cursor.moveToFirst()){
                do{
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    String note=cursor.getString(cursor.getColumnIndex("note"));
                    String date=cursor.getString(cursor.getColumnIndex("date"));
                    list.add(new Mymodel(id,note,date));
                    Log.d("Cursorid",cursor.getString(cursor.getColumnIndex("id")) );
                    Log.d("Cursor note",cursor.getString(cursor.getColumnIndex("note")));
                    Log.d("cursor date",cursor.getString(cursor.getColumnIndex("date")));
                }while (cursor.moveToNext());
            }
            db.close();
            return list ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Mymodel> updatedata(int uid, String newnote,String udate){
        List<Mymodel> list=new ArrayList<>();
        try {
            SQLiteDatabase db=getWritableDatabase();
            //db.execSQL("update notes SET note="+newnote+",date="+udate+"where id="+uid);
            ContentValues values=new ContentValues();
            values.put("note",newnote);
            values.put("date",udate);
           int stat= db.update("notes",values,"id=?",new String[]{String.valueOf(uid)});
            //  db.execSQL("delete from sqlite_sequence where name= notes");
            //  int stat=db.delete("notes","id=?",new String[]{String.valueOf(id)});
                 Log.d("stat",String.valueOf(stat));
            SQLiteDatabase db2 = getReadableDatabase();
            Cursor cursor = db2.rawQuery("select * from notes", null);
            Log.d("count",String.valueOf(cursor.getCount()));

            if(cursor.moveToFirst()){
                do{
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    String note=cursor.getString(cursor.getColumnIndex("note"));
                    String date=cursor.getString(cursor.getColumnIndex("date"));
                    list.add(new Mymodel(id,note,date));
                    Log.d("Cursorid",cursor.getString(cursor.getColumnIndex("id")) );
                    Log.d("Cursor note",cursor.getString(cursor.getColumnIndex("note")));
                    Log.d("cursor date",cursor.getString(cursor.getColumnIndex("date")));
                }while (cursor.moveToNext());
            }
            db.close();
            return list ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS notes";
        sqLiteDatabase.execSQL(sql);
    }
}
