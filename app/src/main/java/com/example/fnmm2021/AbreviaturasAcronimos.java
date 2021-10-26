package com.example.fnmm2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AbreviaturasAcronimos extends AppCompatActivity {


    TextView abreviaturas, nome_abreviatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abreviaturas_acronimos);

        abreviaturas = (TextView) findViewById(R.id.abrev);
        nome_abreviatura = (TextView) findViewById(R.id.nome_abrevitura);

        setDadosAbreviaturas();

    }

    public void setDadosAbreviaturas(){
        abreviaturas.setText(Html.fromHtml(getResources().getString(R.string.abreviaturas_lista)));
        nome_abreviatura.setText(Html.fromHtml(getResources().getString(R.string.nomes_abreviaturas_lista)));
    }

//    public void voltar(){
//        Toast.makeText(this, "Nothing", Toast.LENGTH_LONG).show();
////        startActivity(new Intent(this, CalcularDosagemActivity.class));
//    }

}