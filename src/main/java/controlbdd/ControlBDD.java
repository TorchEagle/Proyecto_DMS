/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlbdd;

import conexion.Conexion;
import static conexion.Conexion.cn;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author paugi
 */
public class ControlBDD {
    public Conexion conexion;

    public ControlBDD() {
        conexion = new Conexion();
    }
    
    public boolean ExisteUsuarios(String nom_usuario, String contrasenya) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        boolean existe = false;
        
        try {
            st = cn.prepareStatement("SELECT * from usuario WHERE nom_usuario = ? AND contrasenya = ?;");
            st.setObject(1, nom_usuario);
            st.setObject(2, contrasenya);
            
            rs = st.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conexion.CerrarConexion();
        
        return existe;
    }
    
    public boolean ExisteNomUsuarios(String nom_usuario) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        boolean existe = false;
        
        try {
            st = cn.prepareStatement("SELECT * from usuario WHERE nom_usuario = ?;");
            st.setObject(1, nom_usuario);
            
            rs = st.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conexion.CerrarConexion();
        return existe;
    }
    
    public void RegistrarUsuario(String nom_usuario, String contrasenya) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        
        try {
            st = cn.prepareStatement("INSERT INTO usuario VALUES (?, ?);");
            st.setObject(1, nom_usuario);
            st.setObject(2, contrasenya);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        conexion.CerrarConexion();
    }
    
    public void RegistrarCliente(String dni, String nombre, String apellidos) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        
        try {
            st = cn.prepareStatement("INSERT INTO cliente VALUES (?, ?, ?);");
            st.setObject(1, dni);
            st.setObject(2, nombre);
            st.setObject(3, apellidos);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        conexion.CerrarConexion();
    }
    
    public void RegistrarEmpleado(String nif, String nombre, String apellidos, String dni) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        
        try {
            st = cn.prepareStatement("INSERT INTO empleado VALUES (?, ?, ?, ?);");
            st.setObject(1, nif);
            st.setObject(2, nombre);
            st.setObject(3, apellidos);
            st.setObject(4, dni);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        conexion.CerrarConexion();
    }
    
    public void RegistrarCoche(long id, String marca, String modelo, long anyo, String color, double kilometraje, String disponibilidad, double precio) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        
        try {
            st = cn.prepareStatement("INSERT INTO public.coche_concesionario"
                    + "(id, marca, modelo, anyo, color, kilometraje, disponibilidad, precio) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            st.setObject(1, id);
            st.setObject(2, marca);
            st.setObject(3, modelo);
            st.setObject(4, anyo);
            st.setObject(5, color);
            st.setObject(6, kilometraje);
            st.setObject(7, disponibilidad);
            st.setObject(8, precio);
            st.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        conexion.CerrarConexion();
    }
    
    public void RegistrarComercio(String nif_empleado, String dni_cliente, long id_coche, String fecha, String interaccion) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        
        try {
            st = cn.prepareStatement("INSERT INTO public.comercio(nif_empleado, dni_cliente, id_coche, fecha, interaccion) VALUES (?, ?, ?, ?, ?);");
            st.setObject(1, nif_empleado);
            st.setObject(2, dni_cliente);
            st.setObject(3, id_coche);
            st.setObject(4, fecha);
            st.setObject(5, interaccion);
            st.execute();
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        conexion.CerrarConexion();
    }
    
    public ResultSet obtenerResultSetDeClientes () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT * from cliente;");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeEmpleados () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT * FROM empleado");
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeEmpleado(String nif) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT * FROM empleado WHERE nif = ?");
            st.setObject(1, nif);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeCliente(String dni) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT * FROM cliente WHERE dni = ?");
            st.setObject(1, dni);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeCocheConcesionarioDisponible(long id) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT marca, modelo, anyo, color, kilometraje, precio FROM coche_concesionario WHERE id = ? AND disponibilidad = 'Disponible'");
            st.setObject(1, id);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeCoches () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT * FROM coche_concesionario");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeCompras() {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT id_venta, nif_empleado, dni_cliente, id_coche, fecha FROM comercio WHERE interaccion = 'Compra'");
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeVentas () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT id_venta, nif_empleado, dni_cliente, id_coche, fecha FROM comercio WHERE interaccion = 'Venta'");
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeMarcas() {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT distinct marca FROM modelo_coche;");
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeModelos(String marca) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT distinct modelo FROM modelo_coche WHERE marca = ?;");
            st.setObject(1, marca);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    public ResultSet obtenerResultSetDeAnyos(String marca, String modelo) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT distinct anyo FROM modelo_coche WHERE marca = ? AND modelo = ?;");
            st.setObject(1, marca);
            st.setObject(2, modelo);
            rs = st.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeNifEmpleados () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT nif FROM empleado");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeDniClientes () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT dni FROM cliente");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public ResultSet obtenerResultSetDeIdCochesDisponibles () {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("SELECT id FROM coche_concesionario WHERE disponibilidad = 'Disponible'");
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
        return rs;
    }
    
    public void cambiarDisponibilidadAVendido(long id) {
        conexion.ConectarBDD();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = cn.prepareStatement("UPDATE coche_concesionario SET disponibilidad = 'Vendido' WHERE id = ?");
            st.setObject(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        conexion.CerrarConexion();
    }
    
}
