
package Vista;

import Tokens.Token;
import Tokens.TokenEnum;
import backend.Sintactico.Instrucciones;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Jonwil
 */
public class VentanaReportes extends javax.swing.JFrame {
    JTable tablaReportes;
    JTable tablaSimbolos;
    private List<Token> listTokens;
    private List<Token> listTokenErrores;
    
    private List<Instrucciones> listExpresiones;
    private List<Instrucciones> listFunciones;
    private List<String> listInstruccionesBloque;
    private List<Instrucciones> listErroresInstrucciones;
    
    private JPanel panelInstruccionesPor_bloque_Codigo;
    private int filas;
    private static final int columnas =6;
    private  JScrollPane scrollPane;
    
    Object [][] datosTable;
    Object [][] datosExpresiones;
    Object [][] datosInstrucciones;
   
    public VentanaReportes(List<Token> listTokens) {
        initComponents();
        scrollPane = new JScrollPane();
//        scrollPane2 = new JScrollPane();
        this.listTokens = listTokens;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        labelNombreReporte.setText("Reportes Lexicos");
        botonLexicos.setVisible(false);
        botonSintacticos.setVisible(false);
        
        botonLexicos.setBackground(new java.awt.Color(0, 0, 102));
        botonLexicos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonLexicos.setForeground(Color.white);
        
        botonSintacticos.setBackground(new java.awt.Color(0, 0, 102));
        botonSintacticos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botonSintacticos.setForeground(Color.white);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        labelNombreReporte = new javax.swing.JLabel();
        botonSintacticos = new javax.swing.JButton();
        botonLexicos = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        menuTokens = new javax.swing.JMenu();
        menuSimboloGlobal = new javax.swing.JMenu();
        menuSimboloBloque = new javax.swing.JMenu();
        menuInstrucciones = new javax.swing.JMenu();
        menuFun_Met = new javax.swing.JMenu();
        menuLLamadas = new javax.swing.JMenu();
        menuErrores = new javax.swing.JMenu();
        menuParametros = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REPORTES");

        panelContenedor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panelContenedor.setLayout(new java.awt.GridLayout(1, 1));

        labelNombreReporte.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        labelNombreReporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        botonSintacticos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonSintacticos.setText("SINTACTICOS");
        botonSintacticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSintacticosActionPerformed(evt);
            }
        });

        botonLexicos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonLexicos.setText("LEXICOS");
        botonLexicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLexicosActionPerformed(evt);
            }
        });

        MenuBar.setBackground(new java.awt.Color(0, 51, 102));
        MenuBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        MenuBar.setForeground(new java.awt.Color(255, 255, 255));

        menuTokens.setForeground(new java.awt.Color(255, 255, 255));
        menuTokens.setText("TOKENS");
        menuTokens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTokensMouseClicked(evt);
            }
        });
        MenuBar.add(menuTokens);

        menuSimboloGlobal.setForeground(new java.awt.Color(255, 255, 255));
        menuSimboloGlobal.setText("SIMBOLOS(global)");
        menuSimboloGlobal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSimboloGlobalMouseClicked(evt);
            }
        });
        MenuBar.add(menuSimboloGlobal);

        menuSimboloBloque.setForeground(new java.awt.Color(255, 255, 255));
        menuSimboloBloque.setText("SIMBOLOS (bloque de codigo)");
        menuSimboloBloque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSimboloBloqueMouseClicked(evt);
            }
        });
        MenuBar.add(menuSimboloBloque);

        menuInstrucciones.setForeground(new java.awt.Color(255, 255, 255));
        menuInstrucciones.setText("INSTRUCCIONES (por bloque)");
        menuInstrucciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuInstruccionesMouseClicked(evt);
            }
        });
        MenuBar.add(menuInstrucciones);

        menuFun_Met.setForeground(new java.awt.Color(255, 255, 255));
        menuFun_Met.setText("FUNCIONES/METODOS");
        menuFun_Met.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuFun_MetMouseClicked(evt);
            }
        });
        MenuBar.add(menuFun_Met);

        menuLLamadas.setForeground(new java.awt.Color(255, 255, 255));
        menuLLamadas.setText("LLAMADAS ");
        menuLLamadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLLamadasMouseClicked(evt);
            }
        });
        MenuBar.add(menuLLamadas);

        menuErrores.setForeground(new java.awt.Color(255, 255, 255));
        menuErrores.setText("ERRORES");
        menuErrores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuErroresMouseClicked(evt);
            }
        });
        MenuBar.add(menuErrores);

        menuParametros.setForeground(new java.awt.Color(255, 255, 255));
        menuParametros.setText("PARAMETROS DE FUNCION");
        menuParametros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuParametrosMouseClicked(evt);
            }
        });
        MenuBar.add(menuParametros);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(labelNombreReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonLexicos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonSintacticos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(385, 385, 385))
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(labelNombreReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSintacticos)
                    .addComponent(botonLexicos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSimboloGlobalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSimboloGlobalMouseClicked
        configurarMenu();
        
        labelNombreReporte.setText("Tabla de simbolos global");
        
        llenarTablaglobal_Simbolos();
        configurarTabla_Simbolos();
        panelContenedor.add(scrollPane);
        
    }//GEN-LAST:event_menuSimboloGlobalMouseClicked

    private void menuTokensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTokensMouseClicked
        configurarMenu();
        verReportes();
        
        
    }//GEN-LAST:event_menuTokensMouseClicked

    private void menuSimboloBloqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSimboloBloqueMouseClicked
            configurarMenu();
