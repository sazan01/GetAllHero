package com.example.getallheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import Model.Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class MainActivity extends AppCompatActivity {
    private ImageView imgProfile;
    private EditText etName, etDesc;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= findViewById(R.id.etName);
        etDesc= findViewById(R.id.etDesc);
        btnSave= findViewById(R.id.btnSave);

        imgProfile= findViewById(R.id.imgProfile);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }

            private void Save() {
                String name= etName.getText().toString();
                String desc= etDesc.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Url.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

               HeroInterface heroInterface = retrofit.create(HeroInterface.class);


                Call<Void> heroesCall = heroInterface.addHeroes(name,desc);

                heroesCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Code" + response.code(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




    }
}
