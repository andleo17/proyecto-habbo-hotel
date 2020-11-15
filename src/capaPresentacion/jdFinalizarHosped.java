/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaNegocio.clsHospedaje;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ROJASB
 */
public class jdFinalizarHosped extends javax.swing.JDialog {

    /**
     * Creates new form jdFinalizarHosped
     */
    clsHospedaje objHospedaje = new clsHospedaje();
    
    public jdFinalizarHosped(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblDias = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHab = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmpleados1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotalHab = new javax.swing.JTextField();
        txtTotalSer = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton1.setText("FINALIZAR HOSPEDAJE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 226, 247));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel3.setText("CANTIDAD DE DIAS ");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel4.setText("NOMBRES");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("APELLIDOS");

        lblApellido.setText("---");

        lblDias.setText("---");

        lblNombre.setText("---");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4)))
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblDias))
                .addGap(114, 114, 114)
                .addComponent(jLabel5)
                .addGap(39, 39, 39)
                .addComponent(lblApellido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(lblApellido)
                    .addComponent(lblNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblDias))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tblHab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° HAB", "TIPO", "COS"
            }
        ));
        tblHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHab);

        tblEmpleados1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SERVICIO", "N° HAB", "COSTO"
            }
        ));
        tblEmpleados1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleados1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEmpleados1);

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel10.setText("HABITACIONES");

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setText("SERVICIOS");

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FECHA INICIO");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DNI");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Buscar32.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel12.setText("TOTAL HOSPEDAJEL:");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel14.setText("TOTAL HAB");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel15.setText("TOTAL SERVICIO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalHab)
                            .addComponent(txtTotalSer)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtTotalHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHabMouseClicked

    }//GEN-LAST:event_tblHabMouseClicked

    private void tblEmpleados1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleados1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEmpleados1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ResultSet rs;
        try {
            if(txtDni.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese un Dni");
            }else{
                rs=objHospedaje.DatosHospedaje(txtDni.getText());
                if(rs.next()){
                    lblNombre.setText(rs.getString("nombres"));
                    lblApellido.setText(rs.getString("apellidos"));
                    lblDias.setText(String.valueOf(rs.getInt("dias")));
                    txtFecha.setText(rs.getString("fechaini"));
                    listarHabitaciones(txtDni.getText());
                    listarServicios(txtDni.getText());
                    Float hab = costoHabitaciones(txtDni.getText()) ;
                    Float ser = costoServicios(txtDni.getText()) ;
                    txtTotalHab.setText(String.valueOf(hab));
                    txtTotalSer.setText(String.valueOf(ser));
                    txtTotal.setText(String.valueOf(hab+ser));
                }else{
                    JOptionPane.showMessageDialog(this, "El cliente no tiene hospedajes registrados");
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jdFinalizarHosped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdFinalizarHosped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdFinalizarHosped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdFinalizarHosped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdFinalizarHosped dialog = new jdFinalizarHosped(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void listarHabitaciones(String dni ){
        ResultSet rs = null;
        Vector registro;
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Número de Habitación");
        modelo.addColumn("Tipo");
        modelo.addColumn("Precio");
        
        try {
            rs=objHospedaje.listarHospedajesCliente(dni);
            
            while(rs.next()){
                registro=new Vector();
                registro.add(0,rs.getInt("numerohab"));
                registro.add(1,rs.getString("descripcion"));
                registro.add(2,rs.getFloat("precio"));
                modelo.addRow(registro);
                    
            }
            
            tblHab.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }
    
    public void listarServicios(String dni ){
        ResultSet rs = null;
        Vector registro;
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Servicio");
        modelo.addColumn("Número de Habitación");
        modelo.addColumn("Precio");
        
        try {
            rs=objHospedaje.listarServiciosCliente(dni);
            
            while(rs.next()){
                registro=new Vector();
                registro.add(0,rs.getString("nombre"));
                registro.add(1,rs.getInt("numerohab"));
                registro.add(2,rs.getFloat("costototal"));
                modelo.addRow(registro);
                    
            }
            
            tblHab.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }    
    }
    
    public float costoHabitaciones(String dni){
        try {
            Float costo=objHospedaje.CostoTHabCliente(dni);
            return costo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return 0;
    }
    
    public float costoServicios(String dni){
        try {
            Float costo=objHospedaje.CostoTServiciosCliente(dni);
            return costo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return 0;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tblEmpleados1;
    private javax.swing.JTable tblHab;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalHab;
    private javax.swing.JTextField txtTotalSer;
    // End of variables declaration//GEN-END:variables
}
