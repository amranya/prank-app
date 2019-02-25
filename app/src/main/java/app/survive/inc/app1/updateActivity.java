package app.survive.inc.app1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class updateActivity extends AppCompatActivity {

    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MyDatabase db = new MyDatabase(this);
        SQLiteDatabase sdb = db.getWritableDatabase();

        Cursor cursor = sdb.rawQuery("SELECT * FROM db", null);

        String rtt = "";

        while (cursor.moveToNext()){
            rtt = cursor.getString(6);
        }

        link = rtt;
    }

    public void click(View view){

        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(link)));

    }
}
