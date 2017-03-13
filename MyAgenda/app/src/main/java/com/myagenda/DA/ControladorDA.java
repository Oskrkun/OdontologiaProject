package com.myagenda.DA;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myagenda.BL.MyAgenda;

/**
 * Created by lulaa on 12/3/2017.
 */

public class ControladorDA extends SQLiteOpenHelper {

    //Constantes
    // Seteo valores para crear la base de datos: nombre, version y una variable contexto
    private static final int DATABASE_VERSION = 1;
    final private static String DB_NAME = "Odontologia_db";


    //Atributos
    private static ControladorDA instancia = null;
    public static ControladorDA getInstance(){
        if (instancia == null){
            instancia = new ControladorDA( DB_NAME, null, DATABASE_VERSION);
        }
        return instancia;
    }


    // Constructor
    public ControladorDA(String name, SQLiteDatabase.CursorFactory factory, int version) {
        //creacion de la base de datos
        super(MyAgenda.getContext(), DB_NAME, null, DATABASE_VERSION);
    }


    //Eventos ciclo de vida
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            //elimino tablas
            db.execSQL("DROP TABLE IF EXISTS paciente");
            db.execSQL("DROP TABLE IF EXISTS consulta");

            //construyo tablas vacias
            db.execSQL("CREATE TABLE paciente ("
                    + "nombre TEXT NOT NULL, "
                    + "apellido TEXT NOT NULL, "
                    + "celular INTEGER NOT NULL, "
                    + "cedula INTEGER PRIMARY KEY )");
            db.execSQL("CREATE TABLE consulta ("
                    + "cedula INTEGER NOT NULL, "
                    + "celular INT NOT NULL, "
                    + "fecha INT NOT NULL, "
                    + "hora INT NOT NULL, "
                    + " FOREIGN KEY (cedula) REFERENCES paciente(cedula),"
                    + " FOREIGN KEY (celular) REFERENCES paciente(celular))");

            //this.copyDB(db);

        } catch (Exception e) {
            Log.i("DB_ERROR", e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }

    //Operaciones
    

}
