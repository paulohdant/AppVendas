package com.example.appvendas.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;


    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cliente(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, numero TEXT NOT NULL)");

        db.execSQL("CREATE TABLE venda(id INTEGER PRIMARY KEY AUTOINCREMENT, quantidade INTEGER NOT NUll, descricao TEXT NOT NULL, data TEXT NOT NULL, preco FLOAT NOT NULL, idCliente INTEGER, FOREIGN KEY (idCliente) REFERENCES cliente(id))");

        //db.execSQL("SELECT * FROM venda WHERE idCliente = id");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cliente");
        onCreate(sqLiteDatabase);
    }
}
