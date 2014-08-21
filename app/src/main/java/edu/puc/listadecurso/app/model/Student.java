package edu.puc.listadecurso.app.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import edu.puc.listadecurso.app.MyAdapter.ListItem;
import edu.puc.listadecurso.app.model.MyDatabase.STUDENT_COLUMNS;

/**
 * Created by jose on 8/12/14.
 */
public class Student implements ListItem, Parcelable {
    private String mName;
    private String mLastName1;
    private String mLastName2;

    public Student(String name, String lastName1, String lastName2) {
        mName = name;
        mLastName1 = lastName1;
        mLastName2 = lastName2;
    }

    public Student(Cursor cursor) {
        mName = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMNS.NAME));
        mLastName1 = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMNS.LAST_NAME_1));
        mLastName2 = cursor.getString(cursor.getColumnIndex(STUDENT_COLUMNS.LAST_NAME_2));
    }

    public Student(Parcel in) {
        mName = in.readString();
        mLastName1 = in.readString();
        mLastName2 = in.readString();
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getText() {
        return mName + " " + mLastName1 + " " + mLastName2;
    }

    public String getFirstLetter() {
        return mLastName1.substring(0, 1);
    }

    public String getName() {
        return mName;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_COLUMNS.NAME, mName);
        cv.put(STUDENT_COLUMNS.LAST_NAME_1, mLastName1);
        cv.put(STUDENT_COLUMNS.LAST_NAME_2, mLastName2);
        return cv;
    }

    // Parcelable interface

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mLastName1);
        dest.writeString(mLastName2);
    }
}
