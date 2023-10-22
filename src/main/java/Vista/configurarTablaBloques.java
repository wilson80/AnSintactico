
package Vista;

import backend.Sintactico.BloqueCodigo;
import backend.Sintactico.Instrucciones;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jonwil
 */

    public class configurarTablaBloques implements ActionListener{
    
    private List<BloqueCodigo> bloques;
    private JPanel panelContenedorBotonesBloques;
    private JPanel panelContenedorTabla;
    
    
    
    public configurarTablaBloques(JPanel panelContenedorBotonesBloques, JPanel contenedorTabla, List<BloqueCodigo> bloques) {
        this.panelContenedorBotonesBloques = panelContenedorBotonesBloques;
        this.panelContenedorTabla = contenedorTabla;
        this.bloques =  bloques;
        
        configurarpanelBotones_Bloques();
        
    }
    
    
    public void configurarpanelBotones_Bloques(){
        
        for (BloqueCodigo bloque : bloques) {
                JButton  botonBloque = new JButton(bloque.getInstruccion_del_Bloque());  //crea el Boton con el texto lexema 
                botonBloque.setBackground(new java.awt.Color(0, 0, 102));
        botonBloque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonBloque.setForeground(Color.white);
        
                accionMostrarBloque(botonBloque, bloque);//agrega las acciones al boton
                panelContenedorBotonesBloques.add(botonBloque);
            
        }
        
            
    }
        
    public void accionMostrarBloque(JButton boton, BloqueCodigo bloque){
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccionesEntabla(boton, bloque);
                
            }
        });
    }
    
    
    public void instruccionesEntabla(JButton boton, BloqueCodigo bloque){
        List<Instrucciones> listaInstrucciones  = bloque.getInstruccionesDentroBloque();
        panelContenedorTabla.removeAll();
        for (Instrucciones list_Instruccione : listaInstrucciones) {
            JLabel instruccion = new JLabel(list_Instruccione.getId());
            instruccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            panelContenedorTabla.add(instruccion);
        }
        panelContenedorTabla.updateUI();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
}
