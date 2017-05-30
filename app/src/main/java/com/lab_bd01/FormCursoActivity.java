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

public class FormCursoActivity extends Activity {

    TextView idCurso;
    EditText nombreCurso;
    EditText descripcion;
    EditText creditos;
    Button guardar;
    int accion = 0; // 1 si es guardar 2 si es actualizar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_curso);

        this.idCurso = (TextView) findViewById(R.id.idCurso);
        this.nombreCurso = (EditText) findViewById(R.id.nombreCurso);
        this.descripcion = (EditText) findViewById(R.id.descripcion);
        this.creditos = (EditText) findViewById(R.id.creditos);
        this.guardar = (Button) findViewById(R.id.guardarCurso);

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
