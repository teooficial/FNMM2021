package com.example.fnmm2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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

public class FarmacosActivity extends AppCompatActivity {

    private static final String TAG = "ActividadeTeste";

    private RecyclerView recyclerView;
    private FarmacosRecyclerViewAdapter mAdapter;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;
    private String numero_capitulo, numero_forma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_farmacos);

        recyclerView = findViewById(R.id.recyclerview_list_todosfarmacos);
        firestoreDB = FirebaseFirestore.getInstance();

        FarmacoIndividualActivity.codigo = "0";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getString("tabela").equalsIgnoreCase("capitulo")){
                numero_capitulo = bundle.getString("numero");
                carregarListaFarmacos(numero_capitulo);
            }
          else if(bundle.getString("tabela").equalsIgnoreCase("forma")){
                numero_forma = bundle.getString("numero_forma");

//                Toast.makeText(this, numero_forma,Toast.LENGTH_LONG).show();
                carregarListaFarmacosForma(numero_forma);
            }



        }else {
            carregarListaFarmacos();
        }
    }

    private void carregarListaFarmacosForma(String numeroforma) {
        firestoreDB.collection("farmacos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<TodosFarmacos> listfarmacos = new ArrayList<>();

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                TodosFarmacos todosFarmacos = documentSnapshot.toObject(TodosFarmacos.class);

                                if(documentSnapshot.get("formafarmaceutica").toString().equalsIgnoreCase(numeroforma)){
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
                                if(documentSnapshot.get("capitulo").toString().equalsIgnoreCase(numero_capitulo)){
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

    private void carregarListaFarmacos() {
        firestoreDB.collection("farmacos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<TodosFarmacos> listfarmacos = new ArrayList<>();

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                TodosFarmacos todosFarmacos = documentSnapshot.toObject(TodosFarmacos.class);
                                todosFarmacos.setId(documentSnapshot.getId());
                                listfarmacos.add(todosFarmacos);
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

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item != null) {
//            if (item.getItemId() == R.id.addNote) {
//                Intent intent = new Intent(this, FarmacoIndividualActivity.class);
//                startActivity(intent);
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        firestoreListener.remove();
//    }
}