package com.example.fnmm2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.fnmm2021.ClassesGuardadas.ActividadeTeste;

public class PaginaInicial extends AppCompatActivity {
    CardView cartao1, cartao2, cartao3, cartao4;
    private Toolbar toolbar;

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
            Intent intent = new Intent(this, FarmacosActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.cartao2) {
            Intent intent = new Intent(this, CapitulosActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.cartao3) {
            Intent intent = new Intent(this, FormasActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.cartao4) {
            Intent intent = new Intent(this, CategoriasActivity.class);
            this.startActivity(intent);

        }

        if (id == R.id.fab_recente_activity) {
//            Intent intent = new Intent(this, ActividadeTeste.class);
            Intent intent = new Intent(this, PesquisasRecentes.class);
            this.startActivity(intent);

        }
        if (id == R.id.linearlayout1) {
//            Intent intent = new Intent(this, ActividadeTeste.class);
            Intent intent = new Intent(this, FarmacosActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.linearlayout2) {
            Intent intent = new Intent(this, CapitulosActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.linearlayout3) {
            Intent intent = new Intent(this, FormasActivity.class);
            this.startActivity(intent);

        }
    }


    //    SearView
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);


        return true;


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.id_menu_anexos) {
//            startActivity(new Intent(this, AbreviaturasAcronimos.class));
//            return true;
//        }
//
//
////        if (id == R.id.id_ver_todos_farmacos) {
////            startActivity(new Intent(this, TodosFarmacos.class));
////            return true;
////        }
//        if (id == R.id.id_menu_sobreapp) {
//            startActivity(new Intent(this, SobreApp.class));
//            return true;
//        }
//
//        if (id == R.id.busca) {
//            startActivity(new Intent(this, Pesquisa_Farmacos.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }

    }
}