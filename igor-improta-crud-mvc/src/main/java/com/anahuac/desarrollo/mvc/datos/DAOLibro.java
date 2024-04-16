package com.anahuac.desarrollo.mvc.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.anahuac.desarrollo.mvc.entidades.Libro;

public class DAOLibro implements IDAOLibro{
    public Connection getConnection(){
        Connection conn = null;
        try {
            String dbPath = getClass().getResource("/libreria.db").toURI().getPath();
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e);
        }
        return conn;
    }

    public void crearTablaLibros(){
        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS libros (" +
                "id INTEGER PRIMARY KEY," +
                "titulo TEXT NOT NULL, " +
                "autor TEXT NOT NULL, " +
                "isbn TEXT NOT NULL); ");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error de creacion de tabla: " + e);
        }
    }

    public Libro crearLibro(String titulo, String autor, String isbn){
        int id = getMaxId() + 1;
        Libro libro = null;

        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO libros (id, titulo, autor, isbn) " +
            "VALUES (?, ?, ?, ?);" , Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.setString(2, titulo);
            ps.setString(3, autor);
            ps.setString(4, isbn);
            int filas = ps.executeUpdate();
            if(filas >= 1){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    libro = new Libro(id, titulo, autor, isbn);
                }
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error de insercion: " + e);
        }
        return libro;
    }

    public int getMaxId(){
        int maxId = 0;

        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT MAX(id) AS max_id FROM libros;");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al obtener el ID máximo: " + e);
        }

        return maxId;
    }
    
    public boolean existeLibroIsbn(String isbn){
        boolean existe = false;

        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM libros WHERE isbn = ?;");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al obtener el ID máximo: " + e);
        }

        return existe;
    }

    public Libro obtenerLibroId(int id){
        Libro libro = null;

        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT titulo, autor, isbn FROM libros WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                libro = new Libro(id, titulo, autor, isbn);
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al obtener libro: " + e);
        }

        return libro;
    }

    public boolean modificarLibro(Libro libro){
        boolean modificado = false;
        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE libros SET titulo = ?, autor = ?, isbn = ? WHERE id = ?;");
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setInt(4, libro.getId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                modificado = true;
            } 
            else {
                System.out.println("No hay este libro.");
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al modificar libro: " + e.getMessage());
        } 
        return modificado;
    }

    public boolean borrarLibroId(int id){
        boolean borrado = false;

        try {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM libros WHERE id = ?;");
            ps.setInt(1, id);
        
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                borrado = true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al borrar: " + e.getMessage());
        }
        
        return borrado;
    }
    
    public List<Libro> obtenerTodosLibros(){
        List<Libro> libros = new ArrayList<>();

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, titulo, autor, isbn FROM libros;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                libros.add(new Libro(id, titulo, autor, isbn));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los libros: " + e.getMessage());
            return null;
        }

        return libros;
    }
}
