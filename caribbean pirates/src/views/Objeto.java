 
package views; 

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
 //clase utilizada para instanciar objetos estaticos de la interfaz
public class Objeto {
    public JLabel label;
    public String tipo;
    String nombre;
    public int sizex;
    public int sizey;
    public int locationx;
    public int locationy;
    //constructor
    public Objeto(String nombre, String tipo, int sizex, int sizey, int locationx, int locationy) {
        this.nombre = nombre;
        this.tipo=tipo;
        this.sizex=sizex;
        this.sizey=sizey;
        this.locationx=locationx;
        this.locationy=locationy;
        String img="";
        if ("mar".equals(tipo)){
            img="mar.png"; 
            label = new JLabel();
        }
        if (("isla".equals(tipo))||("cayo".equals(tipo))){
            label = new JLabel(nombre.toUpperCase());
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            label.setForeground(Color.white);
            if ("isla".equals(tipo))
                img="isla.png";  
            else 
                img="cayo.png";
        }
        label.setIcon(new ImageIcon(getClass().getResource(img)));
        label.setLocation(locationx, locationy);
        label.setSize(sizex,sizey);
    }
//getters and setters
    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
        public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getSizex() {
        return sizex;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }
        public int getLocationx() {
        return locationx;
    }

    public void setLocationx(int locationx) {
        this.locationx = locationx;
    }

    public int getLocationy() {
        return locationy;
    }

    public void setLocationy(int locationy) {
        this.locationy = locationy;
    }
}
