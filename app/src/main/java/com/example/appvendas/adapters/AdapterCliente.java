package com.example.appvendas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appvendas.models.Cliente;
import com.example.appvendas.R;

import java.util.List;

public class AdapterCliente extends ArrayAdapter<Cliente> {

    private final Context context;
    private final List<Cliente> listaClientes;

        public AdapterCliente(Context context, List<Cliente> listaClientes){
            super(context, R.layout.listview_clientes, listaClientes);
            this.context = context;
            this.listaClientes = listaClientes;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.listview_clientes, parent, false);

        TextView tvNomeClientes = linha.findViewById(R.id.tvNomeClientes);
        TextView tvNumeroCliente = linha.findViewById(R.id.tvNumeroCliente);

        tvNomeClientes.setText(listaClientes.get(position).getNome());
        tvNumeroCliente.setText(listaClientes.get(position).getNumero());

        return linha;
    }
}
