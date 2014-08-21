package edu.puc.listadecurso.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.puc.listadecurso.app.MyAdapter.ListItem;
import edu.puc.listadecurso.app.model.Student;

/**
 * Created by jose on 8/12/14.
 */
public class MyAdapter extends ArrayAdapter<ListItem> {
    private static final int VIEW_TYPES = 2;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, int resource, ListItem[] items) {
        super(context, resource, items);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return !getItem(position).isHeader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int viewType = getItemViewType(position);
            if (viewType == VIEW_TYPE_HEADER) {
                convertView = mLayoutInflater.inflate(R.layout.list_student_header, parent, false);
            } else {
                convertView = mLayoutInflater.inflate(R.layout.list_student_item, parent, false);
            }
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(getItem(position).getText());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPES;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isHeader()) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public interface ListItem {
        public boolean isHeader();
        public String getText();
    }


}
