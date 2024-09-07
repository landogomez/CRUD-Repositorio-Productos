package org.implementacion.service;

import org.implementacion.models.Categoria;
import org.implementacion.models.Producto;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Producto> getAll() throws SQLException;
    Producto porId(Long id) throws SQLException;
    Producto guardar(Producto producto) throws SQLException;
    void eliminar(Long id) throws SQLException;

    List<Categoria> listCategoria() throws SQLException;

    Categoria porIdCategoria(Long id) throws SQLException;
    Categoria guardarCategoria(Categoria categoria) throws SQLException;
    void eliminarCategoria(Long id) throws SQLException;
    void guardarPCC(Producto producto, Categoria categoria) throws SQLException;
}
