package org.implementacion.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T>{
    List<T> getAll() throws SQLException;
    T porId(Long id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
