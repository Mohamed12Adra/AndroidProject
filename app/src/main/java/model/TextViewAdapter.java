package model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class TextViewAdapter extends BaseAdapter {
    private Context context;
    private final List<String> textViewValues;

    public TextViewAdapter(Context context, List<String> textViewValues) {
        this.context = context;
        this.textViewValues = textViewValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.textview, null);

            // set value into textview
            @SuppressLint("ResourceType") TextView textView = (TextView) gridView
                    .findViewById(R.string.itemId);
            textView.setText(String.valueOf(textViewValues.get(position)));
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return textViewValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
