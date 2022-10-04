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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class donar_current_items extends Activity {
    ListView lv1;
    SQLiteDatabase db;
    ArrayList<Student> al = new ArrayList<>();
    StudentAdapter ad;
    String title2;
    String category2;
    String condition2;
    String titleToDelete;
    String user2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_current_items);
        Bundle extras1 = getIntent().getExtras();
        if (extras1 != null) {
            user2 = extras1.getString("user_id3");
        }
        lv1 = (ListView) findViewById(R.id.lv1);
        ad = new StudentAdapter();
        lv1.setAdapter(ad);
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        fetchDataFromDataBase();
    }
    void fetchDataFromDataBase() {
        al.clear();
        Cursor c = db.rawQuery("SELECT * FROM items where owner='"+user2+"' ", null);
        while (c.moveToNext()) {
            String n = c.getString(c.getColumnIndexOrThrow("title"));
            String n1 = c.getString(c.getColumnIndexOrThrow("category"));
            String n2 = c.getString(c.getColumnIndexOrThrow("condition"));
            al.add(new Student(n, n1, n2));
        }
        ad.notifyDataSetChanged();
    }
    public void f20(View view) {
        Intent intent = new Intent(this, donarDashboard.class);
        intent.putExtra("user_id4",user2);
        startActivity(intent);
    }
    class Student
    {
        String title;
        String category;
        String condition;

        public Student(String title, String category, String condition)
        {
            this.title = title;
            this.category = category;
            this.condition = condition;
        }
    }
    class StudentAdapter extends BaseAdapter
    {
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
            singleView = inflater.inflate(R.layout.layout_donor_current_items,
                    parent, false);
            TextView title = (TextView) singleView.findViewById(R.id.title2);
            TextView category = (TextView) singleView.findViewById(R.id.category2);
            TextView condition = (TextView) singleView.findViewById(R.id.condition2);
            Button btn= (Button) singleView.findViewById(R.id.btDelete);
            btn.setTag(position);

            title.setText(al.get(position).title);
            category.setText(al.get(position).category + "");
            condition.setText(al.get(position).condition);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Integer index = (Integer) v.getTag();
                    title2 = (al.get(index.intValue()).title).toString();
                    category2 = (al.get(index.intValue()).category).toString();
                    condition2 = (al.get(index.intValue()).condition).toString();
                    titleToDelete=(al.get(index.intValue()).title).toString();
                    deleteRow();
                }
            });
            return singleView;
        }
    }
    public void deleteRow()
    {
        db.execSQL("DELETE FROM items where title = '"+titleToDelete+"' and owner='"+user2+"'");
        Toast.makeText(this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
    }
}