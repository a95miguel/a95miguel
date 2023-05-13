package View;

import Controller.Dao;
import Model.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Medel Lozada
 */
public class ProductoJFrame extends javax.swing.JFrame {
    DefaultTableModel modelo;       
    
    /**
     * Creates new form ProductoJFrame
     */
    public ProductoJFrame() {
        initComponents();
        
        //Para poner al centro JPanel
        setLocationRelativeTo(null);
        setResizable(false);        
        //Color JFrame
        this.getContentPane().setBackground(new Color(254,255,255));
        btn_actualizar.setEnabled(false);        
        btn_Eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);
        cargarDatosTabla();
        cargarJCombo();
    }
    
  
    public void cargarJCombo(){
       ArrayList<Categoria>lista=Dao.lisCategoriaJCombo();
       for(int x=0;x<lista.size();x++){
           Categoria obj=new Categoria(lista.get(x).getIdCategoria(),lista.get(x).getNomCategoria());                      
           jComboCategoria.addItem(obj);
       }
    }
     public void cargarDatosTabla(){
         jTableProducto.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
         jTableProducto.getTableHeader().setOpaque(false);         
         jTableProducto.getTableHeader().setBackground(new Color(32,136,203));
         jTableProducto.getTableHeader().setForeground(new Color(00,00,00));
         
        String[] titulos = {"ID","Nombre", "Precio","Descripcion","Categoria"};
        modelo = new DefaultTableModel(null, titulos);
        String[] fila = new String[5];
        for(Producto a:Dao.listarProducto()){                          
            fila[0]=a.getIdProducto();
            fila[1]=a.getNomProducto();
            fila[2]=a.getPrecio();                 
            fila[3]=a.getDescripcion();
            fila[4]=a.getCategoria_id();         
             modelo.addRow(fila);
        }
        jTableProducto.setModel(modelo);
    }
    /***********************************************************************************************/
     public void seleccionJcomboAc(){
         jComboCategoria.removeAllItems();
         String combo=jTableProducto.getValueAt(jTableProducto.getSelectedRow(),4).toString();         
         jComboCategoria.addItem(combo);   
        ArrayList<Categoria>lista=Dao.lisCategoriaJCombo();
       for(int x=0;x<lista.size();x++){
           Categoria obj=new Categoria(lista.get(x).getIdCategoria(),lista.get(x).getNomCategoria());
           if(!combo.equals(lista.get(x).getNomCategoria())){
            jComboCategoria.addItem(obj);    
           }                      
       }
     }
     /**************************************************************************************************/
    public void limpiarDatos(){
        txt_nombre.setText("");
        txt_descripcion.setText("");
        txt_precio.setText("");
        txt_id.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboCategoria = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProducto = new javax.swing.JTable();
        btn_agregar = new javax.swing.JButton();
        txt_id = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_descripcion = new javax.swing.JTextArea();
        btn_Eliminar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_menu1 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Precio");

        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Descripcion");

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add_box_.png"))); // NOI18N
        jButton1.setText("Agregar categoria");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboCategoriaItemStateChanged(evt);
            }
        });
        jComboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCategoriaActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Categoria");

        jTableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableProducto.setFocusable(false);
        jTableProducto.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableProducto.setRowHeight(25);
        jTableProducto.setSelectionBackground(new java.awt.Color(0, 255, 0));
        jTableProducto.setShowVerticalLines(false);
        jTableProducto.getTableHeader().setReorderingAllowed(false);
        jTableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProducto);

        btn_agregar.setBackground(new java.awt.Color(153, 153, 255));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ic_input_add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        txt_descripcion.setColumns(20);
        txt_descripcion.setRows(5);
        jScrollPane2.setViewportView(txt_descripcion);

        btn_Eliminar.setBackground(new java.awt.Color(153, 153, 255));
        btn_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ic_delete.png"))); // NOI18N
        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_actualizar.setBackground(new java.awt.Color(153, 153, 255));
        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });

        btn_menu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home-house.png"))); // NOI18N
        btn_menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addComponent(btn_actualizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_menu1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_actualizar)
                            .addComponent(btn_Eliminar))))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btn_agregar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_menu1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        CategoriaJFrame cat= new CategoriaJFrame();
        cat.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:         
        if (txt_nombre.getText().isEmpty() || txt_precio.getText().isEmpty() || txt_descripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Verefique sus datos", "ERROR", JOptionPane.ERROR_MESSAGE);           
        }
        else{
        try{
        Producto a= new Producto();
        a.setNomProducto(txt_nombre.getText());
        a.setPrecio(txt_precio.getText());
        a.setDescripcion(txt_descripcion.getText());
            Categoria add=(Categoria)jComboCategoria.getSelectedItem();
            String id=add.getIdCategoria();        
        a.setCategoria_id(id);
        Dao.registrarProducto(a);
        JOptionPane.showMessageDialog(null, "Producto registrado");
        limpiarDatos();
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_Eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void jTableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductoMouseClicked
        // TODO add your handling code here:
        String id =(String)jTableProducto.getValueAt(jTableProducto.getSelectedRow(),0);
        String nombre =(String)jTableProducto.getValueAt(jTableProducto.getSelectedRow(),1);
        String precio =(String)jTableProducto.getValueAt(jTableProducto.getSelectedRow(),2);
        String descripcion =(String)jTableProducto.getValueAt(jTableProducto.getSelectedRow(),3);      
        
        
        seleccionJcomboAc();
        txt_id.setText(id);
        txt_nombre.setText(nombre);
        txt_precio.setText(precio);
        txt_descripcion.setText(descripcion);
        btn_actualizar.setEnabled(true);        
        btn_Eliminar.setEnabled(true);
        btn_agregar.setEnabled(false);
        
    }//GEN-LAST:event_jTableProductoMouseClicked

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        // TODO add your handling code here:
        if (txt_nombre.getText().isEmpty() || txt_descripcion.getText().isEmpty() || txt_precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Verefique sus datos", "ERROR", JOptionPane.ERROR_MESSAGE);            
        }
        else{
        try{
        Producto a= new Producto();                
        a.setIdProducto(txt_id.getText());                
        Dao.eliProducto(a);
        JOptionPane.showMessageDialog(null, "Producto Eliminado");
        limpiarDatos();
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_Eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu1ActionPerformed
        // TODO add your handling code here:
        dispose();
        dashboardJFrame obj= new dashboardJFrame();
        obj.setVisible(true);
    }//GEN-LAST:event_btn_menu1ActionPerformed

    private void jComboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCategoriaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboCategoriaActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
        if (txt_nombre.getText().isEmpty() || txt_descripcion.getText().isEmpty() || txt_precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Verefique sus datos", "ERROR", JOptionPane.ERROR_MESSAGE);            
        }
        else{
        try{
        Producto a= new Producto();
        a.setNomProducto(txt_nombre.getText());
        a.setPrecio(txt_precio.getText());        
        a.setDescripcion(txt_descripcion.getText());  
        Categoria add=(Categoria)jComboCategoria.getSelectedItem();
            String id=add.getIdCategoria().toString();        
            System.out.println(id);
        a.setCategoria_id(id);
        a.setIdProducto(txt_id.getText());                
        Dao.actProducto(a);
        JOptionPane.showMessageDialog(null, "Producto Actualizado");
        limpiarDatos();
        jComboCategoria.removeAllItems();
        cargarJCombo();
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_Eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);       
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void jComboCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboCategoriaItemStateChanged
      
    }//GEN-LAST:event_jComboCategoriaItemStateChanged

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
            java.util.logging.Logger.getLogger(ProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_menu1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProducto;
    private javax.swing.JTextArea txt_descripcion;
    private javax.swing.JLabel txt_id;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables
}