//        mostrarMenu(new JTextArea("simbolos por bloque de CODIGO"));
        labelNombreReporte.setText("Tabla de simbolos por bloque de Codigo");
        
    }//GEN-LAST:event_menuSimboloBloqueMouseClicked

    private void menuInstruccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuInstruccionesMouseClicked
        labelNombreReporte.setText("Lista de Instrucciones por Bloque de Codigo");
        configurarMenu();
        
        panelContenedor.add(panelInstruccionesPor_bloque_Codigo);
        panelContenedor.updateUI();     //pendiente
    }//GEN-LAST:event_menuInstruccionesMouseClicked

    private void menuFun_MetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuFun_MetMouseClicked
        labelNombreReporte.setText("Cantidad de Funciones/Metodos");
        configurarMenu();
    }//GEN-LAST:event_menuFun_MetMouseClicked

    private void menuLLamadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLLamadasMouseClicked
        labelNombreReporte.setText("Cantidad de llamadas a una Funcion/Metodo");
        configurarMenu();
    }//GEN-LAST:event_menuLLamadasMouseClicked

    private void menuParametrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuParametrosMouseClicked
        labelNombreReporte.setText("Parametros de una Funcion");
        configurarMenu();
    }//GEN-LAST:event_menuParametrosMouseClicked

    private void menuErroresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuErroresMouseClicked
        labelNombreReporte.setText("ERRORES LEXICOS/SINTACTICOS");
        
        botonLexicos.setVisible(true);
        botonSintacticos.setVisible(true);
        panelContenedor.removeAll();
        panelContenedor.updateUI();
    }//GEN-LAST:event_menuErroresMouseClicked

    private void botonLexicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLexicosActionPerformed
        llenarTablaErroresLexicos();
        panelContenedor.updateUI();
    }//GEN-LAST:event_botonLexicosActionPerformed

    private void botonSintacticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSintacticosActionPerformed
        llenarTablaErrores_Sintacticos();
        panelContenedor.updateUI();
    }//GEN-LAST:event_botonSintacticosActionPerformed
 
    public void configurarMenu(){
        panelContenedor.removeAll();
        botonLexicos.setVisible(false);
        botonSintacticos.setVisible(false);
    }
