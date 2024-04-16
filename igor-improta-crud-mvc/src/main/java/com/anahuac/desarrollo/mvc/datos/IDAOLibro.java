package com.anahuac.desarrollo.mvc.datos;

import java.util.List;

import com.anahuac.desarrollo.mvc.entidades.Libro;



public interface IDAOLibro {
    int getMaxId();
    Libro crearLibro(String nombre, String autor, String isbn);
    Libro obtenerLibroId(int id);
    boolean modificarLibro(Libro libro);
    boolean borrarLibroId(int id);
    List<Libro> obtenerTodosLibros();
    boolean existeLibroIsbn(String isbn);
    void crearTablaLibros();
}
