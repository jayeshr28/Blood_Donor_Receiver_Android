package com.example.donor_receiver;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.IntentFilter;

public class receivedItems extends Activity {
    ListView lv1;
    SQLiteDatabase db;
    ArrayList<Student> al = new ArrayList<>();
    StudentAdapter ad;
    String user9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_items);
        IntentFilter intentFilter = new IntentFilter();
        lv1 = (ListView) findViewById(R.id.lv1);
        ad = new StudentAdapter();
        lv1.setAdapter(ad);
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user9 = extras.getString("user_id25");
        }
        fetchDataFromDataBase();
    }
    void fetchDataFromDataBase() {
        al.clear();
        Cursor c = db.rawQuery("SELECT * FROM received4 where receivedBy='"+user9+"'", null);

        while (c.moveToNext()) {
            String n = c.getString(c.getColumnIndexOrThrow("title"));
            String n1 = c.getString(c.getColumnIndexOrThrow("category"));
            String n2 = c.getString(c.getColumnIndexOrThrow("condition"));
            String n3 = c.getString(c.getColumnIndexOrThrow("mob"));
            String n4 = c.getString(c.getColumnIndexOrThrow("address"));
            String n5 = c.getString(c.getColumnIndexOrThrow("owner"));
            al.add(new Student(n, n1, n2, n3, n4, n5));
        }

        ad.notifyDataSetChanged();
    }

    public void f6(View view) {
        Intent intent2= new Intent(this,ReceiverDashboard.class);
        intent2.putExtra("user_id33",user9);
        startActivity(intent2);
    }

    class Student
    {
        String title;
        String category;
        String condition;
        String mob;
        String address;
        String owner;

        public Student(String title, String category, String condition, String mob, String address,String owner)
        {
            this.title = title;
            this.category = category;
            this.condition = condition;
            this.mob = mob;
            this.address = address;
            this.owner=owner;
        }
    }
    class StudentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return al.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View singleView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            singleView = inflater.inflate(R.layout.layout_received_items,
                    parent, false);
            TextView title = (TextView) singleView.findViewById(R.id.title1);
            TextView category = (TextView) singleView.findViewById(R.id.category1);
            TextView condition = (TextView) singleView.findViewById(R.id.condition1);
            TextView mob = (TextView) singleView.findViewById(R.id.mob1);
            TextView address = (TextView) singleView.findViewById(R.id.address1);
            TextView owner = (TextView) singleView.findViewById(R.id.owner1);
            title.setText(al.get(position).title);
            category.setText(al.get(position).category + "");
            condition.setText(al.get(position).condition);
            mob.setText(al.get(position).mob);
            address.setText(al.get(position).address);
            owner.setText(al.get(position).owner);
            return singleView;
        }
    }
    public void deleteRow() {
        db.execSQL("DELETE FROM received4");
        Toast.makeText(this, "RECORD DELETED", Toast.LENGTH_SHORT).show();
    }
}