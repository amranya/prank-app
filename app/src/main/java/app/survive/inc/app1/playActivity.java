package app.survive.inc.app1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;

public class playActivity extends AppCompatActivity {


    Helper Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        Helper = new Helper(this);
        Helper.loadBn((RelativeLayout) findViewById(R.id.adView));
        Helper.loadIn();
    }



    public void click(View view){


        go();


    }

    public void click2(View view){

        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.privacyLink))));

    }

    public void go(){

        if(Helper.getmI().isLoaded()) {

            Helper.getmI().show();

            Helper.getmI().setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {

                    startActivity(new Intent(playActivity.this, NameInputActivity.class));
                }
            });
        }
        else {
            startActivity(new Intent(playActivity.this, NameInputActivity.class));
        }


    }

}
