/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static interfaz.Principal.procesos;
import static Logica.Hilo.procesoaleatorio;
import static Logica.Hilo.contador_impresora;
import static Logica.Hilo.contador_video;
import static Logica.Hilo.i;
import static Logica.Hilo.auxi;
import static Logica.Hilo.quantum;
import Logica.Memoria;
import javax.swing.JProgressBar;
import static interfaz.Principal.JPRam;
import static Logica.Hilo.ram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author LSMORALES
 */
public class Ejecutando {
    
    public static void ejecutando(){
        String procesoTerminado = "";
        int n;
        
        procesos.setValueAt("Ejecutando", procesoaleatorio, 2);
        if(JPRam.getValue()<513){
            if(JPRam.getValue() + (int)procesos.getValueAt(i,3) > 512){
                JOptionPane.showMessageDialog(null, "MEMORIA INSUFICIENTE - FIN DEL PROGRAMA");
                try{
                    Thread.sleep(100000);
                }catch(Exception e){
                    System.out.println("ERror de mensaje "+e);
                }
            }else{
                ram = ram +(int)procesos.getValueAt(i, 3);
                JPRam.setValue(ram);
            }
        }
        
        
        //JPRam.setValue(ram);
        
        //Memoria.PintarMemoria();
        if(procesos.getValueAt(procesoaleatorio, 4).equals("Video")){
            contador_video++;
        }else if(procesos.getValueAt(procesoaleatorio, 4).equals("Impresora")){
            contador_impresora++;
        }
        if(procesoaleatorio > (i-4) && procesoaleatorio < (i-2)){
            n= 2;
        }else if(procesoaleatorio>(i-3) && procesoaleatorio<i){
            n=1;
        }else if(procesoaleatorio == i){
            n = 0;
        }else{
            n = (int) Math.round(Math.random() * 3)+1;
        }

        for(int j = 0;j <n;j++){                    
            procesoTerminado = (String)procesos.getValueAt(auxi++, 2);
            System.out.println("El proceso en espera "+auxi + " y su estado es "+procesoTerminado);
            if(procesoTerminado.equals("Listo")){
                System.out.println("Estamos aqui");
                procesos.setValueAt("Espera", (auxi-1), 2);    
            }  

        }   
        try {
            Thread.sleep(quantum*1000);
        } catch (InterruptedException ex) {
            System.out.println("Error "+ex);
        }
    }
    
    public class progreso implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent etv){
            ram = ram +(int)procesos.getValueAt(i, 3);
            JPRam.setValue(ram);
            
        }
    }
    
}
