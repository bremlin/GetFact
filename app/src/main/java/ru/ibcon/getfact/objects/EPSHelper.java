package ru.ibcon.getfact.objects;

import android.util.Log;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class EPSHelper extends HashMap<Integer, SFEPS> {

    private static final String TAG = "EPSHelper";

    public static String ID = "id";
    public static String NAME = "name";
    public static String CODE = "code";
    public static String PARENT_ID = "parent_id";
    public static String DB_NAME = "db_name";
    public static String SEQUENCE = "seq_num";

    public EPSHelper(Response response) {
        try {
            JSONArray jsonArray = new JSONArray(response.body().string());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                SFEPS eps = new SFEPS(object);
                this.put(eps.getId(), eps);
                Log.d(TAG, "ProjectHelper() called with: response = [" + eps.toString() + "]");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
