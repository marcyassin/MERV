package cayenne.team.merv.Main.Tabs.JobList;



import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import cayenne.team.merv.Main.Tabs.Tasks.TabTasks;
import cayenne.team.merv.R;

public class TabJobs extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final ListView listView2 = (ListView) getView().findViewById(R.id.listView2);

        super.onCreate(savedInstanceState);
        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView2.setAdapter(adapter);

        URL url;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        try {
            url = new URL("http://130.64.149.225:8000/tasks/tasklist");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Bearer hQOFR5VuI7ZuYv1XiJYwqqvJGmGlHm");
            in = urlConnection.getInputStream();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }



        try{
            JSONArray tasks = new JSONArray(convertToJSON(in));
            //Loop the Array
            for(int i=0;i < tasks.length();i++) {
                JSONObject obj=new JSONObject(tasks.get(i).toString());
                System.out.println(obj.getString("id"));
                adapter.add("ID:" + obj.getString("id"));
                adapter.notifyDataSetChanged();

            }

        } catch(JSONException e) {
            Log.e("log_tag", "Error parsing data "+e.toString());
        }
    }

    public static String convertToJSON(InputStream in) {
        //convert response to string
        String inputStr="";
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();


            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            return inputStr;
        } catch(Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        return inputStr;
    }

}
