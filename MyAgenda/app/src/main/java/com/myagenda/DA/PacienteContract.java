package com.myagenda.DA;

import android.provider.BaseColumns;

/**
 * Created by lulaa on 12/3/2017.
 */

public class PacienteContract {

    public static abstract class PacienteEntry implements BaseColumns {

        public static final String TABLE_NAME ="paciente";

        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String CELULAR = "celular";
        public static final String CEDULA = "cedula";
    }
}
