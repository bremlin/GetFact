package ru.ibcon.getfact.selectproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.ibcon.getfact.R;
import ru.ibcon.getfact.objects.EPSHelper;

import java.io.IOException;
import java.util.ArrayList;

public class SelectProject extends AppCompatActivity {
    private static final String TAG = "AppCompatActivity";

    private String eps = "http://getfact.ibcon.ru:8080/FactSM-1.0/epsgetter";

    private EPSHelper epsHelper;


    private ListView projectView;
    private ArrayList<SelectItem> items = new ArrayList<>();
    private ProjectEPSAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        epsHelper = new EPSHelper();
        setContentView(R.layout.activity_select_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                projectView = (ListView)findViewById(R.id.listView1);

                ArrayList<SelectItem> selectItems = epsHelper.getTitulList();
                Log.d(TAG, "onCreate: " + selectItems.size());
                adapter.setData(selectItems);
                adapter.notifyDataSetChanged();
            }
        });

        projectView = (ListView)findViewById(R.id.listView1);

        ArrayList<SelectItem> selectItems = epsHelper.getTitulList();
        Log.d(TAG, "onCreate: " + selectItems.size());
        adapter = new ProjectEPSAdapter(this, selectItems);
//        projectView.setOnItemClickListener((parent, view, position) -> {
//            Toast.makeText(SelectProject.this, "click: " + position, Toast.LENGTH_SHORT).show();
//        });

//        SelectAdapter selectAdapter = new SelectAdapter(SelectProject.this, 0, selectItems);
//        ArrayAdapter<SelectItem> adapter = new ArrayAdapter<SelectItem>(this, R.id.listView1, selectItems);

        projectView.setAdapter(adapter);


//// определяем массив типа String
//        final String[] catNames = new String[] {
//                "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
//                "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
//                "Китти", "Масяня", "Симба"
//        };
//
//// используем адаптер данных
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, catNames);
//
//        listView.setAdapter(adapter);


    }

    public String getHttpResponse() throws IOException {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(eps)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
//                    Log.d(TAG, "doInBackground() called with: voids = [" + response.body().string() + "]");
                    epsHelper.setData(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return null;
    }
}
