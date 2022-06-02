package com.example.fnmm2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView cartao1, cartao2, cartao3, cartao4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartao1 = (CardView) findViewById(R.id.cartao1);
        cartao2 = (CardView) findViewById(R.id.cartao2);
        cartao3 = (CardView) findViewById(R.id.cartao3);
        cartao4 = (CardView) findViewById(R.id.cartao4);



    }
    public void abrirfarmacos(View view) {

        int id = view.getId();

        if (id == R.id.cartao1) {
//            Intent intent = new Intent(this, ActividadeTeste.class);
            Intent intent = new Intent(this, ListaTodosFarmacos.class);

            this.startActivity(intent);
        } else if (id == R.id.cartao2) {
            Intent intent = new Intent(this, CapitulosActivity.class);

            this.startActivity(intent);
//            Toast.makeText(this, "Teste", Toast.LENGTH_LONG).show();
        } else if (id == R.id.cartao3) {
            Intent intent = new Intent(this, ListaFormasFarmaceuticas.class);

            this.startActivity(intent);
        } else if (id == R.id.cartao4) {
            Intent intent = new Intent(this, CategoriasActivity.class);

            this.startActivity(intent);
        }
    }
}