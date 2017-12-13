package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>
{
    Context context;
    String [] subject ={};
    String [] grade ={};

    public CustomAdapter(Context context, String[] subject, String[] grade)
    {
        super(context, R.layout.dialog_list_view, subject);
        this.subject = subject;
        this.grade = grade;
        this.context = context;
    }

    public class ViewHolder
    {
        TextView subject, grade;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.dialog_list_view_item, null);
        }

        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.subject = v.findViewById(R.id.subjectsTextDIalog);
        viewHolder.grade = v.findViewById(R.id.gradesTextDialog);

        viewHolder.subject.setText(subject[position]);
        viewHolder.grade.setText(grade[position]);

        return v ;
    }
}