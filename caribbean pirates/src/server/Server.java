 
package server;

import java.rmi.*;
import java.rmi.registry.*;
import java.io.*; 
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
 
import views.Objeto; 

public class Server {
    static int port = 2000;
    static String idserver = "";
    static ServerUtils server = null;
    static NodeList nList1, nList2;
    static Node node1, node2, node3;
    static Element elemBoat, elemMap, elemCalamity;
    static Barco barco;
    static String name, base, machine, groundType, island, place, cayo; 
    static int crew, ammunition, portions, player, idBase, weight, value, id, x, y, parent, navigation;
    static float probability;
    static Cofre cofre;
    static Sitio sitio;
    static File file;
    static Document document;
    
    public static void BoatElements(){
        if (elemBoat.getElementsByTagName("naves").item(0) != null) {
            node1 = elemBoat.getElementsByTagName("naves").item(0);
            elemBoat = (Element) node1;
            nList1 = elemBoat.getElementsByTagName("barco");
            for (int i=0; i<nList1.getLength(); i++) {        
                node1 = nList1.item(i);
                elemBoat = (Element) node1;
                name = elemBoat.getElementsByTagName("nombre").item(0).getTextContent();
                crew = Integer.parseInt(elemBoat.getElementsByTagName("tripulacion").item(0).getTextContent()); 
                ammunition = Integer.parseInt(elemBoat.getElementsByTagName("municiones").item(0).getTextContent()); 
                portions = Integer.parseInt(elemBoat.getElementsByTagName("raciones").item(0).getTextContent());
                if ("pirata".equals(elemBoat.getElementsByTagName("jugador").item(0).getTextContent()))
                     player = 0;
                else player = 1;
                base = elemBoat.getElementsByTagName("base").item(0).getTextContent();
                idBase = Integer.parseInt(elemBoat.getElementsByTagName("idbase").item(0).getTextContent());
                barco = new Barco(name, player, crew, ammunition, portions, base, idBase);
                if (player == 0) 
                    cofre = new Cofre(420); 
                else 
                    cofre = new Cofre(210);  
                Map(elemBoat,"boat");
                barco.setCofre(cofre);
                server.addBarco(barco); 
            }
        }
    }
    
    public static void Treasure(){
        if(elemBoat.getElementsByTagName("cofres").item(0) != null) {
            node1 = elemBoat.getElementsByTagName("cofres").item(0);
            elemMap = (Element) node1;
            nList2 = elemMap.getElementsByTagName("cofre");
            for(int j=0; j<nList2.getLength(); j++) {
                node1 = nList2.item(j);
                elemMap = (Element) node1;
                name = elemMap.getElementsByTagName("nombre").item(0).getTextContent();
                weight = Integer.parseInt(elemMap.getElementsByTagName("peso").item(0).getTextContent());
                value = Integer.parseInt(elemMap.getElementsByTagName("valor").item(0).getTextContent());
                cofre.addTreasure(new Tesoro(name, weight, value));
            }
        }
    }
    
    public static void Map(Element elem, String type){
        node1 = elemBoat.getElementsByTagName("mapas").item(0);
        elem = (Element) node1;
        nList2 = elem.getElementsByTagName("mapa");
        for(int j=0; j<nList2.getLength(); j++) {
            node1 = nList2.item(j);
            elem = (Element) node1;
            island = cayo = place = null;
            machine = elem.getElementsByTagName("pantalla").item(0).getTextContent();
            if (elem.getElementsByTagName("isla").item(0) != null)
                island = elem.getElementsByTagName("isla").item(0).getTextContent();
            if (elem.getElementsByTagName("cayo").item(0) != null)
                cayo = elem.getElementsByTagName("cayo").item(0).getTextContent();
            value = Integer.parseInt(elem.getElementsByTagName("valorposible").item(0).getTextContent());
            if (elem.getElementsByTagName("lugar").item(0) != null)
                place = elem.getElementsByTagName("lugar").item(0).getTextContent();
            if (cayo != null)
                if("map".equals(type))
                    cofre.addTreasure((Tesoro) new Mapa(machine, cayo, value));
                else cofre.addMap(new Mapa(machine, cayo, value));
            else
                if("map".equals(type))
                    cofre.addTreasure((Tesoro) new Mapa(machine, island, place, value));
                else cofre.addMap(new Mapa(machine, island, place, value));
            }
    }
    
