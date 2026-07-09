/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

/**
 *
 * @author Índigo
 * Clase para armar el árbol
 *  Nombre
 *  Parte 1. Análisis sintáctico
 *  Parte 2. Análisis semántico
 *  Parte 3. Código intermedio
 *  Parte 4. Código objeto
 */
public class Nodo {
        // Atributos
        private String dato;
        private Nodo padre;
        private Nodo izquierdo;
        private Nodo derecho;
        private int valor;
        
        
        private String codigoIntermedio; //
        private String lugar; // Para los temporales
        
    public Nodo(String dato){ //Información
        this.dato = dato;
    }// Constructor
    
    public Nodo(String dato, int valor){ //Información
        this.dato = dato;
        this.valor = valor;
    }// Constructor
    
    public Nodo(String dato, Nodo izquierdo, Nodo derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.padre = null;
        this.codigoIntermedio = "";
        this.lugar = "";
    }// Constructor
    
    public Nodo(String dato, Nodo izquierdo, Nodo derecho, int valor) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.padre = null;
        this.codigoIntermedio = "";
        this.lugar = "";
        this.valor = valor;
    }// Constructor

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public String getCodigoIntermedio() {
        return codigoIntermedio;
    }

    public void setCodigoIntermedio(String codigoIntermedio) {
        this.codigoIntermedio = codigoIntermedio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
