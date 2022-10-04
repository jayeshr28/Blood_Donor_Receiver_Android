package com.example.donor_receiver;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forgot_password extends Activity {
    int result;
    SQLiteDatabase db;
    Button btForgot;
    Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void forgotPass(View view) {

        createDB();
        final EditText user = (EditText) findViewById(R.id.emailCheck);
        final EditText mobile = (EditText) findViewById(R.id.mobileCheck);
        String username = user.getText().toString();
        String mob5 = mobile.getText().toString();

        if (isEmailValid(username)==false)
        {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }
         else if (mobile.length() != 10) {
            Toast.makeText(getApplicationContext(), "Mobile Number should be 10 digits", Toast.LENGTH_SHORT).show();
            return;
        } else {

            Cursor c = db.rawQuery("SELECT * FROM Donors1 where email='" + username + "' and mobile='" + mob5 + "'", null);
            result = c.getCount();
            if (result > 0) {
                changePassword();
            } else {
                Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isEmailValid(String user1) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(user1);
        return matcher.matches();
    }
    private void changePassword() {
        final Dialog d = new Dialog(this);
        createDB();
        d.setContentView(R.layout.layout_forgot_password);
        d.setTitle("Insert Data");
        final EditText pass1 = (EditText) d.findViewById(R.id.pass10);
        final EditText pass2 = (EditText) d.findViewById(R.id.pass11);
        Button btChange = (Button) d.findViewById(R.id.ChangePass);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass10= pass1.getText().toString().trim();
                String pass11 = pass2.getText().toString().trim();

                String query = "UPDATE Donors1 SET password1='" + pass11 + "'";

                db.execSQL(query);

                Toast.makeText(getApplicationContext(),"Password Changed! Login to continue",Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });
d.show();

    }

    private void createDB() {
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
    }


    public void f31(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void f32(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
