package com.myagenda.UI;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myagenda.Model.Paciente;
import com.myagenda.R;

import java.util.ArrayList;

/**
 * Created by lulaa on 16/3/2017.
 */

public class ListViewAdapterPacientes extends BaseAdapter {

    public ArrayList<Paciente> list;
    Activity activity;
    TextView txtNombreApellidoPaciente;
    TextView txtCedulaPaciente;

    public ListViewAdapterPacientes(Activity activity, ArrayList<Paciente> list){
        super();
        this.activity=activity;
        this.list=list;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){
            convertView=inflater.inflate(R.layout.lista_pacientes, null);
            txtNombreApellidoPaciente=(TextView) convertView.findViewById(R.id.nombreApellidoPaciente);
            txtCedulaPaciente=(TextView) convertView.findViewById(R.id.cedulaPaciente);

            Paciente p = list.get(position);
            txtNombreApellidoPaciente.setText(p.getNombre() + " " + p.getApellido());
            txtCedulaPaciente.setText(String.format("%.2f", p.getCedula()));

        }

        return convertView;
    }

    @Override
    public int getViewTypeCount(){
        return getCount() < 1 ? 1 : getCount();
    }

    @Override
    public int getItemViewType(int Position){
        return Position;
    }


}