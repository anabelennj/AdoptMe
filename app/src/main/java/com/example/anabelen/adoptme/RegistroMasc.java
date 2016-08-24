package com.example.anabelen.adoptme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class RegistroMasc extends AppCompatActivity {

    DataBaseMasc db;
    Button agregar;
    RadioButton rb1,rb2;

    ImageView Imagen;
    TextView textTargetUri;

    Spinner s,s2,s3,s4;
    RadioGroup rg;

    private String[] arraySpinner1;
    private String[] arraySpinner2;
    private String[] arraySpinner3;
    private String[] arraySpinner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_masc);

        db = new DataBaseMasc(this);

        Spinner s = (Spinner) findViewById(R.id.spinner4);
        Spinner s2 = (Spinner) findViewById(R.id.spinner5);
        Spinner s3 = (Spinner) findViewById(R.id.spinner6);
        Spinner s4 = (Spinner) findViewById(R.id.spinner7);

        rb1 = (RadioButton) findViewById(R.id.radioButton3);
        rb2 = (RadioButton) findViewById(R.id.radioButton4);

        Button agregar = (Button) findViewById(R.id.agregar);
        Button insertar = (Button)findViewById(R.id.insertar);

        textTargetUri = (TextView)findViewById(R.id.targeturi);
        Imagen = (ImageView)findViewById(R.id.imageView2);

        textTargetUri.setVisibility(View.INVISIBLE);

        //AddData();

        this.arraySpinner1 = new String[] {
                "Seleccione un tipo","-------------------------------",
                "Perro", "Gato", "Conejo", "Ave", "Otro"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner1);
        s.setAdapter(adapter);
        s.setSelection(0);

        this.arraySpinner2 = new String[] {
                "Perro","-------------------------------",
                "Labrador", "Golden Retriever", "Pitbull", "Boxer", "Poodle", "Chihuahua", "Husky", "Shih Tzu", "Schnauzer", "Bulldog", "Otro","",
                "Gato","-------------------------------",
                "Siamés","Persa","Romano","Otro"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        s2.setAdapter(adapter2);
        s2.setSelection(0);

        this.arraySpinner3 = new String[] {
                "Edad","-------------------------------",
                "1-3 meses", "4-6 meses", "7-9 meses", "10-12 meses", "1-3 años", "4-6 años", "7-9 años", "10-12 años", "12-15 años", "15+ años"
        };
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner3);
        s3.setAdapter(adapter3);
        s3.setSelection(0);

        this.arraySpinner4 = new String[] {
                "Estado Físico","-------------------------------",
                "Saludable", "Enfermo", "Lesión física", "Otro"
        };
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner4);
        s4.setAdapter(adapter4);
        s4.setSelection(0);


        insertar.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }});

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroMasc.this,PerfilMascota.class));
            }
        });
    }

    public  void AddData() {
        agregar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String tipo = s.getSelectedItem().toString();

                        String raza = s2.getSelectedItem().toString();

                        String edad = s3.getSelectedItem().toString();

                        String estado = s4.getSelectedItem().toString();

                        String radiovalue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

                        boolean isInserted = db.ingresarDatosMasc(tipo, raza, edad, estado, radiovalue);
                        if (isInserted) {
                            Toast.makeText(RegistroMasc.this, "Registro Válido", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(RegistroMasc.this, "Registro Inválido", Toast.LENGTH_LONG).show();}
                    }
                }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                Imagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
