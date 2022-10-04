package com.example.donor_receiver;
import android.app.Activity;
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
import java.util.ArrayList;

public class MyDonations extends Activity {
    ListView lv1;
    SQLiteDatabase db;
    ArrayList<Student> al = new ArrayList<>();
    StudentAdapter ad;
    String user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donations);
        lv1 = (ListView) findViewById(R.id.lv1);
        ad = new StudentAdapter();
        lv1.setAdapter(ad);
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        Bundle extras1 = getIntent().getExtras();
        if (extras1 != null) {
            user2 = extras1.getString("user_id3");
        }
        fetchDataFromDataBase();
    }

    void fetchDataFromDataBase() {
        al.clear();
        Cursor c = db.rawQuery("SELECT * FROM received4 where owner=' " + user2 + "' ", null);

        while (c.moveToNext()) {
            String n = c.getString(c.getColumnIndexOrThrow("title"));
            String n1 = c.getString(c.getColumnIndexOrThrow("category"));
            String n2 = c.getString(c.getColumnIndexOrThrow("condition"));
            String n4 = c.getString(c.getColumnIndexOrThrow("receivedBy"));
            al.add(new Student(n, n1, n2, n4));
        }
        ad.notifyDataSetChanged();
    }
    class Student {
        String title;
        String category;
        String condition;
        String receiver;

        public Student(String title, String category, String condition, String receiver) {
            this.title = title;
            this.category = category;
            this.condition = condition;
            this.receiver = receiver;
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
            singleView = inflater.inflate(R.layout.layout_mydonations,
                    parent, false);
            TextView title = (TextView) singleView.findViewById(R.id.title3);
            TextView category = (TextView) singleView.findViewById(R.id.category3);
            TextView condition = (TextView) singleView.findViewById(R.id.condition3);
            TextView receiver = (TextView) singleView.findViewById(R.id.receivedby);

            title.setText(al.get(position).title);
            category.setText(al.get(position).category + "");
            condition.setText(al.get(position).condition);
            receiver.setText(al.get(position).receiver);
            return singleView;
        }
    }
    public void f6(View view) {
        Intent intent2= new Intent(this,donarDashboard.class);
        intent2.putExtra("user_id3",user2);
        startActivity(intent2);
    }
}

