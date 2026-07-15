/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import static javax.swing.JOptionPane.*;

/**
 *
 * @author Índigo
 * Clase para armar el árbol
 *  Nombre
 *  Parte 1. Análisis sintáctico
 *  Parte 2. Análisis semántico
 *  Parte 3. Código intermedio
 *  Parte 4. Código objeto
 * 
 */
public class ArbolAgenteIA {
        // Atributos
        Stack<Nodo> ArbolNodo;
        Stack<String> caracter;
            //Identificar entre OPERADOR Y OPERANDOS
    final String espacios = "\t";
    final String aritmeticos = "+-*/()^=";
    final String variables = "abcdefghijklmnopqrstuvwxyz";
    final String opMultiplica = "*";
    final String numeros = "1234567890";
    
    private Nodo raiz;
    //30 junio 2026
    //String [] temporales = {"T1","T2","T3","T4","T5"};
    ArrayList<String> temporales;
    int temp;
    
    HashMap<String, String> tablaSimbolos;
    HashMap<String, String> erroresSemanticos;
    HashMap<String, String> producciones;
    int paso;
    // 1ero de Julio
    ArrayList<String> reglasEjecutadas;
    
    ArrayList<String[]> tripletas;
    
    String r;
    String reglaSemantica;
    
    public String emu86; //== 15 julio
    
    // Constrcutor
    public ArbolAgenteIA(){
        emu86 = "; Ortiz Vega Índigo Andre \n"+
                ".model small \n"+
                ".stack \n"+
                ".data \n";
        reglasEjecutadas = new ArrayList<String>(); //1ro Julio
        tablaSimbolos = new HashMap();
        erroresSemanticos = new HashMap();
        producciones = new HashMap();
        temporales = new ArrayList<String>();

        ArbolNodo = new Stack<Nodo>();
        caracter = new Stack<String>();
        
        tripletas = new ArrayList<>();
        
        reglaSemantica = r = "";
        paso = 0;
        temp = 0;
    } // Constructor
    
    //****** Reglas Ejecutadas ==== 1ro Julio
    public String getReglasEjecutadas(){
        String reglasE = "";
        for (int i = 0; i < reglasEjecutadas.size(); i++) {
            System.out.println("Reglas ejecutadas "+reglasEjecutadas.get(i));
            reglasE+=reglasEjecutadas.get(i)+"\n";
        }//for
        
        System.out.println("Tabla de Simbolos");
        System.out.println("Token/id  |  Valor");
        tablaSimbolos.forEach((clave,valor) -> { System.out.println("   "+clave + "      |   " + valor);});
        return reglasE;
    }// reglasEjecutadas
    
    public void agregaValex(String lexema, String valor){
        
    }// agregaValex - Análisis Semántico
    
    public String regresaValex ( String lexema){
        return this.tablaSimbolos.get(lexema);
    } // regresaValex
    
    public void guardar(){ // Permite construir el Árbol
        if (ArbolNodo.size() < 2 || caracter.empty()) return;
        
        paso++;
        temp++;
        r = "r" + paso;
        Nodo derecho = (Nodo) ArbolNodo.pop();
        Nodo izquierdo = (Nodo) ArbolNodo.pop();
        
        String operador = caracter.pop();
        int valorNodo=0;
        //Investigar qué hace peek
            /* Sirve para ver el elemento que se encuentra en la cima 
               de la pila sin extraerlo ni eliminarlo */
            
        switch (operador) {
            case "+": valorNodo = izquierdo.getValor()+derecho.getValor(); break;
            case "-": valorNodo = izquierdo.getValor()-derecho.getValor(); break;
            case "/": valorNodo = izquierdo.getValor()/derecho.getValor(); break;
            case "*": valorNodo = izquierdo.getValor()*derecho.getValor(); break;
        }
        
        ArbolNodo.push(new Nodo(operador,izquierdo,derecho,valorNodo));
        tripletas.add(new String[]{operador,izquierdo.getValor()+"",derecho.getValor()+""});
        
        reglasEjecutadas.add("p"+paso+" E.nodo = new Nodo("+operador+",E1.nodo,T.nodo)");
    }// guardar
    
