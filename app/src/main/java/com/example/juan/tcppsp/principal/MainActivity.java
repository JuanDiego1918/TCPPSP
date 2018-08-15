package com.example.juan.tcppsp.principal;

import android.content.ContentValues;
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
import com.example.juan.tcppsp.Utilidades.conexion;
import com.example.juan.tcppsp.Utilidades.proyectosVo;
import com.example.juan.tcppsp.adapter.proyectosAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText tiempoDemora, nombreProyecto;
    Button registrar;
    RecyclerView recyclerViewProyectos;
    conexion conn;
    SQLiteDatabase bd;
    int numeroProyectos = 1;
    String nombreP, tiempoD;
    ArrayList<proyectosVo> listaProyectos;
    proyectosVo miProyectosVo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new conexion(this, "proyectos", null, 1);
        listaProyectos=new ArrayList<>();

        tiempoDemora = findViewById(R.id.campoTiempoProyecto);
        nombreProyecto = findViewById(R.id.campoNombreProyecto);
        registrar = findViewById(R.id.btnRegistrarProyectos);
        recyclerViewProyectos = findViewById(R.id.recyclerProyectos);

        proyectosAdapter proyectosAdapter=new proyectosAdapter(listaProyectos);

        recyclerViewProyectos.setAdapter(proyectosAdapter);

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
            values.put(Utilidades.CAMPO_NOMBRE_PROYECTO, tiempoD);
            values.put(Utilidades.CAMPO_TIEMPO, tiempoD);
            values.put(Utilidades.CAMPO_ID, numeroProyectos);

            long registroExitoso = bd.insert(Utilidades.NOMBRE_TABLA_PROYECTOS, Utilidades.CAMPO_ID, values);
            if (registroExitoso == 1) {
                Toast.makeText(getApplicationContext(), "Registro Exitoso ", Toast.LENGTH_SHORT).show();
                numeroProyectos++;
                consultarProyecto();
            } else {
                Toast.makeText(getApplicationContext(), "Registro No Exitoso ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void consultarProyecto() {
        bd=conn.getReadableDatabase();

        Cursor cursor=bd.rawQuery("SELECT * FROM "+Utilidades.NOMBRE_TABLA_PROYECTOS,null);

        while (cursor.moveToNext()){
            miProyectosVo=new proyectosVo();
            miProyectosVo.setNombre(cursor.getString(0));
            listaProyectos.add(miProyectosVo);
        }

    }
}
