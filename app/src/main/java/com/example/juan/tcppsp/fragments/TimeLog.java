package com.example.juan.tcppsp.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juan.tcppsp.R;
import com.example.juan.tcppsp.Utilidades.Conexion;
import com.example.juan.tcppsp.Utilidades.Proyecto;
import com.example.juan.tcppsp.Utilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeLog.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimeLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeLog extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Conexion conn;
    SQLiteDatabase db;
    String seleccionaPhase;
    Button btnStart, btnStop,btnRegistrar;

    EditText campoStart,campoInterruption,CampoStop,campoComments,campoDelta;

    String fecha1,fecha2;
    String phase;
    Spinner listaPhase;
    ArrayList arrayPhase;
    public TimeLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimeLog.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeLog newInstance(String param1, String param2) {
        TimeLog fragment = new TimeLog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_time_log, container, false);


        conn = new Conexion(getContext(), "proyectos", null, 1);
        campoStart = vista.findViewById(R.id.campoStartTime);
        CampoStop = vista.findViewById(R.id.campoStopTime);
        campoDelta = vista.findViewById(R.id.campoDeltaTime);
        campoComments = vista.findViewById(R.id.campoCommentsTime);
        campoInterruption = vista.findViewById(R.id.campoInterruptionTime);

        arrayPhase = new ArrayList();
        arrayPhase.add("Phase");
        arrayPhase.add("PLAN");
        arrayPhase.add("DLD");
        arrayPhase.add("CODE");
        arrayPhase.add("COMPILE");
        arrayPhase.add("UT");
        arrayPhase.add("PM");


        listaPhase = vista.findViewById(R.id.spinnerPhaseTime);
        ArrayAdapter<CharSequence> adapterType = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayPhase);
        listaPhase.setAdapter(adapterType);
        listaPhase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    seleccionaPhase = arrayPhase.get(position).toString();
                    Toast.makeText(getContext(),"Type " + seleccionaPhase,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnStart = vista.findViewById(R.id.btnStartTime);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarFechaCampo1();
            }
        });

        btnStop = vista.findViewById(R.id.btnStopTime);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarFechaCampo2();
            }
        });

        btnRegistrar = vista.findViewById(R.id.btnRegistrarTime);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarTimeLog();
            }
        });

        return vista;
    }

    private void asignarFechaCampo1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        fecha1 = dateFormat.format(date);
        campoStart.setText(fecha1);
    }

    private void asignarFechaCampo2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        fecha2 = dateFormat.format(date);
        CampoStop.setText(fecha2);
    }

    private void registrarTimeLog() {
        db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (campoStart.getText().equals("") || CampoStop.equals("")) {
            Toast.makeText(getContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            values.put(Utilidades.CAMPO_PHASE, seleccionaPhase);
            values.put(Utilidades.CAMPO_START, fecha1);
            values.put(Utilidades.CAMPO_STOP, fecha2);
            values.put(Utilidades.CAMPO_DELTA, campoDelta.getText().toString());
            values.put(Utilidades.CAMPO_COMMENTS,campoComments.getText().toString());
            values.put(Utilidades.CAMPO_INTERRUPTION, campoInterruption.getText().toString());
            values.put(Utilidades.CAMPO_ID_TIME, Proyecto.id);
            long registroExitoso = db.insert(Utilidades.NOMBRE_TABLA, Utilidades.CAMPO_ID_TIME, values);
            Toast.makeText(getContext(), "Registro Exitoso ", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
