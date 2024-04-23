package com.anahuac.desarrollo.mvc.vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import com.anahuac.desarrollo.mvc.entidades.Libro;
import com.anahuac.desarrollo.mvc.logica.ControllerLibro;

public class LibroForm extends JFrame implements ActionListener{
    private ControllerLibro controller;

    private JButton botonInsertar, botonObtenerUno, botonObtenerTodos, botonModificar, botonBorrar;
    private JTextField campoInsertarTitulo, campoInsertarAutor, campoInsertarIsbn;
    private JTextField campoObtenerId;
    private JTextField campoModificarId, campoModificarTitulo, campoModificarAutor, campoModificarIsbn;
    private JTextField campoBorrarId;

    public LibroForm(){
        super("Libreria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea la tabla libros si no existe
        this.controller = new ControllerLibro();
        controller.crearTablaLibros();

        // Pestanas de insertar, obtener, borrar y modificar
        JTabbedPane pestannas = new JTabbedPane();

        // Comenzo del panel insertar
        JPanel panelInsertar = new JPanel(new BorderLayout());
        
        JPanel insertarNorte = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel insertarSul = new JPanel(new BorderLayout());
        
        campoInsertarTitulo = new JTextField(); 
        campoInsertarAutor = new JTextField();
        campoInsertarIsbn = new JTextField();
        
        insertarNorte.add(new JLabel("Titulo: "));
        insertarNorte.add(campoInsertarTitulo);
        insertarNorte.add(new JLabel("Autor: "));
        insertarNorte.add(campoInsertarAutor);
        insertarNorte.add(new JLabel("ISBN: "));
        insertarNorte.add(campoInsertarIsbn);
        
        botonInsertar = new JButton("Insertar");
        botonInsertar.addActionListener(this);
        insertarSul.add(botonInsertar, BorderLayout.EAST);
        
        panelInsertar.add(insertarNorte, BorderLayout.NORTH);
        panelInsertar.add(insertarSul,BorderLayout.SOUTH);
        pestannas.addTab("Insertar", panelInsertar);
        
        // Comenzo del panel obtener
        JPanel panelObtener = new JPanel(new BorderLayout());
        JPanel obtenerNorte = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel obtenerSul = new JPanel(new BorderLayout());
        obtenerNorte.add(new JLabel("ID: "));
        campoObtenerId = new JTextField();
        obtenerNorte.add(campoObtenerId);

        panelObtener.add(obtenerNorte, BorderLayout.NORTH);

        botonObtenerUno = new JButton("Obtener Uno");
        botonObtenerUno.addActionListener(this);
        botonObtenerTodos = new JButton("Obtener Todos");
        botonObtenerTodos.addActionListener(this);
        obtenerSul.add(botonObtenerUno, BorderLayout.WEST);
        obtenerSul.add(botonObtenerTodos, BorderLayout.EAST);

        panelObtener.add(obtenerSul, BorderLayout.SOUTH);
        
        pestannas.addTab("Obtener", panelObtener);
        
        // Comenzo del panel modificar
        JPanel panelModificar = new JPanel(new BorderLayout());

        JPanel modificarNorte = new JPanel(new GridLayout(4, 2, 10, 10));
        JPanel modificarSul = new JPanel(new BorderLayout());

        modificarNorte.add(new JLabel("ID: "));
        campoModificarId = new JTextField();
        campoModificarId.addActionListener(this);
        modificarNorte.add(campoModificarId);
        modificarNorte.add(new JLabel("Titulo: "));
        campoModificarTitulo = new JTextField();
        modificarNorte.add(campoModificarTitulo);
        modificarNorte.add(new JLabel("Autor: "));
        campoModificarAutor = new JTextField();
        modificarNorte.add(campoModificarAutor);
        modificarNorte.add(new JLabel("ISBN: "));
        campoModificarIsbn = new JTextField();
        modificarNorte.add(campoModificarIsbn);

        botonModificar = new JButton("Modificar");
        botonModificar.addActionListener(this);
        modificarSul.add(botonModificar,BorderLayout.EAST);
        
        panelModificar.add(new JLabel("Presione ENTER despues de poner el ID para ver datos actuales."), BorderLayout.CENTER);
        panelModificar.add(modificarNorte, BorderLayout.NORTH);
        panelModificar.add(modificarSul, BorderLayout.SOUTH);

        pestannas.addTab("Modificar", panelModificar);

        // Comenzo del panel borrar
        JPanel panelBorrar = new JPanel(new BorderLayout());

        JPanel borrarNorte = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel borrarSul = new JPanel(new BorderLayout());

        borrarNorte.add(new JLabel("ID: "));
        campoBorrarId = new JTextField();
        borrarNorte.add(campoBorrarId);

        botonBorrar = new JButton("Borrar");
        botonBorrar.addActionListener(this);
        borrarSul.add(botonBorrar, BorderLayout.EAST);

        panelBorrar.add(borrarNorte, BorderLayout.NORTH);
        panelBorrar.add(borrarSul, BorderLayout.SOUTH);

        pestannas.addTab("Borrar", panelBorrar);
        
        // Agrega todas las pestanas al Frame
        add(pestannas);

        setSize(400, 300);
        setLocationRelativeTo(null); 
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == botonInsertar){
            String titulo = campoInsertarTitulo.getText();
            String autor = campoInsertarAutor.getText();
            String isbn = campoInsertarIsbn.getText();
            
            if(titulo != null && !titulo.equals("") &&
                autor != null && !autor.equals("") &&
                isbn != null && !isbn.equals("")){
                    Libro libro = controller.crearLibro(titulo, autor, isbn);
                    if(libro != null){
                        JOptionPane.showMessageDialog(null, "Libro Insertado:\n" + libro);
                        campoInsertarTitulo.setText("");
                        campoInsertarAutor.setText("");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Ya existe un libro con el ISBN '" + isbn + "'.");
                    }
                    campoInsertarIsbn.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Todos los campos son necesarios para insercion.");
            }
            
        }

        else if(e.getSource() == botonObtenerUno){
            String id = campoObtenerId.getText();

            if(id != null && !id.equals("")){
                Libro libro = controller.obtenerLibroId(Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, 
                    (libro != null) ? libro : ("ID " + id + " invalido o inexistente"));
                    campoObtenerId.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Es necesario digitar el ID del libro.");
            }
        }

        else if(e.getSource() == botonObtenerTodos){
            List<Libro> list = new ArrayList<>(controller.obtenerTodosLibros());
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay libros en esta libreria.");
                return;
            } 
            else {
                StringBuilder sb = new StringBuilder();

                list.forEach(libro -> sb.append(libro.toString() + "\n------------------------\n"));

                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 300));

