
package backend.Sintactico;

import Tokens.Token;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonwil
 */

public class Analizador_Sintactico {
    
    private String [] ordenExpresion = {"0","1", "2"};
    
    private List<Token> listTokens;
    private List<BloqueCodigo> bloques;
    private List<Instrucciones> listSimbolos;
    private List<Instrucciones> Errores;
//    private List<String> listaInstruccionesBloque;
    private BloqueCodigo bloque;
    private int iterador = 0;
    private boolean declaracionValida = true;
//    private String instruccionEnAnalisis = "";
//    private   iteradorError;
//    private boolean hallarElse = false;
    
    private int inicioBloqueIterador;
    private int finBloqueIterador;
    
    private int lineasDentroBloque;
    
    private boolean dentroDeBloque = false;
    private boolean verificandoCierre = false;
    
    private boolean bloqueValido = true;
    
    private int identacionDentroBloque = 4;
    private int bloqueActual = 0;
    

    public Analizador_Sintactico() {
        listTokens = new ArrayList<>();
        listSimbolos = new ArrayList<>();
//        listaInstruccionesBloque = new ArrayList<>();
        bloques = new ArrayList<>();
        Errores = new ArrayList<>();
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
        for (int i = 0; iterador < listTokens.size(); iterador++) {
//            System.out.println("iterador INSTRUCCIONNNNNNNNNNNNNNNNN" + iterador);
            String enume = listTokens.get(iterador).getEnum().name();
            String lex = listTokens.get(iterador).getLexema();
            switch (enume) {
                case "RESERVADA":       //REPARAR
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
                    
                    break;
                default:
                    
            }
            
        }
//        imprimirBloque();  ya se muestran en interfaz
        iterador =0;
        identificarExpresion();
        iterador =0;
        reconocerFuncion();
        
        
    }
    
    public void reconocerFuncion(){
        
        int lineas=0;
        iterador = 0;
        int contador = 0;
        
        String simbolo ="";
        Token tokenSimbolo = null;
        boolean asignacionNume = false;
        boolean asignacionCad = false;
        
        boolean simboloIdentificado = false;
        
        lineas = listTokens.get((listTokens.size()-1)).getLinea();  //identifica la ultima linea
        
        try {
            for (int i = 0; i < 100; i++) {
                
                if(listTokens.get(iterador).getLexema().equals("def")) {   
                    try {
                        while(!simboloIdentificado){
                            String enume = listTokens.get(iterador).getEnum().name();
                            String lex = listTokens.get(iterador).getLexema();
                            switch (enume) {
                                case "RESERVADA":
                                    if(lex.equals("def")){
                                        tokenSimbolo = listTokens.get(iterador);
                                    }
                                    break;
                                case "ID":
                                    simbolo = lex;
                                    break;

                                case "CONSTANTES_NUMERICAS":
                                    simboloIdentificado = true;
                                    asignacionNume = true;
                                    break;
                                case "CADENA_DE_CARACTERES":
                                    simboloIdentificado = true;
                                    asignacionCad = true;
                                    break;
                                case "CONSTANTES_BOOLEANAS":
                                    simboloIdentificado = true;
                                    break;
                                default:

                            } //finSw
                            iterador++;
                            if(simboloIdentificado){

                                Instrucciones instrucciones = new Instrucciones(tokenSimbolo, simbolo, asignacionNume, asignacionCad);
                                listSimbolos.add(instrucciones);
                                System.out.println("FUNCION RECONOCIDA: " + simbolo);
                                simboloIdentificado = false;
                                asignacionNume = false;
                                asignacionCad = false;
                                simbolo = "";
                                tokenSimbolo = null;

                            }

                        }   //finWhile

                    } catch (Exception e) {
                        System.out.println("Ya no hay mas tokens\t\t\t\t RECONOCER_FUNCION");
                    }
                }else{
                    System.out.println("Else Aumentando el iterador");
                    System.out.println("ITERADOR: " + iterador);
                    iterador++;
                }
            
            }       //finCiclo
        } catch (Exception e) {
            System.out.println("no hay mas tokens CicloFuncinoes");
        }
    }
    
