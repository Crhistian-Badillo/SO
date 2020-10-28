

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FIFO implements Runnable{
    Proceso[] procesos=new Proceso[10];
    Proceso ejecucion=new Proceso();
    Random r = new Random();
    
    int indexSuspL=0;
    int indexBloqueado=0;
    int indexSuspB=0;
    int indexListo=Diagrama.contador;
    

    public FIFO(Proceso[] pr){
        procesos = pr;
    }

    @Override
    public void run() {
        Diagrama.listos=procesos;
        System.out.println("Comienza hilo principal:");
        System.out.println("Index de listo: "+indexListo);
        
        int ind=0;
        int bandera=0;
        int tiempo=0;
        Proceso p=new Proceso();
        do{
            //while(ind < Diagrama.contador){
                
                //JOptionPane.showMessageDialog(null,"Procesos Listos");
                Mostrar(Diagrama.listos,Diagrama.areaListos);
                Dormir(1000);
                int randomListo=r.nextInt(2)+1;

                if(randomListo == 1){ //Se va a ejecucion
                    System.out.println("Llegamos a EJECUCION");
                    p=Diagrama.listos[ind];
                    Diagrama.listos[ind]=null;
                    Mostrar(Diagrama.listos,Diagrama.areaListos);
                    if(p!=null){
                        Diagrama.lbProceso.setText(p.nombre);
                        System.out.println("EL PROCESO EN EJECUCION ES: "+p.nombre);
                        tiempo=Integer.parseInt(p.tiempo);
                        tiempo*=1000;
                    }                    
                    
                    int randomEjecucion=r.nextInt(3)+1;

                    switch (randomEjecucion) {
                        case 1:
                            if(p!=null){
                                //Se ejecuta completo el programa
                                System.out.println("SE DEBE DE EJECUTAR COMPLETO");
                                Dormir(tiempo);
                                System.out.println("PROCESO DURMIO");
                                Diagrama.lbProceso.setText("");
                                mostrarFinalizados(p);
                            }
                            break;
                        case 2:
                            if(p!=null){
                                // El proceso se va a bloqueo
                                System.out.println("EL PROCESO VA A BLOQUEO");
                                Dormir(2000);
                                System.out.println("PROCESO DURMIO");
                                Añadir(Diagrama.bloqueado,p,indexBloqueado);
                                Diagrama.lbProceso.setText("");
                                //JOptionPane.showMessageDialog(null,"BLOQUEADO PROCESO "+p.nombre);
                                Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);
                            }
                            
                            break;
                        case 3:
                            if(p!=null){
                                //El proceso se va suspendido listo
                                System.out.println("EL PROCESO VA A SUSPENDIDO LISTO");  
                                Dormir(2000);
                                System.out.println("PROCESO DURMIO");
                                Añadir(Diagrama.suspListo,p,indexSuspL);
                                Diagrama.lbProceso.setText("");
                                //JOptionPane.showMessageDialog(null,"SUSPENDIDO LISTO PROCESO "+p.nombre);
                                Mostrar(Diagrama.suspListo,Diagrama.areaSuspLis);
                            }
                            
                            break;
                        default:
                            break;
                    }
                }
                else { // Se suspende el proceso 
                    System.out.println("Llegamos a SUSPENDIDO LISTO");
                    p=Diagrama.listos[ind];
                    Diagrama.listos[ind]=null;
                    if(p!=null){
                        Dormir(2000);
                        Añadir(Diagrama.suspListo,p,indexSuspL);
                        //JOptionPane.showMessageDialog(null,"SUSPENDIDO LISTO PROCESO "+p.nombre);
                        Mostrar(Diagrama.suspListo,Diagrama.areaSuspLis);
                        
                        Mostrar(Diagrama.listos,Diagrama.areaListos);
                    }
                    
                }
                verificarEstados(); // Revisar las listas de los demas estados
            ind++; 
            p=null;
            if(ind == Diagrama.listos.length){
                ind=0;
            }
            
        }while(bandera == 0);
        System.out.println("Se termino el RUN");
    }
    
    public void tiempoLlegada(Proceso[] procesos){
        
    }
    
    public int Vacio(Proceso[] listo){   
        int vacio=0;
        for(int x=0;x<listo.length;x++){
            if(listo[x] != null){
                vacio=1;
            }
        }
        return vacio;
    }   
    
    public void Añadir(Proceso[] lista,Proceso p, int index){ //Mandar la lista de el estado a donde ira
        if(index == lista.length){
            index=0;
        }
        lista[index] = p;
        index++;
         System.out.println("Se Añadio: "+p.nombre+" en la posicion: "+index);
    }
    
    public void Mostrar(Proceso[] listo, JTextArea area){//Lista que se mostrara y area
        area.setText("");
        String list="";
        for(int x=0;x<listo.length;x++){
            if(listo[x] != null){
                list += listo[x].nombre+"\n";                    
            }
        }
        System.out.println(list);
        area.setText(list);
        //JOptionPane.showMessageDialog(null, "Procesos \n"+list);
    }
        
    public void mostrarFinalizados(Proceso p){
        String list=Diagrama.areaFinalizado.getText();
        list+="\n"+p.nombre;
        Diagrama.areaFinalizado.setText(list);
    }
        
    public void Dormir(int sueño){
        try {
            Thread.sleep(sueño);
        } catch (InterruptedException ex) {
            Logger.getLogger(FIFO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verificarEstados(){
        int Bloq,Sl,SB;
        
        Bloq=Vacio(Diagrama.bloqueado);
        Sl=Vacio(Diagrama.suspListo);
        SB=Vacio(Diagrama.suspBlo);
        
        if(Bloq == 1){
            Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);
            Dormir(1000);
            int va=r.nextInt(2);
            if(va == 0){// Se va a Listos
                Añadir(Diagrama.listos,Diagrama.bloqueado[Primer(Diagrama.bloqueado)], indexListo);
                Diagrama.bloqueado[Primer(Diagrama.bloqueado)]=null;
                //JOptionPane.showMessageDialog(null,"PROCESOS EN BLOQUEADO ");
                Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);
                //JOptionPane.showMessageDialog(null,"PROCESOS EN LISTO ");
                Mostrar(Diagrama.listos,Diagrama.areaListos);
            }else{// Se va a suspendido bloqueado
                Añadir(Diagrama.suspBlo,Diagrama.bloqueado[Primer(Diagrama.bloqueado)],indexBloqueado);
                Diagrama.bloqueado[Primer(Diagrama.bloqueado)]=null;
                //JOptionPane.showMessageDialog(null,"PROCESOS EN BLOQUEADO ");
                Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);
                //JOptionPane.showMessageDialog(null,"PROCESOS EN SUSPENDIDO BLOQUEADO");
                Mostrar(Diagrama.suspBlo,Diagrama.areaSusBlo);
            }    
        }
        if(Sl == 1){
            Mostrar(Diagrama.suspListo,Diagrama.areaSuspLis);
            Dormir(1000);
            Añadir(Diagrama.listos,Diagrama.suspListo[Primer(Diagrama.suspListo)], indexListo); 
            Diagrama.suspListo[Primer(Diagrama.suspListo)]=null;
            //JOptionPane.showMessageDialog(null,"PROCESOS EN SUSPENDIDO LISTO ");
            Mostrar(Diagrama.suspListo,Diagrama.areaSuspLis);
            //JOptionPane.showMessageDialog(null,"PROCESOS EN LISTOS ");
            Mostrar(Diagrama.listos,Diagrama.areaListos);
        }
        if(SB == 1){
            Mostrar(Diagrama.suspBlo,Diagrama.areaSusBlo);
            Dormir(1000);
            int va=r.nextInt(2);
            if(va == 0){// Se va a SuspListo
                Añadir(Diagrama.suspListo,Diagrama.suspBlo[Primer(Diagrama.suspBlo)],indexSuspL);
                Diagrama.suspBlo[Primer(Diagrama.suspBlo)]=null;
                //JOptionPane.showMessageDialog(null,"PROCESOS EN SUSPENDIDO BLOQUEADO ");
                Mostrar(Diagrama.suspBlo,Diagrama.areaBloqueado);
                //JOptionPane.showMessageDialog(null,"PROCESOS EN SUSPENDIDO BLOQUEADO ");
                Mostrar(Diagrama.suspListo,Diagrama.areaSuspLis);
            }else{// Se va a Bloqueado
                Añadir(Diagrama.bloqueado,Diagrama.suspBlo[Primer(Diagrama.suspBlo)],indexBloqueado);
                Diagrama.suspBlo[Primer(Diagrama.suspBlo)]=null;
                //JOptionPane.showMessageDialog(null,"PROCESOS EN SUSPENDIDO BLOQUEADO ");
                Mostrar(Diagrama.suspBlo,Diagrama.areaSusBlo);
                //JOptionPane.showMessageDialog(null,"PROCESOS EN BLOQUEADO ");
                Mostrar(Diagrama.bloqueado,Diagrama.areaBloqueado);
            }  
        }
    }
    
    public int Primer(Proceso[] lista){
        int y=0;
        if(lista[0] == null){
            for(int x=1;x<lista.length;x++){
                if(lista[x] != null){
                    y=x;
                    x=lista.length;
                }
            }
        }
        System.out.println("Posicion del primer es: "+y);
        return y;
    }
    
    
}
