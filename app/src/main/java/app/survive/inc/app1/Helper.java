package app.survive.inc.app1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by iyadz_000 on 9/5/2018.
 */

public class Helper {

    InterstitialAd mI;
    AdRequest adRequest;
    private Context context;
    private String bn = "";
    private String it = "";
    private int a;

    public Helper(Context context){

        this.context = context;
        MyDatabase db = new MyDatabase(this.context);
        SQLiteDatabase sdb = db.getWritableDatabase();

        Cursor cursor = sdb.rawQuery("SELECT * FROM db", null);

        while (cursor.moveToNext()){
            a = Integer.parseInt(cursor.getString(1));
        }

            bn = context.getString(R.string.bn);
            it = context.getString(R.string.it);


    }

    public void loadBn(RelativeLayout layView){

        if(a==1) {
            AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.SMART_BANNER);
            mAdView.setAdUnitId(bn);
            RelativeLayout relativeLayout =  layView;
            relativeLayout.addView(mAdView);
            adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }

    public void loadIn(){


        mI = new InterstitialAd(context);
        mI.setAdUnitId(it);
        if(a==1){
        adRequest = new AdRequest.Builder()
                .build();
        this.mI.loadAd(adRequest);}
    }


    public InterstitialAd getmI() {
        return mI;
    }
}

