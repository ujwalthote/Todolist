package com.coderfolk.to_do_list;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Myadapter extends ArrayAdapter<List> {
    Context context;
    List<Mymodel> list;
    int resource;

    public Myadapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, 0, objects);
        this.context=context;
        this.list=objects;
        this.resource=resource;
    }
//
//    public Myadapter(@NonNull Context context, List<String> list, int resource) {
//        super(context, resource);
//
//    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view= LayoutInflater.from(getContext()).inflate(
                    R.layout.customlistview, parent, false);
            Log.d("Null object","Initialised");
        }

        TextView uid=(TextView)view.findViewById(R.id.uid);
        ImageView imageView = (ImageView) view.findViewById(R.id.listimage);
        TextView textView = (TextView)view.findViewById(R.id.listtext);
        TextView date = (TextView) view.findViewById(R.id.date);
        imageView.setBackgroundResource(R.mipmap.ic_launcher_round);
        textView.setText(list.get(position).note);
        date.setText(list.get(position).date);
        uid.setText(String.valueOf(list.get(position).id));
        return view;
    }
}
