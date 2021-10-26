package com.example.fnmm2021.ClassesGuardadas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.Adaptadores.FarmacosRecyclerViewAdapter;
import com.example.fnmm2021.Classes.TodosFarmacos;
import com.example.fnmm2021.FarmacoIndividualActivity;
import com.example.fnmm2021.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActividadeTeste extends AppCompatActivity {

    private static final String TAG = "ActividadeTeste";

    private RecyclerView recyclerView;
    private FarmacosRecyclerViewAdapter mAdapter;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_farmacos);

        recyclerView = findViewById(R.id.recyclerview_list_todosfarmacos);
        firestoreDB = FirebaseFirestore.getInstance();

        loadNotesList();
//
//        firestoreListener = firestoreDB.collection("farmacos")
//                .addSnapshotListener(new EventListener() {
//                    @Override
//                    public void onEvent(@Nullable Object value, @Nullable FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Log.e(TAG, "Listen failed!", error);
//                            return;
//                        }
//                        List<TodosFarmacos> listfarmacos = new ArrayList<>();
//
//                        for (DocumentSnapshot doc : value) {
//                            TodosFarmacos todosFarmacos = doc.toObject(TodosFarmacos.class);
//                            todosFarmacos.setId(doc.getId());
//                            listfarmacos.add(todosFarmacos);
//                        }
//
//                        mAdapter = new FarmacosRecyclerViewAdapter(listfarmacos, getApplicationContext(), firestoreDB);
//                        recyclerView.setAdapter(mAdapter);
//                    }
//                    });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }

    private void loadNotesList() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            if (item.getItemId() == R.id.addNote) {
                Intent intent = new Intent(this, FarmacoIndividualActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
