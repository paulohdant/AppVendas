package com.example.appvendas.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.appvendas.adapters.AdapterVenda;
import com.example.appvendas.data.VendaDAO;
import com.example.appvendas.models.Cliente;
import com.example.appvendas.data.ClienteDAO;
import com.example.appvendas.R;
import com.example.appvendas.adapters.AdapterCliente;
import com.example.appvendas.models.Venda;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ClienteDAO dao;
    private ArrayList<Cliente> clientes;
    private ArrayList<Cliente> clientesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        dao = new ClienteDAO(this);
        clientes = dao.obterClientes();
        clientesFiltrados.addAll(clientes);

        AdapterCliente adapter = new AdapterCliente(this,  clientesFiltrados);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente clienteSelecionado = (Cliente) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, ListarVendasActivity.class);
                intent.putExtra("id", clienteSelecionado.getId());
                startActivity(intent);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_context, menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.buscaClientes).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraCliente(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void procuraCliente(String nome){
        clientesFiltrados.clear();
        for(Cliente a : clientes){
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())){
                clientesFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.sair){
            sair();
            return true;
        }
        else if(id == R.id.cadCliente){
            Intent i = new Intent(MainActivity.this, FormsCliente.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        clientes = dao.obterClientes();
        clientesFiltrados.clear();
        clientesFiltrados.addAll(clientes);
        listView.invalidateViews();
    }

    private void sair(){
        finish();
    }

    public void excluirClienteDAO(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente clienteExcluir = clientesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o cliente?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clientesFiltrados.remove(clienteExcluir);
                        clientes.remove(clienteExcluir);
                        dao.deletar(clienteExcluir.getId());
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizarClienteDAO(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente clienteAtualizar = clientesFiltrados.get(menuInfo.position);

        Intent i = new Intent(this, FormsCliente.class);
        i.putExtra("id", clienteAtualizar.getId());
        i.putExtra("nome", clienteAtualizar.getNome());
        i.putExtra("numero", clienteAtualizar.getNumero());
        startActivity(i);
    }

    public void cadastroVendaActivity(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente dadosCliente = clientesFiltrados.get(menuInfo.position);

        Intent i = new Intent(MainActivity.this, FormsVenda.class);
        i.putExtra("idCliente", dadosCliente.getId());
        startActivity(i);
    }
}

