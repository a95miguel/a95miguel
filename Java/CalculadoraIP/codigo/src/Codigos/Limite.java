/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Miguel Medel Lozada
 */
public class Limite extends PlainDocument{
     private final int limite;
    public Limite(int i) {
        super();
        this.limite=i;
    }
    
     @Override
    public void insertString(int compensacion,String str, AttributeSet attr) throws BadLocationException{
        if (str== null)
            return;
        if ((getLength()+str.length())<=limite)
            super.insertString(compensacion, str, attr);
    }
    
}