    public void identificarExpresion() {
        iterador = 0;
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
//                while(listTokens.get(contadorToken).getLinea()==iterador+1) {
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
        String[] analizar = ordenDeclaracion.split(",");        // 0 1 2
        System.out.println("BBBBBBBBBBBB");
        for (String string : analizar) {
            System.out.println(string);
        }
        System.out.println("BBBBBBBBBBBB");
        
            for (int j = 0; j < analizar.length; j++) {
                switch (analizar[j]) {
                    case "0":
                        if(j!=0){
                            declaracionValida = false;
                            System.out.println("la declaracion es false en 0");
//                            marcaError = contadortoken;
                        }
                        break;
                    case "1":
                        if(j!=1){
                            declaracionValida = false;
                            System.out.println("la declaracion es false  en 1");
//                            marcaError = contadortoken;
                        }
                        break;
                    case "2":
                        if(j!=2){
                            declaracionValida = false;
                            System.out.println("la declaracion es false  en 2");
//                            marcaError = contadortoken;
                        }
                        
                        break;
                }
            }
    }
    
    
    private void reconocerIf() {
        dentroDeBloque = true;
        
        boolean instruccionValida = true;
        int inicioInstruccionLinea = listTokens.get(iterador).getLinea();
        String instruccion = "";
        
        String sig1 = "";               //sirve para marcar los posibles siguientes tokens correctos
        String sig2 = "";
        String sig3 = "";
        
        int contador =0;                    //aumenta cada vez que se realiza el while, sirve para saber cuantos tokens se leyeron
        
        boolean hayComparacion = false;
//        int variable_a_Comparar = 0;
//        boolean comparandoCadena = false;   //puede servir para reconocer este error if 5 > "algo":
//        boolean comparandoEntero = false;   
        boolean errorIdentacion = false;
        
        
        if(listTokens.get(iterador).getColumna()!=1 ){
            errorIdentacion = true;
        }

        
        try {
            while(listTokens.get(iterador).getLinea()==inicioInstruccionLinea) {
                
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
                
                if(errorIdentacion){
                    break;
                }
                
            }   //finwhi
            
        } catch (Exception e) {
            System.out.println("ya no hay mas Tokens en reconocerIF while");
        }
        if(contador < 3) {
            instruccionValida = false;
        }
        if(hayComparacion && (contador==3 || contador ==4)){
            instruccionValida = false;
        }
                //FIN VERIFICACION INSTRUCCION
                
                
        guardarError(instruccionValida, instruccion, listTokens.get(iterador-1),errorIdentacion);        //Guardar Error
                
//               SOUTSS
            System.out.println("\n\n\tINTRUCCION IFF: " + instruccion  + "\t "  + instruccionValida + "\n\n\n");
        System.out.println("\t\t\t\t\t\t\t\t\tANTES de Fijar:   " + iterador);
        fijarIterador(inicioInstruccionLinea);                          //Fija el iterador en la siguiente Linea
        System.out.println("\t\t\t\t\t\t\t\t\tDESPUES de fijar:   " + iterador);
        
        try {
            System.out. println(listTokens.get(iterador).getLexema() + "\t\t\t\tUltimo TOKEN despues de reconocer un if");
        } catch (Exception e) {
            System.out.println("Ya no hay mas tokens");
        }
        
                    //Analis del BLOQUE
        reconocerBloque(instruccionValida, instruccion);
        
        bloqueValido = true;        //si el bloque termina en False 
        dentroDeBloque = false;     // al salir de un bloque (para analizar una instruccion fuera de bloques )
        
//    reconocerIf
    }
    
    public void guardarError(boolean instruccionValida, String textoInstruccion, Token token, boolean errorIdentacion){
        String error = "";
        if(errorIdentacion){
            error +="Error de Identacion";
        }
        if(!instruccionValida){
            Errores.add(new Instrucciones(token, textoInstruccion, false, false));
            JOptionPane.showMessageDialog(null, "Errorr Sintactico \n\t" + textoInstruccion + " " + error +  
                                        "\nLinea: " + token.getLinea()+ "    Columna: " + token.getColumna() );
        }
        
    }
    
    
    public void imprimirBloque(){
        System.out.println("\t\t\tTAMAÑO BLOQUES: " + bloques.size());
        
        for (int i = 0; i < bloques.size(); i++) {
            System.out.println("INSTRUCCION  : " + bloques.get(i).instruccion_del_Bloque);
            for (int j = 0; j < bloques.get(i).getInstruccionesDentroBloque().size(); j++) {
                System.out.println("Instruccion dentro bloque: " + bloques.get(i).getInstruccionesDentroBloque().get(j).getId());
            }
            System.out.println("\n\n\n________________");
            
        }
        
    } 
    
