
package backend.Sintactico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonwil
 */
public class BloqueCodigo {
    String instruccion_del_Bloque;
    private int numeroBloque;
    List<Instrucciones> instruccionesDentroBloque;     //print, if, else while, 

    
    public BloqueCodigo(String instruccion_del_Bloque) {
   
        this.instruccion_del_Bloque = instruccion_del_Bloque;
        instruccionesDentroBloque = new ArrayList<>();
    }

    
    public void agregarInstrucciones_a_Bloque(Instrucciones instruccion) {
        instruccionesDentroBloque.add(instruccion);
    }
    
    
    public List<Instrucciones> getInstruccionesDentroBloque() {
        return instruccionesDentroBloque;
    }
    
    
    public void agregarInstruccion_a_Bloque(Instrucciones instruccion){
        instruccionesDentroBloque.add(instruccion);
    }

    
    public String getInstruccion_del_Bloque() {
        return instruccion_del_Bloque;
    }

    public void setNumeroBloque(int numeroBloque) {
        this.numeroBloque = numeroBloque;
    }
    
    
    
    
    
    
    
    
}
