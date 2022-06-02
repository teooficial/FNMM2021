package com.example.fnmm2021.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnmm2021.BancoDados.DBController;
import com.example.fnmm2021.Classes.TodosFarmacos;
import com.example.fnmm2021.FarmacoIndividualActivity;
import com.example.fnmm2021.FarmacoIndividualPorCapitulo;
import com.example.fnmm2021.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListaCapitulosRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<TodosFarmacos> notesList;
    private Context context;
    private FirebaseFirestore firestoreDB;
    TextView nome_farmaco, apresentacao, letrainicial, capitulo_numero;
    ConstraintLayout contraintfarmaco;
    static String numerocapitulo="";
    ImageView edit;
    ImageView delete;
    String inicioglobal = "";
    Cursor cursor;
    DBController dbController;
    FarmacoIndividualActivity objectoFarmaco = new FarmacoIndividualActivity();
    static String constante = "0";


    public ListaCapitulosRecyclerViewAdapter() {
    }

    public ListaCapitulosRecyclerViewAdapter(List<TodosFarmacos> notesList, Context context, FirebaseFirestore firestoreDB) {
        this.notesList = notesList;
        this.context = context;
        this.firestoreDB = firestoreDB;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views_da_lista_sem_icone, parent, false);

        return new ListaCapitulosRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int itemPosition = position;
        final TodosFarmacos todosFarmacos = notesList.get(itemPosition);

        letrainicial.setText(todosFarmacos.getNome().substring(0, 1).toUpperCase());
        nome_farmaco.setText(todosFarmacos.getNome());
        apresentacao.setText(todosFarmacos.getApresentacao());
        capitulo_numero.setText(todosFarmacos.getCapitulo());


        contraintfarmaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getNivelPrescricao(todosFarmacos.getNivelprescricao());
                updateNote(todosFarmacos);

//                Toast.makeText(context, todosFarmacos.getNivelprescricao().toString(), Toast.LENGTH_LONG).show();
            }

        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                deleteNote(todosFarmacos.getId(), itemPosition);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            contraintfarmaco = view.findViewById(R.id.contraintfarmaco);
            letrainicial = view.findViewById(R.id.letrainicial);
            nome_farmaco = view.findViewById(R.id.nome_farmaco);
            apresentacao = view.findViewById(R.id.apresentacao_farmaco);
            capitulo_numero = view.findViewById(R.id.numero_capitulo);


            if(constante.equalsIgnoreCase("0")) {
                letrainicial.setBackgroundResource(R.drawable.circulo_todos_farmacos);
            }else if(constante.equalsIgnoreCase("capitulos")){
                letrainicial.setBackgroundResource(R.drawable.circulo_capitulos);
            }else if(constante.equalsIgnoreCase("formas")){
                letrainicial.setBackgroundResource(R.drawable.circulo_formas);
            }

//            edit = view.findViewById(R.id.ivEdit);
//            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateNote(TodosFarmacos todosFarmacos) {
        Date date = new Date();
        String hora_para_actividade = date + "";


        Calendar gCal1 = Calendar.getInstance();

        int mes = gCal1.get(Calendar.MONTH) + 1;
        int dia = gCal1.get(Calendar.DATE);
        int ano = gCal1.get(Calendar.YEAR);
        int hora = gCal1.get(Calendar.HOUR);
        int minuto = gCal1.get(Calendar.MINUTE);
        int segundo = gCal1.get(Calendar.SECOND);
        String data1 = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":" + segundo;

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = null;
        try {
            date1 = fmt.parse(data1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dia_para_actividade = fmt.format(date1);

        hora_para_actividade = hora_para_actividade.substring(11, 16);
        dia_para_actividade = dia_para_actividade.substring(0, 11);

        dbController = new DBController(this.context);
        dbController.inserirActividadeRecenteCalculo(todosFarmacos.getId(),todosFarmacos.getNome(),todosFarmacos.getApresentacao(), dia_para_actividade, hora_para_actividade);

        Intent intent = new Intent(this.context, FarmacoIndividualPorCapitulo.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        intent.putExtra("farmacoid", todosFarmacos.getId());
        intent.putExtra("nome", todosFarmacos.getNome());
        intent.putExtra("apresentacao", todosFarmacos.getApresentacao());
        intent.putExtra("indicacoes", todosFarmacos.getIndicacoes());
        intent.putExtra("dosagem", todosFarmacos.getDosagem());
        intent.putExtra("viaadministracao", todosFarmacos.getViaadministracao());

        intent.putExtra("efeitossecundarios", todosFarmacos.getEfeitossecundarios());
        intent.putExtra("contraindicacoes", todosFarmacos.getContraindicacoes());
        intent.putExtra("notasprecaucoes", todosFarmacos.getNotasprecaucoes());

        intent.putExtra("nivelprescricao", getNivelPrescricao(todosFarmacos.getNivelprescricao()));
        intent.putExtra("numero",todosFarmacos.getCapitulo());



//        Toast.makeText(this.context, todosFarmacos.getNivelprescricao().toString(), Toast.LENGTH_LONG).show();

        context.startActivity(intent);
    }

    private String getNivelPrescricao(String nivel) {

        if (nivel.equals("0"))
        {
            return  "0 - Medicamentos dispensados pelo Agente Polivalente Elementar.";
        }
        else if (nivel.equals("1")) {
            return "1 - Medicamentos que podem ser prescritos por Agentes de Medicina, Enfermeiros e  categorias superiores a estas.";
        } else if (nivel.equals("2")) {
            return "2 - Medicamentos que podem ser prescritos por Técnicos de Medicina Geral e categorias  superiores a esta.";
        } else if (nivel.equals("3")) {
            return "3 - Medicamentos prescritos por Médicos de Clínica Geral ou categorias superiores a esta.";
        } else if (nivel.equals("4")) {
            return "4 - Medicamentos prescritos por médicos especialistas.";
        }

        return "";
//        firestoreDB.collection("nivelprescricao")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                                if (documentSnapshot.get("nivel").toString().equals(nivel1)) {
//
//                                    inicioglobal = nivel1 + " - " + documentSnapshot.get("nome").toString();
//                                }
//                            }
//                        }
//                    }
//                });
//return inicioglobal;
}

    public void toast() {
//        Toast.makeText(this,"teste", Toast.LENGTH_LONG).show();
    }
//    private void deleteNote(String id, final int position) {
//        firestoreDB.collection("notes")
//                .document(id)
//                .delete()
//                .addOnCompleteListener(new OnCompleteListener<void>() {
//
//
//                    @Override
//                    public void onComplete(@NonNull Task<void> task) {
//                        notesList.remove(position);
//                        notifyItemRemoved(position);
//                        notifyItemRangeChanged(position, notesList.size());
//                        Toast.makeText(context, "Note has been deleted!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


}
