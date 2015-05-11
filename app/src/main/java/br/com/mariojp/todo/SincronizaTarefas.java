package br.com.mariojp.todo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Objects;

/**
 * Created by mariojp on 11/05/2015.
 */
public class SincronizaTarefas extends AsyncTask<Object,Object,String>{

    ListActivity lista;
    ProgressDialog progress;
    public SincronizaTarefas(ListActivity lista){
        this.lista = lista;

    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(lista, "Aguarde...", "Sincronizando!!!", true);
    }

    @Override
    protected String doInBackground(Object... objects) {
        return "";
    }

    @Override
    protected void onPostExecute(String o) {
        progress.dismiss();
    }
}
