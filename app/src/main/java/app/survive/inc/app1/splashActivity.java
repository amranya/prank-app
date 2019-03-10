package app.survive.inc.app1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.onesignal.OneSignal;


import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class splashActivity extends AppCompatActivity {

    final String LOGCAT_TAG = "Clima";
    String lk;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        lk = getString(R.string.jsonLink);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

    }

    private void goNext(){


        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MyDatabase db = new MyDatabase(getApplicationContext());
                            SQLiteDatabase sdb = db.getWritableDatabase();

                            Cursor cursor = sdb.rawQuery("SELECT * FROM db", null);

                            String rtt = "";
                            while (cursor.moveToNext()){
                                rtt = cursor.getString(4);
                            }

                            Log.d("rtt", rtt);

                            if(rtt.isEmpty()){

                                startActivity(new Intent(splashActivity.this, playActivity.class));
                            }

                            else startActivity(new Intent(splashActivity.this, updateActivity.class));
                        }
                    });
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }

            }
        }, 10000); // 200 milliseconds delay



    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(ConnectivityReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(ConnectivityReceiver);
    }

    private BroadcastReceiver ConnectivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(!isNetworkAvailable(context)){

                Toast.makeText(context, "disconnected", Toast.LENGTH_SHORT).show();

            }

            else {


                Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();

                start();

            }
        }
    };

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    // This is the actual networking code. Parameters are already configured.
    private void letsDoSomeNetworking() {

        // AsyncHttpClient belongs to the loopj dependency.
        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

        // Making an HTTP GET request by providing a URL.
        client.get(lk, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d(LOGCAT_TAG, "Success! JSON: " + response.toString());


                data dat = data.fromJson(response);

                MyDatabase db = new MyDatabase(getApplicationContext());
                SQLiteDatabase sdb = db.getWritableDatabase();

                sdb.execSQL("UPDATE db SET updatelink='"+ dat.getLinkupdate()+"' WHERE id=0 ");
                sdb.execSQL("UPDATE db SET admob='"+ dat.getAdmob()+"' WHERE id=0 ");
                sdb.execSQL("UPDATE db SET morelink='"+ dat.moreLink()+"' WHERE id=0 ");
                sdb.execSQL("UPDATE db SET rate='"+ dat.getRate()+"' WHERE id=0 ");
                sdb.execSQL("UPDATE db SET ratebutton='"+ dat.getRatebutton()+"' WHERE id=0 ");
                sdb.execSQL("UPDATE db SET ratetext='"+ dat.getRatetext()+"' WHERE id=0 ");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {

                Log.e(LOGCAT_TAG, "Fail " + e.toString());

            }

        });
    }

    public void start (){

        textView.setVisibility(View.INVISIBLE);
        textView2.setText(R.string.connecting);
        letsDoSomeNetworking();
        goNext();




    }

}
