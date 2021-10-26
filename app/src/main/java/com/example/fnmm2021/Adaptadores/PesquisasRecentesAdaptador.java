package com.example.fnmm2021.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fnmm2021.R;

public class PesquisasRecentesAdaptador extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] nome_farmaco;
    private final String[] data;
    private final String[] hora;
    private final String[] idfarmaco;
    private final String[] trecho;


    public PesquisasRecentesAdaptador(Activity context, String[] nome_farmaco, String[] data, String[] hora, String[] idfarmaco, String[] trecho) {
        super(context, R.layout.activity_pesquisas_recentes, nome_farmaco);
        this.context = context;
        this.nome_farmaco = nome_farmaco;
        this.idfarmaco = idfarmaco;
        this.data = data;
        this.hora = hora;
        this.trecho = trecho;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_pesquisas_recentes, null, true);
        TextView nomeFarmaco = (TextView) rowView.findViewById(R.id.id_textView_nome_actividade);
        TextView data_txt = (TextView) rowView.findViewById(R.id.id_textView_data_actividade);
        TextView hora = (TextView) rowView.findViewById(R.id.id_textView_hora_actividade);
        TextView trecho = (TextView) rowView.findViewById(R.id.trechinho);
        TextView idfarmaco_txt =  rowView.findViewById(R.id.id_farmaco_recentespesquisas);

        nomeFarmaco.setText(nome_farmaco[position]);
        data_txt.setText(data[position]);
        hora.setText(this.hora[position]);
        trecho.setText(this.trecho[position]);
idfarmaco_txt.setText(this.idfarmaco[position]);


        return rowView;
    }


}


