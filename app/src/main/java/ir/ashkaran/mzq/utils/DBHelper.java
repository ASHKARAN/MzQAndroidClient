package ir.ashkaran.mzq.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.ashkaran.mzq.objects.PeopleObject;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Activity activity){
        super(activity,app.main.DATABASE_NAME,null,1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS people (\n" +
                "    ID       STRING  ,\n" +
                "    city     STRING  ,\n" +
                "    fName    STRING,\n" +
                "    lName    STRING,\n" +
                "    father   STRING,\n" +
                "    plaque   STRING,\n" +
                "    office   STRING,\n" +
                "    comments STRING \n" +
                ");\n");
    }
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS people");
        onCreate(database);
    }

    public boolean insertPeople (List<PeopleObject> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (PeopleObject peopleObject : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", peopleObject.getID());
            contentValues.put("city", peopleObject.getCity());
            contentValues.put("fName", peopleObject.getFName());
            contentValues.put("lName", peopleObject.getLName());
            contentValues.put("father", peopleObject.getFather());
            contentValues.put("plaque", peopleObject.getPlaque());
            contentValues.put("office", peopleObject.getOffice());
            contentValues.put("comments", peopleObject.getComments());
            Long id  =  db.insert("people", null, contentValues);
            Logger.e(peopleObject.getID() + " inserted " + id);

        }

        return true;
    }

    public Cursor getPeopleByID(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from people where ID ='"+ID+"'", null );
        return res;
    }
    public Cursor getAllPeople() {
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery( "select * from people ", null );

    }
    public void clearAllPeople() {
        SQLiteDatabase db = this.getReadableDatabase();
          db.delete( "people" , "" ,  null);

    }
    public Cursor searchPeople(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        if(
                query.contains("\\") ||
                query.contains("'") ||
                query.contains("\"") ||
                query.contains("-")  ||
                query.contains("*")  ||
                query.contains("&")  ||
                query.contains("!")
        ) {
            return  null;
        }


        query = app.arabicToDecimal(query);

        if(query.length()<3) {
            app.t("لطفا حاقل ۳ حرف وارد نمایید" , app.ToastType.ERROR);
            return  null;
        }

         Cursor c =   db.rawQuery(
                "select * from people where ID LIKE '%"+query+"%' OR fName LIKE '%"+query+"%' OR lName LIKE '%"+ query+"%' OR plaque LIKE '%"+ query+"%'"
                , null );

        if(c != null) Logger.e(c.getCount() + " items found");
        return c ;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "people");
        return numRows;
    }







}