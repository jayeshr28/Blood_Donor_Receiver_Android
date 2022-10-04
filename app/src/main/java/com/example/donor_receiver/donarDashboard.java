package com.example.donor_receiver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class donarDashboard extends Activity {

    SQLiteDatabase db;
    String user;
    String user4;
    String user3;
    TextView guest1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_dashboard);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user_id");
        }


        Bundle extras2 = getIntent().getExtras();
        if (extras2 != null) {
            user4 = extras2.getString("user_id3");
        }


        Bundle extras3 = getIntent().getExtras();
        if (extras3 != null) {
            user3 = extras3.getString("user_id4");
        }
        if (user4!=null)
        {
            user=user4;
        }
        if(user3!=null)
        {
            user=user3;
        }
        guest1 = (TextView)findViewById(R.id.guest);
        guest1.setText(user);
    }

    public void f9(View view) {
        Intent intent = new Intent(this, donar_current_items.class);
        intent.putExtra("user_id3",user);
        startActivity(intent);
        tv = (TextView) this.findViewById(R.id.quote);
        tv.setSelected(true);

    }

    public void f10(View view) {
        Intent intent = new Intent(this, MyDonations.class);
        intent.putExtra("user_id3",user);
        startActivity(intent);
    }

    public void view(View view) {
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        Toast.makeText(this, "Data Fetched Successfully", Toast.LENGTH_SHORT).show();
    }
    public void insertRow(View view) {
        final Dialog d = new Dialog(this);
        createDB();
        createTable();
        d.setContentView(R.layout.layout_for_insert_row);
        d.setTitle("Insert Data");
        final EditText title = (EditText) d.findViewById(R.id.title1);
        final EditText category = (EditText) d.findViewById(R.id.category1);
        final EditText condition = (EditText) d.findViewById(R.id.condition1);
        final EditText mob = (EditText) d.findViewById(R.id.mob1);
        final EditText address = (EditText) d.findViewById(R.id.address1);
        Button btInsert = (Button) d.findViewById(R.id.btInsert);
        Button btCancel = (Button) d.findViewById(R.id.btCancel);

        btInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String title1 = title.getText().toString();
                String category1 = category.getText().toString();
                String condition1 = condition.getText().toString();
                String mob1 = mob.getText().toString();
                String address1 = address.getText().toString();
                if (title1.length() == 0 || category1.length() == 0 || condition.length() == 0 || mob1.length() == 0 || address1.length() == 0) {

                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(mob1.length()!=10)
                {
                    Toast.makeText(getApplicationContext(), "Mobile Number should be 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    {
                    String query = "INSERT INTO items values('" + title1 + "','" + category1 + "','" + condition1 + "','" + mob1 + "','" + address1 + "','" + user + "')";

                    db.execSQL(query);

                    Toast.makeText(getBaseContext(), "RECORD INSERTED", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }
    private void createDB() {
        db = openOrCreateDatabase("MyDB",MODE_PRIVATE,null);
    }
    private void createTable()
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS items(title varchar, category varchar, condition varchar, mob varchar, address varchar, owner varchar)");
    }

    public void f21(View view) {
        Intent intent2= new Intent(this, MainActivity.class);
        intent2.setFlags(intent2.FLAG_ACTIVITY_NEW_TASK | intent2.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
        Toast.makeText(this,"Logout Successful",Toast.LENGTH_SHORT).show();
        finish();

    }

    public void f34(View view) {
        Toast.makeText(getBaseContext(), "Module is Under Construction", Toast.LENGTH_SHORT).show();

    }
}