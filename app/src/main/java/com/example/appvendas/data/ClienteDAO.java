package com.example.appvendas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appvendas.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ClienteDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("numero", cliente.getNumero());
        return banco.insert("cliente", null, values );
    }

    public List<Cliente> obterTodos(){
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = banco.query("cliente", new String[]{"id", "nome", "numero"}, null, null, null, null, null);

        System.out.println("PASSOU AQUI");
        while(cursor.moveToNext()){
            Cliente a =  new Cliente();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setNumero(cursor.getString(2));
            clientes.add(a);
        }
        return clientes;
    }


    public void excluirClienteDAO(Cliente a){
        banco.delete("cliente", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizarClienteDAO(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("numero", cliente.getNumero());
        banco.update("cliente", values, "id = ?", new String[]{cliente.getId().toString()});
    }
}
