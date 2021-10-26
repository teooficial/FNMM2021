package com.example.fnmm2021.BancoDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBController {
    SQLiteDatabase db;
    Conexao banco;
    ContentValues valores;
    long resultado;

    public DBController(Context context) {
        banco = new Conexao(context);
    }


    public String inserirActividadeRecenteCalculo(String id_farmaco, String nome_farmaco, String descricao, String data_actividade, String hora_actividade) {
        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(Conexao.FK_FARMACO_HISTORICO, id_farmaco);
        valores.put(Conexao.NOME_FARMACO_ACTIVIDADE, nome_farmaco);
        valores.put(Conexao.DESCRICAO_FARMACO, descricao);
        valores.put(Conexao.DATA_ACTIVIDADE, data_actividade);
        valores.put(Conexao.HORA_ACTIVIDADE, hora_actividade);

        resultado = db.insert(Conexao.TABELA_ACTIVIDADES_RECENTES, null, valores);

        if (resultado == -1) {
            return "Erro ao inserir actividade no banco";
        } else {
            return "Actividade inserida com sucesso";
        }

    }

    public Cursor carregarActividades(){
        String selectQuery = "SELECT * FROM " + banco.TABELA_ACTIVIDADES_RECENTES+ "  order by " + banco.ID_ACTIVIDADES_RECENTES + " desc";

        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }
    public Cursor carregaActividadeById(int id){
        Cursor cursor;
        String[] campos = {banco.ID_ACTIVIDADES_RECENTES,
                banco.DATA_ACTIVIDADE,
                banco.FK_FARMACO_HISTORICO,
                banco.DESCRICAO_FARMACO,
                banco.HORA_ACTIVIDADE};
        String where = Conexao.ID_ACTIVIDADES_RECENTES + " = " + id;

        db = banco.getReadableDatabase();
        cursor = db.query(Conexao.TABELA_ACTIVIDADES_RECENTES, campos, where, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }



    public String apagarActividade(int id){

        String where = Conexao.ID_ACTIVIDADES_RECENTES + " = " + id;
        db = banco.getReadableDatabase();
        db.delete(Conexao.TABELA_ACTIVIDADES_RECENTES, where, null);
        db.close();

        return "Item apagado com sucesso";
    }
    public String apagarTodasActividades(){


        db = banco.getReadableDatabase();
        db.delete(Conexao.TABELA_ACTIVIDADES_RECENTES, null, null);
        db.close();

        return "Itens apagados com sucesso";
    }


}
