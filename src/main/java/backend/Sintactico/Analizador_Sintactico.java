
package backend.Sintactico;

import Tokens.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonwil
 */

public class Analizador_Sintactico {
    
//    private String [] ordenExpresion = {"ID","ASIGNACION", "CADENA_DE_CARACTERES", "CONSTANTES_NUMERICAS"};
    private String [] ordenExpresion = {"0","1", "2"};
    private List<Token> listTokens;
    private List<BloqueCodigo> bloques;
    private List<Instrucciones> listSimbolos;
//    private List<Identificadores> listExpresiones;
    private int iterador = 0;
    private boolean declaracionValida = true;
    private String instruccionEnAnalisis = "";
    private int iteradorError;
    private boolean hallarElse = false;
    
    private int indiceInicioIff;
    private int indiceCiereIff;
    
    private int lineasDentroBloque;
    
    private boolean dentroDeBloque = false;
    private boolean verificandoCierre = false;
    
    private boolean bloqueValido = true;
    private int identacionDentroBloque = 4;
    private int bloqueActual = 0;
    

    public Analizador_Sintactico() {
        listTokens = new ArrayList<>();
        listSimbolos = new ArrayList<>();
//        marcarError = new JTextArea(); mandar el texto del error a sumar en la cadena que se muestra en esa area
        
    }


    
    public void identificarInstruccion(){           //nombre pendiente
        System.out.println("S>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IdentificarINSTRUCCIONs");
//        Identificar el tipo de Token       
//        si es un comentario descartado
//        si es un id inicia el Analisis
//  ciclo que verifica linea por linea
        iterador =0;
//        for (int i = 0; iterador < listTokens.size(); iterador++) {
        for (int i = 0; iterador < 1; iterador++) {
//            System.out.println("iterador INSTRUCCIONNNNNNNNNNNNNNNNN" + iterador);
            String enume = listTokens.get(iterador).getEnum().name();
            String lex = listTokens.get(iterador).getLexema();
            switch (enume) {
                case "RESERVADA":
                    if(lex.equals("if")){
                        reconocerIf();
                        iterador--;
                    }
                    
                    if (!dentroDeBloque) {
                        if(lex.equals("print")){
                            identificar_Operadores_Entrada_salida();
                            iterador--;
                        }
                    }
                    
                    
                    break;
                case "ID":
                    identificarExpresion();
                    break;
                default:
                    
            }
            
        }
    }
    
    
    public void analizaToken_porToken(){
        for (int i = 0; i < listTokens.size(); i++) {
//            sw
            if(listTokens.get(i).getEnum().name().equals("ID")){
//                analizarExpresion(listTokens.get(i));
                if(listTokens.get(iterador).getEnum().name() == ""){
                }
            }
        }
        
//            EVALUAR SI NO SE RECONOCE UNA PALABRA RESERVADA AL INICIO DE LA LINEA
//           Analizar todos los tokens de una sola linea para las Expresiones        
        
    }
    
    
    public void identificarExpresion(){
//              cadena 
//              =                                 linea #1                              
//              "Hola Mundo" (CADENA) | 123( ENTERO)
//                         (BOOLEAN) | (FLOAT)                         

//              arreglo1 = [20,2,3]

            String ordenDeclaracion = "";
            String id = "";
            Token token1 = null;
            boolean asignacionNumerica = false;
            boolean asignacionCadena = false;
        
        int contadorToken = 0;
        int tokensLeidos = 0;
        
        int lineas=0;
        lineas = listTokens.get((listTokens.size()-1)).getLinea();
        
        for (int i = 0; iterador < lineas; iterador++) {
            try {
                while(listTokens.get(contadorToken).getLinea()==iterador+1) {
                    String nombreEnum = listTokens.get(contadorToken).getEnum().name();
                    switch (nombreEnum) {
                        case "ID":
                            ordenDeclaracion +="0,";
                            id = listTokens.get(contadorToken).getLexema();
//                            tokensLeidos++;
                            break;
                        case "ASIGNACION":
                            ordenDeclaracion +="1,";
                            break;
                        case "CADENA_DE_CARACTERES":
                            ordenDeclaracion +="2,";
                            asignacionCadena =true;
                            token1 = listTokens.get(contadorToken);
//                            tokensLeidos++;
                            break;
                        case "CONSTANTES_NUMERICAS":
                            ordenDeclaracion +="2,";
                            asignacionNumerica =true;
                            token1 = listTokens.get(contadorToken);
//                            tokensLeidos++;
                            break;
                        case "CONSTANTES_BOOLEANAS":    
//                            [  20, 2, 3 ]
                            ordenDeclaracion +="2,";
//                            asignacionNumerica =true;
                            token1 = listTokens.get(contadorToken);
//                            tokensLeidos++;
                            break;
                    }
                    contadorToken++;
                }
            } catch (Exception e) {
                System.out.println("ENTRANDO AL CATCH AL ANALIZAR UNA EXPRESION");
            }
            
            
            verificarExpresion(ordenDeclaracion, contadorToken);
            
//            if(tokensLeidos<3){ 
////                marlistTokens.get(marcaError).getLexema();
//                
//            }
            ordenDeclaracion = "";
            if(declaracionValida && token1!= null){
//                sout
                Instrucciones expresion =  new Instrucciones(token1, id, asignacionNumerica, asignacionCadena);
                listSimbolos.add(expresion);
             }else{
//                JOptionPane.showMessageDialog(null, "No se han encontrado expresiones");  
            }
            
            
//             id = "";
             token1 = null;
            tokensLeidos = 0;
            asignacionNumerica = false;
            asignacionCadena = false;
            
        }
//___________________________________________________________________
        System.out.println("IMPRESIONES EXXXXXXXX");
        for (Instrucciones expresion : listSimbolos) {
            System.out.println(expresion.getSimbolo());
        }
        
//        identificarInstruccion();
        
    }
    
