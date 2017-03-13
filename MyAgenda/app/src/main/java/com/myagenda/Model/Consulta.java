package com.myagenda.Model;

/**
 * Created by lulaa on 12/3/2017.
 */

public class Consulta {

    //Atributos
    String Date;
    String Nombre;
    String Apellido;
    int ciPaciente;

    //Constructor
    public Consulta (String nombre, String apellido, int ciPaciente, String date) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.ciPaciente = ciPaciente;
        this.Date = date;
    }

    //Getters and Setters
    public void setDate(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setCiPaciente(int ciPaciente) {
        this.ciPaciente = ciPaciente;
    }

    public int getCiPaciente() {
        return ciPaciente;
    }

}
