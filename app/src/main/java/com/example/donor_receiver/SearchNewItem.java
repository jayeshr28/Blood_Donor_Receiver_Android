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

public class SearchNewItem extends Activity {
    ListView lv1;
    SQLiteDatabase db;
    ArrayList<Student> al = new ArrayList<>();
    StudentAdapter ad;
String title2;
    String category2;
    String condition2;
    String mob2;
    String address2;
    String owner1;
    String itemtoDelete;
    String user10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new_item);
        Bundle extras = getIntent().getExtras();
        lv1 = (ListView) findViewById(R.id.lv1);
        ad = new StudentAdapter();
        lv1.setAdapter(ad);
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        fetchDataFromDataBase();
        Bundle extras11 = getIntent().getExtras();
        if (extras11 != null)
        {
            user10 = extras11.getString("user_id23");
        }

    }

    void fetchDataFromDataBase() {
        al.clear();

        Cursor c = db.rawQuery("SELECT * FROM items", null);

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

    public void f11(View view) {
        Intent intent= new Intent(this,ReceiverDashboard.class);
        intent.putExtra("user_id29",user10);
        startActivity(intent);
    }

    class Student
    {
        String title;
        String category;
        String condition;
        String mob;
        String address;
        String owner;
        public Student(String title, String category, String condition, String mob, String address, String owner)
        {
            this.title = title;
            this.category = category;
            this.condition = condition;
            this.mob = mob;
            this.address = address;
            this.owner= owner;
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
            singleView = inflater.inflate(R.layout.layout_search_result,
                    parent, false);
            TextView title = (TextView) singleView.findViewById(R.id.title1);
            TextView category = (TextView) singleView.findViewById(R.id.category1);
            TextView condition = (TextView) singleView.findViewById(R.id.condition1);
            TextView mob = (TextView) singleView.findViewById(R.id.mob1);
            TextView address = (TextView) singleView.findViewById(R.id.address1);
            TextView owner = (TextView) singleView.findViewById(R.id.owner);
            Button btn= (Button) singleView.findViewById(R.id.btInsert);
            btn.setTag(position);
            title.setText(al.get(position).title);
            category.setText(al.get(position).category + "");
            condition.setText(al.get(position).condition);
            mob.setText(al.get(position).mob);
            address.setText(al.get(position).address);
            owner.setText(al.get(position).owner);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Integer index = (Integer) v.getTag();
            title2 = (al.get(index.intValue()).title).toString();
            category2 = (al.get(index.intValue()).category).toString();
            condition2 = (al.get(index.intValue()).condition).toString();
            mob2 = (al.get(index.intValue()).mob).toString();
            address2 = (al.get(index.intValue()).address).toString();
            owner1 = (al.get(index.intValue()).owner).toString();
            itemtoDelete=(al.get(index.intValue()).title).toString();
            itemClaimed();
            deleteRow();
                }
            });
            return singleView;
        }
    }

    private void itemClaimed() {
        createTable();
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.current_items_layout);
        TextView title = (TextView) findViewById(R.id.title1);
        TextView category = (TextView) findViewById(R.id.category1);
        TextView condition = (TextView) findViewById(R.id.condition1);
        TextView mob = (TextView) findViewById(R.id.mob1);
        TextView address = (TextView) findViewById(R.id.address1);

        String title1 = title.getText().toString();
        String category1 = category.getText().toString();
        String condition1 = condition.getText().toString();
        String mob1 = mob.getText().toString();
        String address1 = address.getText().toString();
        String query = "INSERT INTO record3 values('" + title2 + "','" + category2 + "','"+ condition2 + "','"+ mob2 + "','"+ address2 +"','"+ user10 +"','"+ owner1 +"')";

        db.execSQL(query);
         f11();
    }

    public void f11() {
        Intent intent= new Intent(this,ReceiverDashboard.class);
        intent.putExtra("user_id29",user10);
        startActivity(intent);

    }


    public void deleteRow()
    {
        db.execSQL("DELETE FROM items where title = '"+itemtoDelete+"' and owner='"+owner1+"'");
        Toast.makeText(this, "Item Requested", Toast.LENGTH_SHORT).show();
    }
    private void createTable()
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS record3(title varchar, category varchar, condition varchar, mob varchar, address varchar, requestedBy varchar, owner varchar)");

    }
}