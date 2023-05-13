/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Dao;
import Model.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Medel Lozada
 */
public class CategoriaJFrame extends javax.swing.JFrame {
    DefaultTableModel modelo;     
    /**
     * Creates new form CategoriaJFrame
     */
    public CategoriaJFrame() {
        initComponents();
        //Para poner al centro JPanel
        setLocationRelativeTo(null);
        setResizable(false);   
         //Color JFrame
        this.getContentPane().setBackground(new Color(254,255,255));		
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);
        
    }
    
    public void cargarDatosTabla(){
        jTableCategoria.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
         jTableCategoria.getTableHeader().setOpaque(false);         
         jTableCategoria.getTableHeader().setBackground(new Color(32,136,203));
         jTableCategoria.getTableHeader().setForeground(new Color(00,00,00));
         
        String[] titulos = {"ID","Nombre"};
        modelo = new DefaultTableModel(null, titulos);
        String[] fila = new String[2];

        for(Categoria a:Dao.listarCategoria()){                          
            fila[0]=a.getIdCategoria();
            fila[1]=a.getNomCategoria();
             modelo.addRow(fila);
        }
        jTableCategoria.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategoria = new javax.swing.JTable();
        btn_menu1 = new javax.swing.JButton();
        txt_id = new javax.swing.JLabel();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nombre");

        btn_agregar.setBackground(new java.awt.Color(153, 153, 255));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ic_input_add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        jTableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCategoria.setFocusable(false);
        jTableCategoria.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableCategoria.setRowHeight(25);
        jTableCategoria.setSelectionBackground(new java.awt.Color(0, 255, 0));
        jTableCategoria.setShowVerticalLines(false);
        jTableCategoria.getTableHeader().setReorderingAllowed(false);
        jTableCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCategoria);

        btn_menu1.setBackground(new java.awt.Color(153, 153, 255));
        btn_menu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/back_arrow_11236.png"))); // NOI18N
        btn_menu1.setText("Regresar");
        btn_menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu1ActionPerformed(evt);
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

        btn_eliminar.setBackground(new java.awt.Color(153, 153, 255));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ic_delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(btn_agregar))
                                .addGap(35, 35, 35))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_menu1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btn_actualizar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eliminar))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(btn_agregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_menu1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu1ActionPerformed
        // TODO add your handling code here:
        dispose();
        ProductoJFrame pro= new ProductoJFrame();
        pro.setVisible(true);
    }//GEN-LAST:event_btn_menu1ActionPerformed

    private void jTableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCategoriaMouseClicked
        // TODO add your handling code here:
        String  IdCategoria = (String) jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0);
        String nombre=(String) jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 1);
        
        txt_Nombre.setText(nombre);
        txt_id.setText(IdCategoria);
        btn_actualizar.setEnabled(true);        
        btn_eliminar.setEnabled(true);
        btn_agregar.setEnabled(false);
        
    }//GEN-LAST:event_jTableCategoriaMouseClicked

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:
        if (txt_Nombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Verefique sus datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else{
        try{
        Categoria a= new Categoria();
        a.setNomCategoria(txt_Nombre.getText());        
        Dao.registrarCategoria(a);
        JOptionPane.showMessageDialog(null, "Categoria registrado");
        txt_Nombre.setText("");
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
         if (txt_Nombre.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Verefique sus datos", "ERROR", JOptionPane.ERROR_MESSAGE);            
        }
        else{
        try{
        Categoria a= new Categoria();
        a.setNomCategoria(txt_Nombre.getText());
        a.setIdCategoria(txt_id.getText());        
        Dao.actCategoria(a);
        JOptionPane.showMessageDialog(null, "Categoria Actualizado");
        txt_Nombre.setText("");
        txt_id.setText("");
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (txt_Nombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Verefique sus datos", "ERROR", JOptionPane.ERROR_MESSAGE);            
        }
        else{
        try{
        Categoria a= new Categoria();                
        a.setIdCategoria(txt_id.getText());                
        Dao.eliCategoria(a);
        JOptionPane.showMessageDialog(null, "Categoria Eliminado");
        txt_Nombre.setText("");
        txt_id.setText("");
        cargarDatosTabla();
        btn_actualizar.setEnabled(false);        
        btn_eliminar.setEnabled(false);
        btn_agregar.setEnabled(true);
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
        
    }//GEN-LAST:event_btn_eliminarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_menu1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCategoria;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JLabel txt_id;
    // End of variables declaration//GEN-END:variables
}
