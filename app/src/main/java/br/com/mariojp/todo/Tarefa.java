package br.com.mariojp.todo;

import java.io.Serializable;

/**
 * Created by mariojp on 12/04/15.
 */
public class Tarefa implements Serializable {

    private Long id;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
