package main;

import javax.swing.*;

/**
 *
 * @author Brian Compter
 */
public class Gui extends javax.swing.JFrame {

    // This calculator handles the calculations performed for the user
    Calculator calc = Calculator.getInstance();

    // Number format to make all of our values pretty
    java.text.NumberFormat nf;

    // Bool locks the gui while updates are performed.  Prevents gui collisions
    boolean locked;

    /** Creates new form Gui */
    public Gui() {
        initComponents();

        // instantiate our number format
        nf = java.text.NumberFormat.getInstance();

        // Special combobox-fu to center text
        ListCellRenderer renderer = new DefaultListCellRenderer();
        ( (JLabel) renderer ).setHorizontalAlignment( SwingConstants.CENTER );
        jComboBox1.setRenderer(renderer);
        jComboBox2.setRenderer(renderer);

        // add some event listeners to catch updates
        jComboBox1.addItemListener( new java.awt.event.ItemListener(){

            public void itemStateChanged(java.awt.event.ItemEvent e)
            {
                if ( !locked )
                {
                    locked = true;
                    pushSettings();
                    int[] result = calc.recalc();
                    jTextFieldWarchest.setText( nf.format( result[Calculator.WARCHEST] ));
                    jTextFieldSupport.setText( nf.format( result[Calculator.SUPPORT] ));
                    jTextFieldCBills.setText( nf.format( result[Calculator.CBILLS] ));
                    locked = false;
                }
            }

        });

        jComboBox2.addItemListener( new java.awt.event.ItemListener(){

            public void itemStateChanged(java.awt.event.ItemEvent e)
            {
                if ( !locked )
                {
                    locked = true;
                    pushSettings();
                    int[] result = calc.recalc();
                    jTextFieldWarchest.setText( nf.format( result[Calculator.WARCHEST] ));
                    jTextFieldSupport.setText( nf.format( result[Calculator.SUPPORT] ));
                    jTextFieldCBills.setText( nf.format( result[Calculator.CBILLS] ));
                    locked = false;
                }
            }

        });

        // Add document listeners to each testfield
        jTextFieldWarchest.getDocument().addDocumentListener( new javax.swing.event.DocumentListener(){

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if ( !locked )
                {
                    locked = true;
                    updateByWarchest();
                    locked = false;
                }
                
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if ( !locked )
                {
                    locked = true;
                    updateByWarchest();
                    locked = false;
                }
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}

        });

        jTextFieldSupport.getDocument().addDocumentListener( new javax.swing.event.DocumentListener(){

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if ( !locked )
                {
                    locked = true;
                    updateBySupport();
                    locked = false;
                }
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if ( !locked )
                {
                    locked = true;
                    updateBySupport();
                    locked = false;
                }
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}

        });

        jTextFieldCBills.getDocument().addDocumentListener( new javax.swing.event.DocumentListener(){

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if ( !locked )
                {
                    locked = true;
                    updateByCBills();
                    locked = false;
                }
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if ( !locked )
                {
                    locked = true;
                    updateByCBills();
                    locked = false;
                }
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}

        });

    }

    private void pushSettings()
    {
        // send settings
        String techrating = ((String)jComboBox2.getModel().getSelectedItem());
        double tr = 0;
        String forcesizemodifier = ((String)jComboBox1.getModel().getSelectedItem());
        int fsm = Integer.parseInt(forcesizemodifier);
        
        if ( techrating.equalsIgnoreCase("A") )
            tr = 2.0;
        else if ( techrating.equalsIgnoreCase("B") )
            tr = 1.5;
        else if ( techrating.equalsIgnoreCase("C") )
            tr = 1.25;
        else if ( techrating.equalsIgnoreCase("D") )
            tr = 1.0;
        else if ( techrating.equalsIgnoreCase("F") )
            tr = 0.5;

        calc.setParameters(fsm, tr);
    }
    
    private void updateByWarchest()
    {
        pushSettings();

        // get data from form
        int in;
        try{
            in = Integer.parseInt( jTextFieldWarchest.getText() );
        }catch (NumberFormatException e)
        {
            jTextFieldSupport.setText("ERROR");
            jTextFieldCBills.setText("ERROR");
            return;
        }

        // if no errors, then run through the calculator
        int[] result = calc.calculatebyWarchest(in);
        jTextFieldSupport.setText( nf.format( result[Calculator.SUPPORT] ));
        jTextFieldCBills.setText( nf.format( result[Calculator.CBILLS] ));
    }

    private void updateBySupport()
    {
        pushSettings();

        // get data from form
        int in;
        try{
            in = Integer.parseInt( jTextFieldSupport.getText() );
        }catch (NumberFormatException e)
        {
            jTextFieldWarchest.setText("ERROR");
            jTextFieldCBills.setText("ERROR");
            return;
        }

        // if no errors, then run through the calculator
        int[] result = calc.calculatebySupport(in);
        jTextFieldWarchest.setText( nf.format( result[Calculator.WARCHEST] ));
        jTextFieldCBills.setText( nf.format( result[Calculator.CBILLS] ));
    }

    private void updateByCBills()
    {
        pushSettings();

        // get data from form
        int in;
        try{
            in = Integer.parseInt( jTextFieldCBills.getText().replace(",", "") );
        }catch (NumberFormatException e)
        {
            jTextFieldSupport.setText("ERROR");
            jTextFieldWarchest.setText("ERROR");
            return;
        }

        // if no errors, then run through the calculator
        int[] result = calc.calculatebyCBills(in);
        jTextFieldSupport.setText( nf.format( result[Calculator.SUPPORT] ));
        jTextFieldWarchest.setText( nf.format( result[Calculator.WARCHEST] ));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldWarchest = new javax.swing.JTextField();
        jTextFieldSupport = new javax.swing.JTextField();
        jTextFieldCBills = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Warchest Point Calculator");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Calculator"));

        jLabel1.setText("Warchest Points:");

        jLabel2.setText("Support Points:");

        jLabel3.setText("C-Bills:");

        jTextFieldWarchest.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldWarchest.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldWarchest.setText("0");

        jTextFieldSupport.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldSupport.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSupport.setText("0");

        jTextFieldCBills.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldCBills.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldCBills.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldWarchest, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jTextFieldCBills, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jTextFieldSupport, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldWarchest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldSupport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCBills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboBox1.setSelectedIndex(2);

        jLabel4.setText("Force Size Modifier:");

        jLabel5.setText("Technology Rating:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "F" }));
        jComboBox2.setSelectedIndex(3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 100, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("www.ScrapYardArmory.com");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Display using the system look and feel
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch (Exception e){}

                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldCBills;
    private javax.swing.JTextField jTextFieldSupport;
    private javax.swing.JTextField jTextFieldWarchest;
    // End of variables declaration//GEN-END:variables

}
