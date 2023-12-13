/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import controlador.Controlador;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paugi
 */
public class GestorCompras extends javax.swing.JInternalFrame {
    Controlador c;
    DefaultTableModel dtm;
    boolean nuevoCliente;
    DefaultComboBoxModel<String> dcbmNif;
    DefaultComboBoxModel<String> dcbmDni;
    
    /**
     * Creates new form GestorVentas
     */
    public GestorCompras(Controlador c, int indicePaneGestorCompras) {
        initComponents();
        this.c = c; 
        
        jTabbedPaneClienteVenta.setSelectedIndex(0);
        jTabbedPaneClienteVenta.setEnabledAt(1, false);
        jTabbedPaneClienteVenta.setEnabledAt(0, true);
        
        if (indicePaneGestorCompras == 0) {
            jTabbedPaneGestorCompras.setSelectedIndex(0);
            jTabbedPaneGestorCompras.setEnabledAt(1, false);
            jTabbedPaneGestorCompras.setEnabledAt(0, true);
        } else {
            jTabbedPaneGestorCompras.setSelectedIndex(1);
            jTabbedPaneGestorCompras.setEnabledAt(1, true);
            jTabbedPaneGestorCompras.setEnabledAt(0, false);
        }
  
        IniciarTablaCompras();
        c.CargarTablaCompras(dtm);
        IniciarComboboxNif();
        c.CargarComboboxNif(dcbmNif);
        IniciarComboboxDni();
        c.CargarComboboxDni(dcbmDni);
        
        nuevoCliente = false;
    }
    
    private void IniciarTablaCompras() {
        dtm = new DefaultTableModel();
        jTableCompras.setModel(dtm);
        dtm.setColumnIdentifiers(new String[] {"Empleado", "Cliente", "Coche", "Fecha"} );
    }
    
    private void IniciarComboboxNif() {
        dcbmNif = new DefaultComboBoxModel<String>();
        jComboBoxNifEmpleadoVenta.setModel(dcbmNif);
    }
    
    private void IniciarComboboxDni() {
        dcbmDni = new DefaultComboBoxModel<String>();
        jComboBoxDniClienteExistente.setModel(dcbmDni);
    }
    
    private void IniciarDatosEmpleado(String nif) {
        String nombre = c.CargarNombreEmpleado(nif);
        String apellidos = c.CargarApellidosEmpleado(nif);
        String dni = c.CargarDniEmpleado(nif);
        
        jLabelNombreEmpleadoCompra.setText(nombre);
        jLabelApellidosEmpleadoCompra.setText(apellidos);
        jLabelDniEmpleadoCompra.setText(dni);
    }
    
    private void IniciarDatosCliente(String dni) {
        String nombre = c.CargarNombreCliente(dni);
        String apellidos = c.CargarApellidosCliente(dni);
        
        jLabelNombreClienteExistente.setText(nombre);
        jLabelApellidosClienteExistente.setText(apellidos);
    }
    
    private void ModoRegistrarVenta() {
        jTabbedPaneGestorCompras.setSelectedIndex(1);
        jTabbedPaneGestorCompras.setEnabledAt(1, true);
        jTabbedPaneGestorCompras.setEnabledAt(0, false);
    }
    
    private void VolverALista() {
        jTabbedPaneGestorCompras.setSelectedIndex(0);
        jTabbedPaneGestorCompras.setEnabledAt(0, true);
        jTabbedPaneGestorCompras.setEnabledAt(1, false);
    }
    
    private void UsarClienteNuevo() {
        nuevoCliente = true;
        jTabbedPaneClienteVenta.setSelectedIndex(1);
        jTabbedPaneClienteVenta.setEnabledAt(1, true);
        jTabbedPaneClienteVenta.setEnabledAt(0, false);
    }
    
    private void UsarClienteExistente() {
        nuevoCliente = false;
        jTabbedPaneClienteVenta.setSelectedIndex(0);
        jTabbedPaneClienteVenta.setEnabledAt(0, true);
        jTabbedPaneClienteVenta.setEnabledAt(1, false);
    }
    
