/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Framework.Diseño;

/**
 *
 * @author Gelen Ortiz
 */
public class InformacionRestaurante extends javax.swing.JFrame {

    /**
     * Creates new form InformacionRestaurante
     */
    public InformacionRestaurante() {
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

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 30));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Sitka Display", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("<html><p style=\"text-indent: 20px;\">Restaurante \"La Terreza del Sabor\"<br>      \n<p style=\"text-indent: 8px;\"><strong>Horario de Atención:</strong> Lunes a Domingo, de 19:00 a 23:00</p> \n<p style=\"text-indent: 8px;\">Bienvenido a <strong>La Terraza del Strong</strong>, donde la riqueza de la\n<p style=\"text-indent: 8px;\">tradición ecuatoriana se encuentra con la sofisticación moderna.<br>\n <p style=\"text-indent: 8px;\">Fundado en 2022, nuestro restaurante ofrece una experiencia \n<p style=\"text-indent: 8px;\">culinaria única, combinando ingredientes frescos y sabores\n<p style=\"text-indent: 8px;\">   autóctonos en un ambiente acogedor y elegante.         \n<p style=\"text-indent: 8px;\"> Nuestro personal dedicado está comprometido a brindar una\n<p style=\"text-indent: 8px;\"> velada inolvidable, ya sea para una cena romántica, una\n<p style=\"text-indent: 8px;\"> celebración especial o una reunión de negocios.</p>  \n<p style=\"text-indent: 8px;\"> Descubre el verdadero sabor del Ecuador en un entorno\n<p style=\"text-indent: 8px;\">amigable, déjate llevar por una experiencia gastronómica sin \n<p style=\"text-indent: 5px;\">igual.");
        jButton1.setActionCommand("<html>Restaurante \"La Terreza del Sabor\"<br>      \n<p style=\"text-indent: 20px;\"><strong>Horario de Atención:</strong> Lunes a Domingo, de 19:00 a 23:00</p> \n<p style=\"text-indent: 20px;\"><br>Bienvenido a <strong>La Terraza del Strong</strong>, donde la riqueza de la tradición\n<p style=\"text-indent: 20px;\"> ecuatoriana se encuentra con la sofisticación moderna.Fundado en 2022, nuestro restaurante ofrece una experiencia culinaria única, combinando ingredientes frescos y sabores autóctonos en un ambiente acogedor y elegante.</p>           \n<p style=\"text-indent: 20px;\">Nuestro personal dedicado está comprometido a brindar una velada inolvidable, ya sea para una cena romántica, una celebración especial o una reunión de negocios.</p>  \n <p style=\"text-indent: 20px;\">Descubre el verdadero sabor del Ecuador en un entorno amigable y elegante, y déjate llevar por una experiencia gastronómica sin igual.</p>");
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setInheritsPopupMenu(true);
        jButton1.setMargin(new java.awt.Insets(3, 14, 3, 14));
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 340, 290));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Framework/Imagenes/fondo-negro.jpg"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Framework/Imagenes/InfRestaurante1.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 0, 390, 381));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionRestaurante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
