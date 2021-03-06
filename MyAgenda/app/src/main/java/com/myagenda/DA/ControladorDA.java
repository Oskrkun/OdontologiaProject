package com.myagenda.DA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myagenda.BL.MyAgenda;
import com.myagenda.Model.Paciente;

import java.util.ArrayList;

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
            db.execSQL("CREATE TABLE " + PacienteContract.PacienteEntry.TABLE_NAME + " ("
                    + PacienteContract.PacienteEntry.NOMBRE + " TEXT NOT NULL, "
                    + PacienteContract.PacienteEntry.APELLIDO + " TEXT UNIQUE NOT NULL, "
                    + PacienteContract.PacienteEntry.CELULAR + " INTEGER NOT NULL "
                    + PacienteContract.PacienteEntry.CEDULA + " INTEGER PRIMARY KEY, " + ")");
            db.execSQL("CREATE TABLE " + ConsultaContract.ConsultaEntry.TABLE_NAME + " ("
                    + ConsultaContract.ConsultaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ConsultaContract.ConsultaEntry.CI_PACIENTE + " INT NOT NULL, "
                    + ConsultaContract.ConsultaEntry.FECHA_HORA + " TEXT NOT NULL, "
                    + " FOREIGN KEY (" + ConsultaContract.ConsultaEntry.CI_PACIENTE + ") REFERENCES " + PacienteContract.PacienteEntry.TABLE_NAME + "(" + PacienteContract.PacienteEntry.CEDULA + "))");

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
    public long insertarPaciente(Paciente p)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                PacienteContract.PacienteEntry.TABLE_NAME,
                null,
                p.toContentValues());
    }

    public long agregarConsulta(int ciPaciente, String fecha)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        long res = sqLiteDatabase.insert(
                ConsultaContract.ConsultaEntry.TABLE_NAME,
                null,
                ConsultaContract.toContentValues(ciPaciente, fecha));
        return res;
    }

    //Obtengo todos los pacientes
    public ArrayList<Paciente> pacientesGet(){
        String queryString = "SELECT * FROM paciente";
        Cursor c = null;
        ArrayList<Paciente> res = new ArrayList<>();
        String nombre;
        String apellido;
        int celular = -1;
        int cedula = -1;

        try {
            c = this.getReadableDatabase().rawQuery(queryString, null);

            while (c.moveToNext()) {
                nombre = c.getString(c.getColumnIndex("nombre"));
                apellido = c.getString(c.getColumnIndex("apellido"));
                celular = c.getInt(c.getColumnIndex("celular"));
                cedula = c.getInt(c.getColumnIndex("cedula"));
                res.add(new Paciente(nombre, apellido, celular, cedula));
            }
        }catch (Exception ex) {
            Log.i("DB_ERROR", "Error:" + ex.getMessage());
        }finally{
            if (c != null) c.close();
            return res;
        }
    }

    //Obtengo paciente segun CI
    public Paciente obtenerPacienteByCI(int cedula) {

        String query = "select * from usuario WHERE cedula = '" + cedula + "';";
        Paciente p = null;

        Cursor c = null;
        try {
            c = this.getReadableDatabase().rawQuery(query, null);

            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                p = new Paciente(c.getString(c.getColumnIndex("nombre")), c.getString(c.getColumnIndex("apellido")), c.getInt(c.getColumnIndex("celular")), c.getInt(c.getColumnIndex("cedula")));
            }
        }catch(Exception ex){
            Log.i("ERROR_DB","Error: " + ex.getMessage());
            p = null;
        }finally {
            if (c != null) c.close();
            return p;
        }
    }


}
