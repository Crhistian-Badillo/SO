package Bradley;



import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class Diagrama extends javax.swing.JFrame implements Runnable {
    
    public static DefaultTableModel miModelo = new DefaultTableModel();
    public static DefaultTableModel miMod = new DefaultTableModel();
    
    public static String[] procesosTabla=new String[6];
    Proceso[] procesos=new Proceso[200];
    
    static Proceso[] listos = new Proceso[200];
    static Proceso[] bloqueado = new Proceso[200];
    static Proceso[] suspListo = new Proceso[200];
    static Proceso[] suspBlo = new Proceso[200];
    Thread hilo,hiloB,hiloSL,hiloSB;
    Random r = new Random();
    String algo;
    
    static int quantum;
    int index=0;
    static int contador=0;
    
    public static int tiempoTot;
    public static int aux = -1;
    
    public static int tiempTot[] = new int [200];
    public static String list [] = new String[2];
    
    public static int ProcesosRestantes = 0;
    
    Timer timerPrincipal = new Timer();
    public TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                aux ++;
                System.out.println("segundo: "+aux);
                lblCrono.setText("Segundo: "+Integer.toString(aux));

            }
        };

       
    
    public Diagrama() {
        super("Interfaz");
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        btnMatar.setEnabled(false);
        btnDetener.setEnabled(false);
        btnIniciar.setEnabled(false);
        verTabla();
        //ProcesosDefault();
        areaProcess.setText("ID         Nombre");
        
        btnCap.setVisible(false);
        btnGen.setVisible(false);
        lblIm.setIcon(setIcono("proceso3.png",lblIm));
        miMod.addColumn("Nombre");
        miMod.addColumn("Tiempo");
        areaFinalizados.setModel(miMod);
    }
    
    public void verTabla(){
        //Titulo de columnas
        miModelo.addColumn("ID");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Memoria");
        miModelo.addColumn("Estado");
        miModelo.addColumn("Prioridad");
        miModelo.addColumn("Tiempo requerido");
        
        
        miTabla.setModel(miModelo);
    }
    
    public void ProcesosDefault(){
        procesosTabla[0]="1";
        procesosTabla[1]="Proceso 1";
        procesosTabla[2]="RAM";
        procesosTabla[3]="LISTO";
        procesosTabla[4]="1";
        procesosTabla[5]="3";
        miModelo.addRow(procesosTabla);
        
        procesosTabla[0]="2";
        procesosTabla[1]="Proceso 2";
        procesosTabla[2]="RAM";
        procesosTabla[3]="LISTO";
        procesosTabla[4]="5";
        procesosTabla[5]="6";
        miModelo.addRow(procesosTabla);
        
        procesosTabla[0]="3";
        procesosTabla[1]="Proceso 3";
        procesosTabla[2]="RAM";
        procesosTabla[3]="LISTO";
        procesosTabla[4]="5";
        procesosTabla[5]="1";
        miModelo.addRow(procesosTabla);
        
        procesosTabla[0]="4";
        procesosTabla[1]="Proceso 4";
        procesosTabla[2]="Disco Duro";
        procesosTabla[3]="LISTO";
        procesosTabla[4]="10";
        procesosTabla[5]="5";
        miModelo.addRow(procesosTabla);
        
        procesosTabla[0]="5";
        procesosTabla[1]="Proceso 5";
        procesosTabla[2]="RAM";
        procesosTabla[3]="LISTO";
        procesosTabla[4]="1";
        procesosTabla[5]="4";
        miModelo.addRow(procesosTabla);
    }
    
    public static void guardar(String p[]){
        procesosTabla=p;
        miModelo.addRow(procesosTabla);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        miPanel = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnMatar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        btnDetener = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaProcess = new javax.swing.JTextArea();
        lbProceso = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        miTabla = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaBloqueado = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaListos = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaSuspLis = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaSusBlo = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCap = new javax.swing.JButton();
        btnGen = new javax.swing.JButton();
        lblIm = new javax.swing.JLabel();
        lblCrono = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        areaFinalizados = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuProcesos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuSimulador = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        miPanel.setBackground(new java.awt.Color(255, 255, 255));
        miPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        miPanel.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        btnMatar.setText("Detener");
        btnMatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatarActionPerformed(evt);
            }
        });
        miPanel.add(btnMatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        miPanel.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 21, -1, -1));

        btnDetener.setText("Pausar");
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });
        miPanel.add(btnDetener, new org.netbeans.lib.awtextra.AbsoluteConstraints(681, 21, -1, -1));

        areaProcess.setColumns(20);
        areaProcess.setRows(5);
        jScrollPane1.setViewportView(areaProcess);
        areaProcess.setLineWrap(true);

        miPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 105, -1, 240));

        lbProceso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbProceso.setText("Proceso:");
        miPanel.add(lbProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, -1, -1));

        jLabel2.setText("Tabla de Procesos");
        miPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 83, -1, -1));

        miTabla = new JTable() {
            public boolean isCellEditable(int row,int columnn){
                for(int i=0;i<miTabla.getRowCount();i++){
                    if(row == i){
                        return false;
                    }
                }
                return true;
            }
        };
        miTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        miTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(miTabla);

        miPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 672, 227));

        areaBloqueado.setColumns(20);
        areaBloqueado.setRows(5);
        jScrollPane3.setViewportView(areaBloqueado);
        areaBloqueado.setLineWrap(true);
        areaBloqueado.setEditable(false);

        miPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 110, 120));

        areaListos.setColumns(20);
        areaListos.setRows(5);
        jScrollPane4.setViewportView(areaListos);
        areaListos.setLineWrap(true);
        areaListos.setEditable(false);

        miPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 110, 120));

        areaSuspLis.setColumns(20);
        areaSuspLis.setRows(5);
        jScrollPane5.setViewportView(areaSuspLis);
        areaSuspLis.setLineWrap(true);
        areaSuspLis.setEditable(false);

        miPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, 100, 120));

        areaSusBlo.setColumns(20);
        areaSusBlo.setRows(5);
        jScrollPane6.setViewportView(areaSusBlo);
        areaSusBlo.setLineWrap(true);
        areaSusBlo.setEditable(false);

        miPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 540, 100, 120));
        miPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        jLabel5.setText("Arbol de Procesos");
        miPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel7.setText("Procesos Finalizados");
        miPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        btnCap.setText("Capturar");
        btnCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapActionPerformed(evt);
            }
        });
        miPanel.add(btnCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        btnGen.setText("Generar");
        btnGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenActionPerformed(evt);
            }
        });
        miPanel.add(btnGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        lblIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Bradley/proceso3.png"))); // NOI18N
        miPanel.add(lblIm, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 590, 330));
        miPanel.add(lblCrono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 110, 30));

        areaFinalizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(areaFinalizados);

        miPanel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 170, 220));

        menuArchivo.setText("Archivo");
        jMenuBar1.add(menuArchivo);

        menuProcesos.setText("Procesos");

        jMenuItem1.setText("jMenuItem1");
        menuProcesos.add(jMenuItem1);

        jMenuBar1.add(menuProcesos);

        menuSimulador.setText("Simulador");
        jMenuBar1.add(menuSimulador);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        
        btnCrear.setEnabled(false);
        btnIniciar.setEnabled(false);
        btnMatar.setEnabled(true);
        btnDetener.setEnabled(true);
        btnCap.setVisible(false);
        btnGen.setVisible(false);
        
        
        
        
        algo = (String) JOptionPane.showInputDialog(null,"Seleccione Un Algoritmo",
                "ALGORITMOS", JOptionPane.QUESTION_MESSAGE, null,
                new Object[] { "FIFO", "SJF", "RR","SRT","HRN","ColasMN","ColasMNR" },"FIFO");
        
        switch (algo){
            case ("FIFO"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                int length = index+1;
                FIFO ob=new FIFO(procesos, length);
                Thread hilo = new Thread(ob);
                hilo.start();
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
                
            case ("SJF"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                length = index+1;
                SJF obj=new SJF(procesos, length);
                Thread hilo1 = new Thread(obj);
                hilo1.start();
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
                
            case ("RR"):
                aux = -1;

                areaFinalizados.setModel(miMod);
                quantum=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo de Quantum"));
                quantum=quantum*1000;
                length = index+1;
                RR rr=new RR(procesos, length);
                Thread hiloRR = new Thread(rr);
                hiloRR.start();
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;
            
            case ("SRT"):
                aux = -1;
                miMod.setRowCount(0);
                areaFinalizados.setModel(miMod);
                length = index+1;
                SRT srt = new SRT(procesos, length);
                Thread hiloSRT = new Thread(srt);
                hiloSRT.start();
                
                
                try {
                tarea.run();
                timerPrincipal.schedule(tarea, 0, 1000);
                } catch(IllegalStateException e){}
                
                break;    
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        btnCap.setVisible(true);
        btnGen.setVisible(true);
    }//GEN-LAST:event_btnCrearActionPerformed

    private void miTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miTablaMouseClicked
        String id;
        int row;
        if(evt.getClickCount() == 2){
            row=miTabla.getSelectedRow();
            System.out.println("Row: "+row);
            process(row);
            String pro=areaProcess.getText();
            pro += "\n" +(String)miTabla.getValueAt(row,0)+"          "+miTabla.getValueAt(row,1);
            
            areaProcess.setText(pro);
            ProcesosRestantes++;
            btnIniciar.setEnabled(true);
        }
    }//GEN-LAST:event_miTablaMouseClicked
    
    public void process(int row){
        Proceso p=new Proceso();
        System.out.println("Index: "+index);
        String id =(String)miTabla.getValueAt(row,0);
        String Nombre=(String)miTabla.getValueAt(row,1);
        String Memoria=(String)miTabla.getValueAt(row,2);
        String Estado=(String)miTabla.getValueAt(row,3);
        String Prioridad=(String)miTabla.getValueAt(row,4);
        String Tiempo=(String)miTabla.getValueAt(row,5);
        
        p.setId(id);
        p.setNombre(Nombre);
        p.setMemoria(Memoria);
        p.setEstado(Estado);
        p.setPrioridad(Prioridad);
        p.setTiempo(Tiempo);
        procesos[index]=p;
        System.out.println("Proceso: "+procesos[index].nombre+" Index: "+index);
        index ++;
        contador++;
    }
    
    public void procesosa(int n){
        System.out.println("entro al metodo con "+n);
        String prio[] = {"1","5","10"};
        
        Random r= new Random ();
        
        
        for(int nn=1; nn<=n; nn++){
            System.out.println("entro a ciclo");
            String ida="";
            String noma="";
            String mema="";
            String num=String.valueOf(nn);
            ida=num;
            noma="Proceso "+num;
            int ma=r.nextInt(4000);
            String mea=String.valueOf(ma);
            String estaa="Listo";
            int qa=r.nextInt(10)+1;
            String qqa = String.valueOf(qa);
            int ale = r.nextInt(3);
            String priod = prio[ale];
            int ta = r.nextInt(10)+1;
            String tiea=String.valueOf(ta);
            
            procesosTabla[0]=ida;
            procesosTabla[1]=noma;
            procesosTabla[2]=mea;
            procesosTabla[3]=estaa;
            procesosTabla[4]=priod;
            procesosTabla[5]=tiea;
            miModelo.addRow(procesosTabla);
            
        }
        
        
        
    }
    
    public Icon setIcono(String url, JLabel lblIm){
        ImageIcon icon = new ImageIcon(getClass().getResource(url));

        int ancho = lblIm.getWidth();
        int alto = lblIm.getHeight();

        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        return icono;
    }
    
    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        btnCrear.setEnabled(true);
        btnIniciar.setEnabled(true);
        btnMatar.setEnabled(false);
        btnDetener.setEnabled(false);
    }//GEN-LAST:event_btnDetenerActionPerformed

    private void btnMatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatarActionPerformed
        
    }//GEN-LAST:event_btnMatarActionPerformed

    private void btnCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapActionPerformed
        CrearProceso p = new CrearProceso();
        btnGen.setEnabled(false);
        btnIniciar.setBackground(Color.green);
    }//GEN-LAST:event_btnCapActionPerformed

    private void btnGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenActionPerformed
        btnCap.setEnabled(false);
        btnIniciar.setBackground(Color.green);
        try {
            String res=JOptionPane.showInputDialog(null,"¿Cuantos procesos desea generar?",JOptionPane.QUESTION_MESSAGE);
            System.out.println(res);

            int nn=Integer.parseInt(res);

            procesosa(nn); 
        } catch(NumberFormatException e) {
        }
    }//GEN-LAST:event_btnGenActionPerformed
    
    @Override
    public void run(){
        
        
    }
    
    public boolean listoVacio(Proceso[] listo){
        for(int x=0;x<listo.length;x++){
            if((listo[x] != null)&& (x==listo.length-1) ){
                x=0;
            }
        }
        return false;
    }
    public void mostrarListos(Proceso[] listo){
        int y=1;
        for(int x=0;x<listo.length;x++){
            areaListos.setText(listo[x].nombre);
            if(listo[y]== null){
                x=listos.length;
            }
            y++;
        }
    }
    
    
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
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diagrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Diagrama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea areaBloqueado;
    public static javax.swing.JTable areaFinalizados;
    public static javax.swing.JTextArea areaListos;
    private javax.swing.JTextArea areaProcess;
    public static javax.swing.JTextArea areaSusBlo;
    public static javax.swing.JTextArea areaSuspLis;
    public static javax.swing.JButton btnCap;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnDetener;
    public static javax.swing.JButton btnGen;
    public static javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnMatar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    public static javax.swing.JLabel lbProceso;
    private javax.swing.JLabel lblCrono;
    private javax.swing.JLabel lblIm;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuProcesos;
    private javax.swing.JMenu menuSimulador;
    private javax.swing.JPanel miPanel;
    public static javax.swing.JTable miTabla;
    // End of variables declaration//GEN-END:variables
}
