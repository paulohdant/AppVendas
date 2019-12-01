package com.example.appvendas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormsCliente extends AppCompatActivity {

    private Integer id;
    private EditText nome;
    private EditText numero;
    private Button btSalvarCliente;
    private ClienteDAO dao;

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

    }

    public void setup(View view) {
        Cliente a = new Cliente();
        a.setNome(nome.getText().toString());
        a.setNumero(numero.getText().toString());
        long id = dao.inserir(a);
        Toast.makeText(this, "Pessoa inserida: " + id, Toast.LENGTH_SHORT).show();
        System.out.println( a.getNome() + a.getNumero());
    }

}