    // Métodos del árbol
    public Nodo crear(String expresion){
        //1. Considerar la expresió como un conjunto de tokens
        StringTokenizer tokenizer;
        String token;
        //0. Inicializar valores para varias ejecuciones
        paso = 0; // Paso de las reglas semánticas
        reglaSemantica = ""; r = "";
            //investigar la clase StringTokenizer
                /* es una clase que permite dividir una cadena 
                    de texto (String) en partes más pequeñas (llamadas tokens), 
                    utilizando caracteres específicos como separadores (delimitadores) */ 
        //2. Separación de tokens de la expresión
        tokenizer = new StringTokenizer(expresion,espacios+aritmeticos+"/",true);
        //3. Mientras existan tokens
        while(tokenizer.hasMoreTokens()){
            //4. Omitir espacios en blanco
            token = tokenizer.nextToken();
            System.out.println("Token "+token);
            if (espacios.contains(token)) continue;
                //5. Se trata de un identificador
                //System.out.println("Omitiendo espacios");//"Se trata de un identificador");
            if (!aritmeticos.contains(token)) { // No es un operador aritmético
                //6. Extraer de la pila los términos que estaban
                
                paso++;
                reglasEjecutadas.add("p"+paso+" T.nodo = new Hoja(id<"+token+">,id.entrada_"+token+")");
                
                    // Solicitar el valor del token
                    // e insertar en tablaSimbolos
                    // 01. Solicitar el valor para el token
                    // 02. Insertar en Tabla símbolo
                    // 03. Mostrar en consola al finalizar en getReglasEjecutadas
                    if (!numeros.contains(token)) {
                        String valor = "valor";
                        try {
                            while (!numeros.contains(valor)||valor==null) valor = showInputDialog("¿Cuál es el valor de "+token+"?");
                        } catch (Exception e) {    }
                        tablaSimbolos.put(token, valor);
                        ArbolNodo.push(new Nodo(token,Integer.parseInt(valor)));
                        emu86 += token+" dw "+valor+"\n";
                    }else {
                        ArbolNodo.push(new Nodo(token,Integer.parseInt(token)));
                        tablaSimbolos.put(token, token);
                    }// if
                
            }else if (token.equals("(")) caracter.push(token);
                //7. Tratar tokens que no son paréntesis
                
            //8. Guardar el token
            else if (token.equals(")")){
                while (!caracter.peek().equals("(")&&!caracter.empty()) guardar();
                if (!caracter.empty()) caracter.pop(); // quita el "("
            }else{ // es un operador (+,-,*,/,^,=)
                while (!caracter.empty()&&!caracter.peek().equals("(")){
                    // Si el opoerador en el tope tiene mayor o igual prioridad, se procesa primero
                    if (obtenerPrioridad(caracter.peek()) >= obtenerPrioridad(token)) guardar();
                    else break;
                } // while 
                caracter.push(token); // guardar el token
            } // else
        }// while - tokenizer - hasMoreTokens
        while (!caracter.empty()){
            if (caracter.peek().equals("(")) caracter.pop(); // El caracter tiene símbolo de apertura
            else{
                guardar(); // Aquí se insertan los operadores
                raiz = (Nodo) ArbolNodo.peek();
            }// if
        } // while !caracter.empty
        return raiz;
    }// Crear
    
    private int obtenerPrioridad (String operador){
        switch (operador) {
            case "^": return 3;
            case "*": case "/": return 2;
            case "+": case "-": return 1;
            case "=": return 0;
            default:
                return -1; // para paréntesis u otros caracteres
        }// switch
    }// obtenerPrioridad
    
    public Nodo convertirAGAD(Nodo raizAST) {
        HashMap<String, Nodo> tabla = new HashMap<>();
        return convertir(raizAST, tabla);
    }

    private Nodo convertir(Nodo n, HashMap<String, Nodo> tabla) {
        if (n == null) return null;

        if (n.getIzquierdo() == null && n.getDerecho() == null) {
            String clave = "HOJA#" + n.getDato();
            Nodo existente = tabla.get(clave);
            if (existente != null) return existente; // reutiliza
            tabla.put(clave, n);
            return n;
        }

        // Procesar hijos primero (post-orden): así al llegar al padre
        // ya sabemos si los hijos son nodos compartidos o no.
        Nodo izqNuevo = convertir(n.getIzquierdo(), tabla);
        Nodo derNuevo = convertir(n.getDerecho(), tabla);

        // Reasignar hijos (puede que ahora apunten a nodos ya existentes)
        n.setIzquierdo(izqNuevo);
        n.setDerecho(derNuevo);

        String clave = n.getDato() + "#" 
                     + System.identityHashCode(izqNuevo) + "#" 
                     + System.identityHashCode(derNuevo);

        Nodo existente = tabla.get(clave);
        if (existente != null) return existente; 

        tabla.put(clave, n);
        return n;
    }

    public ArrayList<String[]> getTripletas() {
        return tripletas;
    }

    public void setTripletas(ArrayList<String[]> tripletas) {
        this.tripletas = tripletas;
    }
    
    
    
}// Clase Arbol