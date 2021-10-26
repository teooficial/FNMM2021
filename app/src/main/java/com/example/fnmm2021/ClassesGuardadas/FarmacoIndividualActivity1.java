package com.example.fnmm2021.ClassesGuardadas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.Classes.TodosFarmacos;
import com.example.fnmm2021.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class FarmacoIndividualActivity1 extends AppCompatActivity {

    private String TAG = "TodosFarmacos";
    RecyclerView recyclerView_lista;
    ImageButton imageButton;
    TextView textView;
    FirestoreRecyclerAdapter adapter;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Intent intent;

//    private static final String TAG = "AddNoteActivity";

    TextView edtTitle;
    TextView idtext;
    TextView edtContent;
    Button btAdd;

    private FirebaseFirestore firestoreDB;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


//        intent = new Intent();
//        Bundle bundle = intent.getExtras();
        idtext = findViewById(R.id.id_text);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        btAdd = findViewById(R.id.btAdd);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        firestoreDB = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("UpdateNoteId");
idtext.setText(id+"");
            edtTitle.setText(bundle.getString("UpdateNoteNome"));
            edtContent.setText(bundle.getString("UpdateNoteApresentacao"));
        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();

                if (title.length() > 0) {
                    if (id.length() > 0) {
//                        updateNote(id, title, content);
                    } else {
//                        addNote(title, content);
                    }
                }

                finish();
            }
        });
    }

//    private void updateNote(String id, String nome, String apresentacao) {
//        Map note = (new TodosFarmacos(id, nome, apresentacao)).toMap();

//        firestoreDB.collection("farmacos")
//                .document(id)
//                .set(note)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.e(TAG, "Note document update successful!");
//                        Toast.makeText(getApplicationContext(), "Note has been updated!", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "Error adding Note document", e);
//                        Toast.makeText(getApplicationContext(), "Note could not be updated!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    private void addNote(String nome, String apresentacao) {
//        Map note = new TodosFarmacos(nome, apresentacao).toMap();
//
//        firestoreDB.collection("notes")
//                .add(note)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//
//
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
//                        Toast.makeText(getApplicationContext(), "Note has been added!", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "Error adding Note document", e);
//                        Toast.makeText(getApplicationContext(), "Note could not be added!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }



}