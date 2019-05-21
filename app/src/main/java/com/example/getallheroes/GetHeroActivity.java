package com.example.getallheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class GetHeroActivity extends AppCompatActivity {
    private ImageView imgProfile;
    private EditText etName, etDesc;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_hero);

        imgProfile = findViewById(R.id.imgProfile);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);

        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }

            private void Save() {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();

                Map<String,String> map = new HashMap<>();
                map.put("name",name);
                map.put("desc",desc);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Url.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                HeroInterface heroInterface = retrofit.create(HeroInterface.class);

                Call<Void> heroesCall = heroInterface.addHero(map);

                heroesCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(GetHeroActivity.this,"Code" + response.code(),Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(GetHeroActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(GetHeroActivity.this, "Error" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });






    }
}
