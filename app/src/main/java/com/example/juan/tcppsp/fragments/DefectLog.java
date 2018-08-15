package com.example.juan.tcppsp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.juan.tcppsp.R;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.DataFormatException;

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

    View view;
    Chronometer tiempo;
    Button start, stop, restart;
    boolean inciar, despausa;
    long dato;

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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_defect_log, container, false);
        tiempo = view.findViewById(R.id.tiempoChro);
        start = view.findViewById(R.id.star);
        stop = view.findViewById(R.id.stop);
        restart = view.findViewById(R.id.restart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoStar();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoStop();
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRestart();
            }
        });
        return view;
    }

    private void timeRestart() {
        tiempo.setBase(SystemClock.elapsedRealtime());
        tiempo.stop();
        inciar=false;
    }

    private void tiempoStop() {
        if (inciar==true) {
            tiempo.stop();
            dato = ((SystemClock.elapsedRealtime() - tiempo.getBase()) / 1000);
            if (dato > 60) {
                dato = dato / 60;
            }
            Toast.makeText(getContext(), "" + dato, Toast.LENGTH_SHORT).show();
            inciar=false;
        }else {
            Toast.makeText(getContext(), "Primero inicie el tiempo", Toast.LENGTH_SHORT).show();
        }
    }

    private void tiempoStar() {
        inciar=true;
        int stoppedMilliseconds = 0;
        String chronoText = tiempo.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 *
                    1000
                    + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60
                    * 1000
                    + Integer.parseInt(array[1]) * 60 * 1000
                    + Integer.parseInt(array[2]) * 1000;
        }

        tiempo.setBase(SystemClock.elapsedRealtime() -
                stoppedMilliseconds);
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
