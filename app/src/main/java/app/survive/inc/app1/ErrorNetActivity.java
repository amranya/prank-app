package app.survive.inc.app1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ErrorNetActivity extends AppCompatActivity {

    Button otherApp;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_error);

        otherApp = (Button) findViewById(R.id.Button2);

        MyDatabase db = new MyDatabase(this);
        SQLiteDatabase sdb = db.getWritableDatabase();

        Cursor cursor = sdb.rawQuery("SELECT * FROM db", null);

        String rtt = "";

        while (cursor.moveToNext()){
            rtt = cursor.getString(6);
        }


        if(!rtt.isEmpty()){

            otherApp.setVisibility(View.VISIBLE);
            link = rtt;

        }

    }

    public void click(View view){

        startActivity(new Intent(ErrorNetActivity.this, NameInputActivity.class));
    }

    public void click2(View view){

        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(link)));
    }
}
