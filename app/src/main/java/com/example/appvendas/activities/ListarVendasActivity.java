package com.example.appvendas.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appvendas.R;
import com.example.appvendas.adapters.AdapterVenda;
import com.example.appvendas.data.VendaDAO;
import com.example.appvendas.models.Venda;

import java.util.ArrayList;

public class ListarVendasActivity extends AppCompatActivity {

	private ListView listViewVendas;
	private TextView tvTotalDevedor;
	private VendaDAO dao;
	private ArrayList<Venda> vendas;
	private ArrayList<Venda> vendasFiltradas = new ArrayList<>();
	private Integer idCliente;

	private ArrayList<Venda> venda;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_vendas_activity);
		carregarComponentes();
		carregarDados();
		dao = new VendaDAO(this);
		vendas = dao.obterVendas(idCliente);
		vendasFiltradas.addAll(vendas);
		AdapterVenda adapter = new AdapterVenda(this, vendasFiltradas);
		listViewVendas.setAdapter(adapter);

		registerForContextMenu(listViewVendas);
	}

	@Override
	protected void onResume() {
		super.onResume();
		listViewVendas.invalidateViews();
		setTitle("Total: " + percorrer() + "");
		vendas = dao.obterVendas(idCliente);
		vendasFiltradas.clear();
		vendasFiltradas.addAll(vendas);
		listViewVendas.invalidateViews();
	}

	private void carregarComponentes() {
		listViewVendas = findViewById(R.id.listViewVendas);
		tvTotalDevedor = findViewById(R.id.tvTotalDevedor);
	}

	private void carregarDados() {
		Intent intent = getIntent();

		Integer id = intent.getIntExtra("id", -1);
		idCliente = id;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater i = getMenuInflater();
		i.inflate(R.menu.menu_context_venda, menu);
	}

	public double percorrer(){
		double a = 0.0;
		for (Venda v: vendas){
			a = v.getPreco() + a;
			tvTotalDevedor.setText(Double.toString(a));
		}
		return a;
	}

	public void excluirVendaDAO(MenuItem item) {
		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		final Venda vendaExcluir = vendasFiltradas.get(menuInfo.position);

		AlertDialog dialog = new AlertDialog.Builder(this)
				.setTitle("Atenção")
				.setMessage("Realmente deseja excluir a venda?")
				.setNegativeButton("Não", null)
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						vendasFiltradas.remove(vendaExcluir);
						vendas.remove(vendaExcluir);
						dao.deletar(vendaExcluir.getId());
						listViewVendas.invalidateViews();
						finish();
					}
				}).create();
		dialog.show();
	}

	public void atualizarVendaDAO(MenuItem item){
		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		final Venda vendaAtualizar = vendasFiltradas.get(menuInfo.position);

		Intent i = new Intent(this, FormsVenda.class);
		i.putExtra("vendas", vendaAtualizar);
		startActivity(i);
	}
}