//    public void mostrarMenu(JTextArea jtextarea) {
//        panelContenedor.removeAll();;
//        panelContenedor.add(jtextarea);
//        panelContenedor.updateUI();
//    }
    
    public void verReportes(){
        
        llenarTablaLexicos();
        configurarTablaLexico();
        panelContenedor.add(scrollPane);
    }
    
    public void llenarTablaLexicos() {
        this.filas = listTokens.size();
        datosTable = new Object[filas][columnas];
        for (int i = 0; i < listTokens.size(); i++) {
            for (int j = 0; j <columnas; j++) {
                switch (j) {
                    case 0:
                        datosTable [i][j] = i;
                        break;
                    case 1:
                        datosTable [i][j] = listTokens.get(i).getEnum();
                        break;
                    case 2:
                        if(listTokens.get(i).getPatron()!=null){
                            datosTable [i][j] = listTokens.get(i).getPatron();
                        }
                        break;
                    case 3:
                        datosTable [i][j] = listTokens.get(i).getLexema();
                        break;
                    case 4:
                        datosTable [i][j] = listTokens.get(i).getLinea();
                        break;
                    case 5:
                        datosTable [i][j] = listTokens.get(i).getColumna();
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }

    
    public void configurarTablaLexico(){
        tablaReportes = new JTable();
        
        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            datosTable ,
            new String [] {
                "#","Token", "Patron", "Lexema", "Linea", "columna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class         
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(tablaReportes);
    }
    
    
    
    public void llenarTablaglobal_Simbolos(){
        datosExpresiones = new Object[listExpresiones.size()][5];
        for (int i = 0; i < listExpresiones.size(); i++) {
            for (int j = 0; j < 5; j++) {
                switch (j) {
                    case 0:
                        datosExpresiones[i][j] = listExpresiones.get(i).getSimbolo();
                        break;
                    case 1:
                        datosExpresiones[i][j] = listExpresiones.get(i).getTipo();
                        break;
                    case 2:
                        datosExpresiones[i][j] = listExpresiones.get(i).getValor();
                        break;
                    case 3:
                        datosExpresiones[i][j] = listExpresiones.get(i).getLinea();
                        break;
                    case 4:
                        datosExpresiones[i][j] = listExpresiones.get(i).getColumna();
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
    }
    
    public void configurarTabla_Simbolos(){
        tablaSimbolos = new JTable();
        
        tablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            datosExpresiones ,
            new String [] {
                "SIMBOLO", "TIPO", "VALOR", "Linea", "columna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(tablaSimbolos);
    }
    
    public void configurarTAblaErroesLexicos(String nombre_SegundaColumna){
        
        tablaReportes = new JTable();
        
        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            datosTable ,
            new String [] {
                "#", nombre_SegundaColumna, "Linea", "columna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class     
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(tablaReportes);
        
    }
    public void filasErrores(){
        filas = 0;
        listTokenErrores = new ArrayList<>();
        for (Token listToken : listTokens) {
            if(listToken.getEnum()==TokenEnum.ERROR){
                listTokenErrores.add(listToken);
                filas++;
            }
        }
    }
    public void llenarTablaErroresLexicos(){
        filasErrores();
        datosTable = new Object[filas][4];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        datosTable[i][j] = i;
                        break;
                    case 1:
                        datosTable[i][j] = listTokenErrores.get(i).getLexema();
                        break;
                    case 2:
                        datosTable[i][j] = listTokenErrores.get(i).getLinea();
                        break;
                    case 3:
                        datosTable[i][j] = listTokenErrores.get(i).getLinea();
                        break;
                    default:
                }   //finSw
            }
        }
        configurarTAblaErroesLexicos("Lexema");
        panelContenedor.add(scrollPane);
        
    }
    
    public void llenarTablaErrores_Sintacticos() {
        filas = listErroresInstrucciones.size();
        datosTable = new Object[filas][4];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        datosTable[i][j] = i;
                        break;
                    case 1:
                        datosTable[i][j] = listErroresInstrucciones.get(i).getId();
                        
                        break;
                    case 2:
                        datosTable[i][j] = listErroresInstrucciones.get(i).getLinea();
                        
                        break;
                    case 3:
                        datosTable[i][j] = listErroresInstrucciones.get(i).getColumna();
                        break;
                    default:
                }   //finSw
            }
        }
        configurarTAblaErroesLexicos("Instruccion");
        panelContenedor.add(scrollPane);
    }
    
    
    public void setListExpresiones(List<Instrucciones> listExpresiones) {
        this.listExpresiones = listExpresiones;
    }

    public void setListFunciones(List<Instrucciones> listFunciones) {
        this.listFunciones = listFunciones;
    }

    public void setListInstruccionesBloque(List<String> listInstruccionesBloque) {
        this.listInstruccionesBloque = listInstruccionesBloque;
    }

    public void setPanelInstruccionesPor_bloque_Codigo(JPanel panelInstruccionesPor_bloque_Codigo) {
        this.panelInstruccionesPor_bloque_Codigo = panelInstruccionesPor_bloque_Codigo;
    }
    
    public void setListErroresInstrucciones(List<Instrucciones> listErroresInstrucciones) {
        this.listErroresInstrucciones = listErroresInstrucciones;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JButton botonLexicos;
    private javax.swing.JButton botonSintacticos;
    private javax.swing.JLabel labelNombreReporte;
    private javax.swing.JMenu menuErrores;
    private javax.swing.JMenu menuFun_Met;
    private javax.swing.JMenu menuInstrucciones;
    private javax.swing.JMenu menuLLamadas;
    private javax.swing.JMenu menuParametros;
    private javax.swing.JMenu menuSimboloBloque;
    private javax.swing.JMenu menuSimboloGlobal;
    private javax.swing.JMenu menuTokens;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}
