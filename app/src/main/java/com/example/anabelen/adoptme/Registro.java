package com.example.anabelen.adoptme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Registro extends AppCompatActivity {

    DataBase db;
    EditText editNombre, editApellido, editUsuario, editContrasena, editEmail;
    Button confirmar;
    RadioButton rb1, rb2;

    Spinner s, s2, s3;
    RadioGroup rg;

    private String[] arraySpinner1;
    private String[] arraySpinner2;
    private String[] arraySpinner3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();

        db = new DataBase(this);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
        editEmail = (EditText) findViewById(R.id.editEmail);

        Spinner s = (Spinner) findViewById(R.id.spinner);
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        Spinner s3 = (Spinner) findViewById(R.id.spinner3);

        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);

        Button confirmar = (Button) findViewById(R.id.confirmar);


        this.arraySpinner1 = new String[]{
                "Día", "------",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                "24", "25", "26", "27", "28", "29", "30", "31"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner1);
        s.setAdapter(adapter);
        s.setSelection(0);

        this.arraySpinner2 = new String[]{
                "Mes", "----------",
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        s2.setAdapter(adapter2);
        s2.setSelection(0);

        this.arraySpinner3 = new String[]{
                "Año", "----------",
                "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
                "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015", "2016"
        };
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner3);
        s3.setAdapter(adapter3);
        s3.setSelection(0);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this,HomePage.class));
            }
        });

        //AddData();
    }

    Calendar calendar = Calendar.getInstance();
    int thisYear = calendar.get(Calendar.YEAR);
    //int thisMonth = calendar.get(Calendar.MONTH);
    //int thisDay = calendar.get(Calendar.DAY_OF_MONTH);

    public  void AddData() {
        confirmar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String valToSet = s.getSelectedItem().toString();
                        int dia = Integer.valueOf(valToSet);

                        String valToSet2 = s2.getSelectedItem().toString();
                        int mes = Integer.valueOf(valToSet2);

                        String valToSet3 = s3.getSelectedItem().toString();
                        int anio = Integer.valueOf(valToSet3);

                        int Edad = thisYear - anio;

                        int selectedId = rg.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) findViewById(selectedId);
                        String genero = radioButton.getText().toString();

                        //String radiovalue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

                        boolean isInserted = db.ingresarDatos(
                                editNombre.getText().toString(),
                                editApellido.getText().toString(),
                                Edad,
                                editEmail.getText().toString(),
                                editUsuario.getText().toString(),
                                editContrasena.getText().toString(),
                                genero);
                        if (isInserted) {
                            Toast.makeText(Registro.this, "Registro válido", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registro.this, "Registro Inválido", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}

