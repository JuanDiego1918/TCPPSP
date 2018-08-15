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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juan.tcppsp.R;
import com.example.juan.tcppsp.Utilidades.Conexion;
import com.example.juan.tcppsp.Utilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DefectLog.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DefectLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefectLog extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public DefectLog() {
        // Required empty public constructor
    }
    Conexion conn;
    SQLiteDatabase db;
    ////////////////////////////////
    String seleccionaType;
    String seleccionaInjected;
    String fecha;
    String seleccionaRemoved;
    String defectDecriptionR;
    ////////////////////////////////
    EditText campoDate,campoDefectDescription;
    ////////////////////////////////
    View view;
    Chronometer tiempo;
    Button start, stop, restart,btnRegistrar,btnDate;
    ////////////////////////////////
    Spinner listaType;
    Spinner listaInjected;
    Spinner listaRemoved;
    ArrayList arrayType;
    ArrayList arrayInjected;
    ArrayList arrayRemoved;
    ////////////////////////////////

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DefectLog.
     */
    // TODO: Rename and change types and number of parameters
    public static DefectLog newInstance(String param1, String param2) {
        DefectLog fragment = new DefectLog();
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

        view = inflater.inflate(R.layout.fragment_defect_log, container, false);
        conn = new Conexion(getContext(), "proyectos", null, 1);

        arrayType = new ArrayList();
        arrayType.add("Type");
        arrayType.add("Documentation");
        arrayType.add("Syntax");
        arrayType.add("Build");
        arrayType.add("Package");
        arrayType.add("Assigment");
        arrayType.add("Interface");
        arrayType.add("Checking");
        arrayType.add("Function");
        arrayType.add("System");
        arrayType.add("Environment");

        arrayInjected = new ArrayList();
        arrayInjected.add("Phase injected");
        arrayInjected.add("PLAN");
        arrayInjected.add("DLD");
        arrayInjected.add("CODE");
        arrayInjected.add("COMPILE");
        arrayInjected.add("UT");
        arrayInjected.add("PM");

        arrayRemoved = new ArrayList();
        arrayRemoved.add("Phase removed");
        arrayRemoved.add("PLAN");
        arrayRemoved.add("DLD");
        arrayRemoved.add("CODE");
        arrayRemoved.add("COMPILE");
        arrayRemoved.add("UT");
        arrayRemoved.add("PM");

        listaType = view.findViewById(R.id.spinnerTypeDefect);
        ArrayAdapter<CharSequence> adapterType = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayType);
        listaType.setAdapter(adapterType);
        listaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    seleccionaType = arrayType.get(position).toString();
                    Toast.makeText(getContext(),"Type " + seleccionaType,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listaInjected = view.findViewById(R.id.spinnerPhaseInjectedDefect);
        ArrayAdapter<CharSequence> adapterInjected = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayInjected);
        listaInjected.setAdapter(adapterInjected);
        listaInjected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    seleccionaInjected = arrayInjected.get(position).toString();
                    Toast.makeText(getContext(),"Injected " + seleccionaInjected,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listaRemoved = view.findViewById(R.id.spinnerPhaseRemovedDefect);
        ArrayAdapter<CharSequence> adapterRemove = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,arrayRemoved);
        listaRemoved.setAdapter(adapterRemove);
        listaRemoved.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position !=0){
                    seleccionaRemoved = arrayRemoved.get(position).toString();
                    Toast.makeText(getContext(),"Remove " + seleccionaRemoved,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tiempo = view.findViewById(R.id.tiempoChro);
        start = view.findViewById(R.id.star);
        stop=view.findViewById(R.id.stop);
        restart=view.findViewById(R.id.restart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoStar();
            }
        });
        campoDate = view.findViewById(R.id.campoDateDefect);
        campoDefectDescription = view.findViewById(R.id.campoDefectDescription);
        btnDate = view.findViewById(R.id.btnDateDefec);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarFechaCampo();
            }
        });
        btnRegistrar = view.findViewById(R.id.btnRegistrarDefect);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarDefecto();
            }
        });

        return view;
    }

    private void asignarFechaCampo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        fecha = dateFormat.format(date);
        campoDate.setText(fecha);
    }

    private void registrarDefecto() {
        defectDecriptionR = campoDefectDescription.getText().toString();
        db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (campoDate.getText().equals("") || campoDefectDescription.equals("")) {
            Toast.makeText(getContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            values.put(Utilidades.CAMPO_DATE, fecha);
            values.put(Utilidades.CAMPO_TYPE, seleccionaType);
            values.put(Utilidades.CAMPO_PHASE_INJECTED, seleccionaInjected);
            values.put(Utilidades.CAMPO_EXITIME, "tiepoCrono");
            values.put(Utilidades.CAMPO_DEFECT_DESCRIPTION, defectDecriptionR);

            long registroExitoso = db.insert(Utilidades.NOMBRE_TABLA_DEFECT, Utilidades.CAMPO_ID, values);
        }
    }
    private void tiempoStar() {
        tiempo.start();

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
