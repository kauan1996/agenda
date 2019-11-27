package com.example.agendacontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.agendacontatos.dao.PessoaDao;
import com.example.agendacontatos.modelo.Pessoa;

public class FormPessoa extends AppCompatActivity {
    EditText editNome, editIdade, editEndereco, editTelefone;
    Button btnVariavel;
    Pessoa pessoa, altpessoa;
    PessoaDao pessoaDao;
    long retornoDB;
    private PessoaDao PessoaDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pessoa);
        Intent i = getIntent();
        altpessoa = (Pessoa) i.getSerializableExtra("pessoa enviada");
        pessoa = new Pessoa();
        PessoaDao = new PessoaDao(FormPessoa.this);
        editNome = (EditText) findViewById(R.id.editNome);
        editIdade = (EditText) findViewById(R.id.editIdade);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editTelefone = (EditText) findViewById(R.id.Telefone);
        btnVariavel = (Button) findViewById(R.id.btnVariavel);

        if (altpessoa != null) {
            btnVariavel.setText("alterar");
        } else {
            btnVariavel.setText("salvar");
        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pessoa.setNome(editNome.getText().toString());
                pessoa.setIdade(Integer.parseInt(editIdade.getText().toString()));
                pessoa.setEndereco(editEndereco.getText().toString());
                pessoa.setTelefone(editTelefone.getText().toString());
            if (btnVariavel.getText().toString().equals("salvar")) {
             retornoDB = pessoaDao.salvarPessoa(pessoa);
             if (retornoDB == -1) {
                 alert("erro");
             }
            }else{
                alert("Cadastro");

            }

            }
        });
    }
private void alert (String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
}

}



