package com.example.fnmm2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fnmm2021.Adaptadores.CapituloAdaptador;
import com.example.fnmm2021.Adaptadores.FormasAdaptador;
import com.example.fnmm2021.Classes.Capitulos;
import com.example.fnmm2021.Classes.CategoriaFarmaceutica;
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

public class FormasActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formas);

//        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show();
        recyclerView_lista = (RecyclerView) findViewById(R.id.recyclerview_list_forma);

       db= FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();



        carregarListaCapitulos();
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
                                capitulos.setId(documentSnapshot.getId());
                                listfarmacos.add(capitulos);
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

}