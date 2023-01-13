package com.example.androidtraining.Retrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtraining.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    TextView textView;
    String  dataURL = "https://jsonplaceholder.typicode.com/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        textView = (TextView) findViewById(R.id.textJson);
        textView.setText("");

        //1. create a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(dataURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        //2. converting JSON data to model class object
        //convert JSON data to MyApi type
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        //3. create a call of model class
        Call<List<RetrofitModelClass>> call = retrofitAPI.getModels();

        //processing the data and receive the response
        call.enqueue(new Callback<List<RetrofitModelClass>>() {
            @Override
            public void onResponse(Call<List<RetrofitModelClass>> call, Response<List<RetrofitModelClass>> response) {
                List<RetrofitModelClass> data = response.body();

                for (int i = 0; i < data.size(); i++) {
                    textView.append("SL no" + data.get(i).getID()+ "" + " \n Title:"
                    + data.get(i).getTittle() + "\n\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitModelClass>> call, Throwable t) {

            }
        });
    }
}