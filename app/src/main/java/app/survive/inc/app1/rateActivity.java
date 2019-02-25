package app.survive.inc.app1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;

public class rateActivity extends AppCompatActivity {

    private int _clicks = 0;
    Helper Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);


        Button button = (Button) findViewById(R.id.button);

        TextView text = (TextView) findViewById(R.id.textView);

        MyDatabase db = new MyDatabase(this);
        SQLiteDatabase sdb = db.getWritableDatabase();

        Cursor cursor = sdb.rawQuery("SELECT * FROM db", null);

        String rtt = "";
        String rtt2 = "";
        while (cursor.moveToNext()){
            rtt = cursor.getString(2);
            rtt2 = cursor.getString(3);
        }

        button.setText(rtt);
        text.setText(rtt2);

        Helper = new Helper(this);
        Helper.loadBn((RelativeLayout) findViewById(R.id.adView));
        Helper.loadIn();

    }

    public void click(View view){

        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));

    }

    public void click2(View view){

        go();

    }

    public void go(){


        int count = ++_clicks;

        if (count == 1)

        {

            Toast.makeText(this, R.string.toast_text, Toast.LENGTH_LONG).show();
        }


        if (count == 2) {

            if(Helper.getmI().isLoaded()) {

                Helper.getmI().show();

                Helper.getmI().setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {

                        startActivity(new Intent(rateActivity.this, LoadingActivity.class));
                    }
                });
            }
            else {
                startActivity(new Intent(rateActivity.this, LoadingActivity.class));
            }


        }


    }
}
