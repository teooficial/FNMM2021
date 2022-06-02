package com.example.fnmm2021;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.Adaptadores.FarmacosPorFormaRecyclerViewAdapter;
import com.example.fnmm2021.Classes.TodosFarmacos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaFarmacosPorForma extends AppCompatActivity {

    private static final String TAG = "ActividadeTeste";

    private RecyclerView recyclerView;
    private FarmacosPorFormaRecyclerViewAdapter mAdapter;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;
    private String numero_capitulo, numero_forma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_farmacos);

        recyclerView = findViewById(R.id.recyclerview_list_todosfarmacos);
        firestoreDB = FirebaseFirestore.getInstance();

//        FarmacoIndividualActivity.codigo = "0";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            numero_forma = bundle.getString("numero_forma");

//                Toast.makeText(this, numero_forma,Toast.LENGTH_LONG).show();
            carregarListaFarmacosForma(numero_forma);
        }
        if(!FarmacoIndividualPorForma.numeroforma_back.equalsIgnoreCase(""))
        {
            carregarListaFarmacosForma(FarmacoIndividualPorForma.numeroforma_back);

//                Toast.makeText(this, numero_capitulo.toString(), T
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

                                try {
                                    if(documentSnapshot.get("formafarmaceutica").toString().equalsIgnoreCase(numeroforma)){
                                        todosFarmacos.setId(documentSnapshot.getId());
                                        listfarmacos.add(todosFarmacos);
                                    }
                                }catch (Exception e){

                                }

                            }
                            mAdapter = new FarmacosPorFormaRecyclerViewAdapter(listfarmacos, getApplicationContext(), firestoreDB);
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

//    private void carregarListaTodosFarmacos(String numero_capitulo) {
//
//
//        firestoreDB.collection("farmacos")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            List<TodosFarmacos> listfarmacos = new ArrayList<>();
//
////                            Toast.makeText(FarmacosActivity.this, "Teste", Toast.LENGTH_SHORT).show();
//
//
//                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                                TodosFarmacos todosFarmacos = documentSnapshot.toObject(TodosFarmacos.class);
//
//
//
//                                try {
//                                    if(documentSnapshot.get("capitulo").toString().equalsIgnoreCase(numero_capitulo)){
//                                        todosFarmacos.setId(documentSnapshot.getId());
//                                        listfarmacos.add(todosFarmacos);
//
////                                    Toast.makeText(FarmacosActivity.this, documentSnapshot.get("nome").toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }catch (RuntimeException e){
//                                    //CRIAR POP-UP
////                                    Toast.makeText(FarmacosActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            mAdapter = new FarmacosRecyclerViewAdapter(listfarmacos, getApplicationContext(), firestoreDB);
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            recyclerView.setLayoutManager(mLayoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//                            recyclerView.setAdapter(mAdapter);
//
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//
//
//                });
//    }

//    private void carregarListaTodosFarmacos() {
//        firestoreDB.collection("farmacos").orderBy("nome")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                        try {
//                            if (task.isSuccessful()) {
//                                List<TodosFarmacos> listfarmacos = new ArrayList<>();
//
//                                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                                    TodosFarmacos todosFarmacos = documentSnapshot.toObject(TodosFarmacos.class);
//                                    todosFarmacos.setId(documentSnapshot.getId());
//                                    listfarmacos.add(todosFarmacos);
//                                }
//                                mAdapter = new FarmacosRecyclerViewAdapter(listfarmacos, getApplicationContext(), firestoreDB);
//                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                                recyclerView.setLayoutManager(mLayoutManager);
//                                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                                recyclerView.setAdapter(mAdapter);
//
//                            } else {
//                                Log.d(TAG, "Error getting documents: ", task.getException());
//                            }
//                        }catch (Exception e){
//
//                        }
//                    }
//
//
//                });
//    }



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