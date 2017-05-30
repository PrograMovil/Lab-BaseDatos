package com.lab_bd01;

        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnCrudEst;
    Button btnCrudCur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrudCur=(Button) findViewById(R.id.botonCrudCursos);
        btnCrudEst=(Button) findViewById(R.id.botonCrudEstudiantes);

        btnCrudCur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cargar fragment de la lista de cursos
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragmentCursosContainer);

                if (fragment == null) {
                    fragment = new CursosFragment();
                    fm.beginTransaction()
                            .add(R.id.fragmentCursosContainer, fragment)
                            .commit();
                }
            }
        });


        btnCrudEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cargar fragment de la lista de estudiantes
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragmentEstudiantesContainer);

                if (fragment == null) {
                    fragment = new EstudiantesFragment();
                    fm.beginTransaction()
                            .add(R.id.fragmentEstudiantesContainer, fragment)
                            .commit();
                }
            }
        });





    }
}
