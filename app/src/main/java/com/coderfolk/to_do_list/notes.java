package com.coderfolk.to_do_list;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class notes extends AppCompatActivity {
    FloatingActionButton floatbutton = null;
    ListView listView = null;
    List<Mymodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        floatbutton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        listView = (ListView) findViewById(R.id.listview);
       // list = new ArrayList<>();
        final Mydbhelper mydbhelper = new Mydbhelper(getApplicationContext());
//        mydbhelper.insertdata(new Mymodel("hdjhbhebdhdbhcbd", "12/12/1968"));
       list= mydbhelper.showdata();
        final Myadapter myadapter = new Myadapter(getApplicationContext(), R.drawable.ic_launcher_background, list);
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView=(TextView)adapterView.findViewById(R.id.uid);
                TextView listdata=(TextView)adapterView.findViewById(R.id.listtext);
                final int id=Integer.parseInt(textView.getText().toString());
                String data=listdata.getText().toString();
                final Dialog dialog2=new Dialog(notes.this);
                dialog2.setContentView(R.layout.dialog);
                dialog2.show();
                dialog2.setTitle("Add new note");
                final EditText editText=(EditText) dialog2.findViewById(R.id.edittext);
                editText.setText(data);
                Button cancel=(Button)dialog2.findViewById(R.id.cancel);
                Button add=(Button)dialog2.findViewById(R.id.dialogadd);
                add.setText("Update");

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String note=editText.getText().toString();
                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c);
                        Mydbhelper mydbhelper = new Mydbhelper(getApplicationContext());
                        List<Mymodel> mylist=mydbhelper.updatedata(id,note,formattedDate);
                        Myadapter myadapter = new Myadapter(getApplicationContext(), R.drawable.ic_launcher_background, mylist);
                        listView.setAdapter(myadapter);
                        Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT);
                        dialog2.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialog=new Dialog(notes.this);
                dialog.setContentView(R.layout.longclick);
                dialog.show();
                Button delete=(Button) dialog.findViewById(R.id.delete);
                Button update=(Button) dialog.findViewById(R.id.update);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Delete ","Longclick");
                        TextView textView=(TextView)adapterView.findViewById(R.id.uid);
                        int id=Integer.parseInt(textView.getText().toString());
                        Log.d("note",String.valueOf(id));
                        Mydbhelper mydbhelper1=new Mydbhelper(getApplicationContext());
                        List<Mymodel> mylist=mydbhelper1.deletedata(id);
                        Myadapter myadapter = new Myadapter(getApplicationContext(), R.drawable.ic_launcher_background, mylist);
                        listView.setAdapter(myadapter);
                        Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        TextView textView=(TextView)adapterView.findViewById(R.id.uid);
                        TextView listdata=(TextView)adapterView.findViewById(R.id.listtext);
                       final int id=Integer.parseInt(textView.getText().toString());
                        String data=listdata.getText().toString();
                        final Dialog dialog2=new Dialog(notes.this);
                        dialog2.setContentView(R.layout.dialog);
                        dialog2.show();
                        dialog2.setTitle("Add new note");
                        final EditText editText=(EditText) dialog2.findViewById(R.id.edittext);
                        editText.setText(data);
                        Button cancel=(Button)dialog2.findViewById(R.id.cancel);
                        Button add=(Button)dialog2.findViewById(R.id.dialogadd);
                        add.setText("Update");

                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String note=editText.getText().toString();
                                Date c = Calendar.getInstance().getTime();
                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                                String formattedDate = df.format(c);
                                Mydbhelper mydbhelper = new Mydbhelper(getApplicationContext());
                                List<Mymodel> mylist=mydbhelper.updatedata(id,note,formattedDate);
                                Myadapter myadapter = new Myadapter(getApplicationContext(), R.drawable.ic_launcher_background, mylist);
                                listView.setAdapter(myadapter);
                                Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT);
                                dialog2.dismiss();
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();
                            }
                        });



                        Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });
        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog=new Dialog(notes.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Add new note");
                dialog.show();
                final EditText editText=(EditText) dialog.findViewById(R.id.edittext);
                Button cancel=(Button)dialog.findViewById(R.id.cancel);
                Button add=(Button)dialog.findViewById(R.id.dialogadd);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String note=editText.getText().toString();
                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c);
                        Mydbhelper mydbhelper = new Mydbhelper(getApplicationContext());
                        mydbhelper.insertdata(new Mymodel(0,note,formattedDate));
                        List<Mymodel> mylist=mydbhelper.showdata();
                        Myadapter myadapter = new Myadapter(getApplicationContext(), R.drawable.ic_launcher_background, mylist);
                        listView.setAdapter(myadapter);
                        Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });


    }
}
