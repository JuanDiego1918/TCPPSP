package com.example.juan.tcppsp.principal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.juan.tcppsp.R;
import com.example.juan.tcppsp.Utilidades.Proyecto;

public class Secundaria extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        Bundle miBundle=getIntent().getBundleExtra("proyecto");

        if (miBundle!=null){
            Proyecto.proyecto=miBundle.getString("nombre");
            Proyecto.id=miBundle.getInt("id");
            Toast.makeText(getApplicationContext(),Proyecto.id+ " "+Proyecto.proyecto,Toast.LENGTH_SHORT).show();
        }


    }

    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),Tercera.class);
        Bundle miBundle = new Bundle();

        switch (view.getId()){
            case R.id.btnProyectPlanSumary:
                Intent intent2 = new Intent(getApplicationContext(),Tabbed.class);
                startActivity(intent2);
                break;

            case R.id.btnDefectLog:
                miBundle.putInt("pantalla",1);
                intent.putExtra("dato",miBundle);
                startActivity(intent);
                break;

            case R.id.btnTimeLog:
                miBundle.putInt("pantalla",2);
                intent.putExtra("dato",miBundle);
                startActivity(intent);
                break;
        }
    }
}
