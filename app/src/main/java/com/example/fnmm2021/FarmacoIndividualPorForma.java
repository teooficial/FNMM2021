package com.example.fnmm2021;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.Classes.TodosFarmacos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class FarmacoIndividualPorForma extends AppCompatActivity {

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
//    CollapsingToolbarLayout collapsing_toolbar_layout;
    Toolbar toolbar;

//    private static final String TAG = "AddNoteActivity";

    TextView apresentacao;
    TextView nome_do_farmaco, viaadministracao, indicacoes, dosagem, efeitossecundarios, contraindicacoes, notasprecaucoes, nivelprescricao, nome_capitulo;
    ImageView icone_capitulo;
    TextView edtContent;
    Button btAdd;
    static String codigo = "0";

    private FirebaseFirestore firestoreDB;
    String id = "";
    String numero_capitulo = "";
    static String numeroforma_back ="";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmaco_individual);


//        intent = new Intent();
//        Bundle bundle = intent.getExtras();
        nome_do_farmaco = findViewById(R.id.nome_do_farmaco);
        apresentacao = findViewById(R.id.apresentacao);
        viaadministracao = findViewById(R.id.viaadministracao);
        indicacoes = findViewById(R.id.indicacoes);
        dosagem = findViewById(R.id.dosagem);
        efeitossecundarios=findViewById(R.id.efeitos_secundarios);
        contraindicacoes = findViewById(R.id.contraindicacoes);
        notasprecaucoes = findViewById(R.id.notasprecaucoes);
        nivelprescricao = findViewById(R.id.nivelprescricao);
        nome_capitulo = findViewById(R.id.nomecapitulo_farmaco_individual);
        icone_capitulo = (ImageView) findViewById(R.id.imagem_capitulo);
//        collapsing_toolbar_layout= findViewById(R.id.collapsing_toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

//        edtContent = findViewById(R.id.edtContent);
//        btAdd = findViewById(R.id.btAdd);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        firestoreDB = FirebaseFirestore.getInstance();


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            if(codigo.equalsIgnoreCase("0")){
            id = bundle.getString("farmacoid");
//            nome_do_farmaco.setText(id + "");
//            collapsing_toolbar_layout.setTitle(bundle.getString("nome")+"\n"+bundle.getString("apresentacao"));
//            toolbar.setTitle(bundle.getString("nome").toString()  );
//            toolbar.setSubtitle(bundle.getString("apresentacao").toString());

            nome_do_farmaco.setText(bundle.getString("nome"));
            apresentacao.setText(bundle.getString("apresentacao"));
            viaadministracao.setText(bundle.getString("viaadministracao"));
            indicacoes.setText(bundle.getString("indicacoes"));
            dosagem.setText(bundle.getString("dosagem"));
            efeitossecundarios.setText(bundle.getString("efeitossecundarios"));
            contraindicacoes.setText(bundle.getString("contraindicacoes"));
            notasprecaucoes.setText(bundle.getString("notasprecaucoes"));
            nivelprescricao.setText(bundle.getString("nivelprescricao"));
            numero_capitulo = (bundle.getString("numero_capitulo"));
            numeroforma_back = bundle.getString("numero_forma");



            carregarCapitulo(numero_capitulo);

            }



            else if(codigo.equalsIgnoreCase("pesquisarecente")){

                                carregarFarmacoPorId(bundle.getString("id_farmaco"));
            }
        }

//        btAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = apresentacao.getText().toString();
//                String content = edtContent.getText().toString();
//
//                if (title.length() > 0) {
//                    if (id.length() > 0) {
//                        updateNote(id, title, content);
//                    } else {
//                        addNote(title, content);
//                    }
//                }
//
//                finish();
//            }
//        });
    }

    private void updateNote(String id, String nome, String apresentacao, String viaadministracao, String dosagem, String indicacoes, String efeitossecundarios, String contraindicacoes,
                            String notasprecaucoes, String nivelprescricao) {
//        Map note = (new TodosFarmacos(id,nome, apresentacao,viaadministracao,dosagem,indicacoes,efeitossecundarios,contraindicacoes,notasprecaucoes, nivelprescricao)).toMap();

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
    }

//    private void addNote(String nome, String apresentacao) {
//        Map note = new TodosFarmacos(nome, apresentacao).toMap();
//
//        firestoreDB.collection("farmacos")
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

    private void carregarCapitulo(String id){
        firestoreDB.collection("capitulos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {



                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if(documentSnapshot.getString("numero").equalsIgnoreCase(id)){

//                                    Toast.makeText(FarmacoIndividualActivity.this, id+"  l", Toast.LENGTH_SHORT).show();
                                    nome_capitulo.setText(documentSnapshot.get("nome").toString());
                                    Picasso.get().load(documentSnapshot.get("icone").toString()).into(icone_capitulo);



                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void carregarFarmacoPorId(String idfarmaco) {
        firestoreDB.collection("farmacos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if(documentSnapshot.getId().equalsIgnoreCase(idfarmaco)){



                                    nome_do_farmaco.setText(documentSnapshot.get("nome").toString());
                                    apresentacao.setText(documentSnapshot.get("apresentacao").toString());


                                    Toast.makeText(FarmacoIndividualPorForma.this, documentSnapshot.get("apresentacao").toString(), Toast.LENGTH_SHORT).show();
                                    viaadministracao.setText(documentSnapshot.get("viaadministracao").toString());
                                    indicacoes.setText(documentSnapshot.get("indicacoes").toString());
                                    dosagem.setText(documentSnapshot.get("dosagem").toString());
                                    efeitossecundarios.setText(documentSnapshot.get("efeitossecundarios").toString());
                                    contraindicacoes.setText(documentSnapshot.get("contraindicacoes").toString());
                                    notasprecaucoes.setText(documentSnapshot.get("notasprecaucoes").toString());
                                    nivelprescricao.setText(documentSnapshot.get("nivelprescricao").toString());

                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });
    }

    static String getCodigo(String codigo1){
        codigo = codigo1;
        return codigo;
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

}