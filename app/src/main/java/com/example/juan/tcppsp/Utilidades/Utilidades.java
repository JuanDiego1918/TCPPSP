package com.example.juan.tcppsp.Utilidades;

public class Utilidades {


    public static final String NOMBRE_TABLA = "time_log";
    public static final String CAMPO_PHASE = "phase";
    public static final String CAMPO_START = "start";
    public static final String CAMPO_INTERRUPTION = "interruption";
    public static final String CAMPO_STOP = "stop";
    public static final String CAMPO_DELTA = "delta";
    public static final String CAMPO_COMMENTS = "comments";
    public static final String CAMPO_ID_TIME = "id";

    public static final String CREAR_TABLA_TIME = "CREATE TABLE " + NOMBRE_TABLA + " ( "
            + CAMPO_PHASE + " VARCHAR, "
            + CAMPO_START + " INT ," + CAMPO_INTERRUPTION + " VARCHAR," + CAMPO_STOP + " INT,"
            + CAMPO_DELTA + " INT" + CAMPO_COMMENTS + " VARHCAR,"+ CAMPO_ID_TIME + " INT AUTO_INCREMENT)";

    public static final String NOMBRE_TABLA_DEFECT = "defect_log";
    public static final String CAMPO_DATE = "date";
    public static final String CAMPO_TYPE = "type";
    public static final String CAMPO_PHASE_INJECTED = "phase_injected";
    public static final String CAMPO_PHASE_REMOVE = "remove";
    public static final String CAMPO_EXITIME = "exitime";
    public static final String CAMPO_DEFECT_DESCRIPTION = "defect_description";
    public static final String CAMPO_ID_DEFECT = "id";

    public static final String CREAR_TABLA_DEFECT = "CREATE TABLE " + NOMBRE_TABLA_DEFECT + " ( "
            + CAMPO_DATE + " DATE, "
            + CAMPO_TYPE + " VARCHAR ," + CAMPO_PHASE_INJECTED + " VARCHAR," + CAMPO_PHASE_REMOVE + " VARCHAR,"
            + CAMPO_EXITIME + " INT, " + CAMPO_DEFECT_DESCRIPTION + " VARHCAR," + CAMPO_ID_DEFECT + " INT AUTO_INCREMENT)";

    public static final String NOMBRE_TABLA_PROYECTOS = "proyectos";
    public static final String CAMPO_NOMBRE_PROYECTO = "nombre_proyetos";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_TIEMPO = "tiempo";

    public static final String CREAR_TABLA_PROYECTO = "CREATE TABLE " + NOMBRE_TABLA_PROYECTOS + " ( "
            + CAMPO_NOMBRE_PROYECTO + " VARCHAR, "
            + CAMPO_ID + " INT AUTO_INCREMENT ," + CAMPO_TIEMPO + " INT)";
}
