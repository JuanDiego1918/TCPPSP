package com.example.juan.tcppsp.principal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juan.tcppsp.R;
import com.example.juan.tcppsp.Utilidades.Utilidades;
import com.example.juan.tcppsp.Utilidades.Conexion;
import com.example.juan.tcppsp.Utilidades.proyectosVo;
import com.example.juan.tcppsp.adapter.proyectosAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText tiempoDemora, nombreProyecto;
    Button registrar;
    RecyclerView recyclerViewProyectos;
    Conexion conn;
    SQLiteDatabase bd;
    int numeroProyectos = 1;
    String nombreP, tiempoD;
    ArrayList<proyectosVo> listaProyectos;
    proyectosVo miProyectosVo;
    proyectosAdapter proyectosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new Conexion(this, "proyectos", null, 1);


        tiempoDemora = findViewById(R.id.campoTiempoProyecto);
        nombreProyecto = findViewById(R.id.campoNombreProyecto);
        registrar = findViewById(R.id.btnRegistrarProyectos);
        recyclerViewProyectos = findViewById(R.id.recyclerProyectos);

        consultarProyecto();
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProyecto();
            }
        });

    }

    private void registrarProyecto() {
        bd = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        nombreP = nombreProyecto.getText().toString();
        tiempoD = tiempoDemora.getText().toString();
        if (nombreP.equals("") || tiempoD.equals("")) {
            Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            values.put(Utilidades.CAMPO_NOMBRE_PROYECTO, nombreP);
            values.put(Utilidades.CAMPO_TIEMPO, tiempoD);
            values.put(Utilidades.CAMPO_ID, numeroProyectos);

            long registroExitoso = bd.insert(Utilidades.NOMBRE_TABLA_PROYECTOS, Utilidades.CAMPO_ID, values);

            Toast.makeText(getApplicationContext(), "Registro Exitoso ", Toast.LENGTH_SHORT).show();
            numeroProyectos++;
            consultarProyecto();
            nombreProyecto.setText(null);
            tiempoDemora.setText(null);
            nombreP="";
            tiempoD="";

        }
    }

    private void consultarProyecto() {
        listaProyectos = new ArrayList<>();
        bd = conn.getReadableDatabase();

//opaskd
        Cursor cursor = bd.rawQuery("SELECT * FROM " + Utilidades.NOMBRE_TABLA_PROYECTOS, null);

        while (cursor.moveToNext()) {
            miProyectosVo = new proyectosVo();
            miProyectosVo.setNombre(cursor.getString(0));
            miProyectosVo.setId(cursor.getInt(1));
            numeroProyectos= cursor.getInt(1)+1;
            listaProyectos.add(miProyectosVo);
        }

        proyectosAdapter = new proyectosAdapter(listaProyectos);
        recyclerViewProyectos.setAdapter(proyectosAdapter);

        proyectosAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent=new Intent(MainActivity.this,Secundaria.class);
                Bundle miBundle=new Bundle();
                miBundle.putInt("id",listaProyectos.get(recyclerViewProyectos.getChildAdapterPosition(v)).getId());
                miBundle.putString("nombre",listaProyectos.get(recyclerViewProyectos.getChildAdapterPosition(v)).getNombre());

                miIntent.putExtra("proyecto",miBundle);

                startActivity(miIntent);
            }
        });
    }
}
