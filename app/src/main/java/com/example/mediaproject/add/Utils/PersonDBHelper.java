package com.example.mediaproject.add.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.mediaproject.add.model.Person;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Ronsoft on 9/16/2017.
 */

public class PersonDBHelper extends SQLiteOpenHelper {

//     profile = (ImageView) v.findViewById(R.id.icon);
//            date = (TextView) v.findViewById(R.id.date);
//            applicationName = (TextView) v.findViewById(R.id.applicationName);
//            sorting = (TextView) v.findViewById(R.id.sorting);
//            subscribeMoney = (TextView) v.findViewById(R.id.subscribeMoney);

    public static final String DATABASE_NAME = "people.db";
    private static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "People";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PERSON_SERVICENAME = "servicename";
    public static final String COLUMN_PERSON_LASTPAYMENTDATE = "lastpaymentdate";
    public static final String COLUMN_PERSON_TYPEOFMEMBERSHIP = "typeofmembership";
    public static final String COLUMN_PERSON_IMAGEURL = "imageurl";
    public static final String COLUMN_PERSON_PRICE = "price";
    public static final String COLUMN_PERSON_DETAIL = "detail";
    public static final String COLUMN_PERSON_TEXTSHAREINFO = "textshareinfo";
    public static final String COLUMN_PERSON_IMAGEURLSHAREINFO = "imageurlshareinfo";
    public static final String COLUMN_PERSON_SWITCHCONDITION = "switchcondition";
    public static final String COLUMN_PERSON_NEXTMONTHPAYMENTDATE = "nextmonthpaymentdate";
    public static final String COLUMN_PERSON_DIFFPAYMENTDATE = "diffpaymentdate";

    public static final String COLUMN_PERSON_SHAREINFOIMAGE1 = "shareinfoimage1";
    public static final String COLUMN_PERSON_SHAREINFOIMAGE2 = "shareinfoimage2";
    public static final String COLUMN_PERSON_SHAREINFOIMAGE3 = "shareinfoimage3";
    public static final String COLUMN_PERSON_SHAREINFOTEXT1 = "shareinfotext1";
    public static final String COLUMN_PERSON_SHAREINFOTEXT2 = "shareinfotext2";
    public static final String COLUMN_PERSON_SHAREINFOTEXT3 = "shareinfotext3";


    public PersonDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PERSON_SERVICENAME + " TEXT NOT NULL, " +
                COLUMN_PERSON_LASTPAYMENTDATE + " NUMBER NOT NULL, " +
                COLUMN_PERSON_TYPEOFMEMBERSHIP + " TEXT NOT NULL, " +
                COLUMN_PERSON_DETAIL + " TEXT NOT NULL, " +
                COLUMN_PERSON_IMAGEURL + " BLOB NOT NULL, " +
                COLUMN_PERSON_PRICE + " NUMBER NOT NULL, " +
                COLUMN_PERSON_SWITCHCONDITION + " TEXT NOT NULL, " +
                COLUMN_PERSON_NEXTMONTHPAYMENTDATE + " NUMBER NOT NULL, " +
                COLUMN_PERSON_DIFFPAYMENTDATE + " NUMBER NOT NULL, " +
                COLUMN_PERSON_SHAREINFOIMAGE1 + " TEXT, " +
                COLUMN_PERSON_SHAREINFOIMAGE2 + " TEXT, " +
                COLUMN_PERSON_SHAREINFOIMAGE3 + " TEXT, " +
                COLUMN_PERSON_SHAREINFOTEXT1 + " TEXT, " +
                COLUMN_PERSON_SHAREINFOTEXT2 + " TEXT, " +
                COLUMN_PERSON_SHAREINFOTEXT3 + " TEXT );"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * create record
     **/
    public void saveNewPerson(Person person) { // 새로운 사람 저장

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSON_SERVICENAME, person.getServiceName());
        values.put(COLUMN_PERSON_LASTPAYMENTDATE, person.getLastPaymentDate());
        values.put(COLUMN_PERSON_TYPEOFMEMBERSHIP, person.getTypeOfMembership());
        values.put(COLUMN_PERSON_IMAGEURL, person.getImageUrl());
        values.put(COLUMN_PERSON_PRICE, person.getPrice());
        values.put(COLUMN_PERSON_DETAIL, person.getDetail());
        values.put(COLUMN_PERSON_SWITCHCONDITION, person.getSwitchCondition());
        values.put(COLUMN_PERSON_NEXTMONTHPAYMENTDATE, person.getNextMonthPaymentDate());
        values.put(COLUMN_PERSON_DIFFPAYMENTDATE, person.getDiffPaymentDate());