    private void reconocerBloque(boolean instruccionValida, String instruccion){
        if(instruccionValida) {
            bloque = new BloqueCodigo(instruccion);      //Bloque creado, el bloque se agrega a la 
//                                                                     lista de bloques si al final de ser analizado este es valido

            buscarCierreBloque();        //busca el final del bloque y lo valida
            if(bloqueValido){
                AnalizarDentroBloque();                                 //Analiza Dentro del bloque
                if(bloqueValido){
                    bloque.setNumeroBloque(bloqueActual);
                    bloques.add(bloque);
                }
            }
        }
        
    }
    
    
    public void crearBloque(BloqueCodigo bloque){
        bloques.add(bloque);                                        //agregando el bloque a la lista de bloques
    }
    
    public void insertarInstruccion_en_Bloque(String textoInstruccion){
        System.out.println("Instruccion a insertar: " + textoInstruccion);
            Instrucciones instruccion = new Instrucciones(null, textoInstruccion, false, false);
             bloque.agregarInstrucciones_a_Bloque(instruccion);      //Inserta la instruccion en la lista de instrucciones del bloque
    }
    
    private void AnalizarDentroBloque() {
//        if comer:
//            print("adentro")
//            print("nada")
//        print("fuera")         
        
        iterador = inicioBloqueIterador;
        System.out.println("iterador al iniciar a verificar un bloque: " + iterador);
                                                            //calculando las lineas dentro del bloque
        lineasDentroBloque= listTokens.get(finBloqueIterador).getLinea() - listTokens.get(inicioBloqueIterador).getLinea(); 
        
        System.out.println("lineas a analizar en el bloque:\t\t\t" + lineasDentroBloque);
        
        for (int i = 0;  i< lineasDentroBloque; i++) {
            identificar_Operadores_Entrada_salida();        //verificar los espacios
        }
//        if(!errorDentroDeBloque){
            
        iterador = finBloqueIterador;
        System.out.println("iterador al final del bloque " + iterador);

        dentroDeBloque = false;
        
//        guardarExpresiones por bloque
//        validar que por lo menos halla una unica instruccion
            
        }
    
    public void buscarCierreBloque() {
//                print("algo")             al menos una instruccion
//            print("fuera del IF")         nueva Instruccion en col 1 define el fin de la instruccion
//        lineaIf;
        
        this.inicioBloqueIterador = iterador;                    //inicio a iterar en el bloque
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
        finBloqueIterador = iterador;                          //iterador en el token que cierra el bloque                        
                                                                        //cuenta las lineas dentro del Bloque      
        lineasDentroBloque = (listTokens.get(finBloqueIterador).getLinea() - listTokens.get(inicioBloqueIterador).getLinea());
        
//        System.out.println("linea inicio: " +lineaInicioInstruccion);
        System.out.println("lineas bajo Instruccion: " + lineasDentroBloque);
        System.out.println("token actual:  " +listTokens.get(iterador).getLexema());
        System.out.println("Linea actual:  " +listTokens.get(iterador).getLinea());
        
        if(lineasDentroBloque==0){                  //Si lineas dentro de bloque = 0; bloque invalido
            bloqueValido = false;
        }
        
        validarCierreBloque();     //valida si el cierre del bloque es valido
    }
    
    
    
    public void validarCierreBloque(){     //iterador posicionado
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
        
//        if(bloqueValido){
//            iterador =indiceInicioIff ;               revisar DETENIDAMENTE
//        }
    }
    
    
    public void reconocerElse(){ 
////        dentroDeBloque = true;
//        try {   // Verificar else: continuar con el ELSE
//                
//               if(listTokens.get(iterador).getLexema().equals("else") && listTokens.get(iterador+1).getLexema().equals(":")){
//
//               }
//        } catch (Exception e) {
//            System.out.println("ya no hay mas token en analizar Else");
//        }
    }
    
