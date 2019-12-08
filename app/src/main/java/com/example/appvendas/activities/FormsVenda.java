package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appvendas.R;
import com.example.appvendas.data.VendaDAO;
import com.example.appvendas.models.Venda;

public class FormsVenda extends AppCompatActivity {
    private EditText etDescForm;
    private EditText etPrecoForm;
    private EditText etQntForm;
    private EditText etDataForm;
    private Button btSalvarForm;
    private Venda venda = new Venda();
    private VendaDAO dao;
    private Integer idDefalut = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_venda);
        carregarComponentes();
        carregarID();

        dao = new VendaDAO(this);

        btSalvarForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDescForm.getText().toString().isEmpty() || etPrecoForm.getText().toString().isEmpty()
                || etQntForm.getText().toString().isEmpty() || etDataForm.getText().toString().isEmpty()) {
                    Toast.makeText(FormsVenda.this, "Preencha todos os dados antes de salvar!", Toast.LENGTH_LONG).show();
                }
                else {
                        salvar();
//                    else {
//                        try {
//                            dao.atualizar(idDefalut, etNomeCliente.getText().toString(), etNumeroCliente.getText().toString());
//                            finish();
//                        }
//                        catch (Exception e) {
//                            Toast.makeText(FormsCliente.this, "Erro ao alterar cliente!", Toast.LENGTH_LONG).show();
//                        }
//                    }
                }
            }
        });
    }

    private void carregarComponentes() {
        etDescForm = findViewById(R.id.etDescForm);
        etPrecoForm = findViewById(R.id.etPrecoForm);
        etQntForm = findViewById(R.id.etQntForm);
        etDataForm = findViewById(R.id.etDataForm);
        btSalvarForm = findViewById(R.id.btSalvarForm);
    }

    private void carregarID() {
        Intent intent = getIntent();

        Integer id = intent.getIntExtra("idCliente", -1);
        idDefalut = id;
    }

    private void salvar() {
        try {
            String descricao = etDescForm.getText().toString();
            String preco = etPrecoForm.getText().toString();
            String quant = etQntForm.getText().toString();
            String data = etDataForm.getText().toString();

            Float precoFloat = Float.parseFloat(preco);
            Integer quantInt = Integer.parseInt(quant);

            venda.setDescricao(descricao);
            venda.setPreco(precoFloat);
            venda.setQuantidade(quantInt);
            venda.setData(data);
            venda.setIdCliente(idDefalut);

            dao.inserir(venda);
            finish();
        }
        catch (Exception e) {
            Toast.makeText(this, "Erro ao salvar venda!", Toast.LENGTH_LONG).show();
        }
    }
}
