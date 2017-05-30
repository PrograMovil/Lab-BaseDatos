package com.lab_bd01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by SheshoVega on 29/05/2017.
 */

public class FormEstudianteActivity extends Activity {

    EditText idEstudiante;
    EditText nombreEstudiante;
    EditText apellido1;
    EditText apellido2;
    EditText edad;
    Button guardar;
    int accion = 0; // 1 si es guardar 2 si es actualizar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_estudiante);

        this.idEstudiante = (EditText) findViewById(R.id.idEstudiante);
        this.nombreEstudiante = (EditText) findViewById(R.id.nombreCurso);
        this.apellido1 = (EditText) findViewById(R.id.apellido1);
        this.apellido2 = (EditText) findViewById(R.id.apellido2);
        this.edad = (EditText) findViewById(R.id.edad);
        this.guardar = (Button) findViewById(R.id.guardarEstudiante);

        // <-- jalar accion del intent y setearla
//        this.accion =
        //  jalar accion del intent -->

        if(this.accion == 1) {
            this.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardarDatos();
                }
            });
        }
        else if(this.accion == 2){
            this.cargarDatos();
            this.idEstudiante.setKeyListener(null);
            this.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizarDatos();
                }
            });
        }

    }

    public void cargarDatos(){

    }

    public void guardarDatos(){

    }

    public void actualizarDatos(){

    }
}
