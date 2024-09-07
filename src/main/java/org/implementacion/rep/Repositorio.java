package org.implementacion.rep;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T>{

    void setConn(Connection conn);
    List<T> getAll() throws SQLException;
    T porId(Long id) throws SQLException;
    T guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
