
package Vista;

import backend.Sintactico.BloqueCodigo;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Jonwil
 */
public class PanelListaPorBloque_de_Codigo extends javax.swing.JPanel {
    
    private List<BloqueCodigo> bloques;
    
    public PanelListaPorBloque_de_Codigo(List<BloqueCodigo> bloques) {
        initComponents();
        this.bloques =  bloques;
        configurarTablaBloques configurar = new configurarTablaBloques(panelBotonesBloques, panelTabla, bloques);
        
    }
    
    public void setPanelBotonesBloques(JPanel panelBotonesBloques) {
        this.panelBotonesBloques = panelBotonesBloques;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonesBloques = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();

        setLayout(null);

        panelBotonesBloques.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBotonesBloques.setLayout(new java.awt.GridLayout(10, 1));
        add(panelBotonesBloques);
        panelBotonesBloques.setBounds(23, 6, 185, 420);

        panelTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelTabla.setLayout(new java.awt.GridLayout(10, 1));
        add(panelTabla);
        panelTabla.setBounds(220, 6, 760, 420);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelBotonesBloques;
    private javax.swing.JPanel panelTabla;
    // End of variables declaration//GEN-END:variables
}
