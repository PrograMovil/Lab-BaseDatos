package com.lab_bd01;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.lab_bd01.Modelo.Curso;
import com.lab_bd01.Modelo.Estudiante;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    public static final String DB_NAME="movilesDB";

    public BaseDatos(Context context) {
        super(context,DB_NAME,null,1);
    }

    public boolean agregarEstudiante(Estudiante e){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("insert into Estudiante(id, nombre, apellido1, apellido2, edad) values ('"+e.getId()+"', '"+e.getNombre()+"', '"+e.getApellido1()+"', '"+e.getApellido2()+"', '"+e.getEdad()+"');");
            return true;
        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en agregar estudiante", ex);
            return false;
        }
    }

    public Estudiante buscarEstudiante(String nombre, String apellido1, String apellido2, int edad){
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            String query= "select * from Estudiante where nombre='"+nombre+"' and apellido1='"+apellido1+"' and apellido2='"+apellido2+"' and edad='"+edad+"';";
            Cursor cursor = db.rawQuery(query,null);
            Estudiante aux=new Estudiante();
            aux.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            aux.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            aux.setApellido1(cursor.getString(cursor.getColumnIndexOrThrow("apellido1")));
            aux.setApellido2(cursor.getString(cursor.getColumnIndexOrThrow("apellido2")));
            aux.setEdad(cursor.getInt(cursor.getColumnIndexOrThrow("edad")));


            return aux;

        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en bucarEstudiante", ex);
            return null;
        }
    }

    public Estudiante estudianteById(int id){
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            String query= "select * from Estudiante where id='"+id+"';";
            Cursor cursor = db.rawQuery(query,null);
            Estudiante aux=new Estudiante();
            aux.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            aux.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            aux.setApellido1(cursor.getString(cursor.getColumnIndexOrThrow("apellido1")));
            aux.setApellido2(cursor.getString(cursor.getColumnIndexOrThrow("apellido2")));
            aux.setEdad(cursor.getInt(cursor.getColumnIndexOrThrow("edad")));
            return aux;

        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en estudianteById", ex);
            return null;
        }
    }

    public boolean updateEstudiante(Estudiante e){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("update Estudiante set nombre='"+e.getNombre()+"', apellido1='"+e.getApellido1()+"', apellido2='"+e.getApellido2()+"', edad='"+e.getEdad()+"' where id="+e.getId()+";");
            return true;
        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en updateEstudiante", ex);
            return false;
        }
    }

    public boolean deleteEstudiante(Estudiante e){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("delete from Estudiante where id="+e.getId()+";");
            return true;
        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en deleteEstudiante", ex);
            return false;
        }
    }

    public ArrayList<Estudiante> getListaEstudiantes(){
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            String query= "select * from Estudiante;";
            Cursor cursor = db.rawQuery(query,null);
            ArrayList<Estudiante> lista=new ArrayList<>();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Estudiante aux=new Estudiante();
                    aux.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    aux.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    aux.setApellido1(cursor.getString(cursor.getColumnIndexOrThrow("apellido1")));
                    aux.setApellido2(cursor.getString(cursor.getColumnIndexOrThrow("apellido2")));
                    aux.setEdad(cursor.getInt(cursor.getColumnIndexOrThrow("edad")));
                    lista.add(aux);
                    cursor.moveToNext();
                }
            }

            return lista;

        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en getListaEstudiantes", ex);
            return null;
        }
    }




    //CRUD CURSO


    public boolean agregarCurso(Curso c){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("insert into Curso(nombre, descripcion, creditos, estudiante_id) values ('"+c.getNombre()+"', '"+c.getDescripcion()+"', '"+c.getCreditos()+"', '"+ c.getEstudiante().getId() +"');");
            return true;
        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en agregarCurso", ex);
            return false;
        }
    }

    public Curso buscarCurso(String nombre, String descripcion, int creditos, int estudiante_id){
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            String query= "select * from Curso where nombre='"+nombre+"' and descripcion='"+descripcion+"' and creditos='"+creditos+"';";
            Cursor cursor = db.rawQuery(query,null);
            Curso aux=new Curso();
            aux.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            aux.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            aux.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
            aux.setCreditos(cursor.getInt(cursor.getColumnIndexOrThrow("creditos")));
            aux.setEstudiante(estudianteById(cursor.getInt(cursor.getColumnIndexOrThrow("id"))));
            return aux;

        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en buscarCurso", ex);
            return null;
        }
    }

    public Curso cursoById(int id){
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            String query= "select * from Curso where id='"+id+"';";
            Cursor cursor = db.rawQuery(query,null);
            Curso aux=new Curso();
            aux.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            aux.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            aux.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
            aux.setCreditos(cursor.getInt(cursor.getColumnIndexOrThrow("creditos")));
            aux.setEstudiante(estudianteById(cursor.getInt(cursor.getColumnIndexOrThrow("estudiante_id"))));

            return aux;

        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en cursoById", ex);
            return null;
        }
    }


    public boolean updateCurso(Curso c){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("update Curso set nombre='"+c.getNombre()+"', descipcion='"+c.getDescripcion()+"', creditos='"+c.getCreditos()+"', estudiante_id='"+c.getEstudiante().getId()+"' where id="+c.getId()+";");
            return true;
        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en updateCurso", ex);
            return false;
        }
    }


    public boolean deletecurso(Curso c){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("delete from Curso where id="+c.getId()+";");
            return true;
        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en deleteCurso", ex);
            return false;
        }
    }


    public ArrayList<Curso> getListaCursos(){
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            String query= "select * from Curso;";
            Cursor cursor = db.rawQuery(query,null);
            ArrayList<Curso> lista=new ArrayList<>();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Curso aux=new Curso();
                    aux.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    aux.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    aux.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
                    aux.setCreditos(cursor.getInt(cursor.getColumnIndexOrThrow("creditos")));
                    aux.setEstudiante(estudianteById(cursor.getInt(cursor.getColumnIndexOrThrow("estudiante_id"))));
                    lista.add(aux);
                    cursor.moveToNext();
                }
            }

            return lista;

        }catch (SQLiteException ex){
            Log.e("Base de Datos", "Excepcion en getListaCursos", ex);
            return null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table Estudiante ("
                    + "id integer PRIMARY KEY,"
                    + "nombre  text,"
                    + "apellido1 text,"
                    + "apellido2  text,"
                    + "edad  integer);");

        db.execSQL("create table Curso ("
                + "id integer PRIMARY KEY autoincrement, "
                + "nombre text,"
                + "descripcion text,"
                + "creditos integer,"
                + "estudiante_id integer,"
                + "FOREIGN KEY (estudiante_id) REFERENCES Estudiante(id));");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
