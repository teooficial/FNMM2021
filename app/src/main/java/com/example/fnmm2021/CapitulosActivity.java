package com.example.fnmm2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fnmm2021.Adaptadores.CapituloAdaptador;
import com.example.fnmm2021.Adaptadores.FarmacosRecyclerViewAdapter;
import com.example.fnmm2021.Classes.Capitulos;
import com.example.fnmm2021.Classes.TodosFarmacos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class CapitulosActivity extends AppCompatActivity {

    private String TAG = "Capitulos";
    RecyclerView recyclerView_lista;
    ImageButton imageButton;
    TextView textView;
    FirestoreRecyclerAdapter adapter;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView recyclerView;
    private CapituloAdaptador mAdapter;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividade_capitulos);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_list_capitulos);

        firestoreDB = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        carregarListaCapitulos();
    }

    private void carregarListaCapitulos() {
        firestoreDB.collection("capitulos").orderBy("numero")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Capitulos> listfarmacos = new ArrayList<>();

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Capitulos capitulos = documentSnapshot.toObject(Capitulos.class);
                                capitulos.setId(documentSnapshot.getId());
                                listfarmacos.add(capitulos);
                            }
                            mAdapter = new CapituloAdaptador(listfarmacos, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });
    }


//        Query query = db.collection("capitulos").orderBy("numero");
//
//
//        FirestoreRecyclerOptions<Capitulos> options = new FirestoreRecyclerOptions.Builder<Capitulos>()
//                .setQuery(query, Capitulos.class)
//                .build();
//
//        adapter = new FirestoreRecyclerAdapter<Capitulos, CapitulosViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull CapitulosViewHolder holder, int i, @NonNull Capitulos capitulos) {
//                holder.textView1.setText(capitulos.getNome());
////                holder.textView2.setText(capitulos.getNumero());
//
//
//
//                String value = capitulos.getIcone();
//                Picasso.get().load(value).into(holder.list_name);
//
//
//            }
//
//            @NonNull
//            @Override
//            public CapitulosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_com_icone, parent, false);
//                return new CapitulosViewHolder(view);
//            }
//        };
//
//        recyclerView_lista.setHasFixedSize(true);
//        recyclerView_lista.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView_lista.setAdapter(adapter);
//
//
//    }
//
//    private class CapitulosViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView list_name;
//        private TextView textView1;
//        private TextView textView2;
//
//        public CapitulosViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            list_name = itemView.findViewById(R.id.img1);
//            textView1 = itemView.findViewById(R.id.textview1);
//            textView2 = itemView.findViewById(R.id.textview2);
//
//        }
//
//
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//        imageButton = (ImageButton) findViewById(R.id.img1);
//        textView = (TextView) findViewById(R.id.textview1);


}







