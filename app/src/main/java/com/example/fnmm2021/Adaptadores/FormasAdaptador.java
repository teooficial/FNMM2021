package com.example.fnmm2021.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.Classes.FormaFarmaceutica;
import com.example.fnmm2021.ListaTodosFarmacos;
import com.example.fnmm2021.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FormasAdaptador extends RecyclerView.Adapter {


    private List<FormaFarmaceutica> listaForma;
    private Context context;
    private FirebaseFirestore firestoreDB;
    TextView nome_forma, inicial, apresentacao;
    ConstraintLayout contraintfarmaco;
    int tamanho;

    public FormasAdaptador(List<FormaFarmaceutica> notesList, Context context, FirebaseFirestore firestoreDB) {
        this.listaForma = notesList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_sem_icone, parent, false);

        return new FormasAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int itemPosition = position;
        final FormaFarmaceutica forma = listaForma.get(itemPosition);


        if(tamanho == listaForma.size()) {
//        letrainicial.setText(capitulos.getNome().substring(0, 1).toUpperCase());
            nome_forma.setText(forma.getNome());
            inicial.setText(forma.getNome().substring(0, 1));

//

            contraintfarmaco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateNote(forma);

                }

            });
        }
    }

    @Override
    public int getItemCount() {
        tamanho = listaForma.size();
        return listaForma.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            contraintfarmaco = view.findViewById(R.id.contraintfarmaco);
            inicial = view.findViewById(R.id.letrainicial);
            apresentacao = view.findViewById(R.id.apresentacao_farmaco);
            nome_forma = view.findViewById(R.id.nome_farmaco);

            apresentacao.setVisibility(View.GONE);

       inicial.setBackgroundResource(R.drawable.circulo_formas);

//            edit = view.findViewById(R.id.ivEdit);
//            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateNote(FormaFarmaceutica formaFarmaceutica) {
        Intent intent = new Intent(this.context, ListaTodosFarmacos.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



        intent.putExtra("numero_forma", formaFarmaceutica.getNumero());
        intent.putExtra("tabela", "forma");


        context.startActivity(intent);
    }
}
