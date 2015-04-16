package br.com.mariojp.todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Ciclo de Vida", "onCreate");



        Button botao = (Button) findViewById(R.id.botao);
        TextView rotulo = (TextView) findViewById(R.id.rotulo);
        Intent intent = getIntent();
        Tarefa tarefa = (Tarefa) intent.getSerializableExtra("tarefa");
        rotulo.setText(tarefa.getTitulo());

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        Log.i("Ciclo de Vida", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("Ciclo de Vida", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("Ciclo de Vida", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("Ciclo de Vida", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Ciclo de Vida", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i("Ciclo de Vida", "onRestart");
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
