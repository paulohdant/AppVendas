package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
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
    private Venda vendaAtualizar = null;
    private String auxIdCliente;

    public String getAuxIdCliente() {
        return auxIdCliente;
    }

    public void setAuxIdCliente(String auxIdCliente) {
        this.auxIdCliente = auxIdCliente;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_venda);
        carregarComponentes();
        carregarID();

        dao = new VendaDAO(this);

        Intent i = getIntent();
        if(i.hasExtra("vendas")){
            vendaAtualizar = (Venda) i.getSerializableExtra("vendas");

            Integer qnt = vendaAtualizar.getQuantidade();
            Float preco = vendaAtualizar.getPreco();
            Integer id = vendaAtualizar.getIdCliente();

            String auxQnt = qnt.toString();
            String auxPreco = preco.toString();
            String auxId = id.toString();

            etDescForm.setText(vendaAtualizar.getDescricao());
            etDataForm.setText(vendaAtualizar.getData());

            etQntForm.setText(auxQnt);
            etPrecoForm.setText(auxPreco);

            setAuxIdCliente(auxId);
        }

        btSalvarForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saaalvaaaaaaar();
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

    private void saaalvaaaaaaar() {
        if(vendaAtualizar == null){

            Venda vendaSalva = new Venda();

            String descricao = etDescForm.getText().toString();
            String preco = etPrecoForm.getText().toString();
            String quant = etQntForm.getText().toString();
            String data = etDataForm.getText().toString();


            Float precoFloat = Float.parseFloat(preco);
            Integer quantInt = Integer.parseInt(quant);


            vendaSalva.setDescricao(descricao);
            vendaSalva.setPreco(precoFloat);
            vendaSalva.setQuantidade(quantInt);
            vendaSalva.setData(data);
            vendaSalva.setIdCliente(idDefalut);

            dao.inserir(vendaSalva);
            Toast.makeText(this, "Venda cadastrada! ", Toast.LENGTH_LONG).show();
            finish();
        }else{
            String descricao = etDescForm.getText().toString();
            String preco = etPrecoForm.getText().toString();
            String quant = etQntForm.getText().toString();
            String data = etDataForm.getText().toString();
            String idAuxx = getAuxIdCliente();

            Float precoFloat = Float.parseFloat(preco);
            Integer quantInt = Integer.parseInt(quant);
            Integer idAuxxx = Integer.parseInt(idAuxx);

            vendaAtualizar.setDescricao(descricao);
            vendaAtualizar.setPreco(precoFloat);
            vendaAtualizar.setQuantidade(quantInt);
            vendaAtualizar.setData(data);
            vendaAtualizar.setIdCliente(idAuxxx);

            dao.atualizar(vendaAtualizar);
            Toast.makeText(this, "Venda modificada! ", Toast.LENGTH_LONG).show();
            finish();
        }
        finish();
    }
}



//        btSalvarForm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(etDescForm.getText().toString().isEmpty() || etPrecoForm.getText().toString().isEmpty()
//                || etQntForm.getText().toString().isEmpty() || etDataForm.getText().toString().isEmpty()) {
//                    Toast.makeText(FormsVenda.this, "Preencha todos os dados antes de salvar!", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    if(idDefalut != -1)
//                    salvar();
//                    else  {
//                        try {
//
//                            Integer num = Integer.parseInt(etQntForm.getText().toString());
//                            Float num2 = Float.parseFloat(etPrecoForm.getText().toString());
//
//                            dao.atualizar(idDefalut, num, etDescForm.getText().toString(), etDataForm.getText().toString(), num2, idDefalut);
//                            finish();
//                        }
//                        catch (Exception e) {
//                            Toast.makeText(FormsVenda.this, "Erro ao alterar venda!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//            }
//        });