package com.example.fnmm2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fnmm2021.Adaptadores.PesquisasRecentesAdaptador;
import com.example.fnmm2021.BancoDados.Conexao;
import com.example.fnmm2021.BancoDados.DBController;

public class PesquisasRecentes extends AppCompatActivity {

    private ListView lista;
    DBController banco_actividades;


    Cursor cursor_actividades, cursor_farmaco, cursor_calcular;
    TextView campo_1, campo_2, campo_3, nenhuma_actividade;

    ImageButton ib1, ib2, ib3;


    private String[] idfarmaco;
    //    = { R.drawable.coracao,
//            R.drawable.estomago,
//            R.drawable.pills2,} ;
    String[] nomeFarmaco;
    String[] trecho;
    //    = new String[3];
    String[] data;
    //    = new  String[3];
    String[] hora;
//    = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_recentes_calculo);

       // campo_1 = (TextView) findViewById(R.id.campo_1_titulo);
//        campo_2 = (TextView) findViewById(R.id.campo_2_titulo);
//        campo_3 = (TextView) findViewById(R.id.campo_3_titulo);
        nenhuma_actividade = (TextView) findViewById(R.id.nenhuma_activade);
        lista = (ListView) findViewById(R.id.id_list_actividade_recente);
//        ib1 = (ImageButton) findViewById(R.id.imagebutton_seta_voltar);
//        ib2 = (ImageButton) findViewById(R.id.imagebutton_delete);
//        ib3 = (ImageButton) findViewById(R.id.imagebutton_close_delete);


        banco_actividades = new DBController(getBaseContext());
//        banco_farmaco = new BancoControllerFarmacos(getBaseContext());
//        banco_calcular = new BancoControllerCalcularDosagem(getBaseContext());

        cursor_actividades = banco_actividades.carregarActividades();
        nomeFarmaco = new String[cursor_actividades.getCount()];
        trecho = new String[cursor_actividades.getCount()];
        data = new String[cursor_actividades.getCount()];
        hora = new String[cursor_actividades.getCount()];
        idfarmaco = new String[cursor_actividades.getCount()];


        int a = 0;
        if (cursor_actividades.moveToFirst()) {

            do {
                nomeFarmaco[a] = cursor_actividades.getString(3);
                data[a] = cursor_actividades.getString(1);

                hora[a] = cursor_actividades.getString(2);
                idfarmaco[a]=cursor_actividades.getString(4);
                trecho[a] = cursor_actividades.getString(5);


                a++;
            } while (cursor_actividades.moveToNext());
        }


        PesquisasRecentesAdaptador adaptador = new PesquisasRecentesAdaptador(this, nomeFarmaco, data, hora, idfarmaco, trecho);
        lista.setAdapter(adaptador);

//Codigo para colocar a frase nenhuma actividade feita, no textView quando o cursor_actividades nao acha nada na tabela actividades_recentes
        if (cursor_actividades.getCount() == 0) {
            nenhuma_actividade.setVisibility(View.VISIBLE);
            nenhuma_actividade.setText("Nenhuma actividade feita");
        } else {
            nenhuma_actividade.setVisibility(View.INVISIBLE);
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor_actividades.moveToPosition(position);
                codigo = cursor_actividades.getString(cursor_actividades.getColumnIndexOrThrow(Conexao.FK_FARMACO_HISTORICO));



                    Intent intent = new Intent(getApplicationContext(), FarmacoIndividualPesquisaRecente.class);
//                    FarmacoIndividualActivity.codigo = "pesquisarecente";
                  intent.putExtra("id_farmaco", codigo);
                    startActivity(intent);

//                    finish();


//                    startActivity(new Intent(getApplicationContext(), InformacaoBuscaActividade.class));
//                    InformacaoBuscaActividade.codigo = cursor_actividades.getString(3);

//                    finish();

//                Toast.makeText(getApplicationContext(),  "Yes", Toast.LENGTH_LONG).show();
//                com.projecto_fim_de_curso.teo.projectofimdecurso.MakingToast.makeText(ConsultaTipoFarmaco.this, position + "", com.projecto_fim_de_curso.teo.projectofimdecurso.MakingToast.LENGTH_LONG).show();

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final String codigo;

                cursor_actividades.moveToPosition(position);
//                codigo = cursor_actividades.getString(cursor_actividades.getColumnIndexOrThrow(CriaBanco.ID_ACTIVIDADES_RECENTES));

//                asf(codigo);

//                caixa(codigo);

                return true;
            }
        });


    }

//    public void asf(String codigo){
//
//        caixa();
//
//        String resposta = banco_actividades.apagarActividade(Integer.parseInt(codigo));
//
//        Toast.makeText(getApplicationContext(), resposta, Toast.LENGTH_LONG).show();
//
//        startActivity(new Intent(getApplicationContext(), VerFarmacoDeAcordoComAEscolha.class));
//    }

    public void caixa(final String codigo) {

//        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_menu_delete)
//                .setTitle("").setMessage("Tem a certeza que deseja apagar esta actividade?")
//                .setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
//                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        String resposta = banco_actividades.apagarActividade(Integer.parseInt(codigo));
////
//                        Toast.makeText(getApplicationContext(), resposta, Toast.LENGTH_LONG).show();
//
//                        startActivity(new Intent(getApplicationContext(), VerFarmacoDeAcordoComAEscolha.class));
//                    }
//
//                }).setNegativeButton("Cancelar", null).show();

    }

    public void caixa1() {
//        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_menu_delete)
//                .setTitle("").setMessage("Tem a certeza que deseja apagar todas actividades?")
//                .setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
//                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        String resposta = banco_actividades.apagarTodasActividades();
////
//                        Toast.makeText(getApplicationContext(), resposta, Toast.LENGTH_LONG).show();
//
//                        startActivity(new Intent(getApplicationContext(), VerFarmacoDeAcordoComAEscolha.class));
//                    }
//
//                }).setNegativeButton("Cancelar", null).show();

    }

    //    public void onClickButton(View view){
//        int id = view.getId();
//
//        if(id == R.id.imagebutton_seta_voltar){
//            finish();
//        }
//        if(id == R.id.imagebutton_delete){
//            Toast.makeText(getApplicationContext(), "Selecionado" , Toast.LENGTH_LONG).show();
//        }
//        if(id == R.id.imagebutton_close_delete){
//            ib2.setVisibility(View.INVISIBLE);
//            ib3.setVisibility(View.INVISIBLE);
//        }
//    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.actividade_principal, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.id_delete) {
//            caixa1();
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}