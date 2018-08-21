/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import javax.swing.JOptionPane;
import static interfaz.Principal.vector;
import static interfaz.Principal.cantmemoria;
import static interfaz.Principal.marcaM;
import static interfaz.Principal.memoria;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author LSMORALES
 */
public class Memoria {
	
	private int numRandom;
    private int band;
    private String validar;
    
    
    public Memoria(int numRandom, int band, String validar){
    	this.numRandom = numRandom;
    	this.band = band;
    	this.validar = validar;
    }
    
    public static int getNumRandom() {
		return (int) Math.round(Math.random() * 19 );
	}
	public void setNumRandom(int numRandom) {
		this.numRandom = numRandom;
	}
	public int getBand() {
		return band;
	}
	public void setBand(int band) {
		this.band = 0;
	}
	public String getValidar() {
		return validar;
	}
	public void setValidar(String validar) {
		this.validar = validar;
	}
	
	public void PintarMemoria(){

        

        while(getBand()==0){

            
            setValidar(vector[getNumRandom()].getText());

            if(cantmemoria==20){
                //desfragmentar();
                JOptionPane.showMessageDialog(null, "Se ha desfragmentado");
            }

            marcaM=getNumRandom();

            if(getNumRandom()>1 && getNumRandom()<20 && getValidar().equals("                     0 KB")){
                cantmemoria=cantmemoria+1;
                vector[getNumRandom()].setBackground(Color.CYAN);
                vector[getNumRandom()].setText("                     " + memoria + " KB");
                setBand(1);
            }
        }    
    }//FIn del PintarMemoria
}
    
    
