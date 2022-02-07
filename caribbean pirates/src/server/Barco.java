package server;

import java.util.Iterator;
import views.ImgBarco;

public class Barco {
    public String name;
    public int faccion;
    public int tripulacionInicial;
    public int municionInicial;
    public int racionInicial;
    public int tripulacionActual;
    public int municionActual;
    public int racionActual;
    public int idBase;
    public Cofre cofre;
    public Sitio sitioActualLocal;
    public Sitio sitioDestinoLocal;
    public boolean local;
    public boolean ganador;
    public int idimg;
    public int idSitioPartida;
    public int idSitioActual; 
    public String sitioDestino;
    public String maquinaDestino;
    public String base;
    
    public Barco(String name, int faccion, int tripulacionInicial, int municionInicial, int racionInicial, String base, int idBase) {
        this.name = name;
        this.faccion = faccion;
        this.tripulacionInicial = tripulacionInicial;
        this.municionInicial = municionInicial;
        this.racionInicial = racionInicial;
        this.base = base;
        this.idBase = idBase;
        this.maquinaDestino=null;
        this.ganador=false;
        this.local=true;
        this.cofre=null;
    }
     
    // getters and setters
    public String getNombre() {
        return name;
    }

    public int getFaccion() {
        return faccion;
    }

    public int getTripulacionInicial() {
        return tripulacionInicial;
    }

    public int getMunicionInicial() {
        return municionInicial;
    }

    public int getRacionInicial() {
        return racionInicial;
    }

    public int getTripulacion() {
        return tripulacionActual;
    }

    public int getMunicion() {
        return municionActual;
    }

    public int getRacion() {
        return racionActual;
    }

    public int getIdBase() {
        return idBase;
    }

    public String getBase() {
        return base;
    }

    public Cofre getCofre() {
        return cofre;
    }

    public Sitio getSitioActualLocal() {
        return sitioActualLocal;
    }

    public Sitio getSitioDestinoLocal() {
        return sitioDestinoLocal;
    }

    public boolean isLocal() {
        return local;
    }

    public int getIdSitioActual() {
        return idSitioActual;
    }
 
    public String getDestino() {
        return sitioDestino;
    }

    public String getMaquinaDestino() {
        return maquinaDestino;
    } 
    
    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public void setFaccion(int faccion) {
        this.faccion = faccion;
    }

    public void setTripulacionInicial(int tripulacionInicial) {
        this.tripulacionInicial = tripulacionInicial;
    }

    public void setMunicionInicial(int municionInicial) {
        this.municionInicial = municionInicial;
    }

    public void setRacionInicial(int racionInicial) {
        this.racionInicial = racionInicial;
    }

    public void setTripulacion(int tripulacion) {
        this.tripulacionActual = tripulacion;
    }

    public void setMunicion(int municion) {
        this.municionActual = municion;
    }

    public void setRacion(int racion) {
        this.racionActual = racion;
    }

