package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
	}

	@Override
	protected void onResume() {
		super.onResume();
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
}
