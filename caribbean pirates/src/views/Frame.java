
package views;

import javax.swing.*; 
 
public class Frame extends JFrame{
    //ventana principal, contiene la interfaz grafica
    public Frame() {
        setTitle("Caribbean Pirates");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850,550);
        setVisible(true);
        setLayout(null);
    }
}
