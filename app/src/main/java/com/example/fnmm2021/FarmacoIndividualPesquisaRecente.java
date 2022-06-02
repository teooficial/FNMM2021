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

public class FarmacoIndividualPesquisaRecente extends AppCompatActivity {

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
    static String numeroforma_back = "";

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
        efeitossecundarios = findViewById(R.id.efeitos_secundarios);
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


            carregarFarmacoPorId(bundle.getString("id_farmaco"));
//            carregarCapitulo(numero_capitulo);

        }
    }

    private void carregarCapitulo(String id) {
        firestoreDB.collection("capitulos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if (documentSnapshot.getString("numero").equalsIgnoreCase(id)) {

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

                                if (documentSnapshot.getId().equalsIgnoreCase(idfarmaco)) {


                                    nome_do_farmaco.setText(documentSnapshot.get("nome").toString());
                                    apresentacao.setText(documentSnapshot.get("apresentacao").toString());

                                    carregarCapitulo(documentSnapshot.get("capitulo").toString());
//                                    Toast.makeText(FarmacoIndividualPesquisaRecente.this, documentSnapshot.get("apresentacao").toString(), Toast.LENGTH_SHORT).show();
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

    static String getCodigo(String codigo1) {
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