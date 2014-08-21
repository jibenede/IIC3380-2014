package edu.puc.listadecurso.app.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jose on 8/12/14.
 */
public class MyDatabase extends SQLiteOpenHelper {
    private static final Student[] STUDENTS = {
            new Student("MARCO ANTONIO", "ARIAS", "FIGUEROA"),
            new Student("CRISTIAN", "ARNOLDS", "PEREZ"),
            new Student("ESTEBAN", "DIB", "PUELMA"),
            new Student("DIEGO ANDRES", "ESPINOZA", "MORI"),
            new Student("SEBASTIAN RODOLFO", "FERNANDEZ", "SCHLEIN"),
            new Student("IGNACIO", "GOUET", "VERGARA"),
            new Student("SANTIAGO", "LARRAIN", "PEREZ"),
            new Student("DIEGO", "LARRAIN", "YUNGE"),
            new Student("ROBERTO ANDRES", "MARTINEZ", "STOCKER"),
            new Student("JOSE ANTONIO", "MATTE", "OCHAGAVIA"),
            new Student("ESTEBAN NICOLAS", "PIÑONEZ", "VALENZUELA"),
            new Student("NICOLAS ALFREDO", "RISSO", "CARDENAS"),
            new Student("ORLANDO ESTEBAN", "VASQUEZ", "HERRERA"),
            new Student("MATIAS", "VERGARA", "IRARRAZAVAL")
    };

    public interface TABLES {
        String STUDENT = "student";
    }

    public interface STUDENT_COLUMNS {
        String NAME = "name";
        String LAST_NAME_1 = "last_name_1";
        String LAST_NAME_2 = "last_name_2";
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDatabase.sql";
    private static MyDatabase sDatabase;

    public static MyDatabase getDatabase(Context context) {
        if (sDatabase == null) {
            sDatabase = new MyDatabase(context);
        }
        return sDatabase;
    }

    private MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLES.STUDENT + "(" +
                STUDENT_COLUMNS.NAME + " TEXT, " +
                STUDENT_COLUMNS.LAST_NAME_1 + " TEXT, " +
                STUDENT_COLUMNS.LAST_NAME_2 + " TEXT);");
        seed(db);

    }

    private void seed(SQLiteDatabase db) {
        for (Student student : STUDENTS) {
            db.insert(TABLES.STUDENT, null, student.getContentValues());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
