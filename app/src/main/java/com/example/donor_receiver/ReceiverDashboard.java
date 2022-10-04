package com.example.donor_receiver;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiverDashboard extends Activity {
    String user5;
    String user6;
    String user7;
    String user9;
    String user8;
    SQLiteDatabase db;
    TextView guest3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_dashboard);
        Bundle extras11 = getIntent().getExtras();
        if (extras11 != null)
        {
            user5 = extras11.getString("user_id20");
        }
        Bundle extras12 = getIntent().getExtras();
        if (extras12 != null)
        {
            user8 = extras12.getString("user_id29");
        }
        Bundle extras13 = getIntent().getExtras();
        if (extras13 != null)
        {
            user6 = extras13.getString("user_id33");
        }
        Bundle extras15 = getIntent().getExtras();
        if (extras15 != null)
        {
            user7 = extras15.getString("user_id5");
        }
        Bundle extras99 = getIntent().getExtras();
        if (extras99 != null)
        {
            user9 = extras99.getString("user_id99");
        }
        if(user6!=null)
        {
            user5=user6;
        }
        if(user8!=null)
        {
            user5=user8;
        }
        if(user7!=null)
        {
            user5=user7;
        }
        if(user9!=null)
        {
            user5=user9;
        }
     guest3=(TextView)findViewById(R.id.guest2);
        guest3.setText(user5);
    }

    public void f7(View view) {
        Intent intent = new Intent(this, SearchNewItem.class);
        intent.putExtra("user_id23",user5);
        startActivity(intent);
    }
    public void f13(View view) {
        Intent intent = new Intent(this, CurrentItems.class);
        intent.putExtra("user_id22",user5);
        startActivity(intent);
    }

    public void f14(View view) {
        Intent intent = new Intent(this, receivedItems.class);
        intent.putExtra("user_id25",user5);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this,"Logout Successful",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void f33(View view) {
        Toast.makeText(this,"Module is Under Construction",Toast.LENGTH_SHORT).show();

    }
}
