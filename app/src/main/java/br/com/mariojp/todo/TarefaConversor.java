package br.com.mariojp.todo;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by mariojp on 11/05/2015.
 */
public class TarefaConversor {

    public String toJSON(List<Tarefa> tarefas) {
        try {
            JSONStringer jsonStringer = new JSONStringer();
            jsonStringer.object().key("list").array()
                    .object().key("aluno").array();
            for (Tarefa tarefa : tarefas) {
                jsonStringer.object()
                        .key("id").value(tarefa.getId())
                        .key("titulo").value(tarefa.getTitulo())
                        .endObject();
            }
            return jsonStringer.endArray().endObject()
                    .endArray().endObject().toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}