    public void setIdBase(int idBase) {
        this.idBase = idBase;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public void setSitioActualLocal(Sitio sitioActualLocal) {
        this.sitioActualLocal = sitioActualLocal;
    }

    public void setSitioDestinoLocal(Sitio sitioDestinoLocal) {
        this.sitioDestinoLocal = sitioDestinoLocal;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public void setIdSitioActual(int idSitioActual) {
        this.idSitioActual = idSitioActual;
    } 
    
    public void setDestino(String destino) {
        this.sitioDestino = destino;
    }

    public void setMaquinaDestino(String maquinaDestino) {
        this.maquinaDestino = maquinaDestino;
    }
    
    //metodos de simulacion
    public void definirDestino() {
        this.local = false; 
        this.sitioDestino = null;
        int aprox = -1;
        boolean isBase = false;
        Sitio sitioBase = null; 
        for (Sitio s : ServerUtils.sitios) {
            if (s.nombre.equals(base))
                sitioBase = s; 
        } 
        if (!isBase) {
            for (Mapa map : cofre.getMapas()) {
                if (map.aprox > aprox && !(sitioActualLocal.nombre.equals(map.sitio))) {
                    aprox = map.aprox;
                    sitioDestino = map.sitio;
                    maquinaDestino = map.machine;
                }
            }
        } 
        else {
            for (Mapa map : cofre.getMapas()) {
                if (!map.localizacion.equals(sitioBase.nombre) && map.aprox > aprox && !(sitioActualLocal.nombre.equals(map.sitio))) {
                    aprox = map.aprox;
                    sitioDestino = map.sitio;
                    maquinaDestino = map.machine;
                }
            }
        } 
        ServerUtils.sitios.stream().filter((s) -> (s.nombre.equals(sitioDestino))).forEachOrdered((_item) -> {
            this.local = true; 
        });
    }
    
    public void definirDestino(String sitio) {
        this.local = false;
        this.sitioDestino = sitio; 
        cofre.getMapas().stream().filter((m) -> (m.sitio.equals(sitio))).forEachOrdered((m) -> {
            maquinaDestino = m.machine;
        }); 
        ServerUtils.sitios.stream().filter((s) -> (s.nombre.equals(sitio))).forEachOrdered((_item) -> {
            this.local = true;
        });
    }
    
    public Evaluar retirarse() {
        System.out.println( name + " se ha retirado. Ha dejado la mitad de su tesoro.");
        cofre.transferirMitad(sitioActualLocal.cofre);
        return new Evaluar("zarpar", base);
    }
     
    public void iniciar() { //inicio de simulacion 
        boolean aux = true;
        Sitio destino = null;
        while(aux) { 
            if (this.sitioDestino != null) { 
                if (local) { 
                    if (sitioActualLocal != null) {
                        for (Sitio site : ServerUtils.sitios) {
                            if (this.sitioDestino.equals(site.nombre))
                                destino = site;   
                        } 
                        this.fondearLocal(this.partirLocal(destino));
                    } else
                        fondeaRemoto(this.sitioDestino);
                } 
                else {
                    partirRemoto(); 
                    break;
                } 
                Evaluar result = evaluar();
                switch (result.orden){
                    case "ganador" : { 
                        aux = false;
                        ganador = true;                   
                    }
                    case "zarpar" : {
                        if(result.destino != null) {
                            this.sitioDestino = result.destino;
                            definirDestino(this.sitioDestino);
                        } else
                            definirDestino();
                    }
                    case "retirada" : {
                        result = this.retirarse();
                        this.sitioDestino = result.destino;
                        definirDestino(this.sitioDestino);
                    }
                    case "explorar" : {
                        result = this.explorar();
                        switch (result.orden){
                            case "retirada":
                                this.retirarse();
                            case "zarpar":
                                if (result.destino == null){
                                    definirDestino();
                                    break;
                                } 
                                else
                                    if (result.destino != null){
                                        this.sitioDestino = result.destino;
                                        definirDestino(this.sitioDestino);
                                        break;
                                    }     
                        }
                    }

                } 
                if (ganador || ServerUtils.fin)
                    aux = false; 
            }  
            else { 
                ServerUtils.sitios.stream().filter((s) -> (s.nombre.equals(base))).forEachOrdered((s) -> { 
                    this.fondearInitLocal(s);
                }); 
                definirDestino(); 
                if (!local) 
                    aux = false;  
                partirRemoto();
            }       
        }
    }
    
    public Sitio partirLocal(Sitio destino) {
        ImgBarco imgbarco =  ServerUtils.imgbarcos.get(idimg);
        imgbarco.setLocation(-300, 400);
        Sitio inicio = this.sitioActualLocal;
        sitioActualLocal.removeBarco(this);   
        sitioActualLocal = null; 
        int tiempo = ServerUtils.tiempos[inicio.id][destino.id];
        System.out.println("Ha partido " + name + " rumbo a " + destino.nombre);
        System.out.println("A "+ name + " tardará " + tiempo + " segundos ir desde "+ inicio.nombre +" hasta "+ destino.nombre);
        sleep(tiempo);
        return destino;
    }
     
    public void fondearLocal(Sitio sitio){
        this.sitioActualLocal = sitio;
        this.idSitioActual = sitio.getIdSitio();
        sitio.recibirBarco(this);
        ImgBarco imagenbarco =  ServerUtils.imgbarcos.get(idimg);
        imagenbarco.setLocation(sitio.x, sitio.y);
        System.out.println("Ha llegado "+ this.name + " a " + sitioActualLocal.nombre);
    }
    
    public void fondearInitLocal(Sitio sitio) {
        ImgBarco imgbarco = null;
        int aux = 0;
        for (ImgBarco i : ServerUtils.imgbarcos) {
            if (i.label.getText() == null) {
                idimg = aux;
                imgbarco = i;
                i.label.setText(name);
                break;
            }
            aux++;
        }
        imgbarco.setLocation(sitio.x, sitio.y);
        this.sitioActualLocal = sitio;
        this.idSitioActual = sitio.getIdSitio();
        sitio.recibirBarco(this); 
        System.out.println(this.name + " llegó a " + sitioActualLocal.nombre);
    }
    
    public int destino() {
        int aux = -1;
        String location = "";
        for (Mapa map : cofre.mapas) {
            if (sitioDestino.equals(map.sitio)) 
                location = map.localizacion; 
        }
        switch (location) {
            case "Isla Nueva Esperanza" : {
                aux = 0;
            }
            case "Cayo del buen viento" : {
                aux = 1;
            }
            case "Cayo de sotavento" : {
                aux = 2;
            }
            case "Isla La Holandesa" : {
                aux = 3;
            }
            case "Isla El Naufrago" : {
                aux = 4;
            }
            case "Isla Tortuga" : {
                aux = 5;
            }
            case "Isla Las Aves" : {
                aux = 6;
            }
            case "Cayo de Barlovento" : {
                aux = 7;
            }
            case "La Gran Isla de la Española" : {
                aux = 8;
            }
            case "Cayo de los Pelicanos" : {
                aux = 9;
            }
        }
        return aux;
    } 
     
    public boolean statusAgente(){
        return !(tripulacionActual < tripulacionInicial/3 || municionActual < municionInicial/3 || racionActual < racionInicial/3);
    } 
    public void partirRemoto() {
        sitioActualLocal.removeBarco(this);
        idSitioActual = sitioActualLocal.getIdSitio();
        int tiempo = ServerUtils.tiempos[idSitioActual][destino()];
        System.out.println("Ha partido " + name + " rumbo a " + sitioDestino);
        ImgBarco imgbarco =  ServerUtils.imgbarcos.get(idimg);
        imgbarco.label.setText(null);
        imgbarco.setLocation(-300, 400);
        System.out.println("A "+ name + " tardará " + tiempo + " segundos ir desde "+ sitioActualLocal.nombre +" hasta "+ sitioDestino);
        sleep(tiempo / 2);
    }

    public void fondeaRemoto(String sitio) {
        int aux = 0;
        ImgBarco imgbarco = null;
        for (ImgBarco img : ServerUtils.imgbarcos) {
            if (img.label.getText() == null) {
                idimg = aux;
                imgbarco = img;
                img.label.setText(name);
                break;
            }
            aux++;
        }
        for (Sitio site : ServerUtils.sitios) {
            if (sitio.equals(site.nombre)) {
                sitioActualLocal = site;
                int segundos = (int) ServerUtils.tiempos[idSitioPartida][sitioActualLocal.id]/2;
                sleep(segundos);
                site.recibirBarco(this);
                imgbarco.setLocation(sitioActualLocal.x, sitioActualLocal.y);
                break;
            }
        }
        System.out.println("Ha llegado "+ this.name + " a " + sitioActualLocal.nombre);
    }
 
    public Evaluar evaluar(){ 
        sleep(1);
        Sitio auxbase = null; 
        for (Sitio site : ServerUtils.sitios) {
            if (site.nombre.equals(base))
                auxbase = site; 
        } 
        if (auxbase != null && sitioActualLocal == auxbase) { 
            while (this.tripulacionActual < this.tripulacionInicial || municionActual < municionInicial || racionActual < racionInicial){
                tripulacionActual += tripulacionActual;
                municionActual += 5;
                racionActual += 5;
                this.sleep(1);
                if(tripulacionActual > tripulacionInicial) 
                    tripulacionActual = tripulacionInicial;
                if(municionActual > municionInicial)
                    municionActual = municionInicial;
                if(racionActual > racionInicial)
                    racionActual = racionInicial;
                System.out.println(name+": se está recuperando - Tripulación: "+ tripulacionActual + " - Munición: " + municionActual + " - Ración: " + racionActual);
            }
            System.out.println(name + ": se ha recuperado - Tripulación: " + tripulacionActual+ " - Munición: "+ municionActual + " - Ración: " + racionActual);
            while (this.getCofre().getTesoros().size() > 0){ 
                sleep(getCofre().getTesoros().size()); 
                this.getCofre().transferAll(this.sitioActualLocal.getCofre());
                System.out.println(name + " transfirió todo su cofre a " + sitioActualLocal.nombre);
            }
            for (Tesoro t : sitioActualLocal.cofre.tesoros) {
                if  (t.valor == 3)
                    System.out.println(name + " ha conseguido el Corazón del la Princesa - Ganador!");
                return new Evaluar("ganador");
            } 
            return new Evaluar("zarpar");
        } 
        else  { 
            if(!sitioActualLocal.getCalamidades().isEmpty()){
                String nombreC = sitioActualLocal.getCalamidades().get(0).getNombre();
                int auxTrip = sitioActualLocal.getCalamidades().get(0).getBajasTripulacion();
                int auxMun = sitioActualLocal.getCalamidades().get(0).getBajasMunicion();
                int auxRac = sitioActualLocal.getCalamidades().get(0).getBajasRacion();
                tripulacionActual -= auxTrip;
                municionActual -= auxMun;
                racionActual -= auxRac;
                System.out.println(name + " sufrió la calamidad "+ nombreC);
                System.out.println("Se sufrieron daños de "+auxTrip+" a tripulacion, " + auxMun +" a municion y " + auxRac + " a raciones");
                if(!sitioActualLocal.getCalamidades().isEmpty())
                    sitioActualLocal.removeCalamidad(sitioActualLocal.calamidades.get(0));
                sleep(1);
            } 
            if(this.statusAgente()){ 
                if(sitioActualLocal.getBarcos().size() > 1) { 
                    for (Barco i : sitioActualLocal.barcos) {
                        if (i.getFaccion() != this.getFaccion()){
                            batalla(i); 
                            if(!this.statusAgente())
                                return new Evaluar("retirada"); 
                        }
                    } 
                    return new Evaluar("explorar");   
                } 
                else 
                    return new Evaluar("explorar"); 
            } 
            else {
                System.out.println(this.getNombre() + ": decidió partir por falta de tripulacion, municion o raciones");
                return new Evaluar("zarpar", base);
            }     
        } 
    } 
    
    public void batalla(Barco enemigo) {
        System.out.println("Acaba de comenzar una batalla!");
        System.out.println("Atacante : " + name);
        System.out.println("Tripulación: " + tripulacionActual);
        System.out.println("Munición: " + municionActual);
        System.out.println("Atacado : " + enemigo.name);
        System.out.println("Tripulacion: " + enemigo.tripulacionActual);
        System.out.println("Munición: " + enemigo.municionActual);
        System.out.println(name + " fue a batalla con " + enemigo.name + "!");
        int dañosTripulacion = Math.abs(tripulacionActual - enemigo.tripulacionActual);
        int dañosMunicion = Math.abs(municionActual - enemigo.municionActual);
        tripulacionActual -= dañosTripulacion;
        municionActual -= dañosMunicion;
        enemigo.tripulacionActual -= dañosTripulacion;
        enemigo.municionActual -= dañosMunicion;
        System.out.println("Batalla terminada");
        System.out.println("Atacante : " + name);
        System.out.println("Tripulacion: " + tripulacionActual);
        System.out.println("Municion: " + municionActual);
        System.out.println("Atacado : " + enemigo.name);
        System.out.println("Tripulacion: " + enemigo.tripulacionActual);
        System.out.println("Municion: " + enemigo.municionActual);      
    }
 
    public Evaluar explorar(){
        System.out.println(name + " ha decidido explorar " + sitioActualLocal.nombre);
        boolean full = false;
        Sitio objetivo = sitioActualLocal; 
        while(this.statusAgente() && !objetivo.cofre.tesoros.isEmpty() && cofre.plenitudBarco() <= 1){
            if (full==true)
                break; 
            System.out.println(name + " - Objetivo: "+ objetivo.nombre); 
            System.out.println(name + ": Explorar " + objetivo.nombre +" tardará " + objetivo.time + " segundos..");
            sleep(objetivo.getTime());
            System.out.println(name + ": Cofre del tesoro encontrado!");  
            while(cofre.plenitudBarco() < 1 && !objetivo.cofre.tesoros.isEmpty()) {
                System.out.println(name +": Porcentaje de ocupación del cofre: "+ cofre.plenitudBarco()*100);
                Tesoro tesoroTomando = objetivo.getCofre().getTesoros().get(0);
                for(Tesoro j : objetivo.cofre.tesoros) 
                    if (j.getValor()==3) 
                        tesoroTomando=j; 
                    else if(j.getValor()==2)
                        tesoroTomando=j; 
                switch (tesoroTomando.getValor()) {
                    case 3 : {
                        this.getCofre().addTreasure(tesoroTomando);
                        System.out.println(this.getNombre()+" obtuvo el Corazon de la Princesa!");
                    }
                    case 2 : {
                        this.getCofre().addTreasure(tesoroTomando);
                        System.out.println(this.getNombre() + " ha tomado el tesoro " + tesoroTomando.getNombre() + " de "+ objetivo.getNombre());
                        this.getCofre().addMap();
                    }
                    case 1 : {
                        this.getCofre().addTreasure(tesoroTomando);
                        System.out.println(this.getNombre() + " ha tomado el tesoro " + tesoroTomando.getNombre()+ " de "+ objetivo.getNombre());
                    }
                } 
                if (this.getCofre().plenitudBarco() > 1) {
                    System.out.println(this.getNombre()+": Se eliminó " + tesoroTomando.getNombre() + " del cofre porque sobrepasaba la capacidad.");
                    this.getCofre().deleteTreasure(tesoroTomando);
                    full = true; 
                    break; 
                } else {
                    System.out.println(this.getNombre()+": Peso actual: " + this.getCofre().getValorTotal() + " Porcentaje ocupado del cofre : " + this.getCofre().plenitudBarco()*100);
                    objetivo.getCofre().deleteTreasure(tesoroTomando); 
                    sitioActualLocal.updateAprox();
                } 
                if (!this.statusAgente()){
                    System.out.println(name + " fue atacado mientras exploraba " + sitioActualLocal.nombre);
                    break;  
                }
            } 
        } 
        objetivo.updateAprox();
        cofre.actualizarMapa(objetivo);
        System.out.println(name + " actualizo el valor de " + sitioActualLocal.nombre); 
        if (!this.statusAgente()) 
            return new Evaluar("retirada");
          else {
            boolean corazon = false;
            for (Tesoro i : this.getCofre().getTesoros()){
                if (i.getValor()==3)
                    corazon=true;
            } 
            if(this.getCofre().plenitudBarco()>0.9 || corazon==true){
                System.out.println(this.getNombre()+": navegando hacia base a dejar botín.");
                return new Evaluar("zarpar", base);
            } else 
                return new Evaluar("zarpar"); //siguiente destino
        }
    }
    
    //delay por segundos
    public void sleep(int segundos){
        try {
            if(segundos*1000<1)
                Thread.sleep(5); 
            else
                Thread.sleep(segundos*1000); 
        } catch (InterruptedException ex) {
            System.out.println("Error en sleep " + name);        
        }
    }
}