    public void identificar_Operadores_Entrada_salida(){
        String texto_InstruccionEntradaSalida = "";
        boolean instruccionValida = true;
        
        boolean errorIdentacion = false;
        
        boolean finWhile = false;
        String siguiente1 = "print";                
        String siguiente2 = "";                
//        String siguiente3 = "";                

        int lineaAnalizada = listTokens.get(iterador).getLinea();

        if(dentroDeBloque && !verificandoCierre) {         //verificando IDENTACION dentro bloque analizar el primer token de la linea
                    if(!(listTokens.get(iterador).getColumna()==(identacionDentroBloque+1))) {
                        System.out.println("collllllllllllllllll: " +listTokens.get(iterador).getColumna());
                        instruccionValida =false;
                        errorIdentacion = true;
                        System.out.println("Error en el Bloque, columna en diferente identacion");
//                        if(listTokens.get(indiceCiereIff).getLinea() ==listTokens.get(iterador).getLinea()){
//                            salida = true;                                  //correccion para lineas Vacias dentro de bloque
//                            
//                        }
                    }    
        }
        if(listTokens.get(iterador).getColumna() !=1 && !dentroDeBloque){
            errorIdentacion = true;
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
                                instruccionValida = false;
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
                            instruccionValida =false;
                        }
                        
                        if(lex.equals(",")){
                            if(siguiente2.equals("+")){
                                instruccionValida = true;
                                finWhile = false;
                                texto_InstruccionEntradaSalida +=lex;
                                siguiente1 = "CADENA_DE_CARACTERES";
                                siguiente2 = "";
                            }else{
                                finWhile = true;
                                instruccionValida =false;
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
                            instruccionValida = false;
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
                                instruccionValida = false;
                                finWhile = true;
                            }
                        }
                        break;
                    default:
                        instruccionValida = false;
                        finWhile = true;
                } //finSw
                
                iterador++;
                if(errorIdentacion){
                    break;
                }
                
                if(finWhile){
                    break;
                }
            }       //finWhi
        } catch (Exception e) {
            System.out.println("Catch operadores de Entrada y Salida");
        }   
        
        try {   // si el ultimo token en una linea es un comentario la salida es Verdadera si es que es valida
            if(!listTokens.get(iterador).getEnum().name().equals("COMENTARIO") && listTokens.get(iterador).getLinea()==lineaAnalizada){
                instruccionValida=false;
            }
        } catch (Exception e) {
            System.out.println("ya no hay mas Tokens");
        }

        if(!listTokens.get(iterador-1).getLexema().equals(")")){ //verifica que el final de la instruccion de salida sea ")"
            instruccionValida =false;
        }
                //FIN VALIDACION
                
        guardarError(instruccionValida, texto_InstruccionEntradaSalida, listTokens.get(iterador-1), errorIdentacion);  // Guardar Error
                
            if(dentroDeBloque){                          // si esta dentro de un bloque y la instruccion es falsa El bloqueValido = False 
                if(!instruccionValida){               
                    bloqueValido = false;
                        System.out.println("\t\t\t BLOQUE INVALIDO");
                }else{
                    if(!verificandoCierre){
                        insertarInstruccion_en_Bloque(texto_InstruccionEntradaSalida); //inserta la instruccion en la lista del bloque
                    }
                }                         
            }        
            
        
//                            SOUT
        if(instruccionValida){
            System.out.println("\n\n\t\t SALIDA TRUE: " + texto_InstruccionEntradaSalida);
        }else{
            System.out.println("\n\n\t\t SALIDA FALSE: " + texto_InstruccionEntradaSalida);
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
    }//insEntrSali
    
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

    public List<Instrucciones> getListSimbolos() {
        return listSimbolos;
    }

//    public List<String> getListaInstruccionesBloque() {
//        return listaInstruccionesBloque;
//    }

    public List<BloqueCodigo> getBloques() {
        return bloques;
    }

    public List<Instrucciones> getErrores() {
        return Errores;
    }
    
    

    
}
