package com.myagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
}