        values.put(COLUMN_PERSON_SHAREINFOIMAGE1, person.getShareInfoimageurl1());
        values.put(COLUMN_PERSON_SHAREINFOIMAGE2, person.getShareInfoimageurl2());
        values.put(COLUMN_PERSON_SHAREINFOIMAGE3, person.getShareInfoimageurl3());

        values.put(COLUMN_PERSON_SHAREINFOTEXT1, person.getShareInfotext1());
        values.put(COLUMN_PERSON_SHAREINFOTEXT2, person.getShareInfotext2());
        values.put(COLUMN_PERSON_SHAREINFOTEXT3, person.getShareInfotext3());

        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /**
     * Query records, give options to filter results
     **/
    public List<Person> peopleList(String filter) {
        String query;
        if (filter.equals("")) {
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        } else {
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + filter;
        }

        List<Person> personLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Person person;

        if (cursor.moveToFirst()) {
            do {
                person = new Person();

                person.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                person.setServiceName(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SERVICENAME)));
                person.setLastPaymentDate(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LASTPAYMENTDATE)));
                person.setTypeOfMembership(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_TYPEOFMEMBERSHIP)));
                person.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGEURL)));
                person.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_PRICE)));
                person.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_DETAIL)));
                person.setSwitchCondition(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SWITCHCONDITION)));
                person.setNextMonthPaymentDate(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_NEXTMONTHPAYMENTDATE)));
                person.setDiffPaymentDate(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_DIFFPAYMENTDATE)));

                person.setShareInfoimageurl1(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOIMAGE1)));
                person.setShareInfoimageurl2(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOIMAGE2)));
                person.setShareInfoimageurl3(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOIMAGE3)));

                person.setShareInfotext1(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOTEXT1)));
                person.setShareInfotext2(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOTEXT2)));
                person.setShareInfotext3(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOTEXT3)));

                personLinkedList.add(person);
            } while (cursor.moveToNext());
        }


        return personLinkedList;
    }

    /**
     * Query only 1 record
     **/
    public Person getPerson(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Person receivedPerson = new Person();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedPerson.setServiceName(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SERVICENAME)));
            receivedPerson.setLastPaymentDate(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_LASTPAYMENTDATE)));
            receivedPerson.setTypeOfMembership(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_TYPEOFMEMBERSHIP)));
            receivedPerson.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGEURL)));
            receivedPerson.setPrice(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_PRICE)));
            receivedPerson.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_DETAIL)));

            receivedPerson.setSwitchCondition(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SWITCHCONDITION)));
            receivedPerson.setNextMonthPaymentDate(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_NEXTMONTHPAYMENTDATE)));
            receivedPerson.setDiffPaymentDate(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_DIFFPAYMENTDATE)));

            receivedPerson.setShareInfoimageurl1(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOIMAGE1)));
            receivedPerson.setShareInfoimageurl2(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOIMAGE2)));
            receivedPerson.setShareInfoimageurl3(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOIMAGE3)));

            receivedPerson.setShareInfotext1(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOTEXT1)));
            receivedPerson.setShareInfotext2(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOTEXT2)));
            receivedPerson.setShareInfotext3(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_SHAREINFOTEXT3)));

        }


        return receivedPerson;


    }


    /**
     * delete record
     **/
    public void deletePersonRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
        Toast.makeText(context, "성공적으로 삭제 되었다구미!", Toast.LENGTH_SHORT).show();

    }


    /**
     * update record
     **/
    public void updatePersonRecord(long personId, Context context, Person updatedperson) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  " + TABLE_NAME + " SET servicename ='" + updatedperson.getServiceName() + "', lastpaymentdate ='" + updatedperson.getLastPaymentDate() + "', " +
                "typeofmembership ='" + updatedperson.getTypeOfMembership() + "', imageurl ='" + updatedperson.getImageUrl() + "', " +
                " price = '" + updatedperson.getPrice() + "', detail = '" + updatedperson.getDetail() + "' , "  +
                " shareinfoimage1 = '" + updatedperson.getShareInfoimageurl1() + "', shareinfoimage2 = '" + updatedperson.getShareInfoimageurl2() +"',shareinfoimage3 = '" + updatedperson.getShareInfoimageurl3() + "', switchcondition = '" + updatedperson.getSwitchCondition() + "',nextmonthpaymentdate = '" + updatedperson.getNextMonthPaymentDate() + "', " +
                " shareinfotext1 = '" + updatedperson.getShareInfotext1() + "', shareinfotext2 = '" + updatedperson.getShareInfotext2() + "', shareinfotext3 = '" + updatedperson.getShareInfotext3() + "', diffpaymentdate = '" + updatedperson.getDiffPaymentDate() + "' WHERE _id='" + personId + "'");

        Toast.makeText(context, "수정 완료되었다구미!", Toast.LENGTH_SHORT).show();


    }


}
