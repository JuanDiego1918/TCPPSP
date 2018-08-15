package com.example.juan.tcppsp.principal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportActionModeWrapper;

import com.example.juan.tcppsp.R;
import com.example.juan.tcppsp.Utilidades.AllFrgaments;
import com.example.juan.tcppsp.fragments.DefectLog;
import com.example.juan.tcppsp.fragments.TimeLog;

public class Tercera extends AppCompatActivity implements AllFrgaments {


    android.support.v4.app.Fragment miFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);
        Bundle miBundle = this.getIntent().getBundleExtra("dato");

        switch (miBundle.getInt("pantalla")){

            case 1:
                miFragment = new DefectLog();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorTercera,miFragment).commit();
                break;

            case 2:
                miFragment = new TimeLog();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorTercera,miFragment).commit();
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
