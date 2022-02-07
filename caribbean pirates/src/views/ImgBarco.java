 
package views;

import java.awt.*;
import javax.swing.*;
 
public class ImgBarco{
    //atributos del barco/agente
    public String nombre;
    public JLabel label; 
    
    public ImgBarco() {
        this.nombre = null;
        this.label = new JLabel(nombre);
        label.setIcon(new ImageIcon(getClass().getResource("barco.png")));
        label.setLocation(-300,400);
        label.setSize(200,130);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.white);
    }
    
    // getters and setters 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
     
    public String getNombre() {
        return nombre;
    }

    public JLabel getLabel() {
        return label;
    }
    
    //metodo para asignar location
    public void setLocation(int x, int y){
        label.setLocation(x, y);
    }
}
