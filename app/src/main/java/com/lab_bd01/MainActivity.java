package com.lab_bd01;

        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try{
            db=SQLiteDatabase.openDatabase("/data/data/cis493.sqldatabases/movilesDB",null,SQLiteDatabase.CREATE_IF_NECESSARY);
            db.execSQL("create table Estudiante ("
                    + " id integer PRIMARY KEY autoincrement, "
                    + " nombre  text, "
                    + " apellido1 text, "
                    + " apellido2  text, "
                    + " edad  integer);  "    );

            db.execSQL("create table Cursos ("
                    + " id integer PRIMARY KEY autoincrement, "
                    + " nombre text, "
                    + " descripcion text, "
                    + " creditos integer, "
                    + " estudiante_id integer,"
                    + " FOREIGN KEY (estudiante_id) REFERENCES Estudiante(id)"
                    + "); ");
            db.close();

        }catch (SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}
