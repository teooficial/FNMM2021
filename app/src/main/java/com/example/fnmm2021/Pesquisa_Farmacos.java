package com.example.fnmm2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fnmm2021.Adaptadores.FarmacosRecyclerViewAdapter;
import com.example.fnmm2021.Classes.TodosFarmacos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Pesquisa_Farmacos extends AppCompatActivity {

    private String TAG = "Pesquisa Farmacos";

    private RecyclerView recyclerView;
    private EditText texto_pesquisa;
    private FarmacosRecyclerViewAdapter mAdapter;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa__farmacos);

        recyclerView = findViewById(R.id.recyclerview_list_pesquisafarmacos);
        texto_pesquisa = findViewById(R.id.edit_text_pesquisa);
        TextView textView = findViewById(R.id.texto_teste);
        firestoreDB = FirebaseFirestore.getInstance();




        texto_pesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                recyclerView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                leituraTexto(s.toString());


            }



            @Override
            public void afterTextChanged(Editable s){
            textView.setText(s.toString());


                    leituraTexto(s.toString());


        }
        });
    }

    private void leituraTexto(String s) {
        firestoreDB.collection("farmacos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<TodosFarmacos> listfarmacos = new ArrayList<>();


                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                TodosFarmacos todosFarmacos = documentSnapshot.toObject(TodosFarmacos.class);

                                if (s.length() <= documentSnapshot.get("nome").toString().length()) {

                                    if (documentSnapshot.get("nome").toString().substring(0, s.length()).equalsIgnoreCase(s.toString())) {

                                        todosFarmacos.setId(documentSnapshot.getId());
                                        listfarmacos.add(todosFarmacos);


                                    }
                                }

                                mAdapter = new FarmacosRecyclerViewAdapter(listfarmacos, getApplicationContext(), firestoreDB);
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(mAdapter);
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });
    }

    private void carregarListaFarmacos(String numero_capitulo) {
        firestoreDB.collection("farmacos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<TodosFarmacos> listfarmacos = new ArrayList<>();

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                TodosFarmacos todosFarmacos = documentSnapshot.toObject(TodosFarmacos.class);
                                if(documentSnapshot.get("nome").toString().equalsIgnoreCase(numero_capitulo)){
                                    todosFarmacos.setId(documentSnapshot.getId());
                                    listfarmacos.add(todosFarmacos);
                                }
                            }
                            mAdapter = new FarmacosRecyclerViewAdapter(listfarmacos, getApplicationContext(), firestoreDB);
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
}