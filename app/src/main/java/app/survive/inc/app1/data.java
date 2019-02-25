package app.survive.inc.app1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by iyadz_000 on 8/30/2018.
 */

public class data {


    private int rate;
    private int admob;
    private String morelink;
    private String ratebutton = "work bitch";
    private String ratetext;
    private String linkupdate;



    // Create a data from a JSON.
    // We will call this instead of the standard constructor.
    public static data fromJson(JSONObject jsonObject) {

        // JSON parsing is risky business. Need to surround the parsing code with a try-catch block.
        try {

            data dat = new data();

            dat.rate = jsonObject.getInt("rate");
            dat.admob = jsonObject.getInt("admob");
            dat.morelink = jsonObject.getString("morelink");
            dat.ratebutton = jsonObject.getString("ratebutton");
            dat.ratetext = jsonObject.getString("ratetext");
            dat.linkupdate = jsonObject.getString("linkupdate");

            return dat;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Getter methods for temperature, city, and icon name:

    public int getRate() {
        return rate;
    }

    public int getAdmob() {
        return admob;
    }

    public String moreLink() {
        return morelink;
    }

    public String getRatebutton() {
        return ratebutton;
    }

    public String getRatetext() {
        return ratetext;
    }

    public String getLinkupdate() {
        return linkupdate;
    }

}

