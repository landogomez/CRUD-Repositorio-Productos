package org.implementacion.service;

import org.implementacion.models.Categoria;
import org.implementacion.models.Producto;
import org.implementacion.rep.CategoriaRepositorio;
import org.implementacion.rep.ProductoRepositorio;
import org.implementacion.rep.Repositorio;
import org.implementacion.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Catalogo implements  Service{
    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;

    public Catalogo(){
        this.productoRepositorio = new ProductoRepositorio();
        this.categoriaRepositorio = new CategoriaRepositorio();
    }
    @Override
    public List<Producto> getAll() throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            return productoRepositorio.getAll();
        }
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            return productoRepositorio.porId(id);
        }
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            Producto nuevoP = null;
            try{
                nuevoP = productoRepositorio.guardar(producto);
                conn.commit();
            }catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
            return nuevoP;
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            try{
                productoRepositorio.eliminar(id);
                conn.commit();
            }catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Categoria> listCategoria() throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.getAll();
        }
    }

    @Override
    public Categoria porIdCategoria(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.porId(id);
        }
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            Categoria nuevaCategoria = null;
            try{
                nuevaCategoria = categoriaRepositorio.guardar(categoria);
                conn.commit();
            }catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
            return nuevaCategoria;
        }
    }

    @Override
    public void eliminarCategoria(Long id) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            try{
                categoriaRepositorio.eliminar(id);
                conn.commit();
            }catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public void guardarPCC(Producto producto, Categoria categoria) throws SQLException {
        try (Connection conn = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConn(conn);
            categoriaRepositorio.setConn(conn);
            if (conn.getAutoCommit())
                conn.setAutoCommit(false);
            try{
                Categoria nuevaCategoria = categoriaRepositorio.guardar(categoria);
                producto.setCategoria(nuevaCategoria);
                productoRepositorio.guardar(producto);
                conn.commit();
            }catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
}
