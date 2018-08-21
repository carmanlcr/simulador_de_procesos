/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;
 

import static interfaz.Principal.procesos;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LSMORALES
 */
public class AgregarProcesos {
    
    public static int contadorp = 0;
    
    public static void agregarProceso(){

        
        int quantum,n,rec;
       
        String recurso = "";
        
        n=(int) Math.round(Math.random() * 60) + 30;
             
        
        quantum=(int) Math.round(Math.random() * 3) + 1;
        
        
        DefaultTableModel modelo=(DefaultTableModel) procesos.getModel();
        if(contadorp > 14){
            contadorp = 1;
        }else{
            contadorp++;
        }
        
        Object[] procesos = new Object[8];
        procesos[0]= contadorp;
        procesos[1]= quantum;
        procesos[2]= "Listo";
        procesos[3]= n;
        n=(int) Math.round(Math.random() * 4) + 1;
        if(n <= 2){
            rec=(int) Math.round(Math.random() *2)+1;
            if(rec == 2){
                recurso = "Video";
            }else{
                recurso = "Impresora";
            } 
        }else{
            recurso = "Otro";
        }
        procesos[4] = recurso;
        modelo.addRow(procesos);
    
    

}
    
}
