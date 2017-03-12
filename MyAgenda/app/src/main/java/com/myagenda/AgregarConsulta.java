package com.myagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AgregarConsulta extends AppCompatActivity {

    Button btnAdd;
    Button btnCancel;
    EditText etxTime;
    EditText etxDate;
    TextView txtTitle;
    Spinner spin;
    private String[] arraySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_consulta);

        txtTitle = (TextView)findViewById(R.id.txtTitulo);
        etxTime = (EditText)findViewById(R.id.etxtTime);
        etxDate = (EditText)findViewById(R.id.etxtDia);


        spin = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spin.setAdapter(adapter);

        btnAdd = (Button)findViewById(R.id.btnAgendar);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd_Click();
            }
        });

        btnCancel = (Button)findViewById(R.id.btnCancela);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCancel_Click();
            }
        });

    }

    private void btnAdd_Click() {
        //chequear que los campos no esten vacios, parcearlos y agregar la consulta en la base de datos.
        Intent explicitIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(explicitIntent);
        salir ();
    }

    private void btnCancel_Click() {
        Intent explicitIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(explicitIntent);
        salir ();
    }

    //Eventos
    public void salir (){
        this.finish();
    }
}