    public void verificarExpresion(String ordenDeclaracion, int contadortoken ){
        String[] analizar = ordenDeclaracion.split(",");
            for (int j = 0; j < analizar.length; j++) {
                switch (analizar[j]) {
                    case "0":
                        if(j!=0){
                            declaracionValida = false;
                            System.out.println("la declaracion es false");
//                            marcaError = contadortoken;
                        }
                        break;
                    case "1":
                        if(j!=1){
                            declaracionValida = false;
                            System.out.println("la declaracion es false");
//                            marcaError = contadortoken;
                        }
                        break;
                    case "2":
                        if(j!=2){
                            declaracionValida = false;
                            System.out.println("la declaracion es false");
//                            marcaError = contadortoken;
                        }
                        
                        break;
                }
            }
    }
    
    
//    private void reconocerIf() {
//        dentroDeBloque = true;
//        String instruccion = "";
//        
//        boolean instruccionValida = true;
//        boolean aceptarInstruccion = false;
//        
//        boolean hayComparacion = false;
////        String comparador = "";
//        int variable_a_Comparar = 0;
//        boolean comparandoCadena = false;   //String
//        boolean comparandoEntero = false;   
//        int tokenInstruccion = 0;          // ++ cada vez que se lee un token   
//        
//        int inicioInstruccionLinea = listTokens.get(iterador).getLinea();
//        try {
//            for (int i = 0; i < 5; i++) {//______________________________________________________
////                System.out.println("Entrando al Ciclo de los Iff " + iterador);
//                if(listTokens.get(iterador).getLinea()!=inicioInstruccionLinea){  //verifica si el token esta en la misma Linea
//                    if(tokenInstruccion<2){
//                        instruccionValida = false;
//                    }
//                    break;
//                }
//    //                tokenInstruccion   //verificar en caso de que hayyan mas de 5 tokens e la misma linea
//                String comparador2 = "";
//                String enumm = listTokens.get(iterador).getEnum().name();
//                switch (enumm) { //SW
//                        case "RESERVADA":
//                            instruccion += listTokens.get(iterador).getLexema() + "  ";
//                            tokenInstruccion++;
//                            if(listTokens.get(iterador).getColumna() != 1){     //para que la instruccion sea valida
//                                instruccionValida = false;
//                            }
//                            if(i!=0){
//                                instruccionValida = false;
//                            }
//                            break;
//                        case "COMPARACION":
//                            instruccion += listTokens.get(iterador).getLexema() + " ";
//                            hayComparacion = true;
//                            if(listTokens.get(iterador).getLexema().equals("=")){       //  = invalida la instruccion
//                                instruccionValida =false;
//                                tokenInstruccion++;
//                            }
//                            if(i!=2){
//                                instruccionValida = false;
//                            }
//                            break;
//                        case "OTROS":
//                        instruccion += listTokens.get(iterador).getLexema() + " ";
//                        tokenInstruccion++;
//                        if(hayComparacion){             //verificacion, no se puede operar String con int
//                            if(comparandoCadena&&comparandoEntero){
//                               instruccionValida = false; 
//                            }
//                        }
//
//                        if(i!=4){
//                            instruccionValida = false;
//                            if(variable_a_Comparar==1 && i==2){     //cuando solo hay una variable
//                                instruccionValida =true;
//                                
//                            }
//                        }
////                        if a: "d"
////                        if a > b :
//                        if(i==4){                       //Aceptar Instruccion
//                            if(variable_a_Comparar==2){
//                                aceptarInstruccion = true;
//                            }
//                        }
//                        if(i==2){
//                            if(variable_a_Comparar==1){
//                                aceptarInstruccion = true;
//                            }
//                        }
//                        
//                        break;
//                    case "ID":
//                        instruccion += listTokens.get(iterador).getLexema() + " "  ;
//                        tokenInstruccion++;
//                        variable_a_Comparar++;
//
//                        break;
//                    case "CADENA_DE_CARACTERES":    
//                        instruccion += listTokens.get(iterador).getLexema() + " ";
//                        tokenInstruccion++;
//                        comparandoCadena =true;
//                        variable_a_Comparar++;
//
//                        break;
//                    case "CONSTANTES_NUMERICAS":
//                        instruccion += listTokens.get(iterador).getLexema() + " ";
//                        tokenInstruccion++;
//                        comparandoEntero = true;
//                        variable_a_Comparar++;
//
//                        break;
//                    
//                    default:
//                        instruccion += listTokens.get(iterador).getLexema() + " ";
//                        System.out.println(">>>>>>>>ONLY" + instruccion);
//                            instruccionValida = false;
//
//                }//finSw
////                            SOUT
//                if(!instruccionValida){
//                    System.out.println("break Instruccion valida False");
//                    break;
//                }
//                iterador++;
//
//            }   //finCiclo
//            
//        } catch (Exception e) {
//            System.out.println("Catch reconocer Ciclo");
//        }
//        
//        if(!listTokens.get(iterador-1).getLexema().equals(":")){        //verifica el ultimo token de la instruccion
//            aceptarInstruccion = false;
//        }
//        
//        try {                                                           // analizar si el ultimo token en una linea es un comentario
//            if(!listTokens.get(iterador).getEnum().name().equals("COMENTARIO") && listTokens.get(iterador).getLinea()==inicioInstruccionLinea){
//                aceptarInstruccion=false;
//            }
//        } catch (Exception e) {
//            System.out.println("ya no hay mas Tokens");
//        }
//        
//        
////               SOUTSS
//        if(aceptarInstruccion){
//            System.out.println("\n\n\n");
//            System.out.println("\tINTRUCCION VALIDA IFF");
//            System.out.println(instruccion);
////            aceptarInstruccion =false;
//        }else if(!aceptarInstruccion){
//            System.out.println("\n\n");
//            System.out.println("\t\t\tINTRUCCION IFF         FALSE");
//            System.out.println(instruccion);
//        }
////                    SOUTS
//        System.out.println("iterador en reconocer If antes de Fijar:   " + iterador);
//      
//        fijarIterador(inicioInstruccionLinea);                                              //Fija el iterador en la siguiente Linea
//        System.out.println("iterador en reconocer If despues de fijar:   " + iterador);
////                       SOUTS 
//        try {
//            System.out. println(listTokens.get(iterador).getLexema());
//        } catch (Exception e) {
//            System.out.println("Ya no hay mas tokens");
//        }
//        
//        if(aceptarInstruccion && instruccionValida){
//            buscarCierreBloque(inicioInstruccionLinea);        //busca el final del bloque
//            if(bloqueValido){
//                reconocerBloque();                                 //reconocer un bloque
//            }
//        }
////    reconocerIf
//        
//        if(bloqueValido){
//            System.out.println("bloque valido________________________________________");
//        }else{
//            System.out.println("bloque invalido______________________________________");
//        }
//        
//        bloqueValido = true;
////        dentroDeBloque = false;
//        
//    }
    private void reconocerIf() {
        String sig1 = "";
        String sig2 = "";
        String sig3 = "";
        dentroDeBloque = true;
        String instruccion = "";
        int contador =0;
        boolean instruccionValida = true;
//        boolean aceptarInstruccion = false;
        
        boolean hayComparacion = false;
//        String comparador = "";
        int variable_a_Comparar = 0;
        boolean comparandoCadena = false;   //String
        boolean comparandoEntero = false;   
        int tokenInstruccion = 0;          // ++ cada vez que se lee un token   
        
        int inicioInstruccionLinea = listTokens.get(iterador).getLinea();
        try {
            while(listTokens.get(iterador).getLinea()==inicioInstruccionLinea) {//______________________________________________________
//                System.out.println("Entrando al Ciclo de los Iff " + iterador);
                if(listTokens.get(iterador).getLinea()==inicioInstruccionLinea && contador==5){  //verifica si el token esta en la misma Linea
                        if(!listTokens.get(iterador).getEnum().name().equals("COMENTARIO")){
                            instruccionValida = false;
                            break;
                        }else{
                            break;
                        }                       
                }else if(contador == 5){
                    break;
                }
//                 0 1 2 3 4 5 
//                if a  
//                if a > 
//                if a > b
//                if a > b :                0 1 2 3 4 5
//                 1 2 3 4 5 
//                
//                                        if a > b
//                                            print("2")
//                                        print("cierre")    

                String enumm = listTokens.get(iterador).getEnum().name();
                String lex = listTokens.get(iterador).getLexema();
                switch (enumm) { //SW
                        case "RESERVADA":
                            if(lex.equals("if")){
                                sig1 = "CADENA_DE_CARACTERES";
                                sig2 = "CONSTANTES_NUMERICAS";
                                sig3 = "ID";
                                instruccion += lex + " ";
                            }
                            break;
                        case "COMPARACION":
                            if(contador == 4){          // 
                                instruccionValida = false;
                                break;
                            }
                            if(enumm.equals(sig2)){
                                hayComparacion = true;
                                sig1 = "CADENA_DE_CARACTERES";
                                sig2 = "CONSTANTES_NUMERICAS";
                                sig3 = "ID";
                                instruccion += lex + " ";
                            }else{
                                instruccionValida = false;
                                break;
                            }
                            break;
                        case "OTROS":
                            if(lex.equals(":")){
                                if(enumm.equals(sig1)){     //comparando orden
                                    contador = 4;           //  para cerrar una instruccion cuando llega a _:_
                                    instruccion += lex + " ";
                                }else{
                                    instruccionValida = false;
                                }
                            }else{
                                instruccionValida = false;
                            }                    
                            break;
                        case "ID":
                            if(enumm.equals(sig1) || enumm.equals(sig2) || enumm.equals(sig3)){
                                sig1 = "OTROS";
                                sig2 = "COMPARACION";
                                sig3 = "";
                                instruccion += lex + " ";
                            }else{
                                instruccionValida = false;
                            }
                            break;
                        case "CADENA_DE_CARACTERES":
                            if(enumm.equals(sig1) || enumm.equals(sig2) || enumm.equals(sig3)){
                                sig1 = "OTROS";
                                sig2 = "COMPARACION";
                                sig3 = "";
                                instruccion += lex + " ";
                            }else{
                                instruccionValida = false;
                            }
                            break;
                        case "CONSTANTES_NUMERICAS":
                            if(enumm.equals(sig1) || enumm.equals(sig2) || enumm.equals(sig3)){
                                sig1 = "OTROS";
                                sig2 = "COMPARACION";
                                sig3 = "";
                                instruccion += lex + " ";
                            }else{
                                instruccionValida = false;
                            }

                            break;
                        default:
                            instruccionValida = false;
//                            break;                       
                }//finSw                
//                            SOUT
                if(!instruccionValida){
                    break;
                }
                iterador++;
                contador++;

            }   //finwhi
            if(contador < 3){
                instruccionValida = false;
            }
            if(hayComparacion && (contador==3 || contador ==4)){
                instruccionValida = false;
            }
            
        } catch (Exception e) {
            System.out.println("ya no hay mas Tokens en reconocerIF while");
        }
        
        
//               SOUTSS
        if(instruccionValida){
            System.out.println("\n\n\n");
            System.out.println("\tINTRUCCION IFF    VALIDA ");
            System.out.println(instruccion);
//            aceptarInstruccion =false;
        }else {
            System.out.println("\n\n");
            System.out.println("\t\t\tINTRUCCION IFF         FALSE");
            System.out.println(instruccion);
        }
//                    SOUTS
        System.out.println("iterador en reconocer If antes de Fijar:   " + iterador);
        fijarIterador(inicioInstruccionLinea);                                              //Fija el iterador en la siguiente Linea
        System.out.println("iterador en reconocer If despues de fijar:   " + iterador);
//                       SOUTS 
        try {
            System.out. println(listTokens.get(iterador).getLexema());
        } catch (Exception e) {
            System.out.println("Ya no hay mas tokens");
        }
        
        if(instruccionValida){
            buscarCierreBloque(inicioInstruccionLinea);        //busca el final del bloque
            if(bloqueValido){
                reconocerBloque();                                 //reconocer un bloque
            }
        }
//    reconocerIf
        
        if(bloqueValido && instruccionValida ){
            System.out.println("bloque valido________________________________________");
        }else{
            System.out.println("bloque invalido______________________________________");
        }
        
//        bloqueValido = true;
//        dentroDeBloque = false;
        
    }
    
