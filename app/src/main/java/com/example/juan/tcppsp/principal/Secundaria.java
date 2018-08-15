package com.example.juan.tcppsp.principal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.juan.tcppsp.R;

public class Secundaria extends AppCompatActivity {

    int idProyecto;
    String nombreProyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        Bundle miBundle=getIntent().getBundleExtra("proyecto");
        if (miBundle!=null){
            idProyecto=miBundle.getInt("id");
            nombreProyecto=miBundle.getString("nombre");
        }
        Toast.makeText(getApplicationContext(),idProyecto+""+nombreProyecto,Toast.LENGTH_SHORT).show();
    }
}
