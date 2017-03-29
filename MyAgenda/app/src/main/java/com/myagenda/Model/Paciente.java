package com.myagenda.Model;

import android.content.ContentValues;

import com.myagenda.DA.PacienteContract;

import java.util.ArrayList;

/**
 * Created by lulaa on 12/3/2017.
 */

public class Paciente {


    //Atributos
    private String nombre;
    private String apellido;
    private int celular;
    private int cedula;
    ArrayList<Paciente> pacientes;

    //constructor
    public Paciente(String nombre, String apellido, Integer celular, Integer cedula){
        this.apellido = apellido;
        this.nombre = nombre;
        this.celular = celular;
        this.cedula = cedula;
    }

    public Paciente(String nombre, String apellido, Integer celular, Integer cedula, ArrayList<Paciente> pacientes){
        this.apellido = apellido;
        this.nombre = nombre;
        this.celular = celular;
        this.cedula = cedula;
        this.pacientes = pacientes;
    }

    //Getters and Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCelular() {
        return celular;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getCedula() {
        return cedula;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }



    //Operaciones
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(PacienteContract.PacienteEntry.NOMBRE, nombre);
        values.put(PacienteContract.PacienteEntry.APELLIDO, apellido);
        values.put(PacienteContract.PacienteEntry.CELULAR, celular);
        values.put(PacienteContract.PacienteEntry.CEDULA, cedula);
        return values;
    }
}
