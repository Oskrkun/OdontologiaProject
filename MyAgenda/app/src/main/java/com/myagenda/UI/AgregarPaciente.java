package com.myagenda.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myagenda.R;

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
            String NombrePaciente = etxtNombre.getText().toString();
            String ApellidoPaciente = etxApellido.getText().toString();
            String Cedula = etxCI.getText().toString();
            String Celular = etxCelular.getText().toString();
            if (Cedula.length() != 0) {
                if (Celular.length() != 0) {
                    Integer CelularPaciente = Integer.parseInt(etxCelular.getText().toString());
                    Integer CedulaPaciente = Integer.parseInt(etxCI.getText().toString());
                    //Guardar estos datos en la base de datos

                    // terminar esta activity
                    Intent explicitIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(explicitIntent);
                    salir();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Cedula o Telefono incorrectos.", Toast.LENGTH_LONG).show();
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
