/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.swing.JOptionPane;

import java.util.Random;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import interfaz.Principal;
import static interfaz.Principal.JPRam;



/**
 *
 * @author LSMORALES
 */
public class Hilo implements Runnable{
    private int contador;
    private int procesoAleatorio;
    private int contadorVideo;
    private int contadorImpresora;
    private int i;
    private int auxi;
    private int quantum;
    private int ram;
    private int recvideo = 0;
    private int recimpresora = 0;
    
    
	public final  String LISTO = "Listo";
    private static final  String ESPERA = "Espera";
    public final  String EJECUTANDO = "Ejecutando";
    public final  String VIDEO = "Video";
    public final  String IMPRESORA ="Impresora";
    private static final  String INTERBLOQUEO = "Interbloqueo";
    private static final  String TERMINADO = "Terminado";
    
    /************** IMPORTADOR DE CLASES ******************/
    Principal prin = new Principal();
    Ejecutando ejecutando = new Ejecutando();
    DefaultTableModel modelo = (DefaultTableModel) prin.procesos.getModel();
    Random matematico = new Random();
    /*******************************************************/
    
    public void run(){
    	/************** DECLARACIÓN DE VARIABLES ******************/
          
        
        int randomAProcesoAleatorio;
        int auxI;
        
        /*******************************************************/
        
        setI(modelo.getRowCount());
        setContadorVideo(0);
        setContadorImpresora(0);
        setRam(0);
        while(getI() >= 0){
            auxI = getI();
            auxI--;
            setI(auxI);
            
            randomAProcesoAleatorio = matematico.nextInt(getI());
            setProcesoAleatorio(randomAProcesoAleatorio);
            setAuxi(getProcesoAleatorio());
            
            primerPaso();
            
            segundoPaso();
        }
    }
    
    

	private void primerPaso() {
    	 
    	 String proc = (String)prin.procesos.getValueAt(getProcesoAleatorio(), 2);
         
         setQuantum((int)(prin.procesos.getValueAt(getProcesoAleatorio(), 1)));
         if((proc.equals(LISTO)) || (proc.equals(ESPERA)) || (proc.equals(EJECUTANDO))){
             if(proc.equals(ESPERA) && ((prin.procesos.getValueAt(getProcesoAleatorio(), 4).equals(VIDEO)) 
                     || (prin.procesos.getValueAt(getProcesoAleatorio(), 4).equals(IMPRESORA)))){
              
                 if((contadorVideo > 0) && (prin.procesos.getValueAt(getProcesoAleatorio(), 4).equals(VIDEO))){
                     recvideo++;
                     if(recvideo > 0){
                         
                     	prin.procesos.setValueAt(INTERBLOQUEO, getProcesoAleatorio(), 2);
                         JOptionPane.showMessageDialog(null, "El recurso "+(getProcesoAleatorio()+1)+" entro en "+INTERBLOQUEO);
                     }
                 }else if((contadorImpresora>0) && (prin.procesos.getValueAt(getProcesoAleatorio(), 4).equals(IMPRESORA))){
                     recimpresora++;
                     if(recimpresora > 0){
                         
                     	prin.procesos.setValueAt(INTERBLOQUEO, getProcesoAleatorio(), 2);
                         JOptionPane.showMessageDialog(null, "El recurso "+(getProcesoAleatorio()+1)+" entro en "+INTERBLOQUEO);
                     }
                 }else{
                	 ejecutando.ejecutando();
                 }
                 
             }else if(getContadorVideo() < 1 || getContadorImpresora() <1){
                 if(proc.equals(EJECUTANDO)){
                 	prin.procesos.setValueAt(EJECUTANDO, getProcesoAleatorio(), 2);
                 }else{
                	 ejecutando.ejecutando();
                 }
             }
             
         }
    }
	
	private void segundoPaso() {
		int randomterminado;  
		int finalizados = 0;
    	if(prin.procesos.getValueAt(getProcesoAleatorio(), 2).equals(EJECUTANDO)){
            randomterminado = matematico.nextInt(4);
            if(randomterminado <= 2){
            	prin.procesos.setValueAt(TERMINADO, getProcesoAleatorio(), 2);
                setRam(getRam() -(int)prin.procesos.getValueAt(getI(), 3));
                JPRam.setValue(getRam());
                if(prin.procesos.getValueAt(getProcesoAleatorio(), 2).equals(TERMINADO)){
                    if(prin.procesos.getValueAt(getProcesoAleatorio(), 4).equals(VIDEO)){
                        setContadorVideo(0);
                        recvideo = 0;
                    }else if (prin.procesos.getValueAt(getProcesoAleatorio(), 4).equals(IMPRESORA)){
                        setContadorImpresora(0);
                        recimpresora = 0;
                    }
                    for(int j=0;j<i;j++){
                        if((prin.procesos.getValueAt(j, 2).equals(INTERBLOQUEO)) || (prin.procesos.getValueAt(j, 2).equals(TERMINADO))){
                            finalizados++;
                            if(finalizados > 13){
                                JOptionPane.showMessageDialog(null, "FIN DEL SIMULADOR");
                                try{
                                    Thread.sleep(100000);
                                }catch(Exception e){
                                    Logger.getLogger("Log"+e);
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
    
    public int getProcesoAleatorio() {
		return procesoAleatorio;
	}
	public void setProcesoAleatorio(int procesoAleatorio) {
		this.procesoAleatorio = procesoAleatorio;
	}
	public int getContadorVideo() {
		return contadorVideo;
	}
	public void setContadorVideo(int contadorVideo) {
		this.contadorVideo = contadorVideo;
	}
	public int getContadorImpresora() {
		return contadorImpresora;
	}
	public void setContadorImpresora(int contadorImpresora) {
		this.contadorImpresora = contadorImpresora;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getAuxi() {
		return auxi;
	}
	public void setAuxi(int auxi) {
		this.auxi = auxi;
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
}

