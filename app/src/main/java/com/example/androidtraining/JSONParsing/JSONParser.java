package com.example.androidtraining.JSONParsing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.GridView;

import com.example.androidtraining.R;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONParser extends AsyncTask<String, String, Void> {

    JSONParsingAdapter adapter;

    //1. arraylist to store details that I'm fetching
    public List<FoodItems> foodDetails = new ArrayList<>();

    //2. JSON array containing entire json data
    JSONArray jsonArray;

    //3. string variables to store the final json data converted
    String result = "";

    //4. declare context and activity
    Activity activity;
    Context context;

    //5. read stream of data from the url
    BufferedInputStream bufferedInputStream;

    //6. for showing the progress before dynamic data is fetched and updated the UI
    public ProgressDialog progressDialog;

    //7. make a constructor
    public JSONParser(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;

        this.progressDialog = new ProgressDialog(this.context);
    }

    //8. generating override methods onPre and onPost
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // if there're any progress bars on screen - dismiss them
        progressDialog.dismiss();

        progressDialog.setMessage("Loading Page...");

        progressDialog.show();

        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                JSONParser.this.cancel(true);
            }
        });
    }

    @Override
    protected void onPostExecute(Void unused) {

        try {
            jsonArray = new JSONArray(result);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    FoodItems foodItems = new FoodItems();

                    foodItems.id = jsonArray.getJSONObject(i).getInt("ProductID");
                    foodItems.name = jsonArray.getJSONObject(i).getString("ProductName");
                    foodItems.price = jsonArray.getJSONObject(i).getString("UnitPrice");
                    foodItems.imageurl = DataURL.imagedata + foodItems.id +".jpg";
                    foodDetails.add(foodItems);
                }
            }

            GridView gridView;
            gridView = (GridView) this.activity.findViewById(R.id.listview);
            //gridView = (GridView) this.activity.findViewById(R.id.listview);
            adapter = new JSONParsingAdapter(this.context, foodDetails);
            gridView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.progressDialog.dismiss();
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;

        try {

            //pass the url inside this instance
            URL url = new URL(DataURL.fetchdata);
            //open the webpage/url/json file on server
            httpURLConnection = (HttpURLConnection) url.openConnection();

            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            result = readStream();

            result = result.substring
                    (result.indexOf("(") + 1, result.lastIndexOf(")"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //readStream -> logic of reading the json file from the url
    //in form of bytes, line, appending lines
    private String readStream() throws IOException {
        //1. inputstream -> stream of bytes -> characters -> line -> data
        //2. inside the buffered reader, pass this stream of bytes
        //3. made a string builder to append each line of data
        //4. a string variabled named line which will be append to stringbuilder
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
