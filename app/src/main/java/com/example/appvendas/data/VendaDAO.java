package com.example.appvendas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.appvendas.models.Venda;

import java.util.ArrayList;

public class VendaDAO {
	private Conexao conexao;
	private final Context context;
	private SQLiteDatabase banco = null;

	public VendaDAO(Context context){
		conexao = new Conexao(context);
		this.context = context;
	}

	public void inserir(Venda venda) throws SQLException {
		openDB();
		banco.insert("venda", null, venda.getContentValues());
		Toast.makeText(context, "Venda inserida com sucesso!", Toast.LENGTH_SHORT).show();
		closeDB();
	}

	public void deletar(Integer IdCliente) throws SQLException {
		openDB();
		banco.delete("venda", "_id = " +IdCliente, null);
		Toast.makeText(context, "Venda excluída com sucesso!", Toast.LENGTH_SHORT).show();
		closeDB();
	}

	public void atualizar(Venda venda) throws SQLException {
		ContentValues valores;
		openDB();
		valores = new ContentValues();
		//valores.put("_id", venda.getId());
		valores.put("quantidade", venda.getQuantidade());
		valores.put("descricao", venda.getDescricao());
		valores.put("data", venda.getData());
		valores.put("preco", venda.getPreco());
		valores.put("idCliente", venda.getIdCliente());
		banco.update("venda", valores, "_id = ?", new String[]{venda.getId().toString()});
		Toast.makeText(context, "Venda modificada com sucesso!", Toast.LENGTH_SHORT).show();
		closeDB();
	}

	public ArrayList<Venda> obterVendas(Integer idCliente) {
		ArrayList<Venda> vendas = new ArrayList<>();
		banco = conexao.getWritableDatabase();
		Cursor cursor = banco.rawQuery("SELECT * FROM venda WHERE idCliente = " + idCliente, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Venda v = new Venda();
			v.setId(cursor.getInt(0));
			v.setQuantidade(cursor.getInt(1));
			v.setDescricao(cursor.getString(2));
			v.setData(cursor.getString(3));
			v.setPreco(cursor.getFloat(4));
			v.setIdCliente(cursor.getInt(5));
			vendas.add(v);
			cursor.moveToNext();
		}
		cursor.close();
		return vendas;
	}

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
