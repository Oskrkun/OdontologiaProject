package com.myagenda.DA;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by lulaa on 12/3/2017.
 */

public class ConsultaContract {

    public static abstract class ConsultaEntry implements BaseColumns {

        public static final String TABLE_NAME ="consulta";

        public static final String _ID = "id";
        public static final String CI_PACIENTE = "CIPaciente";
        public static final String FECHA_HORA = "fecha_hora";
    }

    //Operaciones
    public static ContentValues toContentValues(int ciPaciente, String date) {
        ContentValues values = new ContentValues();
        values.put(ConsultaEntry.CI_PACIENTE, ciPaciente);
        values.put(ConsultaEntry.FECHA_HORA, date);
        return values;
    }
}
