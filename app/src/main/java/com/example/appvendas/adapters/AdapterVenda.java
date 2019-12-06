package com.example.appvendas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appvendas.R;
import com.example.appvendas.models.Venda;

import java.util.List;

public class AdapterVenda extends ArrayAdapter<Venda> {
    private final Context context;
    private final List<Venda> listaVendas;

    public AdapterVenda(Context context, List<Venda> listaVendas){
        super(context, R.layout.listview_vendas, listaVendas);
        this.context = context;
        this.listaVendas = listaVendas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.listview_vendas, parent, false);

        TextView tvDescricao = linha.findViewById(R.id.tvDescricao);
        TextView tvQuantidade = linha.findViewById(R.id.tvQuantidade);
        TextView tvData = linha.findViewById(R.id.tvData);
        TextView tvPreco = linha.findViewById(R.id.tvPreco);

        tvDescricao.setText(listaVendas.get(position).getDescricao());
        tvQuantidade.setText(listaVendas.get(position).getQuantidade());
        tvData.setText(listaVendas.get(position).getData());
        tvPreco.setText(listaVendas.get(position).getPreco());

        return linha;
    }
}
