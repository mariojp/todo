package br.com.mariojp.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroActivity extends ActionBarActivity {



    private EditText titulo;
    private Tarefa tarefaAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        titulo = (EditText) findViewById(R.id.titulo);

        Intent intent = getIntent();
        tarefaAlterar = (Tarefa) intent.getSerializableExtra("tarefa");

        if(tarefaAlterar!=null) {
            titulo.setText(tarefaAlterar.getTitulo());
        }

    }


    public void salvar(View view){
        Tarefa tarefa = new Tarefa();
        TarefaDAO dao = new TarefaDAO(this);
        tarefa.setTitulo(titulo.getText().toString());
        if ( tarefaAlterar == null) {
            dao.insere(tarefa);
        }else{
            tarefa.setId(tarefaAlterar.getId());
            dao.alterar(tarefa);
        }
        dao.close();
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
