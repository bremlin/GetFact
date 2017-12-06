package ru.ibcon.getfact.objects;

import android.util.Log;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectHelper extends ArrayList<SFProject> {

    private static final String TAG = "ProjectHelper";

    private String TABLE = "project";

    public static String PROJ_ID = "objectId";
    public static String PARENT_ID = "parentId";
    public static String WBS_ID = "wbsId";
    public static String NAME = "name";
    public static String ID = "id";

    private String OBJECT_ID = "object_id";

    public ProjectHelper(Response response) {
        try {
//            String jsonData = response.body().toString();
//            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONArray jsonArray = new JSONArray(response.body().string());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                SFProject sfProject = new SFProject(object);
                this.add(sfProject);
                Log.d(TAG, "ProjectHelper() called with: response = [" + sfProject.toString() + "]");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
