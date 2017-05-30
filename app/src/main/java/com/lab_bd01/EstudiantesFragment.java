package com.lab_bd01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lab_bd01.Modelo.Curso;
import com.lab_bd01.Modelo.Estudiante;

import java.util.ArrayList;

/**
 * Created by SheshoVega on 29/05/2017.
 */

public class EstudiantesFragment extends Fragment {

    ArrayList<Estudiante> estudiantesList = new ArrayList<Estudiante>();
    RecyclerView estudiantesRecycler;
    BaseDatos basedatos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basedatos=BaseDatos.getInstance(getContext());
        initializeList();
        getActivity().setTitle("Lista de Cursos");
    }

    public void initializeList() {
        estudiantesList.clear();
        estudiantesList=basedatos.getListaEstudiantes();
        /*for(int i =0;i<7;i++){
            Estudiante estudiante = new Estudiante();
            estudiante.setId(i+1);
            estudiante.setNombre("Estudiante "+i);
            estudiante.setApellido1("1er ape del estu "+i);
            estudiante.setApellido2("2do ape del estu "+i);
            estudiante.setEdad(18);
            estudiantesList.add(estudiante);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.estudiante_card, container, false);
        estudiantesRecycler = (RecyclerView) view.findViewById(R.id.estudianteCardsView);
        estudiantesRecycler.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (estudiantesList.size() > 0 & estudiantesRecycler != null) {
            estudiantesRecycler.setAdapter(new EstudiantesFragment.ListaDeEstudiantesAdapter(estudiantesList));
        }
        estudiantesRecycler.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class ListaDeEstudiantesAdapter extends RecyclerView.Adapter<EstudiantesFragment.EstudianteHolder> {
        private ArrayList<Estudiante> list;

        public ListaDeEstudiantesAdapter(ArrayList<Estudiante> Data) {
            list = Data;
        }

        @Override
        public EstudiantesFragment.EstudianteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.estudiante_items, parent, false);
            EstudiantesFragment.EstudianteHolder estudianteHolder = new EstudiantesFragment.EstudianteHolder(view);
            return estudianteHolder;
        }

        @Override
        public void onBindViewHolder(final EstudiantesFragment.EstudianteHolder holder, int position) {
            System.out.println("ID DEL ESTUDIANTE ES:" + list.get(position).getId());
            System.out.println("INPUT ID TEXT: " + holder.idText.getText());
            holder.idText.setText(String.valueOf(list.get(position).getId()));
            holder.nombreText.setText(list.get(position).getNombre());
            holder.apellido1Text.setText(list.get(position).getApellido1());
            holder.apellido2Text.setText(list.get(position).getApellido2());
            holder.edadText.setText(String.valueOf(list.get(position).getEdad()));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class EstudianteHolder extends RecyclerView.ViewHolder {

        public TextView idText;
        public TextView nombreText;
        public TextView apellido1Text;
        public TextView apellido2Text;
        public TextView edadText;

        public ImageView edit;
        public ImageView delete;

        public EstudianteHolder(View v) {
            super(v);
            idText = (TextView) v.findViewById(R.id.idEstudianteText);
            nombreText = (TextView) v.findViewById(R.id.nombreEstudianteText);
            apellido1Text = (TextView) v.findViewById(R.id.apellido1EstudianteText);
            apellido2Text = (TextView) v.findViewById(R.id.apellido2EstudianteText);
            edadText = (TextView) v.findViewById(R.id.edadEstudianteText);

            edit = (ImageView) v.findViewById(R.id.editEstudiante);
            delete = (ImageView) v.findViewById(R.id.deleteEstudiante);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Toast.makeText(getActivity(), "Editar Estudiante...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), FormEstudianteActivity.class);
                    if (position != RecyclerView.NO_POSITION) {
                        Estudiante estudiante = estudiantesList.get(position);
                        intent.putExtra("accion",2);
                        intent.putExtra("estudiante",estudiante);
                        EstudiantesFragment.this.startActivity(intent);
                    }



                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        int position=getAdapterPosition();

                        if(basedatos.deleteEstudiante(estudiantesList.get(position))){
                            Toast.makeText(getActivity(), "Estudiante Eliminado...", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            startActivity(getActivity().getIntent());
                        }
                        else{
                            Toast.makeText(getActivity(), "Estudiante Asociado a un curso, elimine los cursos asociados primero", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Error al eliminar el estudiante", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
