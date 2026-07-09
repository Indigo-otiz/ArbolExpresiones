/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Índigo
 * =============================================
 * MÉTODO PARA DIBUJAR ÁRBOL GRÁFICO
 * INSTRUCCIONES:
 * a. Solicitar el ancho y color de las líneas
 * b. Solicitar el ancho y color de los nodos
 * c. Decorarlo con contenido del nodo
 * d. Agregar el método ON CLOSE con la opción de this.dispose() para EVITAR
 *    que cierre el proyecto.
 * 
 * NOMBRE:  Índigo Andre Ortiz Vega
 * FECHA: 08/07/2026
 * ==============================================
 */
public class PanelArbol extends JPanel {
    private final Nodo raiz;
    private final int RADIO = 20;
    private final int ESPACIO_VERTICAL = 60;
    private PersonalizarPanelArbol p = new PersonalizarPanelArbol();
    private int anchoNodo;
    private int anchoLinea;
    private Color colorNodo;
    private Color colorLinea;
    
    public PanelArbol(Nodo raiz) {
        this.raiz = raiz;
        setBackground(Color.WHITE);
    }
    
    public PanelArbol(Nodo raiz, PersonalizarPanelArbol p) {
        this.p = p;
        this.raiz = raiz;
        
        this.anchoNodo = p.getAnchoNodo();
        this.anchoLinea = p.getAnchoLinea();
        this.colorNodo = p.getColorNodo();
        this.colorLinea = p.getColorLinea();
    
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            // LINEAS...MODIFICAR
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // INICIA DESDE EL CENTRO SUPERIOR
            dibujarNodo(g2, raiz, getWidth() / 2, 40, getWidth() / 4);
            Nodo nodoValor = new Nodo(String.valueOf(raiz.getValor()));
            dibujarNodo(g2, nodoValor, getWidth() / 2+anchoNodo, 40, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics2D g, Nodo nodo, int x, int y, int espacioHorizontal) {
        if (nodo == null) return;
        
        // Dibujar NODOS IZQUIERDO Y DERECHO 
        g.setStroke(new BasicStroke(anchoLinea));
        
        if (nodo.getIzquierdo() != null) {
            g.setColor(colorLinea);
            g.drawLine(x, y, x - espacioHorizontal, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.getIzquierdo(), x - espacioHorizontal,
                    y + ESPACIO_VERTICAL, espacioHorizontal / 2);
            
            Nodo nodoValor = new Nodo(String.valueOf(nodo.getIzquierdo().getValor()));
            dibujarNodo(g, nodoValor, x - espacioHorizontal+anchoNodo,
                    y + ESPACIO_VERTICAL, espacioHorizontal / 2);
        }
        if (nodo.getDerecho() != null) {
            g.setColor(colorLinea);
            g.drawLine(x, y, x + espacioHorizontal, y + ESPACIO_VERTICAL);
            dibujarNodo(g, nodo.getDerecho(), x 
                    + espacioHorizontal, y + ESPACIO_VERTICAL, espacioHorizontal / 2);
            
            Nodo nodoValor = new Nodo(String.valueOf(nodo.getDerecho().getValor()));
            dibujarNodo(g,nodoValor , x 
                    + espacioHorizontal+anchoNodo, y + ESPACIO_VERTICAL, espacioHorizontal / 2);
        }

        // FORMATO DEL NODO
        g.setColor(colorNodo); 
        g.fillRect(x - anchoNodo/2, y - anchoNodo/2, anchoNodo, anchoNodo);
        g.setStroke(new BasicStroke(anchoLinea));
        g.setColor(colorNodo.darker());
        g.drawRect(x - anchoNodo/2, y - anchoNodo/2, anchoNodo, anchoNodo);

        //TEXTO CENTRADO DEL NODO
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(nodo.getDato());
        int altoTexto = fm.getAscent();
        g.drawString(nodo.getDato(), x - (anchoTexto / 2), y + (altoTexto / 4));
    }//dibujarNodo
    
}//FIN CLASE
