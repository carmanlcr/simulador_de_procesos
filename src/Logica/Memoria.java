/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

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
    
    public static void PintarMemoria(){

        int numRandon,band=0;
        String validar;

        while(band==0){

            numRandon = (int) Math.round(Math.random() * 19 );
            validar=vector[numRandon].getText();

            if(cantmemoria==20){
                //desfragmentar();
                JOptionPane.showMessageDialog(null, "Se ha desfragmentado");
            }

            marcaM=numRandon;

            if(numRandon>1 && numRandon<20 && validar.equals("                     0 KB")){
                cantmemoria=cantmemoria+1;
                vector[numRandon].setBackground(Color.CYAN);
                vector[numRandon].setText("                     " + memoria + " KB");
                band=1;
            }
        }    
    }//FIn del PintarMemoria
}
    
    
