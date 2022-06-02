package com.example.fnmm2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fnmm2021.Adaptadores.FarmacosRecyclerViewAdapter;
import com.example.fnmm2021.Adaptadores.FormasAdaptador;
import com.example.fnmm2021.Classes.FormaFarmaceutica;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ListaFormasFarmaceuticas extends AppCompatActivity {

    private String TAG = "Formas";
    RecyclerView recyclerView_lista;
    ImageButton imageButton;
    TextView textView;
    FirestoreRecyclerAdapter adapter;
    FirebaseStorage storage;
    FormasAdaptador mAdapter;
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseFirestore db;
    FarmacosRecyclerViewAdapter farmacosRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formas);

//        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show();
        recyclerView_lista = (RecyclerView) findViewById(R.id.recyclerview_list_forma);

       db= FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();



//        carregarListaCapitulos();
        carregarTeste();
    }

    private void carregarListaCapitulos() {
        db.collection("formasfarmaceuticas").orderBy("nome")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FormaFarmaceutica> listfarmacos = new ArrayList<>();

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                FormaFarmaceutica capitulos = documentSnapshot.toObject(FormaFarmaceutica.class);
                                capitulos.setNumero(documentSnapshot.getString("numero"));
                                listfarmacos.add(capitulos);

//                                Toast.makeText(FormasActivity.this, documentSnapshot.getString("nome"), Toast.LENGTH_SHORT).show();

                            }
                            mAdapter = new FormasAdaptador(listfarmacos, getApplicationContext(), db);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView_lista.setLayoutManager(mLayoutManager);
                            recyclerView_lista.setItemAnimator(new DefaultItemAnimator());
                            recyclerView_lista.setAdapter(mAdapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });





    }



    private  void carregarTeste(){

        Query query = db.collection("formasfarmaceuticas").orderBy("nome");
        FirestoreRecyclerOptions<FormaFarmaceutica> options = new  FirestoreRecyclerOptions.Builder<FormaFarmaceutica>().
                setQuery(query,FormaFarmaceutica
                        .class).build();

        adapter = new FirestoreRecyclerAdapter<FormaFarmaceutica, ListaFormasFarmaceuticas.FormasViewHolder>(options) {


            @NonNull
            @Override
            public ListaFormasFarmaceuticas.FormasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_sem_icone, parent, false);

                return new ListaFormasFarmaceuticas.FormasViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ListaFormasFarmaceuticas.FormasViewHolder holder, int i, @NonNull FormaFarmaceutica capitulos) {


                holder.letrainicial.setText(capitulos.getNome().substring(0, 1));
                holder.nome_farmaco.setText(capitulos.getNome());
//                Toast.makeText(FormasActivity1.this, capitulos.getNome(), Toast.LENGTH_SHORT).show();


                holder.contraintfarmaco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                getNivelPrescricao(todosFarmacos.getNivelprescricao());
                        updateNote(capitulos);

//                Toast.makeText(context, todosFarmacos.getNivelprescricao().toString(), Toast.LENGTH_LONG).show();
                    }

                });
            }
        };

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView_lista.setLayoutManager(mLayoutManager);
//        recyclerView_lista.setItemAnimator(new DefaultItemAnimator());
//        recyclerView_lista.setAdapter(mAdapter);

        recyclerView_lista.setHasFixedSize(true);
        recyclerView_lista.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_lista.setAdapter(adapter);

    }

    private class FormasViewHolder extends RecyclerView.ViewHolder{

        TextView nome_farmaco;
        TextView letrainicial;
        TextView apresentacao_farmaco;

//        ImageView icone;
        ConstraintLayout contraintfarmaco;


        public FormasViewHolder(@NonNull View view) {
            super(view);


            contraintfarmaco = view.findViewById(R.id.contraintfarmaco);
            letrainicial = view.findViewById(R.id.letrainicial);
            apresentacao_farmaco = view.findViewById(R.id.apresentacao_farmaco);
            apresentacao_farmaco.setVisibility(View.GONE);
            letrainicial.setBackgroundResource(R.drawable.circulo_formas);
//            icone = view.findViewById(R.id.icone_capitulo);

            nome_farmaco = view.findViewById(R.id.nome_farmaco);



        }

    }
    private void updateNote(FormaFarmaceutica formaFarmaceutica) {


        farmacosRecyclerViewAdapter = new FarmacosRecyclerViewAdapter();
//        farmacosRecyclerViewAdapter.constante = TAG;

        Intent intent = new Intent(this.getApplicationContext(), ListaFarmacosPorForma.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



        intent.putExtra("numero_forma", formaFarmaceutica.getNumero());
        intent.putExtra("tabela", "forma");


        this.startActivity(intent);
    }


    //    SearView
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);



        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.id_menu_anexos) {
            startActivity(new Intent(this, AbreviaturasAcronimos.class));
            return true;
        }


//        if (id == R.id.id_ver_todos_farmacos) {
//            startActivity(new Intent(this, TodosFarmacos.class));
//            return true;
//        }
        if (id == R.id.id_menu_sobreapp) {
            startActivity(new Intent(this, SobreApp.class));
            return true;
        }

        if (id == R.id.busca) {
            startActivity(new Intent(this, Pesquisa_Farmacos.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}