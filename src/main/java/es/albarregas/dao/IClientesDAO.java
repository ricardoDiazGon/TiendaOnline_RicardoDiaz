package es.albarregas.dao;

import es.albarregas.beans.Cliente;
import java.util.ArrayList;

public interface IClientesDAO {

    public int addClientes(Cliente cliente);

    public ArrayList<Cliente> getClientes(String clausulaWhere);

    public int updClientes(Cliente cliente);

    public int delClientes(String clausulaWhere);

    public void closeConnection();
}
