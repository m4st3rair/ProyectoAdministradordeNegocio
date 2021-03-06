package ProyectoAdministrador;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tonit
 */
public class SeleccionarCliente extends javax.swing.JFrame {

    /**
     * Creates new form SeleccionarCliente
     */
    public SeleccionarCliente() {
        obj= new Ventana(1);
        initComponents();
        cargarClientes();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void cargarClientes(){
        listaSec.clear();
         
        for (int i = 0; i < obj.listaClientes.getListaClientes().size(); i++) {
            String todoText="";
            System.out.println(todoText);
            listaSec.addElement(obj.listaClientes.getListaClientes().get(i).getNombre());
        }

    
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>(listaSec);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        guardar();
        obj.listaClientes.getListaClientes().get(index3).getIDVentas().add(""+obj.listaClientes.getListaClientes().get(index3).getIDC()+obj.jLabel36.getText());
        obj.listaClientes.guardar();
        this.dispose();
        obj.listaCompras.clear();
        obj.jPanel10.removeAll();
        obj.jLabel36.setText("0");
        obj.jPanel10.repaint();


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        index3=jList1.getSelectedIndex();
        
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ClienteAgregar agregar= new ClienteAgregar();
        agregar.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

        public void guardar(){
            File archivo = null;
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {   
            fichero = new FileWriter(folder2+"\\"+obj.listaClientes.getListaClientes().get(index3).getIDC()+obj.jLabel36.getText()+".txt");
            archivo = new File(folder2+"\\"+obj.listaClientes.getListaClientes().get(index3).getIDC()+obj.jLabel36.getText()+".txt");
            
            pw = new PrintWriter(fichero);
            
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(null,"Creando Nota");
                try {
                    // A partir del objeto File creamos el fichero físicamente
                    if (archivo.createNewFile())
                      System.out.println("El fichero se ha creado correctamente");
                    else
                      System.out.println("No ha podido ser creado el fichero");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            
            pw.println(obj.listaClientes.getListaClientes().get(index3).getIDC());
            pw.println(obj.listaClientes.getListaClientes().get(index3).getIDC()+obj.jLabel36.getText());
            pw.println(obj.jLabel36.getText());
            pw.println(obj.jLabel18.getText());
            
            for (int i = 0; i < obj.listaCompras.size(); i++){ 
                pw.println(obj.listaCompras.get(i).getID().getText()+"$"+obj.listaCompras.get(i).getCombo().getSelectedIndex()+"$"+obj.listaCompras.get(i).getTotaltotal());
            }
        }catch(Exception ed) {
            ed.printStackTrace();
        } finally {
            try {
                        // Nuevamente aprovechamos el finally para 
                        // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
            e2.printStackTrace();
            }
        }
    
    
    }
    
    
    
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
            java.util.logging.Logger.getLogger(SeleccionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionarCliente().setVisible(true);
            }
        });
    }
    int index3;
    DefaultListModel<String> listaSec= new DefaultListModel();
    Ventana obj;
    private File folder2 = new File("C:\\Archivos de programa\\DeltaSoftDB\\Archivos\\Notas");
    private ArrayList<Cliente> clienteBusqueda;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
