package ru.ibcon.getfact;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.ibcon.getfact.objects.EPSHelper;
import ru.ibcon.getfact.objects.ProjectHelper;

import java.io.IOException;

public class LoadProjectActivity extends AppCompatActivity {

    public String url = "http://getfact.ibcon.ru:8080/FactSM-1.0/servlets/index";
    public String eps = "http://getfact.ibcon.ru:8080/FactSM-1.0/servlets/epsgetter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_project);
    }

    private void loadData() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(eps)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    EPSHelper epsHelper = new EPSHelper(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    ProjectHelper projects = new ProjectHelper(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
