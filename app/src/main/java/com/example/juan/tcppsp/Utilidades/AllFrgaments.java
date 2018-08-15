package com.example.juan.tcppsp.Utilidades;

import com.example.juan.tcppsp.fragments.DefectLog;
import com.example.juan.tcppsp.fragments.TimeLog;
import com.example.juan.tcppsp.fragments_tabed.DefectsInjectedInPhase;
import com.example.juan.tcppsp.fragments_tabed.DefectsRemoveInPhase;
import com.example.juan.tcppsp.fragments_tabed.TimeInPhase;

public interface AllFrgaments extends
        DefectLog.OnFragmentInteractionListener,
        TimeLog.OnFragmentInteractionListener,
        DefectsRemoveInPhase.OnFragmentInteractionListener,
        DefectsInjectedInPhase.OnFragmentInteractionListener,
        TimeInPhase.OnFragmentInteractionListener {
}
