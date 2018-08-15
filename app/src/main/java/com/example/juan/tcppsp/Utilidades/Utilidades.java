package com.example.juan.tcppsp.Utilidades;

public class Utilidades {


    public static final String NOMBRE_TABLA = "time_log";
    public static final String CAMPO_PHASE = "phase";
    public static final String CAMPO_START = "start";
    public static final String CAMPO_INTERRUPTION = "interruption";
    public static final String CAMPO_STOP = "stop";
    public static final String CAMPO_DELTA = "delta";
    public static final String CAMPO_COMMENTS = "comments";

    public static final String CREAR_TABLA_TIME = "CREATE TABLE " + NOMBRE_TABLA + " ( "
            + CAMPO_PHASE + " VARCHAR, "
            + CAMPO_START + " INT ," + CAMPO_INTERRUPTION + " VARCHAR," + CAMPO_STOP + " INT,"
            + CAMPO_DELTA + " INT" + CAMPO_COMMENTS + " VARHCAR)";
}
