/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.swing.JOptionPane;
import static interfaz.Principal.procesos;
import Logica.AgregarProcesos;
import static interfaz.Principal.procesos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import Logica.Ejecutando;
import Logica.Memoria;
import static interfaz.Principal.JPRam;



/**
 *
 * @author LSMORALES
 */
public class Hilo implements Runnable{
    public static int Contador;
    public static int procesoaleatorio, contador_video = 0,contador_impresora = 0, i, auxi, quantum, ram=0;
    public void run(){
        int randomterminado, recvideo = 0, recimpresora = 0, finalizados = 0;
        String procesoTerminado = "";
        DefaultTableModel modelo = (DefaultTableModel) procesos.getModel();;
        i = modelo.getRowCount();
        while(i >= 0){
            int contador_terminados = 0;
            i = modelo.getRowCount();
            i--;
            
            procesoaleatorio = (int) Math.round(Math.random() * i)+0;
            auxi = procesoaleatorio;
            
            String proc = (String)procesos.getValueAt(procesoaleatorio, 2);
            
            quantum = (int)(procesos.getValueAt(procesoaleatorio, 1));
            if((proc.equals("Listo")) || (proc.equals("Espera")) || (proc.equals("Ejecutando"))){
                if(proc.equals("Espera") && ((procesos.getValueAt(procesoaleatorio, 4).equals("Video")) 
                        || (procesos.getValueAt(procesoaleatorio, 4).equals("Impresora")))){
                 
                    if((contador_video > 0) && (procesos.getValueAt(procesoaleatorio, 4).equals("Video"))){
                        recvideo++;
                        if(recvideo > 0){
                            
                            procesos.setValueAt("Interbloqueo", procesoaleatorio, 2);
                            JOptionPane.showMessageDialog(null, "El recurso "+(procesoaleatorio+1)+" entro en interbloqueo");
                        }
                    }else if((contador_impresora>0) && (procesos.getValueAt(procesoaleatorio, 4).equals("Impresora"))){
                        recimpresora++;
                        if(recimpresora > 0){
                            
                            procesos.setValueAt("Interbloqueo", procesoaleatorio, 2);
                            JOptionPane.showMessageDialog(null, "El recurso "+(procesoaleatorio+1)+" entro en interbloqueo");
                        }
                    }else{
                        Ejecutando.ejecutando();
                    }
                    
                }else if(contador_video < 1 || contador_impresora <1){
                    if(proc.equals("Ejecutando")){
                        procesos.setValueAt("Ejecutando", procesoaleatorio, 2);
                    }else{
                        Ejecutando.ejecutando();
                    }
                }
                
            }
            if(procesos.getValueAt(procesoaleatorio, 2).equals("Ejecutando")){
                randomterminado = (int) Math.round(Math.random() * 4)+1;
                if(randomterminado <= 2){
                    procesos.setValueAt("Terminado", procesoaleatorio, 2);
                    ram = ram -(int)procesos.getValueAt(i, 3);
                    JPRam.setValue(ram);
                    if(procesos.getValueAt(procesoaleatorio, 2).equals("Terminado")){
                        if(procesos.getValueAt(procesoaleatorio, 4).equals("Video")){
                            contador_video = 0;
                            recvideo = 0;
                        }else if (procesos.getValueAt(procesoaleatorio, 4).equals("Impresora")){
                            contador_impresora = 0;
                            recimpresora = 0;
                        }
                        for(int j=0;j<i;j++){
                            if((procesos.getValueAt(j, 2).equals("Interbloqueo")) || (procesos.getValueAt(j, 2).equals("Terminado"))){
                                finalizados++;
                                if(finalizados > 13){
                                    JOptionPane.showMessageDialog(null, "FIN DEL SIMULADOR");
                                    try{
                                        Thread.sleep(100000);
                                    }catch(Exception e){
                                        System.out.println("OCURRIO UN ERROR "+e);
                                    }
                                }
                            }else{
                                finalizados = 0;
                            }
                        }
                        
                        
                    }
                }
            }
        }
            //i--;
    }
}

