package app.survive.inc.app1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoadingActivity.this, ErrorNetActivity.class));
                            finish();
                        }
                    });
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }

            }
        }, 10000); // 200 milliseconds delay
    }
}