    public static void Calamity(){
        if(elemBoat.getElementsByTagName("calamidades").item(0) != null) {
            node2 = elemBoat.getElementsByTagName("calamidades").item(0);
            elemMap = (Element) node2;
            nList2 = elemMap.getElementsByTagName("calamidad");
            for(int j=0; j<nList2.getLength(); j++) {
                node3 = nList2.item(j);
                elemCalamity = (Element) node3;
                name = elemCalamity.getElementsByTagName("nombre").item(0).getTextContent();
                crew = Integer.parseInt(elemCalamity.getElementsByTagName("quitaTripulacion").item(0).getTextContent());
                portions = Integer.parseInt(elemCalamity.getElementsByTagName("quitaRacion").item(0).getTextContent());
                ammunition = Integer.parseInt(elemCalamity.getElementsByTagName("quitaMunicion").item(0).getTextContent());
                probability = Float.parseFloat(elemCalamity.getElementsByTagName("probabilidad").item(0).getTextContent());
                sitio.addCalamidad(new Calamidad(name, crew, ammunition, portions, probability));
            }
        }
    }
    
    public static void Location(){
        nList1 = document.getElementsByTagName("servidor");
        node1 = nList1.item(0);
        elemBoat = (Element) node1;
        node1 = elemBoat.getElementsByTagName("localizaciones").item(0);
        elemBoat = (Element) node1;
        nList1 = elemBoat.getElementsByTagName("localizacion");
        for (int i=0; i<nList1.getLength(); i++){
            id = -1;
            groundType = null;
            parent = -1;
            x = y = 0;
            navigation = -1;
            node1 = nList1.item(i);
            elemBoat = (Element) node1;
            name = elemBoat.getElementsByTagName("nombre").item(0).getTextContent(); 
            if (elemBoat.getElementsByTagName("id").item(0) != null) {
                id = Integer.parseInt(elemBoat.getElementsByTagName("id").item(0).getTextContent());
                groundType = elemBoat.getElementsByTagName("terreno").item(0).getTextContent();
                x = Integer.parseInt(elemBoat.getElementsByTagName("x").item(0).getTextContent());
                y = Integer.parseInt(elemBoat.getElementsByTagName("y").item(0).getTextContent());
                if (groundType.equals("cayo")) 
                    server.addCayo(new Objeto(name,"cayo", 200,200,x, y));
                 else if(groundType.equals("isla")) 
                    server.addIsla(new Objeto(name,"isla",362,282,x,y));
            }
            if (elemBoat.getElementsByTagName("navegacion").item(0) != null) 
                navigation = Integer.parseInt(elemBoat.getElementsByTagName("navegacion").item(0).getTextContent()); 
            if (elemBoat.getElementsByTagName("posicion").item(0) != null) {
                parent = Integer.parseInt(elemBoat.getElementsByTagName("posicion").item(0).getTextContent());
                sitio = new Sitio(parent, name, groundType, navigation, parent);
            } else 
                sitio = new Sitio(id, name, groundType, navigation, x, y); 
            if (!"isla".equals(groundType) || groundType == null) 
                cofre = new Cofre(1000);
            else  
                cofre = null; 
            Treasure(); 
            if((elemBoat.getElementsByTagName("mapas").item(0) != null))
                Map(elemMap,"map"); 
            Calamity();
            sitio.setCofre(cofre);
            server.addSitio(sitio);
        }
    }
    
    public static void ProcessXML(){
        try{
            //procesamiento de xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(file);
            document.getDocumentElement().normalize();
            nList1 = document.getElementsByTagName("servidor");
            node1 = nList1.item(0);
            elemBoat = (Element) node1;
            // IDENTIFICADOR DEL SERVIDOR
            idserver = elemBoat.getElementsByTagName("nombre").item(0).getTextContent();
           try {
                ServerUtils s = new ServerUtils(idserver);
                server = s;
            } catch (RemoteException ex) {
                System.out.println("error: " + ex);
                System.exit(0);
           }
           // ELEMENTOS DE BARCO
           // si hay barcos
            BoatElements();
            // SITIOS DEL SERVIDOR
            Location();
        }catch (ParserConfigurationException ex) {
            System.out.println("Error en Parser: "+ ex);
            System.exit(0);
        }
        catch (SAXException ex) {
            System.out.println("Error en SAX: " + ex);
            System.exit(0);
        }catch (IOException e) {
            System.out.println("Error en lectura de numero: " + e);
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        try { 
            System.out.println("Ingrese el numero del servidor (1/2/3/4)");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt(); 
            file=new File("");
            if ((option==1)||(option==2)||(option==3)||(option==4)){
                file = new File ("/C:/Users/Luis/Documents/9no semestre/model/xml/archivo"+option+".xml"); 
                System.out.println("Validando y procesando archivo XML....");
            }
            else
                System.out.println("Ingrese una opción válida");
           ProcessXML();
            // inicializacion de registro RMI
            try {
                Registry registro = LocateRegistry.getRegistry(port);
                registro.rebind(idserver, server);
            } catch (RemoteException e) {
                Registry registro = LocateRegistry.createRegistry(port);
                registro.rebind(idserver, server);
            }
            server.setSubsitios();
            server.iniciarVentana(); 
            System.out.println(idserver + " escuchando");
        } 
        catch (RemoteException e) {
            System.out.println("Error en servidor: " + e);
            System.exit(0);
        } 
    }
}
