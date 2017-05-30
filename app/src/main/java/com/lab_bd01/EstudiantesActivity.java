package com.lab_bd01;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EstudiantesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentEstudiantesContainer);
        if (fragment == null) {
            fragment = new CursosFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentEstudiantesContainer, fragment)
                    .commit();
        }
    }
}
