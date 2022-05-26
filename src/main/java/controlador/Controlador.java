/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlbdd.ControlBDD;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paugi
 */
public class Controlador {

    public ControlBDD controlBDD;

    public Controlador() {
        controlBDD = new ControlBDD();
    }
    
    public String ObtenerFechaActual() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fechaActual = dtf.format(now);
        
        return fechaActual;
    }

    public int ObtenerCuentaColumnas(ResultSet rs) {
        int count = 0;

        try {
            while (rs.next()) {
                ++count;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count == 0) {
            System.out.println("No records found");
        }
        
        return count;
    }

    public void CargarTablaClientes(DefaultTableModel modeloClientes) {
        String dni;
        String nombre;
        String apellidos;

        ResultSet rsTableCliente = controlBDD.obtenerResultSetDeClientes();

        try {
            while (rsTableCliente.next()) {                
                dni = rsTableCliente.getString("dni");
                nombre = rsTableCliente.getString("nombre");
                apellidos = rsTableCliente.getString("apellidos");
                
                modeloClientes.addRow(new Object[] {dni, nombre, apellidos});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CargarTablaEmpleados(DefaultTableModel modeloEmpleados) {
        String nif;
        String nombre;
        String apellidos;
        String dni;
        
        ResultSet rsTableEmpleado = controlBDD.obtenerResultSetDeEmpleados();

        try {
            while (rsTableEmpleado.next()) {                
                nif = rsTableEmpleado.getString("nif");
                nombre = rsTableEmpleado.getString("nombre");
                apellidos = rsTableEmpleado.getString("apellidos");
                dni = rsTableEmpleado.getString("dni");
                
                modeloEmpleados.addRow(new Object[] {nif, nombre, apellidos, dni});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CargarTablaCoches(DefaultTableModel modeloCoches) {
        long id;
        String marca;
        String modelo;
        long anyo;
        String color;
        double kilometraje;
        String disponibilidad;
        double precio;
        
        ResultSet rsTableCoches = controlBDD.obtenerResultSetDeCoches();

        try {
            while (rsTableCoches.next()) {  
                id = rsTableCoches.getLong("id");
                marca = rsTableCoches.getString("marca");
                modelo = rsTableCoches.getString("modelo");
                anyo = rsTableCoches.getLong("anyo");
                color = rsTableCoches.getString("color");
                kilometraje = rsTableCoches.getDouble("kilometraje");
                disponibilidad = rsTableCoches.getString("disponibilidad");
                precio = rsTableCoches.getDouble("precio");
                
                modeloCoches.addRow(new Object[] {id, marca, modelo, anyo, color, kilometraje, disponibilidad, precio});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CargarTablaCompras(DefaultTableModel modeloCompras) {
        String nif_empleado;
        String dni_cliente;
        long id_coche;
        String fecha;
        
        ResultSet rsTableComercioCompras = controlBDD.obtenerResultSetDeCompras();

        try {
            while (rsTableComercioCompras.next()) {                
                nif_empleado = rsTableComercioCompras.getString("nif_empleado");
                dni_cliente = rsTableComercioCompras.getString("dni_cliente");
                id_coche = rsTableComercioCompras.getLong("id_coche");
                fecha = rsTableComercioCompras.getString("fecha");
                
                modeloCompras.addRow(new Object[] {nif_empleado, dni_cliente, id_coche, fecha});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CargarTablaVentas(DefaultTableModel modeloVentas) {
        String nif_empleado;
        String dni_cliente;
        long id_coche;
        String fecha;
        
        ResultSet rsTableComercioVentas = controlBDD.obtenerResultSetDeVentas();

        try {
            while (rsTableComercioVentas.next()) {                
                nif_empleado = rsTableComercioVentas.getString("nif_empleado");
                dni_cliente = rsTableComercioVentas.getString("dni_cliente");
                id_coche = rsTableComercioVentas.getLong("id_coche");
                fecha = rsTableComercioVentas.getString("fecha");
                
                modeloVentas.addRow(new Object[] {nif_empleado, dni_cliente, id_coche, fecha});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CargarComboboxNif(DefaultComboBoxModel<String> modeloNif) {
        String nif;
        
        ResultSet rsNifEmpleados = controlBDD.obtenerResultSetDeNifEmpleados();

        try {
            while (rsNifEmpleados.next()) {                
                nif = rsNifEmpleados.getString("nif");                
                modeloNif.addElement(nif);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CargarComboboxDni(DefaultComboBoxModel<String> modeloDni) {
        String dni;
        
        ResultSet rsDniClientes = controlBDD.obtenerResultSetDeDniClientes();

        try {
            while (rsDniClientes.next()) {                
                dni = rsDniClientes.getString("dni");                
                modeloDni.addElement(dni);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CargarComboboxId_Coche(DefaultComboBoxModel<String> modeloId) {
        String id;
        ResultSet rsId_Coche = controlBDD.obtenerResultSetDeIdCochesDisponibles();

        try {
            while (rsId_Coche.next()) {                
                id = rsId_Coche.getString("id");
                modeloId.addElement(id);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String CargarNombreEmpleado(String nif) {        
        ResultSet rsEmpleado = controlBDD.obtenerResultSetDeEmpleado(nif);
        String nombre = null;

        try {
            rsEmpleado.next();
            nombre = rsEmpleado.getString("nombre");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nombre;
    }
    
    public String CargarApellidosEmpleado(String nif) {        
        ResultSet rsEmpleado = controlBDD.obtenerResultSetDeEmpleado(nif);
        String apellidos = null;

        try {
            rsEmpleado.next();
            apellidos = rsEmpleado.getString("apellidos");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return apellidos;
    }
    
    public String CargarDniEmpleado(String nif) {        
        ResultSet rsEmpleado = controlBDD.obtenerResultSetDeEmpleado(nif);
        String dni = null;

        try {
            rsEmpleado.next();
            dni = rsEmpleado.getString("dni");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dni;
    }
    
    public String CargarNombreCliente(String dni) {        
        ResultSet rsCliente = controlBDD.obtenerResultSetDeCliente(dni);
        String nombre = null;

        try {
            rsCliente.next();
            nombre = rsCliente.getString("nombre");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nombre;
    }
    
    public String CargarApellidosCliente(String dni) {        
        ResultSet rsCliente = controlBDD.obtenerResultSetDeCliente(dni);
        String apellidos = null;

        try {
            rsCliente.next();
            apellidos = rsCliente.getString("apellidos");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return apellidos;
    }
    
    public String CargarMarcaCocheConcesionarioDisponible(long id) {        
        ResultSet rsCoche_Concesionario = controlBDD.obtenerResultSetDeCocheConcesionarioDisponible(id);
        String marca = null;

        try {
            rsCoche_Concesionario.next();
            marca = rsCoche_Concesionario.getString("marca");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return marca;
    }
    
    public String CargarModeloCocheConcesionarioDisponible(long id) {        
        ResultSet rsCoche_Concesionario = controlBDD.obtenerResultSetDeCocheConcesionarioDisponible(id);
        String modelo = null;

        try {
            rsCoche_Concesionario.next();
            modelo = rsCoche_Concesionario.getString("modelo");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return modelo;
    }
    
    public String CargarAnyoCocheConcesionarioDisponible(long id) {        
        ResultSet rsCoche_Concesionario = controlBDD.obtenerResultSetDeCocheConcesionarioDisponible(id);
        String anyo = null;

        try {
            rsCoche_Concesionario.next();
            anyo = rsCoche_Concesionario.getString("anyo");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return anyo;
    }
    
    public String CargarColorCocheConcesionarioDisponible(long id) {        
        ResultSet rsCoche_Concesionario = controlBDD.obtenerResultSetDeCocheConcesionarioDisponible(id);
        String color = null;

        try {
            rsCoche_Concesionario.next();
            color = rsCoche_Concesionario.getString("color");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return color;
    }
    
    public String CargarKilometrajeCocheConcesionarioDisponible(long id) {        
        ResultSet rsCoche_Concesionario = controlBDD.obtenerResultSetDeCocheConcesionarioDisponible(id);
        String kilometraje = null;

        try {
            rsCoche_Concesionario.next();
            kilometraje = rsCoche_Concesionario.getString("kilometraje");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kilometraje;
    }
    
    public String CargarPrecioCocheConcesionarioDisponible(long id) {        
        ResultSet rsCoche_Concesionario = controlBDD.obtenerResultSetDeCocheConcesionarioDisponible(id);
        String precio = null;

        try {
            rsCoche_Concesionario.next();
            precio = rsCoche_Concesionario.getString("precio");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return precio;
    }
    
    
    public void IntroducirEnTablaEmpleados(String nif, String nombre, String apellidos, String dni, DefaultTableModel modeloEmpleados) {
        controlBDD.RegistrarEmpleado(nif, nombre, apellidos, dni);
        CargarTablaEmpleados(modeloEmpleados);
    }
    
    public void RegistrarCliente(String dni, String nombre, String apellidos) {
        controlBDD.RegistrarCliente(dni, nombre, apellidos);
    }
    
    public void RegistrarVenta(String nif_empleado, String dni_cliente, long id_coche, String fecha) {
        controlBDD.RegistrarComercio(nif_empleado, dni_cliente, id_coche, fecha, "Venta");
    }
    
    public void RegistrarCompra(String nif_empleado, String dni_cliente, long id_coche, String fecha) {
        controlBDD.RegistrarComercio(nif_empleado, dni_cliente, id_coche, fecha, "Compra");
    }
    
    public void RegistrarCocheConcesionario(long id, String marca, String modelo, long anyo, String color, double kilometraje, String disponibilidad, double precio) {
        controlBDD.RegistrarCoche(id, marca, modelo, anyo, color, kilometraje, disponibilidad, precio);
    }
    
    public boolean RegistrarUsuario(String nom_usuario, String contrasenya) {
        boolean registrado = false;
        
        try {
            String con_encriptada = encriptarContrasenya(contrasenya);
            controlBDD.RegistrarUsuario(nom_usuario, con_encriptada);
            registrado = true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return registrado;
    }
    
    public boolean VerificarInicioSesion(String nom_usuario, String contrasenya) {
        boolean verificacion = false;
        try {
            String con_encriptada = encriptarContrasenya(contrasenya);
            verificacion = controlBDD.ExisteUsuarios(nom_usuario, con_encriptada);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return verificacion;
    }
    
    public void CambiarDisponibilidadAVendido(long id) {
        controlBDD.cambiarDisponibilidadAVendido(id);
    }
    
    private String encriptarContrasenya (String contraseña) throws NoSuchAlgorithmException{
        
        String cod;
 
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(contraseña.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        cod = bigInt.toString(16);
        while(cod.length() < 32 ){
            cod = "0"+cod;
        }
        return cod;
    }
}
