package com.example.agendacontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agendacontatos.dao.PessoaDao;
import com.example.agendacontatos.modelo.Pessoa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listVisivel;
    Button btnNovoCadastro;
    Pessoa pessoa;
    PessoaDao pessoaDao;
    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listVisivel = (ListView) findViewById(R.id.ListView);
        btnNovoCadastro = (Button) findViewById(R.id.btnNovoCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormPessoa.class);
                startActivity(i);
            }
        });
    }
  public void polulaLista() {
    pessoaDao = new PessoaDao(MainActivity.this);
    arrayListPessoa = pessoaDao.selectAllPessoa();
    pessoaDao.close();

    if (listVisivel != null){
        arrayAdapterPessoa = new ArrayAdapter<Pessoa>(MainActivity.this,android.R.layout.simple_list_item_1,arrayListPessoa);
        listVisivel.setAdapter(arrayAdapterPessoa);
    }
}

    @Override
    protected void onResume() {
        super.onResume();
        polulaLista();
    }
}