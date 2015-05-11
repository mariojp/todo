package br.com.mariojp.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mariojp on 11/04/15.
 */
public class ListActivity extends ActionBarActivity {

    private ListView lista;

    private Tarefa tarefaSelecionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        lista = (ListView) findViewById(R.id.lista);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                Tarefa valor = (Tarefa) adapter.getItemAtPosition(posicao);
                intent.putExtra("tarefa", valor);
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Toast.makeText(ListActivity.this,"Item: "+adapter.getItemAtPosition(posicao),Toast.LENGTH_SHORT).show();
                tarefaSelecionada = (Tarefa) adapter.getItemAtPosition(posicao);
                return false;
            }
        });

        registerForContextMenu(lista);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                Toast.makeText(this,"OPCOES",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_nova:
                Intent intent = new Intent(this, CadastroActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_sync:
                TarefaDAO dao = new TarefaDAO(this);
                List<Tarefa> tarefas = dao.listar();
                String json = new TarefaConversor().toJSON(tarefas);
                String jsonDeResposta = new WebClient("http://www.meuservidor.com.br").post(json);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.editar:
                Intent intent = new Intent(this, CadastroActivity.class);
                intent.putExtra("tarefa", tarefaSelecionada);
                startActivity(intent);
                return true;
            case R.id.excluir:
                TarefaDAO dao = new TarefaDAO(this);
                dao.deletar(tarefaSelecionada);
                this.carregaLista();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void carregaLista(){
        TarefaDAO dao = new TarefaDAO(this);
        List<Tarefa> dados = dao.listar();
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, dados);
        lista.setAdapter(adapter);
    }



}