    public void reconocerElse(){ 
//        dentroDeBloque = true;
        try {   // Verificar else: continuar con el ELSE
                
               if(listTokens.get(iterador).getLexema().equals("else") && listTokens.get(iterador+1).getLexema().equals(":")){

               }
        } catch (Exception e) {
            System.out.println("ya no hay mas token en analizar Else");
        }
    }
    
    public void crearBloque(String instruccion){
        BloqueCodigo bloque = new BloqueCodigo();
        bloques.add(bloque);                                        //agregando el bloque a la lista de bloques
    }
    
    public void insertarInstruccion_en_Bloque(int bloque_a_Insertar, String textoInstruccion){
        Instrucciones instruccionI = new Instrucciones(null, textoInstruccion, false, false);
        bloques.get(bloque_a_Insertar).getInstruccionesDentroBloque().add(instruccionI);    //agregando una instruccion al bloque
        
    }
    
    
    
    private void reconocerBloque() {
//        if comer:
//            print("adentro")
//            print("nada")
//        print("fuera")         
        
        iterador = indiceInicioIff;
        System.out.println("iterador al iniciar a verificar un bloque: " + iterador);
                                                            //calculando las lineas dentro del bloque
        lineasDentroBloque= listTokens.get(indiceCiereIff).getLinea() - listTokens.get(indiceInicioIff).getLinea(); 
        
        System.out.println("lineas a analizar en el bloque:\t\t\t" + lineasDentroBloque);
        for (int i = 0;  i< lineasDentroBloque; i++) {
            identificar_Operadores_Entrada_salida();        //verificar los espacios
        }
//        if(!errorDentroDeBloque){
            
        iterador = indiceCiereIff;
        System.out.println("iterador al final del bloque " + iterador);

        dentroDeBloque = false;
        
//        guardarExpresiones por bloque
//        validar que por lo menos halla una halla una unica instruccion
    }
    
    
    public void buscarCierreBloque(int lineaInicioInstruccion){
//                print("algo")             al menos una instruccion
//            print("fuera del IF")         nueva Instruccion en col 0 define el fin de la instruccion
//        lineaIf;
        
        this.indiceInicioIff = iterador;                    //inicio a iterar en el bloque
        boolean finInstruccion = true;
        lineasDentroBloque = 0 ;

        
        while(finInstruccion){
            if(listTokens.get(iterador).getColumna()==1){
                finInstruccion = false;
                break;
            }
            if((iterador==listTokens.size()-1)){
                break;
            }
            iterador++;
        }   //finWhi
        indiceCiereIff = iterador;                          //iterador en el token que cierra el bloque                        
                                                                        //cuenta las lineas dentro del Bloque      
        lineasDentroBloque = (listTokens.get(indiceCiereIff).getLinea() - listTokens.get(indiceInicioIff).getLinea());
        
        System.out.println("linea inicio: " +lineaInicioInstruccion);
        System.out.println("lineas bajo Instruccion: " + lineasDentroBloque);
        System.out.println("token actual:  " +listTokens.get(iterador).getLexema());
        System.out.println("Linea actual:  " +listTokens.get(iterador).getLinea());
        
        if(lineasDentroBloque==0){                  //Si lineas dentro de bloque = 0; bloque invalido
            bloqueValido = false;
        }
        
        verificarCierreBloque();     //valida si el cierre del bloque es valido
        
        
    }
    
    
    
