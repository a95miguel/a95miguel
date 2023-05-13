/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Codigos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Medel Lozada
 */
public class Calculadora_ip extends javax.swing.JFrame {
    
    
    /**
     * Creates new form Calculadora_ip
     */
    public Calculadora_ip() {
        initComponents();
               
        //PARA PONER UNA IMAGEN DE FONDO ALA VENTANA
        ((JPanel) getContentPane()).setOpaque(fal­se);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("/Codigos/Fondo.jpg"));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, uno.getIconWidth(), u­no.getIconHeight());
       //para poner al centro la ventana
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("----------Calculadora IP---------");
        
        txt_casilla1.setDocument(new Limite(3));
        txt_casilla2.setDocument(new Limite(3));
        txt_casilla3.setDocument(new Limite(3));
        txt_casilla4.setDocument(new Limite(3));
        txt_cidr.setDocument(new Limite(3));
        
        }
    
void MostrarDatos(){
    int num=Integer.parseInt(txt_casilla1.getText());
    if(num >=1 && num <=127){  
        this.txt_clase.setText("A");
     //bits robados clase A
        int cidrA;
        int brA = 0;
        cidrA = Integer.parseInt(txt_cidr.getText());
        if (cidrA >= 10 && cidrA <= 30) {
            brA = cidrA - 8;
          String mostrarA;
            mostrarA = Integer.toString(brA);
            txt_bits_robados.setText(mostrarA);
            
            //calculo de mascara de subred
        int xA;
        int yA;
        int zA;
        xA = 8 - brA;
        yA = (int) Math.pow(2, xA);
        zA = 256 - yA;
        String resulA;
        resulA = Integer.toString(zA);
        txt_mascara4.setText(resulA);
        
        this.txt_mascara2.setText(txt_mascara4.getText());
        this.txt_mascara3.setText("0");
        this.txt_mascara4.setText("0");
       
        //Saltos Clase A
        int sA;
        sA = 256 - zA;
        String resultA;
        resultA = Integer.toString(sA);
        txt_saltos.setText(resultA);
        //los bits de los host
        int bhA;
        bhA=8-brA;
        String resultadoA;
        resultadoA = Integer.toString(bhA);
        txt_bits_host.setText(resultadoA);
       
        // subredes
        int srA;
        srA =  (int) Math.pow(2, brA);
        String resulsrA;
        resulsrA = Integer.toString(srA);
        txt_subredes.setText(resulsrA);
        
        //subredes validos
        int srvA;
        srvA = srA-2;
        String ressubvalidA;
        ressubvalidA = Integer.toString(srvA);
        txt_subredes_validos.setText(ressubvalidA);
        
         //host validos
        int hvA;
        hvA = (int) Math.pow(2, bhA+16);
        String redhostvalidA;
        redhostvalidA = Integer.toString(hvA);
        txt_host.setText(redhostvalidA);
         
          //subredes utiles
        int huA;
        huA = hvA-2;
        String redhostutilesA;
        redhostutilesA = Integer.toString(huA);
        txt_host_validos.setText(redhostutilesA);    
           
        
             
        
        //////////////////////////////////////////////////////////////////////////
        int valor1=Integer.parseInt(txt_cidr.getText());
        if(valor1>=16){
            
        int xAV;
        int yAV;
        int zAV;
        xAV = 16- brA;
        yAV = (int) Math.pow(2, xAV);
        zAV = 256 - yAV;
        String resulAV;
        resulAV = Integer.toString(zAV);
        txt_mascara3.setText(resulAV);
         
        this.txt_mascara1.setText("255");
        this.txt_mascara2.setText("255");
        this.txt_mascara3.setText(txt_mascara3.getText());
        this.txt_mascara4.setText("0");
        //Saltos Clase A
        int sAV;
        sAV = 256 - zAV;
        String resultAV;
        resultAV = Integer.toString(sAV);
        txt_saltos.setText(resultAV);
        
        //bits robados clase A
        int cidrAV;
        int brAV = 0;
        cidrAV = Integer.parseInt(txt_cidr.getText());
        brAV = cidrAV - 8;
        String mostrarAV;
        mostrarAV = Integer.toString(brAV);
        txt_bits_robados.setText(mostrarAV);
            
        // subredes A
        int srAV;
        srAV =  (int) Math.pow(2, brAV);
        String resulsrAV;
        resulsrAV = Integer.toString(srAV);
        txt_subredes.setText(resulsrAV);
        
        //subredes validos A
        int srvAV;
        srvAV = srAV-2;
        String ressubvalidAV;
        ressubvalidAV = Integer.toString(srvAV);
        txt_subredes_validos.setText(ressubvalidAV);
        
         //host validos A
        int hvAV;
        hvAV = (int) Math.pow(2, bhA+16);
        String redhostvalidAV;
        redhostvalidAV = Integer.toString(hvAV);
        txt_host.setText(redhostvalidAV);
         
          //HOST utiles A
        int huAV;
        huAV = hvAV-2;
        String redhostutilesAV;
        redhostutilesAV = Integer.toString(huAV);
        txt_host_validos.setText(redhostutilesAV);    
        
        
        }
        
        
        //////////////////////////////////////////////////////////////////////////
        int valor2=Integer.parseInt(txt_cidr.getText());
        if(valor2>=25){
            
        int xAQ;
        int yAQ;
        int zAQ;
        xAQ = 24- brA;
        yAQ = (int) Math.pow(2, xAQ);
        zAQ = 256 - yAQ;
        String resulAQ;
        resulAQ = Integer.toString(zAQ);
        txt_mascara4.setText(resulAQ);
         
        this.txt_mascara1.setText("255");
        this.txt_mascara2.setText("255");
        this.txt_mascara3.setText("255");
        this.txt_mascara4.setText(txt_mascara4.getText());
        //Saltos Clase A
        int sAQ;
        sAQ = 256 - zAQ;
        String resultAQ;
        resultAQ = Integer.toString(sAQ);
        txt_saltos.setText(resultAQ);
        
        //bits robados clase A
        int cidrAQ;
        int brAQ = 0;
        cidrAQ = Integer.parseInt(txt_cidr.getText());
        brAQ = cidrAQ - 8;
        String mostrarAQ;
        mostrarAQ = Integer.toString(brAQ);
        txt_bits_robados.setText(mostrarAQ);
            
        // subredes A
        int srAQ;
        srAQ =  (int) Math.pow(2, brAQ);
        String resulsrAQ;
        resulsrAQ = Integer.toString(srAQ);
        txt_subredes.setText(resulsrAQ);
        
        //subredes validos A
        int srvAQ;
        srvAQ = srAQ-2;
        String ressubvalidAQ;
        ressubvalidAQ = Integer.toString(srvAQ);
        txt_subredes_validos.setText(ressubvalidAQ);
        
         //host validos A
        int hvAQ;
        hvAQ = (int) Math.pow(2, bhA+16);
        String redhostvalidAQ;
        redhostvalidAQ = Integer.toString(hvAQ);
        txt_host.setText(redhostvalidAQ);
         
          //HOST utiles A
        int huAQ;
        huAQ = hvAQ-2;
        String redhostutilesAQ;
        redhostutilesAQ = Integer.toString(huAQ);
        txt_host_validos.setText(redhostutilesAQ);    
        
        
        }
        
        
        } else {
            JOptionPane.showMessageDialog(null, "Esta CDIR no pertenece  a esta clase");
            
        }
        
  }
    if(num >=128 && num <=191 ){
        this.txt_clase.setText("B");
        //bits robados clase b
        int cidrB;
        int brB = 0;
        cidrB = Integer.parseInt(txt_cidr.getText());
        if (cidrB >= 8 && cidrB <= 30) {
            brB = cidrB - 16;
          String mostrar;
            mostrar = Integer.toString(brB);
            txt_bits_robados.setText(mostrar);
            //calculo de mascara de subred B
        int xB;
        int yB;
        int zB;
        xB = 8 - brB;
        yB = (int) Math.pow(2, xB);
        zB = 256 - yB;
        String resulB;
        resulB = Integer.toString(zB);
        txt_mascara4.setText(resulB);
        this.txt_mascara1.setText("255");
        this.txt_mascara2.setText("255");
        this.txt_mascara3.setText(txt_mascara4.getText());
        this.txt_mascara4.setText("0");
       
        //Saltos Clase B
        int sB;
        sB = 256 - zB;
        String result;
        result = Integer.toString(sB);
        txt_saltos.setText(result);
        //los bits de los host B
        int bhB;
        bhB=8-brB;
        String resultadoB;
        resultadoB = Integer.toString(bhB);
        txt_bits_host.setText(resultadoB);
       
        // subredes B
        int srB;
        srB =  (int) Math.pow(2, brB);
        String resulsrB;
        resulsrB = Integer.toString(srB);
        txt_subredes.setText(resulsrB);
        
        //subredes validos B
        int srvB;
        srvB = srB-2;
        String ressubvalidB;
        ressubvalidB = Integer.toString(srvB);
        txt_subredes_validos.setText(ressubvalidB);
        
         //host validos B
        int hvB;
        hvB = (int) Math.pow(2, bhB+8);
        String redhostvalidB;
        redhostvalidB = Integer.toString(hvB);
        txt_host.setText(redhostvalidB);
         
          //host utiles B
        int huB;
        huB = hvB-2;
        String redhostutilesB;
        redhostutilesB = Integer.toString(huB);
        txt_host_validos.setText(redhostutilesB);    
        
        
        //////////////////////////////////////////////////////////////
        int valor=Integer.parseInt(txt_cidr.getText());
        if(valor>=25){
            
        int xBV;
        int yBV;
        int zBV;
        xBV = 16- brB;
        yBV = (int) Math.pow(2, xBV);
        zBV = 256 - yBV;
        String resulBV;
        resulBV = Integer.toString(zBV);
        txt_mascara4.setText(resulBV);
         
        this.txt_mascara1.setText("255");
        this.txt_mascara2.setText("255");
        this.txt_mascara3.setText("255");
        this.txt_mascara4.setText(txt_mascara4.getText());
        
        //Saltos Clase B
        int sBV;
        sBV = 256 - zBV;
        String resultV;
        resultV = Integer.toString(sBV);
        txt_saltos.setText(resultV);
        
        //bits robados clase b
        int cidrBV;
        int brBV = 0;
        cidrBV = Integer.parseInt(txt_cidr.getText());
        brBV = cidrBV - 24;
        String mostrarV;
        mostrarV = Integer.toString(brBV);
        txt_bits_robados.setText(mostrarV);
            
        // subredes B
        int srBV;
        srBV =  (int) Math.pow(2, brBV);
        String resulsrBV;
        resulsrBV = Integer.toString(srBV);
        txt_subredes.setText(resulsrBV);
        
        //subredes validos B
        int srvBV;
        srvBV = srBV-2;
        String ressubvalidBV;
        ressubvalidBV = Integer.toString(srvBV);
        txt_subredes_validos.setText(ressubvalidBV);
        
         //host validos B
        int hvBV;
        hvBV = (int) Math.pow(2, bhB+8);
        String redhostvalidBV;
        redhostvalidBV = Integer.toString(hvBV);
        txt_host.setText(redhostvalidBV);
         
          //HOST utiles B
        int huBV;
        huBV = hvBV-2;
        String redhostutilesBV;
        redhostutilesBV = Integer.toString(huBV);
        txt_host_validos.setText(redhostutilesBV);    
        
        
        }
        
        
        } else {
            JOptionPane.showMessageDialog(null, "Esta CDIR no pertenece  a esta clase");
        }
    }
    
    
    if(num >=192 && num <=223 ){
         this.txt_clase.setText("C");
        //bits robados Clase "C"
        int cidr;
        int br = 0;
        cidr = Integer.parseInt(txt_cidr.getText());
        if (cidr >= 10 && cidr <= 30) {
            br = cidr - 24;
          String mostrar;
            mostrar = Integer.toString(br);
            txt_bits_robados.setText(mostrar);
            //calculo de mascara de subred
        int x;
        int y;
        int z;
        x = 8 - br;
        y = (int) Math.pow(2, x);
        z = 256 - y;
        String resul;
        resul = Integer.toString(z);
        txt_mascara4.setText(resul);
        this.txt_mascara1.setText("255");
        this.txt_mascara2.setText("255");
        this.txt_mascara3.setText("255");
        this.txt_mascara4.setText(txt_mascara4.getText());
       
        //Saltos 
        int s;
        s = 256 - z;
        String result;
        result = Integer.toString(s);
        txt_saltos.setText(result);
 
        //los bits de los host
        int bh;
        bh=8-br;
        String resultado;
        resultado = Integer.toString(bh);
        txt_bits_host.setText(resultado);
       
        // subredes
        int sr;
        sr =  (int) Math.pow(2, br);
        String resulsr;
        resulsr = Integer.toString(sr);
        txt_subredes.setText(resulsr);
        
        //subredes validos
        int srv;
        srv = sr-2;
        String ressubvalid;
        ressubvalid = Integer.toString(srv);
        txt_subredes_validos.setText(ressubvalid);
        
         //host validos
        int hv;
        hv = (int) Math.pow(2, bh);
        String redhostvalid;
        redhostvalid = Integer.toString(hv);
        txt_host.setText(redhostvalid);
         
          //subredes utiles
        int hu;
        hu = hv-2;
        String redhostutiles;
        redhostutiles = Integer.toString(hu);
        txt_host_validos.setText(redhostutiles);    
              
        
        } else {
            JOptionPane.showMessageDialog(null, "Esta CDIR no pertenece  a esta clase");
        }
        
    }          
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_casilla1 = new javax.swing.JTextField();
        txt_clase = new javax.swing.JTextField();
        bn_aceptar = new javax.swing.JButton();
        txt_casilla2 = new javax.swing.JTextField();
        txt_casilla3 = new javax.swing.JTextField();
        txt_casilla4 = new javax.swing.JTextField();
        txt_cidr = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_mascara1 = new javax.swing.JTextField();
        txt_mascara2 = new javax.swing.JTextField();
        txt_mascara4 = new javax.swing.JTextField();
        txt_mascara3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_bits_robados = new javax.swing.JTextField();
        txt_saltos = new javax.swing.JTextField();
        txt_subredes = new javax.swing.JTextField();
        txt_subredes_validos = new javax.swing.JTextField();
        txt_host = new javax.swing.JTextField();
        txt_host_validos = new javax.swing.JTextField();
        txt_bits_host = new javax.swing.JTextField();
        btn_limpiar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_casilla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_casilla1ActionPerformed(evt);
            }
        });
        txt_casilla1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_casilla1KeyTyped(evt);
            }
        });

        txt_clase.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_clase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_claseActionPerformed(evt);
            }
        });

        bn_aceptar.setBackground(new java.awt.Color(51, 255, 0));
        bn_aceptar.setText("Calcular");
        bn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bn_aceptarActionPerformed(evt);
            }
        });

        txt_casilla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_casilla2KeyTyped(evt);
            }
        });

        txt_casilla3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_casilla3KeyTyped(evt);
            }
        });

        txt_casilla4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_casilla4KeyTyped(evt);
            }
        });

        txt_cidr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cidrKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 18)); // NOI18N
        jLabel1.setText("Direccion IP");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Clase");

        txt_mascara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mascara1ActionPerformed(evt);
            }
        });

        jLabel5.setText("/");

        btn_limpiar.setBackground(new java.awt.Color(255, 153, 51));
        btn_limpiar.setText("Limpar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 18)); // NOI18N
        jLabel13.setText("CIDR");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Mascara de subred");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(java.awt.Color.blue);
        jLabel14.setText("Bits Robados");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(java.awt.Color.blue);
        jLabel15.setText("Bits Host");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(java.awt.Color.blue);
        jLabel16.setText("Saltos");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(java.awt.Color.blue);
        jLabel17.setText("Subredes");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(java.awt.Color.blue);
        jLabel18.setText("Subredes Validos");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(java.awt.Color.blue);
        jLabel19.setText("Host Totales");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(java.awt.Color.blue);
        jLabel20.setText("Host validos");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 36)); // NOI18N
        jLabel6.setText(".");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 36)); // NOI18N
        jLabel7.setText(".");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 2, 36)); // NOI18N
        jLabel11.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_casilla2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_casilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_casilla4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_cidr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bn_aceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_limpiar))
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(txt_clase, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_saltos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_subredes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(8, 8, 8)
                        .addComponent(txt_bits_robados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bits_host, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_mascara1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_mascara2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_mascara3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txt_mascara4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_subredes_validos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_host_validos))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_host, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_casilla2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_casilla3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_casilla4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cidr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(bn_aceptar)
                    .addComponent(btn_limpiar)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_clase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_mascara1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_mascara2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_mascara3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_mascara4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bits_robados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bits_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_saltos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_subredes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_subredes_validos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_host_validos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_claseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_claseActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_claseActionPerformed

    private void bn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bn_aceptarActionPerformed
        // TODO add your handling code here:

        MostrarDatos();
       
    }//GEN-LAST:event_bn_aceptarActionPerformed

    private void txt_mascara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mascara1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mascara1ActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        // TODO add your handling code here:
        txt_casilla1.setText("");
        txt_casilla2.setText("");
        txt_casilla3.setText("");
        txt_casilla4.setText("");
        txt_cidr.setText("");       
        txt_clase.setText("");
        txt_mascara1.setText("");
        txt_mascara2.setText("");
        txt_mascara3.setText("");
        txt_mascara4.setText("");
        txt_bits_host.setText("");       
        txt_bits_robados.setText("");    
        txt_saltos.setText("");       
        txt_subredes.setText("");       
        txt_subredes_validos.setText("");       
        txt_host.setText("");       
        txt_host_validos.setText("");       
               
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_casilla1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_casilla1KeyTyped
        // TODO add your handling code here:
         char d= evt.getKeyChar();
        if(d < '0' || d > '9'){
            evt.consume();
        }
        
    }//GEN-LAST:event_txt_casilla1KeyTyped

    private void txt_casilla2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_casilla2KeyTyped
        // TODO add your handling code here:
         char d= evt.getKeyChar();
        if(d < '0' || d > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_txt_casilla2KeyTyped

    private void txt_casilla3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_casilla3KeyTyped
        // TODO add your handling code here:
         char d= evt.getKeyChar();
        if(d < '0' || d > '9'){
            evt.consume();
        }

    }//GEN-LAST:event_txt_casilla3KeyTyped

    private void txt_casilla4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_casilla4KeyTyped
        // TODO add your handling code here:
         char d= evt.getKeyChar();
        if(d < '0' || d > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_txt_casilla4KeyTyped

    private void txt_cidrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cidrKeyTyped
        // TODO add your handling code here:
         char d= evt.getKeyChar();
        if(d < '0' || d > '9'){
            evt.consume();
        }
    }//GEN-LAST:event_txt_cidrKeyTyped

    private void txt_casilla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_casilla1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_casilla1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calculadora_ip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculadora_ip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculadora_ip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculadora_ip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculadora_ip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bn_aceptar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txt_bits_host;
    private javax.swing.JTextField txt_bits_robados;
    private javax.swing.JTextField txt_casilla1;
    private javax.swing.JTextField txt_casilla2;
    private javax.swing.JTextField txt_casilla3;
    private javax.swing.JTextField txt_casilla4;
    private javax.swing.JTextField txt_cidr;
    private javax.swing.JTextField txt_clase;
    private javax.swing.JTextField txt_host;
    private javax.swing.JTextField txt_host_validos;
    private javax.swing.JTextField txt_mascara1;
    private javax.swing.JTextField txt_mascara2;
    private javax.swing.JTextField txt_mascara3;
    private javax.swing.JTextField txt_mascara4;
    private javax.swing.JTextField txt_saltos;
    private javax.swing.JTextField txt_subredes;
    private javax.swing.JTextField txt_subredes_validos;
    // End of variables declaration//GEN-END:variables
}
