/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import static interfaz.Principal.JPRam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



/**
 *
 * @author LSMORALES
 */
public class Ejecutando {
    
    public void ejecutando(){
    	Hilo hilo = new Hilo();
        String procesoTerminado = "";
        int n;
        int contadorImpresora = hilo.getContadorImpresora();
        int contadorVideo = hilo.getContadorVideo();
        
        
        procesos.setValueAt(hilo.EJECUTANDO,hilo.getProcesoAleatorio(), 2);
        if(JPRam.getValue()<513){
            if(JPRam.getValue() + (int)procesos.getValueAt(hilo.getI(),3) > 512){
                JOptionPane.showMessageDialog(null, "MEMORIA INSUFICIENTE - FIN DEL PROGRAMA");
                try{
                    Thread.sleep(100000);
                }catch(Exception e){
                    Logger.getLogger("ERror de mensaje "+e, null);
                }
            }else{
                hilo.setRam(hilo.getRam() +((int)(procesos.getValueAt(hilo.getI(), 3))));
                JPRam.setValue(hilo.getRam());
            }
        }

        if(procesos.getValueAt(hilo.getProcesoAleatorio(), 4).equals(hilo.VIDEO)){
        	contadorVideo++;
        }else if(procesos.getValueAt(hilo.getProcesoAleatorio(), 4).equals(hilo.IMPRESORA)){
        	contadorImpresora++;
        }
        if(hilo.getProcesoAleatorio() > (hilo.getI()-4) && hilo.getProcesoAleatorio() < (hilo.getI()-2)){
            n= 2;
        }else if(hilo.getProcesoAleatorio()>(hilo.getI()-3) && hilo.getProcesoAleatorio()<hilo.getI()){
            n=1;
        }else if(hilo.getProcesoAleatorio() == hilo.getI()){
            n = 0;
        }else{
            n = (int) Math.round(Math.random() * 3)+1;
        }

        for(int j = 0;j <n;j++){                    
            procesoTerminado = (String)procesos.getValueAt(auxi++, 2);
            System.out.println("El proceso en espera "+auxi + " y su estado es "+procesoTerminado);
            if(procesoTerminado.equals(hilo.LISTO)){
                System.out.println("Estamos aqui");
                procesos.setValueAt("Espera", (auxi-1), 2);    
            }  

        }   
        try {
            Thread.sleep(hilo.getQuantum()*1000);
        } catch (InterruptedException ex) {
        	Logger.getLogger("Error "+ex);
        }
    }
    
    public class progreso implements ActionListener{
    	Hilo hilo = new Hilo();
        @Override
        public void actionPerformed(ActionEvent etv){
            hilo.setRam(hilo.getRam() +(int)procesos.getValueAt(hilo.getI(), 3));
            JPRam.setValue(hilo.getRam());
            
        }
    }
    
}
