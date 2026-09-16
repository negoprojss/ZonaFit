package zona_fit.datos;

import zona_fit.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;


public class ClienteDAO implements  IClienteDAO{
    @Override
    public List<Cliente> listaCliente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY ID";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMenbrecia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al consultar");
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion");
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorID(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente WHERE ID = ?";
        try
        {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMenbrecia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("error al buscar");
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: "+e);
            }
        }

        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement sp;
        Connection con = getConexion();
        var sql = "INSERT INTO cliente(nombre,apellido,membresia)" +
                "VALUES(?, ?, ?)";
        try{
            sp=con.prepareStatement(sql);
            sp.setString(1,cliente.getNombre());
            sp.setString(2,cliente.getApellido());
            sp.setInt(3,cliente.getMenbrecia());
            sp.execute();
            return true;
        }catch (Exception e){
            System.out.println("error al insertar: "+ e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: "+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET nombre=?,apellido=?,membresia=? " + " WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMenbrecia());
            ps.setInt(4,cliente.getId());
            ps.execute();
            return true;

        }catch (Exception e){
            System.out.println("Error al modificar: "+e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar Conexion: "+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM cliente WHERE ID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar: "+e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar Conexion: "+e.getMessage());
            }
        }
        return false;
    }

}
