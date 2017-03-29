package com.myagenda.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.myagenda.DA.ControladorDA;
import com.myagenda.Model.Paciente;
import com.myagenda.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ui elemets
    Button miBoton;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mTextMessage.setText(R.string.title_pacientes);
                inicializarPacientes();
                return true;
            case R.id.navigation_notifications:
                mTextMessage.setText(R.string.title_consultas);
                return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setup UI
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        miBoton = (Button) findViewById(R.id.btnAgregar);
        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bttnAgregar_Click();
            }
        });
    }

    private void bttnAgregar_Click () {

        String textmessage = mTextMessage.getText().toString();
        if (textmessage.equals("Pacientes")) {
            Intent explicitIntent = new Intent(getApplicationContext(), AgregarPaciente.class);
            startActivity(explicitIntent);
        } if (textmessage.equals("Consultas")) {
            Intent explicitIntent = new Intent(getApplicationContext(), AgregarConsulta.class);
            startActivity(explicitIntent);
        }
    }

    //carga la lista de pacientes
    private void inicializarPacientes() {

        final ArrayList<Paciente> pacientes  = ControladorDA.getInstance().pacientesGet();

        ListView listView = (ListView) findViewById(android.R.id.list);

        ListViewAdapterPacientes adapter = new ListViewAdapterPacientes(this,pacientes);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //cuando presiono un paciente de la lista muestro la activity detallePaciente
                int cedula = pacientes.get(position).getCedula();
                Paciente paciente = ControladorDA.getInstance().obtenerPacienteByCI(cedula);

                Intent intent = new Intent(getApplicationContext(), DetallePaciente.class);
                intent.putExtra("nombrePaciente", paciente.getNombre());
                intent.putExtra("apellidoPaciente", paciente.getApellido());
                intent.putExtra("celularPaciente", paciente.getCelular());
                intent.putExtra("cedulaPaciente", cedula);

                startActivity(intent);

            }
        });
    }


}