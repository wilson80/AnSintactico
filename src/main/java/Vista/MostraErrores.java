
package Vista;

import Tokens.TokenEnum;
import Tokens.Token;
import backend.Sintactico.Instrucciones;
import java.util.List;
import javax.swing.JTextPane;

/**
 *
 * @author Jonwil
 */
public class MostraErrores {
    private List<Token> listTokens;
    
    private List<Instrucciones> listaErrorInstrucciones;
    
    private String erroresLexicos = "";
    private String errores_Sintacticos = "";
    private VistaGeneral vistaGeneral;
    private JTextPane areaImpresion;

    public MostraErrores(List<Token> listTokens, List<Instrucciones> listaErrorInstrucciones, VistaGeneral vista) {
        this.vistaGeneral = vista;
        this.listTokens = listTokens;
        this.areaImpresion = vista.getcajonMostrarErroers();
        this.listaErrorInstrucciones = listaErrorInstrucciones;
    }
    
    
    public void clasificarerrores() {
        erroresLexicos += "ERRORES LEXICOS\n";
        for (Token listToken : listTokens) {
            if(listToken.getEnum()==TokenEnum.ERROR){
                erroresLexicos += listToken.getLexema() + ",  ";
                erroresLexicos += listToken.getEnum()+ ",  ";
                erroresLexicos += "Linea: " + listToken.getLinea()+ ",  ";
                erroresLexicos += "Columna: " + listToken.getColumna()+ ",  ";
                erroresLexicos += "\n";
            }
        }
        vistaGeneral.setCajonErroresLexicos(erroresLexicos);
        
        mostrarErroresLexicos();
    }
    
    
    public void mostrarErroresLexicos(){
            errores_Sintacticos += "ERRORES SINTACTICOS\n";
        for (Instrucciones lista : listaErrorInstrucciones) {
            errores_Sintacticos += lista.getId() + " ";
            errores_Sintacticos += "Linea:" + lista.getLinea() + " ";
            errores_Sintacticos += "Columna" + lista.getColumna() + " ";
            errores_Sintacticos += "\n";
        }
        
        vistaGeneral.setCajonErroresSintacticos(errores_Sintacticos);
    }
    
    public String getErrores() {
        return erroresLexicos;
    }
    
    
}