                JOptionPane.showMessageDialog(null, scrollPane, "Lista de Libros", JOptionPane.PLAIN_MESSAGE);
            }
        }

        else if(e.getSource() == campoModificarId){
            String id = campoModificarId.getText();
            if(id != null && !id.equals("")){
                Libro libro = controller.obtenerLibroId(Integer.parseInt(id));
                if(libro != null){
                    campoModificarTitulo.setText(libro.getTitulo());
                    campoModificarAutor.setText(libro.getAutor());
                    campoModificarIsbn.setText(libro.getIsbn());
                }
                else{
                    JOptionPane.showMessageDialog(null, "No existe libro con el ID " + id + ".");
                }
            }
        }
        
        else if(e.getSource() == botonModificar){
            String id = campoModificarId.getText();
            String titulo = campoModificarTitulo.getText();
            String autor = campoModificarAutor.getText();
            String isbn = campoModificarIsbn.getText();

            if( id != null && !id.equals("") &&
                titulo != null && !titulo.equals("") &&
                autor != null && !autor.equals("") &&
                isbn != null && !isbn.equals("")){
                    Libro libro = controller.obtenerLibroId(Integer.parseInt(id));
                    if(libro != null){
                        if(controller.obtenerIdIsbn(isbn) == -1 || controller.obtenerIdIsbn(isbn) == Integer.parseInt(id)){
                            libro.setTitulo(titulo);
                            libro.setAutor(autor);
                            libro.setIsbn(isbn);
                            if(controller.modificarLibro(libro)){
                                JOptionPane.showMessageDialog(null, "Libro modificado:\n" + libro);
                                campoModificarId.setText("");
                                campoModificarTitulo.setText("");
                                campoModificarAutor.setText("");
                                campoModificarIsbn.setText("");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Error al modificar el libro." );
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Ya existe un libro con el ISBN '" + isbn + "'");
                            campoModificarIsbn.setText(isbn);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No existe libro con el ID " + id + ".");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados. " +
                        "Al seleccionar ENTER en el campo ID los campos son llenados con los datos actuales.");
                }

        }

        else if(e.getSource() == botonBorrar){
            String id = campoBorrarId.getText();

            if(id != null && !id.equals("")){
                if(controller.obtenerLibroId(Integer.parseInt(id)) != null){
                    boolean borrado = controller.borrarLibroId(Integer.parseInt(id));
                    JOptionPane.showMessageDialog(null, 
                        (borrado) ? "Libro borrado exitosamente." : "Error al borrar el libro.");
                        campoBorrarId.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No existe libro con el ID " + id);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "El campo ID debe ser llenado.");
            }
        }

    }
}
