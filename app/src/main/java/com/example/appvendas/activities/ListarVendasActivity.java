package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appvendas.R;
import com.example.appvendas.adapters.AdapterVenda;
import com.example.appvendas.models.Venda;

import java.util.ArrayList;

public class ListarVendasActivity extends AppCompatActivity {
	private ListView listViewVendas;
	private TextView tvTotalDevedor;
	private ArrayList<Venda> vendas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listar_vendas_activity);
		carregarComponentes();

		AdapterVenda adapter = new AdapterVenda(this, vendas);
		listViewVendas.setAdapter(adapter);
	}

	private void carregarComponentes() {
		listViewVendas = findViewById(R.id.listViewVendas);
		tvTotalDevedor = findViewById(R.id.tvTotalDevedor);
	}
}
