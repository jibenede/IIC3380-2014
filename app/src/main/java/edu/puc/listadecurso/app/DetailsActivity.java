package edu.puc.listadecurso.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import edu.puc.listadecurso.app.model.Student;

/**
 * Created by jose on 8/21/14.
 */
public class DetailsActivity extends Activity {
    public static final String DATA_STUDENT = "data_student";

    private Student mStudent;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, DetailsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            mStudent = intent.getParcelableExtra(DATA_STUDENT);
        } else {
            mStudent = savedInstanceState.getParcelable(DATA_STUDENT);
        }

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(mStudent.getText());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(DATA_STUDENT, mStudent);
    }
}
