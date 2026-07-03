/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

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
public class Arbol {
        // Atributos
        Stack<Nodo> ArbolNodo;
        Stack<String> caracter;
            //Identificar entre OPERADOR Y OPERANDOS
    final String espacios = "\t";
    final String aritmeticos = "+-*/()^=";
    final String variables = "abcdefghijklmnopqrstuvwxyz";
    final String opMultiplica = "*";
    
    private Nodo raiz;
    //30 junio 2026
    String [] temporales = {"T1","T2","T3","T4","T5"};
    
    HashMap<String, String> tablaSimbolos;
    HashMap<String, String> erroresSemanticos;
    HashMap<String, String> producciones;
    int paso;
    // 1ero de Julio
    ArrayList<String> reglasEjecutadas;
    // Constrcutor
    public Arbol(){
        reglasEjecutadas = new ArrayList<String>(); //1ro Julio
        tablaSimbolos = new HashMap();
        erroresSemanticos = new HashMap();
        producciones = new HashMap();

        ArbolNodo = new Stack<Nodo>();
        caracter = new Stack<String>();

        paso = 0;
    } // Constructor
    
    //****** Reglas Ejecutadas ==== 1ro Julio
    public String getReglasEjecutadas(){
        String reglasE = "";
        for (int i = 0; i < reglasEjecutadas.size(); i++) {
            System.out.println("Reglas ejecutadas "+reglasEjecutadas.get(i));
            reglasE+=reglasEjecutadas.get(i)+"\n";
        }//for
        return reglasE;
    }// reglasEjecutadas
    
    public void agregaValex(String lexema, String valor){
        
    }// agregaValex - Análisis Semántico
    
    public String regresaValex ( String lexema){
        return this.tablaSimbolos.get(lexema);
    } // regresaValex
    
    public void guardar(){ // Permite construir el Árbol
        paso++;
        Nodo izquierdo = (Nodo) ArbolNodo.pop();
        Nodo derecho = (Nodo) ArbolNodo.pop();
        
        String operador = caracter.peek();
        //Investigar qué hace peek
            /* Sirve para ver el elemento que se encuentra en la cima 
               de la pila sin extraerlo ni eliminarlo */
        ArbolNodo.push(new Nodo(caracter.pop(),izquierdo,derecho));
        //
        if (operador.equals("+")) {
            String reglaE = "E.nodo = new Nodo(+,E1.nodo,T.nodo)";
            reglasEjecutadas.add("p"+paso+" "+reglaE);
        }// El operador es +
        
        if (operador.equals("*")) {
            String reglaE = "E.nodo = new Nodo(*,E1.nodo,T.nodo)";
            reglasEjecutadas.add("p"+paso+" "+reglaE);
        }// El operador es *
        
        if (operador.equals("-")) {
            String reglaE = "E.nodo = new Nodo(-,E1.nodo,T.nodo)";
            reglasEjecutadas.add("p"+paso+" "+reglaE);
        }// El operador es -
        
        if (operador.equals("/")) {
            String reglaE = "E.nodo = new Nodo(/,E1.nodo,T.nodo)";
            reglasEjecutadas.add("p"+paso+" "+reglaE);
        }// El operador es /
    }// guardar
    
    // Métodos del árbol
    public Nodo crear(String expresion){
        //1. Considerar la expresió como un conjunto de tokens
        StringTokenizer tokenizer;
        String token;
        //0. Inicializar valores para varias ejecuciones
        paso = 0; // Paso de las reglas semánticas
            //investigar la clase StringTokenizer
                /* es una clase que permite dividir una cadena 
                    de texto (String) en partes más pequeñas (llamadas tokens), 
                    utilizando caracteres específicos como separadores (delimitadores) */ 
        //2. Separación de tokens de la expresión
        tokenizer = new StringTokenizer(expresion,espacios+aritmeticos,true);
        //3. Mientras existan tokens
        while(tokenizer.hasMoreTokens()){
            //4. Omitir espacios en blanco
            token = tokenizer.nextToken();
            System.out.println("Token "+token);
            if (espacios.indexOf(token)>=0) {
                //5. Se trata de un identificador
                System.out.println("Omitiendo espacios");//"Se trata de un identificador");
                //
            }else if (aritmeticos.indexOf(token)<0) { // No es un operador aritmético
                //6. Extraer de la pila los términos que estaban
                        ArbolNodo.push(new Nodo(token));
                        paso++;
                        String regla = "T.nodo = new Hoja(id<"+token+">,id.entrada_"+token+")";
                        reglasEjecutadas.add("p"+paso+" "+regla);
                    }else if (token.equals(")")){
                    //7. Tratar tokens que no son paréntesis
                        while(!caracter.empty()&& !caracter.peek().equals("(")){
                            guardar();
                        }// while
                        caracter.pop();
            //8. Guardar el token
            }else{
                if (!token.equals("(")&&!caracter.empty()) {
                    String exa = (String) caracter.peek();
                    while (!exa.equals("(")&&!caracter.empty()&&aritmeticos.indexOf(exa)>=aritmeticos.indexOf(token)) { 
                            guardar();
                            if (!caracter.empty()) { exa = (String) caracter.peek(); } // If !caracter.empty
                    }// while !exa
                }// if-token
                caracter.push(token); // guardar el token
            } // if else
        }// while - tokenizer - hasMoreTokens
        while (!caracter.empty()){
            if (caracter.peek().equals("(")) { // El caracter tiene símbolo de apertura
                caracter.pop();
            }else{
                guardar(); // Aquí se insertan los operadores
                raiz = (Nodo) ArbolNodo.peek();
            }// if
        } // while !caracter.empty
        return raiz;
    }// Crear
    
}// Clase Arbol