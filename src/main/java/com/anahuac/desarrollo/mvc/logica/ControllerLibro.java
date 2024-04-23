package com.anahuac.desarrollo.mvc.logica;

import java.util.List;

import com.anahuac.desarrollo.mvc.datos.DAOLibro;
import com.anahuac.desarrollo.mvc.datos.IDAOLibro;
import com.anahuac.desarrollo.mvc.entidades.Libro;

public class ControllerLibro {
    private IDAOLibro dao;

    public ControllerLibro() {
        this.dao = new DAOLibro();
    }

    public void crearTablaLibros(){
        this.dao.crearTablaLibros();
    }

    public int obtenerIdIsbn(String isbn){
        return this.dao.obtenerIdIsbn(isbn);
    }

    public Libro crearLibro(String nombre, String autor, String isbn){
        if(dao.obtenerIdIsbn(isbn) != -1){
            return null;
        }
        return dao.crearLibro(nombre, autor, isbn);
    }

    public Libro obtenerLibroId(int id){
        return dao.obtenerLibroId(id);
    }

    public boolean modificarLibro(Libro libro){
        return dao.modificarLibro(libro);    
    }

    public boolean borrarLibroId(int id){
        return dao.borrarLibroId(id);
    }

    public List<Libro> obtenerTodosLibros(){
        return dao.obtenerTodosLibros();
    }
}
