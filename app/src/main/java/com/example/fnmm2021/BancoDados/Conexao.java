package com.example.fnmm2021.BancoDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao  extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bancofnm";

    //TABELA TIPO DE FARMACO
    public static final String TABELA_TIPO_FARMACO = "tipo_farmaco";
    public static final String ID_TIPO_FARMACO = "_id";
    public static final String NOME_TIPO_FARMACO = "nome_tipo_farmaco";


    //TABELA ABREVIATURAS E ACRONIMOS
    public static final String TABELA_ABREVIATURAS_ACRONIMOS = "abreviaturas_acronimos";
    public static final String ID_ABREVIATURAS_ACRONIMOS = "_id";
    public static final String ABREVIATURAS_OU_ACRONIMOS = "abreviatura_ou_acronimo";
    public static final String DETALHES_ABREVIATURAS_ACRONIMOS = "detalhes_abreviaturas_acronimos";


    //TABELA INFORMACOES DO SISTEMA
    public static final String TABELA_INFORMACOES_SISTEMA = "informacoes_sistema";
    public static final String ID_INFORMACOES_SISTEMA = "_id";
    public static final String DESCRICAO_INFORMACOES_SISTEMA = "descricao";
    public static final String VERSAO_DO_SISTEMA = "versao";
    public static final String AUTOR_SISTEMA = "autor";

    //TABELA FARMACOS
    public static final String TABELA_FARMACOS = "farmacos";
    public static final String ID_FARMACOS = "_id";
    public static final String NOME_FARMACO = "nome_farmaco";
    public static final String APRESENTACAO = "apresentacao";
    public static final String DOSAGEM = "dosagem";
    public static final String INDICACOES = "indicacoes";
    public static final String CONTRA_INDICACOES = "contra_indicacoes";
    public static final String EFEITOS_SECUNDARIOS = "efeitos_secundarios";
    public static final String NOTAS_PRECAUCOES = "notas_precaucoes";
    public static final String VIAS_DE_ADMINISTRACAO = "vias_de_administracao";
    public static final String TOMAS = "tomas";
    public static final String DOSE_MINIMA = "dose_minima";
    public static final String DOSE_MAXIMA = "dose_maxima";
    public static final String VALOR_EM_MILIGRAMAS = "valor_em_miligramas";
    public static final String VALOR_EM_MILILITROS = "valor_em_mililitros";
    public static final String VALOR_EM_UNIDADES = "valor_em_unidades";
    public static final String VALOR_EM_MICROGRAMAS = "valor_em_microgramas";
    public static final String PARA_CALCULO = "para_calculo";
    public static final String NIVEL_PRESCRICAO = "nivel_prescricao";
    public static final String FK_TIPO_FARMACO = "fk_id_tipo_de_farmaco";
    public static final String FK_CATEGORIA_FARMACO = "fk_id_categoria_farmaco";
    public static final String FK_CAPITULO = "fk_capitulo";

    //TABELA ACTIVIDADES RECENTES
    public static final String TABELA_ACTIVIDADES_RECENTES = "actividades_recentes";
    public static final String ID_ACTIVIDADES_RECENTES = "_id";

    public static final String DATA_ACTIVIDADE = "data_actividade";
    public static final String HORA_ACTIVIDADE = "hora_actividade";
    public static final String NOME_FARMACO_ACTIVIDADE = "nome_farmaco_actividade";
    public static final String FK_FARMACO_HISTORICO = "fk_id_farmaco";
    public static final String DESCRICAO_FARMACO = "descricao_farmaco";


    //TABELA LOGIN
    public static final String TABELA_LOGIN = "login";
    public static final String ID_LOGIN = "_id";
    public static final String CODIGO = "codigo";
    public static final String CODIGO_RECUPERACAO = "codigo_de_recuperacao";


    private static final int VERSAO = 1;

    public Conexao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CRIANDO TABELA TIPO DE FARMACO
        String sql_tipo_farmaco = "CREATE TABLE " + TABELA_TIPO_FARMACO + " ("
                + ID_TIPO_FARMACO + " integer primary key autoincrement, "
                + NOME_TIPO_FARMACO + " text); ";


        //CRIANDO TABELA ABREVIATURAS E ACRONIMOS
        String sql_abreviaturas_acronimos = "CREATE TABLE " + TABELA_ABREVIATURAS_ACRONIMOS + " ("
                + ID_ABREVIATURAS_ACRONIMOS + " integer primary key autoincrement, "
                + ABREVIATURAS_OU_ACRONIMOS + " text,"
                + DETALHES_ABREVIATURAS_ACRONIMOS + " text); ";

        //CRIANDO TABELA INFORMACOES DO SISTEMA
        String sql_informacoes_sistema = "CREATE TABLE " + TABELA_INFORMACOES_SISTEMA + " ("
                + ID_INFORMACOES_SISTEMA + " integer primary key autoincrement, "
                + DESCRICAO_INFORMACOES_SISTEMA + " text,"
                + VERSAO_DO_SISTEMA + " text, "
                + AUTOR_SISTEMA + " text); ";

        //CRIANDO TABELA FARMACOS
        String sql_farmacos = "CREATE TABLE " + TABELA_FARMACOS + " ("
                + ID_FARMACOS + " integer primary key autoincrement, "
                + NOME_FARMACO + " text, "
                + APRESENTACAO + " text, "
                + DOSAGEM + " text, "
                + INDICACOES + " text, "
                + CONTRA_INDICACOES + " text, "
                + EFEITOS_SECUNDARIOS + " text, "
                + NOTAS_PRECAUCOES + " text, "
                + VIAS_DE_ADMINISTRACAO + " text,"
                + DOSE_MAXIMA + " decimal, "
                + DOSE_MINIMA + " decimal, "
                + VALOR_EM_MILIGRAMAS + " decimal, "
                + VALOR_EM_MILILITROS + " decimal, "
                + VALOR_EM_UNIDADES + " decimal, "
                + VALOR_EM_MICROGRAMAS + " decimal, "
                + PARA_CALCULO + " boolean, "
                + FK_TIPO_FARMACO + " integer not null, "
                + FK_CATEGORIA_FARMACO + " integer not null, "
                + FK_CAPITULO + " integer not null, "
                + TOMAS + " integer,"
                + NIVEL_PRESCRICAO + " integer, "
                + " FOREIGN KEY (" + FK_TIPO_FARMACO + ") REFERENCES " + TABELA_TIPO_FARMACO + " (" + ID_TIPO_FARMACO + ") "
                + " );";

        //CRIANDO TABELA ACTIVIDADES RECENTES
        String sql_actividade = "CREATE TABLE " + TABELA_ACTIVIDADES_RECENTES + " ("
                + ID_ACTIVIDADES_RECENTES + " integer primary key autoincrement, "

                + DATA_ACTIVIDADE + " text, "
                + HORA_ACTIVIDADE + " text, "
                + NOME_FARMACO_ACTIVIDADE + " text, "
                + FK_FARMACO_HISTORICO + " text, "
                + DESCRICAO_FARMACO + " text) ";


        //CRIANDO TABELA LOGIN
        String sql_login = "CREATE TABLE " + TABELA_LOGIN + " ("
                + ID_LOGIN + " integer primary key autoincrement, "
                + CODIGO + " text, "
                + CODIGO_RECUPERACAO + " text);";


        db.execSQL(sql_tipo_farmaco);
        db.execSQL(sql_abreviaturas_acronimos);
        db.execSQL(sql_informacoes_sistema);
        db.execSQL(sql_farmacos);
        db.execSQL(sql_actividade);
        db.execSQL(sql_login);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_TIPO_FARMACO);
        onCreate(db);


        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ABREVIATURAS_ACRONIMOS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_INFORMACOES_SISTEMA);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FARMACOS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ACTIVIDADES_RECENTES);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LOGIN);
        onCreate(db);

    }
}