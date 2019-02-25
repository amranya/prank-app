package app.survive.inc.app1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;

public class choosePasswordActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;

    Helper Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_password);

        editText = (EditText) findViewById(R.id.edtName);
        editText2 = (EditText) findViewById(R.id.edtName2);

        Helper = new Helper(this);
        Helper.loadBn((RelativeLayout) findViewById(R.id.adView));
        Helper.loadIn();
    }

    public void click(View view){


        if(editText.getText().toString().equals("")){

            new AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("Please enter a password")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    }).show();

        }

        else if(editText2.getText().toString().equals("")&& !editText.getText().toString().equals("")){

            new AlertDialog.Builder(this)
                    .setTitle(R.string.Error_activity3)
                    .setMessage(R.string.Error_text1)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    }).show();

        }
        else if(!editText.getText().toString().equals(editText2.getText().toString())){

            new AlertDialog.Builder(this)
                    .setTitle(R.string.Error_activity3)
                    .setMessage(R.string.Error_text2)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    }).show();
        }

        else {

                go();
        }
    }

    public void go(){

        if(Helper.getmI().isLoaded()) {

            Helper.getmI().show();

            Helper.getmI().setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {

                    startActivity(new Intent(choosePasswordActivity.this, CountryPickActivity.class));
                }
            });
        }
        else {
            startActivity(new Intent(choosePasswordActivity.this, CountryPickActivity.class));
        }

    }
}
