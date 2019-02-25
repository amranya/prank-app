package app.survive.inc.app1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;

public class ActivationPackActivity extends AppCompatActivity {


    Helper Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_gender);

        Helper = new Helper(this);
        Helper.loadBn((RelativeLayout) findViewById(R.id.adView));
        Helper.loadIn();
    }

    public void click(View view){


      go();


    }

    public void check(){

        MyDatabase db = new MyDatabase(getApplicationContext());
        SQLiteDatabase sdb = db.getWritableDatabase();

        Cursor cursor = sdb.rawQuery("SELECT * FROM db", null);

        String rtt = "";
        while (cursor.moveToNext()){
            rtt = cursor.getString(0);
        }

        Log.d("rtt", rtt);

        if(Integer.parseInt(rtt)==0){

            startActivity(new Intent(ActivationPackActivity.this, LoadingActivity.class));
        }

        else startActivity(new Intent(ActivationPackActivity.this, rateActivity.class));
    }

    public void go(){

        if(Helper.getmI().isLoaded()) {

            Helper.getmI().show();

            Helper.getmI().setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {

                    check();
                }
            });
        }
        else {
            check();
        }

    }

}
