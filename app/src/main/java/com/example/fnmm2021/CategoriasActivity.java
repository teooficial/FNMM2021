package com.example.fnmm2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fnmm2021.Classes.CategoriaFarmaceutica;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CategoriasActivity extends AppCompatActivity {

    private String TAG = "Categorias";
    RecyclerView recyclerView_lista;
    ImageButton imageButton;
    TextView textView;
    FirestoreRecyclerAdapter adapter;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

//        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show();
        recyclerView_lista = (RecyclerView) findViewById(R.id.recyclerview_list_categoria);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("icone");







        Query query = db.collection("categoriasfarmaceuticas").orderBy("categoria");


        FirestoreRecyclerOptions<CategoriaFarmaceutica> options = new FirestoreRecyclerOptions.Builder<CategoriaFarmaceutica>()
                .setQuery(query, CategoriaFarmaceutica.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<CategoriaFarmaceutica, CategoriasActivity.CategoriasViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoriasActivity.CategoriasViewHolder holder, int i, @NonNull CategoriaFarmaceutica categorias) {
                holder.textView1.setText(categorias.getCategoria());
//                holder.textView2.setText(categorias.getNumero());



//                String value = categorias.getIcone();
//                Picasso.get().load(value).into(holder.list_name);


            }

            @NonNull
            @Override
            public CategoriasActivity.CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_sem_icone, parent, false);
                return new CategoriasActivity.CategoriasViewHolder(view);
            }
        };

        recyclerView_lista.setHasFixedSize(true);
        recyclerView_lista.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_lista.setAdapter(adapter);


    }

    private class CategoriasViewHolder extends RecyclerView.ViewHolder {

        private ImageView icone;
        private TextView textView1;
        private TextView textView2;

        public CategoriasViewHolder(@NonNull View itemView) {
            super(itemView);

//            icone = itemView.findViewById(R.id.img1);
            textView1 = itemView.findViewById(R.id.nome_farmaco);
//            textView2 = itemView.findViewById(R.id.apresentacao_farmaco);

        }


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