package com.example.myvolleykiraz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnjsonDers, btnDersListesi, btnResim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnjsonDers= (Button) findViewById(R.id.btnjsonDers);
        btnDersListesi= (Button) findViewById(R.id.btnDersListesi);
        btnResim= (Button) findViewById(R.id.btnResim);

        btnjsonDers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DersActivity.class));
            }
        });

        btnDersListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DersListesiActivity.class));
            }
        });

        btnResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResimActivity.class));
            }
        });
    }
}
