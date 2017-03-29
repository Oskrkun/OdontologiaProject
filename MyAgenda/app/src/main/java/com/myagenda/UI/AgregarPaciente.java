package com.myagenda.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myagenda.DA.ControladorDA;
import com.myagenda.Model.Paciente;
import com.myagenda.R;

import java.util.regex.Pattern;

public class AgregarPaciente extends AppCompatActivity {

    TextView txtTitulo;
    EditText etxtNombre;
    EditText etxApellido;
    EditText etxCI;
    EditText etxCelular;
    Button btnAccept;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);

        txtTitulo = (TextView)findViewById(R.id.txtTitle);
        etxtNombre = (EditText)findViewById(R.id.txtNombre);
        etxApellido = (EditText)findViewById(R.id.txtApellido);
        etxCI = (EditText)findViewById(R.id.txtCI);
        etxCelular = (EditText)findViewById(R.id.txtCelular);

        btnAccept = (Button)findViewById(R.id.btnAceptar);
        btnAccept.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                bttnAceptar_Click();
             }
        });

        btnCancel = (Button)findViewById(R.id.btnCancelar);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bttnCancelar_Click();
            }
        });
    }

    private void bttnAceptar_Click() {

            //Conversiones de los EditText para luego guardarlos en la base de datos
            String nombrePaciente = etxtNombre.getText().toString();
            String apellidoPaciente = etxApellido.getText().toString();
            String cedula = etxCI.getText().toString();
            String celular = etxCelular.getText().toString();
            //chequea que la cedula tenga entre 6 y 7 digitos
            if (Pattern.matches(cedula, "([0-9]{6,7})")) {
                //chequea que el celular comience con 09 y tengo luego 7 digitos entre 0-9
                if (Pattern.matches(celular, "09([0-9]{7})")) {
                    Integer celularPaciente = Integer.parseInt(etxCelular.getText().toString());
                    Integer cedulaPaciente = Integer.parseInt(etxCI.getText().toString());
                    //creo un nuevo paciente
                    Paciente p = new Paciente(nombrePaciente, apellidoPaciente, celularPaciente, cedulaPaciente);
                    //Guardar estos datos en la base de datos
                    ControladorDA.getInstance().insertarPaciente(p);
                    // terminar esta activity
                    Toast.makeText(getApplicationContext(), "Paciente agregado exitosamente.", Toast.LENGTH_LONG).show();

                    Intent explicitIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(explicitIntent);

                    salir();
                } else {
                    Toast.makeText(getApplicationContext(), "Celular incorrecto.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Cedula incorrecta.", Toast.LENGTH_LONG).show();
            }
    }

    private void bttnCancelar_Click() {
        Intent explicitIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(explicitIntent);
        salir ();
    }

    //Eventos
    public void salir (){
        this.finish();
    }
}