    public void verificarCierreBloque(){     //iterador posicionado
        verificandoCierre =true;
        String lexema = listTokens.get(iterador).getLexema();
        String enumm = listTokens.get(iterador).getEnum().name();
        switch (enumm) {
            case "RESERVADA":
                if(lexema.equals("if")){
//                    
                } 
                if(lexema.equals("print")){
                    identificar_Operadores_Entrada_salida();  //reconocer operadores de Entrada                    
                }
                if(lexema.equals("else")){
//                    reconocer el  else:
                    //reconocer operadores de Entrada
                }
                if(lexema.equals("elif")){
//                    reconocer el  else:
                    //reconocer operadores de Entrada
                }
                
                break;
                
            case "CADENA_DE_CARACTERES":
//                reconocer id o exp
                break;
                
            default:
                
        }
        verificandoCierre = false;
        
    }
    
    
    public void identificar_Operadores_Entrada_salida(){
//    public void identificar_Operadores_Entrada_salida(int iterador){
                        //print("Hola Mundo") # 35      
                        //print("Hola", "Mundo") # 36
                        //print("Hola" + "Mundo") # 37
//        iterador =0;
        String texto_InstruccionEntradaSalida = "";
        boolean salida = true;
        boolean finWhile = false;
        String siguiente1 = "print";                
        String siguiente2 = "";                
//        String siguiente3 = "";                
        int lineaAnalizada = listTokens.get(iterador).getLinea();

        if(dentroDeBloque && !verificandoCierre){         //verificando IDENTACION dentro bloque analizar el primer token de la linea
                    if(!(listTokens.get(iterador).getColumna()==(identacionDentroBloque+1))){
                        System.out.println("collllllllllllllllll: " +listTokens.get(iterador).getColumna());
                        salida =false;
                        System.out.println("Error en el Bloque, columna en diferente identacion");
                    }    
        }
        try {
            while (listTokens.get(iterador).getLinea()==lineaAnalizada) {
                String enume =listTokens.get(iterador).getEnum().name();
                String lex =listTokens.get(iterador).getLexema();
                        //print("Hola Mundo") # 35      0 1 2 3 tokens
                        //print("Hola", "Mundo") # 36
                        //print("Hola" + "Mundo") # 37
                switch (enume) {
                    case "RESERVADA":
                        if(lex.equals("print")){
                            if(!siguiente1.equals("print")){
//                                finWhile = true;
                                salida = false;
                                finWhile = true;
                            }
                            texto_InstruccionEntradaSalida +=lex;
                            siguiente1 = "(";
                        }
                        break;
                    case "OTROS":
                        if(siguiente1.equals(lex)){     //lex =  , ) ( 
                            if(lex.equals("(")){
                                texto_InstruccionEntradaSalida +=lex;
                                siguiente1 = "CADENA_DE_CARACTERES";
                            }    
                            if(siguiente1.equals(")")){
                                finWhile = true;
                                texto_InstruccionEntradaSalida +=lex;
                            }
                                
//                                if(){ esto es para la ,
//                                }
                        }else{
                            finWhile = true;
                            salida =false;
                        }
                        
                        if(lex.equals(",")){
                            if(siguiente2.equals("+")){
                                salida = true;
                                finWhile = false;
                                texto_InstruccionEntradaSalida +=lex;
                                siguiente1 = "CADENA_DE_CARACTERES";
                                siguiente2 = "";
                            }else{
                                finWhile = true;
                                salida =false;
                            }
                        }
                        break;
                    case "CADENA_DE_CARACTERES":
                        if(siguiente1.equals("CADENA_DE_CARACTERES")){
                            texto_InstruccionEntradaSalida +=lex;
                            siguiente1 = ")";
                            siguiente2 = "+";
//                            siguiente3 = ",";
                        }else{
                            finWhile = true;
                            salida = false;
                        }
                        break;
                    case "ARITMETICOS":
                        if(lex.equals("+")) {
                            if(lex.equals(siguiente2)){
                                texto_InstruccionEntradaSalida +=lex;
                                siguiente1 = "CADENA_DE_CARACTERES";
                                siguiente2 = "";
//                                siguiente3 = "";
                            }else{
                                salida = false;
                                finWhile = true;
                            }
                        }
                        break;
                    default:
                        salida = false;
                        finWhile = true;
                } //finSw
                
                iterador++;
                if(finWhile){
                    break;
                }
            }       //finWhi
        } catch (Exception e) {
            System.out.println("Catch operadores de Entrada y Salida");
        }   
        
        
        
        try {   // si el ultimo token en una linea es un comentario la salida es Verdadera si es que es valida
            if(!listTokens.get(iterador).getEnum().name().equals("COMENTARIO") && listTokens.get(iterador).getLinea()==lineaAnalizada){
                salida=false;
            }
        } catch (Exception e) {
            System.out.println("ya no hay mas Tokens");
        }

        if(!listTokens.get(iterador-1).getLexema().equals(")")){ //verifica que el final de la instruccion de salida sea ")"
            salida =false;
        }
        
        if(!salida){                // si la instruccion es false el bloque es false
            bloqueValido = false;
        }
        //esto podria ser un metodo que guarde la instruccion y el bloque
        if(dentroDeBloque){         //hacer false el bloque si la salida es falsa cuando esta dentro de  un bloque
            if(!salida){
                bloqueValido = false;               //agregar el Error o algo || o no agregar las nada del bloque
                System.out.println("\t\t\t BLOQUE INVALIDO");
//                                              // desde el inicioItt y fin Itt
            }
        }
         
//insEntrSali        
//                            SOUT
        if(salida){
            System.out.println("\n\n\t\t SALIDA TRUE: " +texto_InstruccionEntradaSalida);
        }else{
            System.out.println("\n\n\t\t SALIDA FALSE: " +texto_InstruccionEntradaSalida);
        }
        
        System.out.println("iterador en salidaaaaaa " + iterador);
        fijarIterador(lineaAnalizada);                                   //   fija el iterador en el primer token de la SigLinea
        System.out.println("iterador en salidaaaaaa despues de fijar " + iterador);
//                            SOUT
        try {
            System.out.println("ONLY>>>>>> " + listTokens.get(iterador).getLexema());
            
        } catch (Exception e) {
            System.out.println("no+TOKENS");
        }
        
    }

    public void fijarIterador(int lineaAnalizada){
        try {
            while(listTokens.get(iterador).getLinea()==lineaAnalizada){
                iterador++;
            }
        } catch (Exception e) {
            System.out.println("Ya no hay mas tokens en funcion Fijar Token");
        }
    }
    
      
    public void setListTokens(List<Token> listTokens) {
        this.listTokens = listTokens;
    }

    public List<Instrucciones> getListExpresiones() {
        return listSimbolos;
    }

    
}
