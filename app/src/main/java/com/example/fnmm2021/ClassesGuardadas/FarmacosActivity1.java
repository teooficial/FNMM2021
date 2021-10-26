package com.example.fnmm2021.ClassesGuardadas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.Classes.TodosFarmacos;
import com.example.fnmm2021.R;
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

public class FarmacosActivity1 extends AppCompatActivity {

    private String TAG = "TodosFarmacos";
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
        setContentView(R.layout.activity_todos_farmacos);

//        Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show();
        recyclerView_lista = (RecyclerView) findViewById(R.id.recyclerview_list_todosfarmacos);
//        TextView teste = (TextView) findViewById(R.id.testetext);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("icone");




        db.collection("farmacos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                teste.setText(document.getId()+"");
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        Query query = db.collection("farmacos").orderBy("nome");


        FirestoreRecyclerOptions<TodosFarmacos> options = new FirestoreRecyclerOptions.Builder<TodosFarmacos>()
                .setQuery(query, TodosFarmacos.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<TodosFarmacos, FarmacosActivity1.FarmacosViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FarmacosActivity1.FarmacosViewHolder holder, int i, @NonNull TodosFarmacos todosFarmacos) {
//                holder.textView1.setText(todosFarmacos.getNome());
//                holder.textView2.setText(todosFarmacos.getApresentacao());




                holder.textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        chamarActividade(todosFarmacos.getId());
                    }
                });
//                String value = categorias.getIcone();
//                Picasso.get().load(value).into(holder.list_name);


            }

            @NonNull
            @Override
            public FarmacosActivity1.FarmacosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_sem_icone, parent, false);
                return new FarmacosActivity1.FarmacosViewHolder(view);
            }
        };

        recyclerView_lista.setHasFixedSize(true);
        recyclerView_lista.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_lista.setAdapter(adapter);


    }

    private class FarmacosViewHolder extends RecyclerView.ViewHolder {

        private ImageView icone;
        private TextView textView1;
        private TextView textView2;

        public FarmacosViewHolder(@NonNull View itemView) {
            super(itemView);

//            icone = itemView.findViewById(R.id.img1);
            textView1 = itemView.findViewById(R.id.nome_farmaco);
            textView2 = itemView.findViewById(R.id.apresentacao_farmaco);

        }


    }

    public void chamarActividade(String id){
//        Intent intent = new Intent(this, FarmacoIndividualActivity.class);
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