package ru.ibcon.getfact.objects;

import android.util.Log;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.ibcon.getfact.selectproject.SelectItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EPSHelper extends HashMap<Integer, SFEPS> {

    private ArrayList<SFEPS> titulList = new ArrayList<>();

    private String eps = "http://getfact.ibcon.ru:8080/FactSM-1.0/epsgetter";


    private static final String TAG = "EPSHelper";

    public static String ID = "id";
    public static String NAME = "name";
    public static String CODE = "code";
    public static String PARENT_ID = "parentId";
    public static String DB_NAME = "dbName";
    public static String SEQUENCE = "sequenceNumber";


    public EPSHelper() {
        getMakeApiCall();
    }

    public void setData(String jsonString) {
        try {
            Log.d(TAG, "setData() called " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                SFEPS eps = new SFEPS(object);
                this.put(eps.getId(), eps);
                if (eps.getParentId() == 0) titulList.add(eps);
                Log.d(TAG, "EPSHelper() called with: response = [" + eps.toString() + "]");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getMakeApiCall() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(eps)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
//                        Log.v(TAG, "Check Log " + response.body().string());
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData.toString());
                        if (response.isSuccessful()) {
                            setData(jsonData);
                        } else {
                            Log.v(TAG, "Exception caught");
                        }
                    } catch (IOException e) {
                        Log.v(TAG, "Exception caught : ", e);
                    }
                }

            });

            Log.d(TAG, "Main thread is running");
        }

    public ArrayList<SelectItem> getTitulList() {
        ArrayList<SelectItem> itemList = new ArrayList<>();
        for (SFEPS sfeps : titulList) {
            itemList.add(new SelectItem(sfeps));
        }
        return itemList;
    }
}
