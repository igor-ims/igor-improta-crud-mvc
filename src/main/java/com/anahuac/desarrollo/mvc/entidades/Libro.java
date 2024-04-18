package com.anahuac.desarrollo.mvc.entidades;

public class Libro {
    private int id;
    private String titulo, autor, isbn;
    
    public Libro(int id, String titulo, String autor, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }
    
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String toString(){
        return "ID: " + this.getId() +
            "\nTitulo: " + this.getTitulo() +
            "\nAutor: " + this.getAutor() +
            "\nISBN: " + this.getIsbn();
    }
}
