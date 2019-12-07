package com.example.appvendas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.appvendas.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Conexao conexao;
    private final Context context;
    private SQLiteDatabase banco = null;

    public ClienteDAO(Context context){
        conexao = new Conexao(context);
        this.context = context;
    }

//    public long inserir(Cliente cliente){
//        ContentValues values = new ContentValues();
//        values.put("nome", cliente.getNome());
//        values.put("numero", cliente.getNumero());
//        return banco.insert("cliente", null, values );
//    }

    public void inserir(Cliente cliente) throws SQLException {
        openDB();
        banco.insert("cliente", null, cliente.getContentValues());
        Toast.makeText(context, "Cliente inserido(a) com sucesso!", Toast.LENGTH_SHORT).show();
        closeDB();
    }

    public ArrayList<Cliente> obterClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        banco = conexao.getWritableDatabase();
        Cursor cursor = banco.rawQuery("SELECT * FROM cliente", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cliente c = new Cliente();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setNumero(cursor.getString(2));
            clientes.add(c);
            cursor.moveToNext();
        }
        cursor.close();
        return clientes;
    }

//    public List<Cliente> obterTodos(){
//        List<Cliente> clientes = new ArrayList<>();
//        Cursor cursor = banco.query("cliente", new String[]{"id", "nome", "numero"}, null, null, null, null, null);
//
//        System.out.println("PASSOU AQUI");
//        while(cursor.moveToNext()){
//            Cliente a =  new Cliente();
//            a.setId(cursor.getInt(0));
//            a.setNome(cursor.getString(1));
//            a.setNumero(cursor.getString(2));
//            clientes.add(a);
//        }
//        return clientes;
//    }


//    public void excluirClienteDAO(Cliente a){
//        banco.delete("cliente", "id = ?", new String[]{a.getId().toString()});
//    }

    public void atualizar(Integer id, String nome, String numero) throws SQLException {
        ContentValues valores;
        openDB();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("numero", numero);
        banco.update("cliente", valores, "_id = " + id, null);
        Toast.makeText(context, "Cliente modificado(a) com sucesso!", Toast.LENGTH_SHORT).show();
        closeDB();
    }

    public void deletar(Integer id) throws SQLException {
        openDB();
        banco.delete("cliente", "_id = " +id, null);
        Toast.makeText(context, "Cliente excluído(a) com sucesso!", Toast.LENGTH_SHORT).show();
        closeDB();
    }

//    public void atualizarClienteDAO(Cliente cliente){
//        ContentValues values = new ContentValues();
//        values.put("nome", cliente.getNome());
//        values.put("numero", cliente.getNumero());
//        banco.update("cliente", values, "id = ?", new String[]{cliente.getId().toString()});
//    }

    private void openDB() throws SQLException {
        if (this.banco == null) {
            this.banco = conexao.getWritableDatabase();
        }
    }

    private void closeDB() throws SQLException {
        if (this.banco != null) {
            if (this.banco.isOpen()) {
                this.banco.close();
            }
        }
    }
}
