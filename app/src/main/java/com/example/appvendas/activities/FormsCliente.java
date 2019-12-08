package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.SearchView;
import android.widget.Toast;

import com.example.appvendas.models.Cliente;
import com.example.appvendas.data.ClienteDAO;
import com.example.appvendas.R;

public class FormsCliente extends AppCompatActivity {

    private Integer id;
    private EditText nome;
    private EditText numero;
    private Button btSalvarCliente;
    private ClienteDAO dao;
    private Cliente cliente = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_cliente);
        nome = findViewById(R.id.etNomeCliente);
        numero = findViewById(R.id.etNumeroCliente);
        btSalvarCliente = findViewById(R.id.btSalvarCliente);
        dao = new ClienteDAO(this);

        Intent i = getIntent();
        if(i.hasExtra("cliente")){
            cliente = (Cliente) i.getSerializableExtra("cliente");
            nome.setText(cliente.getNome());
            numero.setText(cliente.getNumero());

        }
    }

//    public void setup(View view) {
//        if(cliente == null) {
//            cliente = new Cliente();
//            cliente.setNome(nome.getText().toString());
//            cliente.setNumero(numero.getText().toString());
//            long id = dao.inserir(cliente);
//            Toast.makeText(this, "Pessoa inserida: " + id, Toast.LENGTH_SHORT).show();
//            System.out.println(cliente.getNome() + cliente.getNumero());
//            finish();
//        }else{
//            cliente.setNome(nome.getText().toString());
//            cliente.setNumero(numero.getText().toString());
//            dao.atualizarClienteDAO(cliente);
//            Toast.makeText(this, "Pessoa atualizada: ", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//    }
}
