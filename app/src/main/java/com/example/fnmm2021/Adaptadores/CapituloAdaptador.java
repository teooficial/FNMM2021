package com.example.fnmm2021.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.BancoDados.DBController;
import com.example.fnmm2021.Classes.Capitulos;
import com.example.fnmm2021.Classes.TodosFarmacos;
import com.example.fnmm2021.FarmacoIndividualActivity;
import com.example.fnmm2021.FarmacosActivity;
import com.example.fnmm2021.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CapituloAdaptador extends RecyclerView.Adapter {

    private List<Capitulos> listaCapitulos;
    private Context context;
    private FirebaseFirestore firestoreDB;
    TextView nome_farmaco;
    ImageView icone;
    ConstraintLayout contraintfarmaco;
    ImageView edit;
    ImageView delete;
    String inicioglobal = "";
    Cursor cursor;
    DBController dbController;
    String TAG = "capitulo";


    public CapituloAdaptador(List<Capitulos> notesList, Context context, FirebaseFirestore firestoreDB) {
        this.listaCapitulos = notesList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_com_icone, parent, false);

        return new CapituloAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int itemPosition = position;
        final Capitulos capitulos = listaCapitulos.get(itemPosition);


//        letrainicial.setText(capitulos.getNome().substring(0, 1).toUpperCase());
        nome_farmaco.setText(capitulos.getNome());
//
        String value = capitulos.getIcone();
                Picasso.get().load(value).into(icone);
//

        contraintfarmaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getNivelPrescricao(todosFarmacos.getNivelprescricao());
                updateNote(capitulos);

//                Toast.makeText(context, todosFarmacos.getNivelprescricao().toString(), Toast.LENGTH_LONG).show();
            }

        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                deleteNote(todosFarmacos.getId(), itemPosition);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listaCapitulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            contraintfarmaco = view.findViewById(R.id.constraint_com_icone);
            icone = view.findViewById(R.id.icone_capitulo);
            nome_farmaco = view.findViewById(R.id.txt_nomecapitulo);



//            edit = view.findViewById(R.id.ivEdit);
//            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateNote(Capitulos capitulos) {

        FarmacosRecyclerViewAdapter farmacosRecyclerViewAdapter = new FarmacosRecyclerViewAdapter();
        farmacosRecyclerViewAdapter.constante = TAG;

        Intent intent = new Intent(this.context, FarmacosActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



        intent.putExtra("numero", capitulos.getNumero());
        intent.putExtra("tabela", "capitulo");


        context.startActivity(intent);
    }


}