    private String ClienteNuevoOExistente() {
        String dni_cliente = null;
        
        if (nuevoCliente == true) {
            dni_cliente = jTextFieldDniNuevoClienteVenta.getText();
            String nombre = jTextFieldNombreNuevoClienteVenta.getText();
            String apellidos = jTextFieldApellidosNuevoClienteVenta.getText();
            c.RegistrarCliente(dni_cliente, nombre, apellidos);
            
            dni_cliente = jTextFieldDniNuevoClienteVenta.getText();
        } else {
            dni_cliente = String.valueOf(jComboBoxDniClienteExistente.getSelectedItem());
        }
        
        return dni_cliente;
    }
    
    private void RegistrarCompra() {
        String nif_empleado;
        String dni_cliente;
        long id_coche;
        String fecha;
        
        nif_empleado = String.valueOf(jComboBoxNifEmpleadoVenta.getSelectedItem());
        dni_cliente = ClienteNuevoOExistente();
        id_coche = Long.parseLong(jTextFieldId.getText());
        fecha = c.ObtenerFechaActual();
        
        c.RegistrarCompra(nif_empleado, dni_cliente, id_coche, fecha);
    }
    
    private void RegistrarCocheConcesionario() {
        long id = Long.parseLong(jTextFieldId.getText());
        String marca = jTextFieldMarca.getText();
        String modelo = jTextFieldModelo.getText();
        long anyo = Long.parseLong(jTextFieldAnyo.getText());
        String color = jTextFieldColor.getText();
        double kilometraje = Double.parseDouble(jTextFieldKilometraje.getText());
        String disponibilidad = "Disponible";
        double precio = Long.parseLong(jTextFieldPrecio.getText());
        
        c.RegistrarCocheConcesionario(id, marca, modelo, anyo, color, kilometraje, disponibilidad, precio);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneGestorCompras = new javax.swing.JTabbedPane();
        jPanelListarCompras = new javax.swing.JPanel();
        jScrollPaneGestorCompras = new javax.swing.JScrollPane();
        jTableCompras = new javax.swing.JTable();
        jButtonModoRegistrarCompras = new javax.swing.JButton();
        jPanelRegistrarCompras = new javax.swing.JPanel();
        jPanelClienteCompra = new javax.swing.JPanel();
        jTabbedPaneClienteVenta = new javax.swing.JTabbedPane();
        jPanelClienteExistenteVenta = new javax.swing.JPanel();
        jComboBoxDniClienteExistente = new javax.swing.JComboBox<>();
        jLabelEtiquetaNombreClienteExistente = new javax.swing.JLabel();
        jLabelEtiquetaApellidosNombreClienteExistente = new javax.swing.JLabel();
        jLabelEtiquetaDniClienteExistente = new javax.swing.JLabel();
        jLabelNombreClienteExistente = new javax.swing.JLabel();
        jLabelApellidosClienteExistente = new javax.swing.JLabel();
        jButtonUsarClienteNuevo = new javax.swing.JButton();
        jPanelNuevoClienteVenta = new javax.swing.JPanel();
        jLabelEtiquetaDniNuevoClienteVenta = new javax.swing.JLabel();
        jLabelEtiquetaNombreNuevoClienteVenta = new javax.swing.JLabel();
        jLabelEtiquetaApellidosNuevoClienteVenta = new javax.swing.JLabel();
        jTextFieldDniNuevoClienteVenta = new javax.swing.JTextField();
        jTextFieldNombreNuevoClienteVenta = new javax.swing.JTextField();
        jTextFieldApellidosNuevoClienteVenta = new javax.swing.JTextField();
        jButtonUsarClienteExistente = new javax.swing.JButton();
        jPanelEmpleadoCompra = new javax.swing.JPanel();
        jComboBoxNifEmpleadoVenta = new javax.swing.JComboBox<>();
        jLabelEtiquetaNombreEmpleadoCompra = new javax.swing.JLabel();
        jLabelEtiquetaApellidosEmpleadoCompra = new javax.swing.JLabel();
        jLabelEtiquetaDniEmpleadoCompra = new javax.swing.JLabel();
        jLabelEtiquetaNifEmpleadoCompra = new javax.swing.JLabel();
        jLabelApellidosEmpleadoCompra = new javax.swing.JLabel();
        jLabelNombreEmpleadoCompra = new javax.swing.JLabel();
        jLabelDniEmpleadoCompra = new javax.swing.JLabel();
        jPanelRegistrarCoche = new javax.swing.JPanel();
        jLabelEtiquetaMarcaCocheCompra = new javax.swing.JLabel();
        jLabelEtiquetaModeloCocheCompra = new javax.swing.JLabel();
        jLabelEtiquetaAnyoCocheCompra = new javax.swing.JLabel();
        jLabelEtiquetaColorCocheCompra = new javax.swing.JLabel();
        jLabelEtiquetaCocheCompra = new javax.swing.JLabel();
        jLabelEtiquetaPrecioCocheCompra = new javax.swing.JLabel();
        jLabelEtiquetaIdCocheCompra = new javax.swing.JLabel();
        jLabelSimboloEuro = new javax.swing.JLabel();
        jLabelError = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldAnyo = new javax.swing.JTextField();
        jTextFieldColor = new javax.swing.JTextField();
        jTextFieldKilometraje = new javax.swing.JTextField();
        jTextFieldPrecio = new javax.swing.JTextField();
        jButtonVolverALista = new javax.swing.JButton();
        jButtonRegistrarCompra = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestor Compras");

        jTableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneGestorCompras.setViewportView(jTableCompras);

        jButtonModoRegistrarCompras.setText("Registrar compra -->");
        jButtonModoRegistrarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModoRegistrarComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelListarComprasLayout = new javax.swing.GroupLayout(jPanelListarCompras);
        jPanelListarCompras.setLayout(jPanelListarComprasLayout);
        jPanelListarComprasLayout.setHorizontalGroup(
            jPanelListarComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneGestorCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelListarComprasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonModoRegistrarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelListarComprasLayout.setVerticalGroup(
            jPanelListarComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListarComprasLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonModoRegistrarCompras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneGestorCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
        );

        jTabbedPaneGestorCompras.addTab("Listar", jPanelListarCompras);

        jPanelClienteCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jComboBoxDniClienteExistente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDniClienteExistenteItemStateChanged(evt);
            }
        });

        jLabelEtiquetaNombreClienteExistente.setText("Nombre: ");

        jLabelEtiquetaApellidosNombreClienteExistente.setText("Apellidos:");

        jLabelEtiquetaDniClienteExistente.setText("DNI:");

        jButtonUsarClienteNuevo.setText("Usar cliente nuevo -->");
        jButtonUsarClienteNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsarClienteNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelClienteExistenteVentaLayout = new javax.swing.GroupLayout(jPanelClienteExistenteVenta);
        jPanelClienteExistenteVenta.setLayout(jPanelClienteExistenteVentaLayout);
        jPanelClienteExistenteVentaLayout.setHorizontalGroup(
            jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteExistenteVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClienteExistenteVentaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonUsarClienteNuevo))
                    .addGroup(jPanelClienteExistenteVentaLayout.createSequentialGroup()
                        .addGroup(jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEtiquetaApellidosNombreClienteExistente)
                            .addComponent(jLabelEtiquetaNombreClienteExistente)
                            .addComponent(jLabelEtiquetaDniClienteExistente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxDniClienteExistente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNombreClienteExistente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelApellidosClienteExistente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanelClienteExistenteVentaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelEtiquetaApellidosNombreClienteExistente, jLabelEtiquetaDniClienteExistente, jLabelEtiquetaNombreClienteExistente});

        jPanelClienteExistenteVentaLayout.setVerticalGroup(
            jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteExistenteVentaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonUsarClienteNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaDniClienteExistente)
                    .addComponent(jComboBoxDniClienteExistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelEtiquetaNombreClienteExistente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNombreClienteExistente, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelClienteExistenteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaApellidosNombreClienteExistente)
                    .addComponent(jLabelApellidosClienteExistente, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelClienteExistenteVentaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxDniClienteExistente, jLabelApellidosClienteExistente, jLabelEtiquetaApellidosNombreClienteExistente, jLabelEtiquetaDniClienteExistente, jLabelEtiquetaNombreClienteExistente, jLabelNombreClienteExistente});

        jTabbedPaneClienteVenta.addTab("Existente", jPanelClienteExistenteVenta);

        jLabelEtiquetaDniNuevoClienteVenta.setText("DNI:");

        jLabelEtiquetaNombreNuevoClienteVenta.setText("Nombre:");

        jLabelEtiquetaApellidosNuevoClienteVenta.setText("Apellidos:");

        jButtonUsarClienteExistente.setText("<-- Usar cliente existente");
        jButtonUsarClienteExistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsarClienteExistenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNuevoClienteVentaLayout = new javax.swing.GroupLayout(jPanelNuevoClienteVenta);
        jPanelNuevoClienteVenta.setLayout(jPanelNuevoClienteVentaLayout);
        jPanelNuevoClienteVentaLayout.setHorizontalGroup(
            jPanelNuevoClienteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevoClienteVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNuevoClienteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNuevoClienteVentaLayout.createSequentialGroup()
                        .addComponent(jLabelEtiquetaDniNuevoClienteVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldDniNuevoClienteVenta))
                    .addGroup(jPanelNuevoClienteVentaLayout.createSequentialGroup()
                        .addComponent(jLabelEtiquetaApellidosNuevoClienteVenta)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldApellidosNuevoClienteVenta))
                    .addGroup(jPanelNuevoClienteVentaLayout.createSequentialGroup()
                        .addComponent(jLabelEtiquetaNombreNuevoClienteVenta)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldNombreNuevoClienteVenta))
                    .addGroup(jPanelNuevoClienteVentaLayout.createSequentialGroup()
                        .addComponent(jButtonUsarClienteExistente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelNuevoClienteVentaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelEtiquetaApellidosNuevoClienteVenta, jLabelEtiquetaDniNuevoClienteVenta, jLabelEtiquetaNombreNuevoClienteVenta});

        jPanelNuevoClienteVentaLayout.setVerticalGroup(
            jPanelNuevoClienteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevoClienteVentaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonUsarClienteExistente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNuevoClienteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaDniNuevoClienteVenta)
                    .addComponent(jTextFieldDniNuevoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNuevoClienteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaNombreNuevoClienteVenta)
                    .addComponent(jTextFieldNombreNuevoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNuevoClienteVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaApellidosNuevoClienteVenta)
                    .addComponent(jTextFieldApellidosNuevoClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelNuevoClienteVentaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelEtiquetaApellidosNuevoClienteVenta, jLabelEtiquetaDniNuevoClienteVenta, jLabelEtiquetaNombreNuevoClienteVenta});

        jTabbedPaneClienteVenta.addTab("Nuevo", jPanelNuevoClienteVenta);

        javax.swing.GroupLayout jPanelClienteCompraLayout = new javax.swing.GroupLayout(jPanelClienteCompra);
        jPanelClienteCompra.setLayout(jPanelClienteCompraLayout);
        jPanelClienteCompraLayout.setHorizontalGroup(
            jPanelClienteCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneClienteVenta)
                .addContainerGap())
        );
        jPanelClienteCompraLayout.setVerticalGroup(
            jPanelClienteCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClienteCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneClienteVenta)
                .addContainerGap())
        );

        jPanelEmpleadoCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        jComboBoxNifEmpleadoVenta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxNifEmpleadoVentaItemStateChanged(evt);
            }
        });
        jComboBoxNifEmpleadoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNifEmpleadoVentaActionPerformed(evt);
            }
        });

        jLabelEtiquetaNombreEmpleadoCompra.setText("Nombre:");

        jLabelEtiquetaApellidosEmpleadoCompra.setText("Apellidos:");

        jLabelEtiquetaDniEmpleadoCompra.setText("DNI:");

        jLabelEtiquetaNifEmpleadoCompra.setText("NIF:");

        javax.swing.GroupLayout jPanelEmpleadoCompraLayout = new javax.swing.GroupLayout(jPanelEmpleadoCompra);
        jPanelEmpleadoCompra.setLayout(jPanelEmpleadoCompraLayout);
        jPanelEmpleadoCompraLayout.setHorizontalGroup(
            jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmpleadoCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEtiquetaDniEmpleadoCompra)
                    .addComponent(jLabelEtiquetaApellidosEmpleadoCompra)
                    .addComponent(jLabelEtiquetaNombreEmpleadoCompra)
                    .addComponent(jLabelEtiquetaNifEmpleadoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxNifEmpleadoVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelApellidosEmpleadoCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNombreEmpleadoCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDniEmpleadoCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelEmpleadoCompraLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelEtiquetaApellidosEmpleadoCompra, jLabelEtiquetaDniEmpleadoCompra, jLabelEtiquetaNifEmpleadoCompra, jLabelEtiquetaNombreEmpleadoCompra});

        jPanelEmpleadoCompraLayout.setVerticalGroup(
            jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmpleadoCompraLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaNifEmpleadoCompra)
                    .addComponent(jComboBoxNifEmpleadoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEtiquetaNombreEmpleadoCompra)
                    .addComponent(jLabelNombreEmpleadoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaApellidosEmpleadoCompra)
                    .addComponent(jLabelApellidosEmpleadoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEmpleadoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEtiquetaDniEmpleadoCompra)
                    .addComponent(jLabelDniEmpleadoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelEmpleadoCompraLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxNifEmpleadoVenta, jLabelApellidosEmpleadoCompra, jLabelDniEmpleadoCompra, jLabelEtiquetaApellidosEmpleadoCompra, jLabelEtiquetaDniEmpleadoCompra, jLabelEtiquetaNifEmpleadoCompra, jLabelEtiquetaNombreEmpleadoCompra, jLabelNombreEmpleadoCompra});

        jPanelRegistrarCoche.setBorder(javax.swing.BorderFactory.createTitledBorder("Coche"));

        jLabelEtiquetaMarcaCocheCompra.setText("Marca:");

        jLabelEtiquetaModeloCocheCompra.setText("Modelo:");

        jLabelEtiquetaAnyoCocheCompra.setText("Anyo:");

        jLabelEtiquetaColorCocheCompra.setText("Color:");

        jLabelEtiquetaCocheCompra.setText("Kilometraje:");

        jLabelEtiquetaPrecioCocheCompra.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelEtiquetaPrecioCocheCompra.setText("Precio:");

        jLabelEtiquetaIdCocheCompra.setText("ID:");

        jLabelSimboloEuro.setText("€");

        jLabelError.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelError.setForeground(new java.awt.Color(255, 0, 0));
        jLabelError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelRegistrarCocheLayout = new javax.swing.GroupLayout(jPanelRegistrarCoche);
        jPanelRegistrarCoche.setLayout(jPanelRegistrarCocheLayout);
        jPanelRegistrarCocheLayout.setHorizontalGroup(
            jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                        .addGroup(jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                                .addComponent(jLabelEtiquetaMarcaCocheCompra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEtiquetaModeloCocheCompra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldModelo)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEtiquetaAnyoCocheCompra))
                            .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jTextFieldKilometraje)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEtiquetaPrecioCocheCompra)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                        .addComponent(jLabelEtiquetaColorCocheCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldColor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarCocheLayout.createSequentialGroup()
                        .addComponent(jLabelEtiquetaCocheCompra)
                        .addGap(495, 495, 495)
                        .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSimboloEuro))
                    .addComponent(jLabelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                        .addComponent(jLabelEtiquetaIdCocheCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldId)))
                .addContainerGap())
        );
        jPanelRegistrarCocheLayout.setVerticalGroup(
            jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarCocheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaIdCocheCompra)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaMarcaCocheCompra)
                    .addComponent(jLabelEtiquetaModeloCocheCompra)
                    .addComponent(jLabelEtiquetaAnyoCocheCompra)
                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaColorCocheCompra)
                    .addComponent(jTextFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistrarCocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEtiquetaCocheCompra)
                    .addComponent(jLabelEtiquetaPrecioCocheCompra)
                    .addComponent(jLabelSimboloEuro)
                    .addComponent(jTextFieldKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonVolverALista.setText("<-- Volver a listado");
        jButtonVolverALista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverAListaActionPerformed(evt);
            }
        });

        jButtonRegistrarCompra.setBackground(new java.awt.Color(0, 153, 255));
        jButtonRegistrarCompra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonRegistrarCompra.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegistrarCompra.setText("Registrar compra");
        jButtonRegistrarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRegistrarComprasLayout = new javax.swing.GroupLayout(jPanelRegistrarCompras);
        jPanelRegistrarCompras.setLayout(jPanelRegistrarComprasLayout);
        jPanelRegistrarComprasLayout.setHorizontalGroup(
            jPanelRegistrarComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistrarComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegistrarComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelRegistrarCoche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegistrarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelRegistrarComprasLayout.createSequentialGroup()
                        .addComponent(jButtonVolverALista, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelRegistrarComprasLayout.createSequentialGroup()
                        .addComponent(jPanelEmpleadoCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelClienteCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelRegistrarComprasLayout.setVerticalGroup(
            jPanelRegistrarComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistrarComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVolverALista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRegistrarComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelEmpleadoCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelClienteCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelRegistrarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRegistrarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneGestorCompras.addTab("Anyadir", jPanelRegistrarCompras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneGestorCompras)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneGestorCompras, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModoRegistrarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModoRegistrarComprasActionPerformed
        ModoRegistrarVenta();
    }//GEN-LAST:event_jButtonModoRegistrarComprasActionPerformed

    private void jButtonVolverAListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverAListaActionPerformed
        VolverALista();
    }//GEN-LAST:event_jButtonVolverAListaActionPerformed

    private void jButtonUsarClienteExistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsarClienteExistenteActionPerformed
        UsarClienteExistente();
    }//GEN-LAST:event_jButtonUsarClienteExistenteActionPerformed

    private void jComboBoxNifEmpleadoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNifEmpleadoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNifEmpleadoVentaActionPerformed

    private void jComboBoxNifEmpleadoVentaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxNifEmpleadoVentaItemStateChanged
        String nif = String.valueOf(jComboBoxNifEmpleadoVenta.getSelectedItem());
        IniciarDatosEmpleado(nif);
    }//GEN-LAST:event_jComboBoxNifEmpleadoVentaItemStateChanged

    private void jButtonRegistrarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarCompraActionPerformed
        RegistrarCocheConcesionario();
        RegistrarCompra();
        
        IniciarTablaCompras();
        c.CargarTablaCompras(dtm);
        
        jTextFieldId.setText("");
        jTextFieldMarca.setText("");
        jTextFieldModelo.setText("");
        jTextFieldAnyo.setText("");
        jTextFieldColor.setText("");
        jTextFieldKilometraje.setText("");
        jTextFieldPrecio.setText("");
        
        UsarClienteExistente();
        VolverALista();
    }//GEN-LAST:event_jButtonRegistrarCompraActionPerformed

    private void jButtonUsarClienteNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsarClienteNuevoActionPerformed
        UsarClienteNuevo();
    }//GEN-LAST:event_jButtonUsarClienteNuevoActionPerformed

    private void jComboBoxDniClienteExistenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDniClienteExistenteItemStateChanged
        String dni = String.valueOf(jComboBoxDniClienteExistente.getSelectedItem());
        IniciarDatosCliente(dni);
    }//GEN-LAST:event_jComboBoxDniClienteExistenteItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonModoRegistrarCompras;
    private javax.swing.JButton jButtonRegistrarCompra;
    private javax.swing.JButton jButtonUsarClienteExistente;
    private javax.swing.JButton jButtonUsarClienteNuevo;
    private javax.swing.JButton jButtonVolverALista;
    private javax.swing.JComboBox<String> jComboBoxDniClienteExistente;
    private javax.swing.JComboBox<String> jComboBoxNifEmpleadoVenta;
    private javax.swing.JLabel jLabelApellidosClienteExistente;
    private javax.swing.JLabel jLabelApellidosEmpleadoCompra;
    private javax.swing.JLabel jLabelDniEmpleadoCompra;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelEtiquetaAnyoCocheCompra;
    private javax.swing.JLabel jLabelEtiquetaApellidosEmpleadoCompra;
    private javax.swing.JLabel jLabelEtiquetaApellidosNombreClienteExistente;
    private javax.swing.JLabel jLabelEtiquetaApellidosNuevoClienteVenta;
    private javax.swing.JLabel jLabelEtiquetaCocheCompra;
    private javax.swing.JLabel jLabelEtiquetaColorCocheCompra;
    private javax.swing.JLabel jLabelEtiquetaDniClienteExistente;
    private javax.swing.JLabel jLabelEtiquetaDniEmpleadoCompra;
    private javax.swing.JLabel jLabelEtiquetaDniNuevoClienteVenta;
    private javax.swing.JLabel jLabelEtiquetaIdCocheCompra;
    private javax.swing.JLabel jLabelEtiquetaMarcaCocheCompra;
    private javax.swing.JLabel jLabelEtiquetaModeloCocheCompra;
    private javax.swing.JLabel jLabelEtiquetaNifEmpleadoCompra;
    private javax.swing.JLabel jLabelEtiquetaNombreClienteExistente;
    private javax.swing.JLabel jLabelEtiquetaNombreEmpleadoCompra;
    private javax.swing.JLabel jLabelEtiquetaNombreNuevoClienteVenta;
    private javax.swing.JLabel jLabelEtiquetaPrecioCocheCompra;
    private javax.swing.JLabel jLabelNombreClienteExistente;
    private javax.swing.JLabel jLabelNombreEmpleadoCompra;
    private javax.swing.JLabel jLabelSimboloEuro;
    private javax.swing.JPanel jPanelClienteCompra;
    private javax.swing.JPanel jPanelClienteExistenteVenta;
    private javax.swing.JPanel jPanelEmpleadoCompra;
    private javax.swing.JPanel jPanelListarCompras;
    private javax.swing.JPanel jPanelNuevoClienteVenta;
    private javax.swing.JPanel jPanelRegistrarCoche;
    private javax.swing.JPanel jPanelRegistrarCompras;
    private javax.swing.JScrollPane jScrollPaneGestorCompras;
    private javax.swing.JTabbedPane jTabbedPaneClienteVenta;
    private javax.swing.JTabbedPane jTabbedPaneGestorCompras;
    private javax.swing.JTable jTableCompras;
    private javax.swing.JTextField jTextFieldAnyo;
    private javax.swing.JTextField jTextFieldApellidosNuevoClienteVenta;
    private javax.swing.JTextField jTextFieldColor;
    private javax.swing.JTextField jTextFieldDniNuevoClienteVenta;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldKilometraje;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNombreNuevoClienteVenta;
    private javax.swing.JTextField jTextFieldPrecio;
    // End of variables declaration//GEN-END:variables
}
