package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appvendas.models.Cliente;
import com.example.appvendas.data.ClienteDAO;
import com.example.appvendas.R;

public class FormsCliente extends AppCompatActivity {
    private EditText etNomeCliente;
    private EditText etNumeroCliente;
    private Button btSalvarCliente;
    private Integer idDefalut = -1;
    private Cliente cliente = new Cliente();
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_cliente);
        carregarComponentes();
        carregarDados();

        dao = new ClienteDAO(this);

        btSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNomeCliente.getText().toString().isEmpty() || etNumeroCliente.getText().toString().isEmpty()) {
                    Toast.makeText(FormsCliente.this, "Preencha todos os dados antes de salvar!", Toast.LENGTH_LONG).show();
                }
                else {
                    if(idDefalut == -1) {
                        salvar();
                    }
                    else {
                        try {
                            dao.atualizar(idDefalut, etNomeCliente.getText().toString(), etNumeroCliente.getText().toString());
                            finish();
                        }
                        catch (Exception e) {
                            Toast.makeText(FormsCliente.this, "Erro ao alterar cliente!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    private void carregarComponentes() {
        etNomeCliente = findViewById(R.id.etNomeCliente);
        etNumeroCliente = findViewById(R.id.etNumeroCliente);
        btSalvarCliente = findViewById(R.id.btSalvarCliente);
    }

    private void carregarDados() {
        Intent intent = getIntent();

        Integer id = intent.getIntExtra("id", -1);
        String nome = intent.getStringExtra("nome");
        String numero = intent.getStringExtra("numero");

        idDefalut = id;

        etNomeCliente.setText(nome);
        etNumeroCliente.setText(numero);
    }

    private void salvar() {
        try {
            String nome = etNomeCliente.getText().toString();
            String numero = etNumeroCliente.getText().toString();

            cliente.setNome(nome);
            cliente.setNumero(numero);

            dao.inserir(cliente);
            finish();
        }
        catch (Exception e) {
            Toast.makeText(this, "Erro ao salvar cliente!", Toast.LENGTH_LONG).show();
        }
    }
}
