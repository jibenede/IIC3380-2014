package edu.puc.listadecurso.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.puc.listadecurso.app.MyAdapter.ListItem;
import edu.puc.listadecurso.app.model.MyDatabase;
import edu.puc.listadecurso.app.model.MyDatabase.STUDENT_COLUMNS;
import edu.puc.listadecurso.app.model.MyDatabase.TABLES;
import edu.puc.listadecurso.app.model.Student;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = DetailsActivity.getIntent(MainActivity.this);
                Student student = (Student) parent.getAdapter().getItem(position);
                intent.putExtra(DetailsActivity.DATA_STUDENT, student);
                startActivity(intent);
            }
        });
        MyAdapter adapter = new MyAdapter(this, R.layout.list_student_item, getItems());
        list.setAdapter(adapter);
    }

    private ListItem[] getItems() {
        final List<Student> students = getStudents();
        List<ListItem> items = new ArrayList<ListItem>();

        for (int i = 0; i < students.size(); i++) {
            final int index = i;
            if (i == 0 || !students.get(i).getFirstLetter().equals(students.get(i - 1).getFirstLetter())) {
                items.add(new ListItem() {
                    @Override
                    public boolean isHeader() {
                        return true;
                    }

                    @Override
                    public String getText() {
                        return students.get(index).getFirstLetter();
                    }
                });
            }

            items.add(students.get(i));
        }


        return items.toArray(new ListItem[items.size()]);
    }

    private List<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();

        MyDatabase database = MyDatabase.getDatabase(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLES.STUDENT, null, null, null, null, null, STUDENT_COLUMNS.LAST_NAME_1);
        while (cursor.moveToNext()) {
            Student student = new Student(cursor);
            students.add(student);
        }
        cursor.close();
        return students;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
