package br.com.mariojp.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariojp on 12/04/15.
 */
public class TarefaDAO extends SQLiteOpenHelper {


    private static final String DB_NAME = "tarefa.db";
    private static final int DB_VERSION = 1;


    public TarefaDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TAREFAS (id INTEGER PRIMARY KEY, titulo TEXT UNIQUE NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insere(Tarefa tarefa) {
        ContentValues valores = new ContentValues();
        valores.put("titulo", tarefa.getTitulo());
        getWritableDatabase().insert("TAREFAS", null, valores);
    }

    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        Cursor cursor = getReadableDatabase().query("TAREFAS", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(cursor.getLong(cursor.getColumnIndex("id")));
            tarefa.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            tarefas.add(tarefa);
        }
        return tarefas;
    }

    public void deletar(Tarefa tarefa) {

        String[] args = {tarefa.getId().toString()};
        getWritableDatabase().delete("TAREFAS", "id=?", args);
    }


    public void alterar(Tarefa tarefa) {
        ContentValues valores = new ContentValues();
        valores.put("titulo", tarefa.getTitulo());
        String[] args = {tarefa.getId().toString()};
        getWritableDatabase().update("TAREFAS",valores, "id=?", args);
    }
}
