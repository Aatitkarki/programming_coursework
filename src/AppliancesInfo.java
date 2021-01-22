import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.Timer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anish kc, ashesh rai, roshani karki, suresh Karki
 */
public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private boolean isWindowOpen = false;
    private String path = "";
    private byte openedWindow = 0;
    private ArrayList <InventoryManagement> arraylistInventory = new ArrayList <> ();
    private ArrayList <Sales> arraylistSales = new ArrayList <> ();
    private ArrayList <InventoryManagement> temp = new ArrayList <> ();
    private CardLayout cl = new CardLayout();
    private CardLayout clSales = new CardLayout();
    private JFrame frameSearch = new JFrame("Search");
    private JFrame frameSearchSales = new JFrame("Search");
    //Creating a document listener to add in the jtextfield
    DocumentListener doc = new DocumentListener() {
        @Override
        public void changedUpdate (DocumentEvent evt) {
            setIt(evt);
        }
        @Override
        public void insertUpdate(DocumentEvent evt) {
            setIt(evt);
        }
        @Override
        public void removeUpdate(DocumentEvent evt) {
            setIt(evt);
        }
        private void setIt(DocumentEvent evt) {
            try {
                String modelNum = comboBoxModelNumber.getSelectedItem().toString();
                String brand = comboBoxBrandSales.getSelectedItem().toString();
                for(InventoryManagement im : arraylistInventory) {
                    String modelNumArray = im.getModelNo();
                    String brandArray = im.getBrand();
                    if(modelNumArray.equalsIgnoreCase(modelNum) && brandArray.equalsIgnoreCase(brand)) {
                        int cost = im.getSellingPrice();
                        int quantity = Integer.parseInt(tfQuantitySales.getText().trim());
                        double discount = Integer.parseInt(comboBoxDiscount.getSelectedItem().toString());

                        if(discount != 0) {
                            discount = discount / 100;
                        }
                        else {
                            tfTotal.setText(String.valueOf(cost * quantity));
                            return;
                        }
                        double discountAmt = (cost * quantity) * discount;
                        double total = (cost * quantity) - discountAmt;
                        tfTotal.setText(String.valueOf(Math.round(total)));
                        return;
                    }
                }
            }
            catch (NumberFormatException nfe) {
            }
            catch (NullPointerException | ArithmeticException e) {
                if (tfQuantitySales.getText() != null || !tfQuantitySales.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frameAddSales, e, "Error!", 0);
                }
            }
        }
    };
    DocumentListener docEdit = new DocumentListener() {
        @Override
        public void changedUpdate (DocumentEvent evt) {
            setIt(evt);
        }
        @Override
        public void insertUpdate(DocumentEvent evt) {
            setIt(evt);
        }
        @Override
        public void removeUpdate(DocumentEvent evt) {
            setIt(evt);
        }
        private void setIt(DocumentEvent evt) {
            try {
                String modelNum = comboBoxModelNumberEdit.getSelectedItem().toString();
                String brand = comboBoxBrandSalesEdit.getSelectedItem().toString();
                for(InventoryManagement im : arraylistInventory) {
                    String modelNumArray = im.getModelNo();
                    String brandArray = im.getBrand();
                    if(modelNumArray.equalsIgnoreCase(modelNum) && brandArray.equalsIgnoreCase(brand)) {
                        int cost = im.getSellingPrice();
                        int quantity = Integer.parseInt(tfQuantitySalesEdit.getText().trim());
                        double discount = Integer.parseInt(comboBoxDiscountEdit.getSelectedItem().toString());

                        if(discount != 0) {
                            discount = discount / 100;
                        }
                        else {
                            tfTotalEdit.setText(String.valueOf(cost * quantity));
                            return;
                        }
                        double discountAmt = (cost * quantity) * discount;
                        double total = (cost * quantity) - discountAmt;
                        tfTotalEdit.setText(String.valueOf(Math.round(total)));
                        return;
                    }
                }
            }
            catch (NumberFormatException nfe) {
            }
            catch (NullPointerException | ArithmeticException e) {
                if (tfQuantitySalesEdit != null || !tfQuantitySalesEdit.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frameEditSales, e, "Error!", 0);
                }
                else {
                    JOptionPane.showMessageDialog(frameEditSales, e, "Error!", 0);
                }
            }
        }
    };
    /**
     * Creates new form DashboardPage
     */
    public AppliancesInfo() {
        initComponents();
        setIcon();
        panelCard.setLayout(cl);
        panelCard.add(panelSearch, "1");
        panelCard.add(panelSearchCombo, "2");
        cl.show(panelCard, "1");
        frameSearch.setSize(370, 215);
        frameSearch.setResizable(false);
        frameSearch.setLocationRelativeTo(null);
        frameSearch.add(panelCard);
        frameSearch.setFocusableWindowState(true);
        //Adding window listener to the frame and creating a windowclosing event
        frameSearch.addWindowListener(new WindowAdapter () {
            @Override
            public void windowClosed(WindowEvent we) {
                isWindowOpen = false;
                openedWindow = 0;
            }
            @Override
            public void windowLostFocus(WindowEvent we) {
                frameSearch.requestFocus();
                frameSearch.toFront();
            }
            @Override
            public void windowDeactivated(WindowEvent we) {
                frameSearch.requestFocus();
                frameSearch.toFront();
            }
        });
        panelCardSales.setLayout(clSales);
        panelCardSales.add(panelSearchSales, "1");
        panelCardSales.add(panelSearchSalesCombo, "2");
        clSales.show(panelCardSales, "1");
        frameSearchSales.setSize(370, 215);
        frameSearchSales.setResizable(false);
        frameSearchSales.setLocationRelativeTo(null);
        frameSearchSales.add(panelCardSales);
        frameSearch.setFocusableWindowState(true);
        frameSearchSales.addWindowListener(new WindowAdapter () {
            @Override
            public void windowClosed(WindowEvent we) {
                isWindowOpen = false;
                openedWindow = 0;
            }
            @Override
            public void windowLostFocus(WindowEvent we) {
                frameSearchSales.requestFocus();
                frameSearchSales.toFront();
            }
            @Override
            public void windowDeactivated(WindowEvent we) {
                frameSearchSales.requestFocus();
                frameSearchSales.toFront();
            }
        });
        //Adding keylistener and document listeners to the constructor 
        tfQuantitySales.getDocument().addDocumentListener(doc);
        tfQuantitySalesEdit.getDocument().addDocumentListener(docEdit);
        //adding keylistener here because other good ways to initialize the keylistener with the frame was not found
        addKeyListener(this);
        Utility util = new Utility();
        util.checkDirs();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogAbout = new javax.swing.JDialog();
        lblAbt = new javax.swing.JLabel();
        frameAddInventory = new javax.swing.JFrame();
        panelAddInventory = new javax.swing.JPanel();
        lblModelNo = new javax.swing.JLabel();
        lblModelName = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblRecommendation = new javax.swing.JLabel();
        lblCostPrice = new javax.swing.JLabel();
        lblSellingPrice = new javax.swing.JLabel();
        tfModelNo = new javax.swing.JTextField();
        tfModelName = new javax.swing.JTextField();
        comboBoxBrand = new javax.swing.JComboBox<>();
        comboCategory = new javax.swing.JComboBox<>();
        radioBtnHigh = new javax.swing.JRadioButton();
        radioBtnMid = new javax.swing.JRadioButton();
        radioBtnLow = new javax.swing.JRadioButton();
        tfCostPrice = new javax.swing.JTextField();
        jButtonAddInventory = new javax.swing.JButton();
        jButtonClearInventory = new javax.swing.JButton();
        lblQuantity = new javax.swing.JLabel();
        tfQuantity = new javax.swing.JTextField();
        tfSellingPrice = new javax.swing.JTextField();
        frameAddSales = new javax.swing.JFrame();
        panelAddSales = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblModelNoSales = new javax.swing.JLabel();
        lblBrandSales = new javax.swing.JLabel();
        lblSalePrice = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        tfDate = new javax.swing.JTextField();
        tfQuantitySales = new javax.swing.JTextField();
        jBtnAddSales = new javax.swing.JButton();
        jBtnClearSales = new javax.swing.JButton();
        lblQuantitySales = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        comboBoxModelNumber = new javax.swing.JComboBox<>();
        comboBoxBrandSales = new javax.swing.JComboBox<>();
        lblDiscount = new javax.swing.JLabel();
        comboBoxDiscount = new javax.swing.JComboBox<>();
        tfFirstName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        frameEditInventory = new javax.swing.JFrame();
        panelEditInventory = new javax.swing.JPanel();
        lblEditInvModelNo = new javax.swing.JLabel();
        lblEditInvModelName = new javax.swing.JLabel();
        lblEditInvBrand = new javax.swing.JLabel();
        lblEditInvCategory = new javax.swing.JLabel();
        lblEditInvRecommendation = new javax.swing.JLabel();
        lblEditInvCostPrice = new javax.swing.JLabel();
        lblEditInvSellingPrice = new javax.swing.JLabel();
        tfEditInvModelNo = new javax.swing.JTextField();
        tfEditInvModelName = new javax.swing.JTextField();
        comboEditInvBrand = new javax.swing.JComboBox<>();
        comboEditInvCategory = new javax.swing.JComboBox<>();
        radioBtnEditInvHigh = new javax.swing.JRadioButton();
        radioBtnEditInvMid = new javax.swing.JRadioButton();
        radioBtnEditInvLow = new javax.swing.JRadioButton();
        tfEditInvCostPrice = new javax.swing.JTextField();
        jButtonEditInventorySave = new javax.swing.JButton();
        jButtonEditInventoryCancel = new javax.swing.JButton();
        lblEditInvQuantity = new javax.swing.JLabel();
        tfEditInvQuantity = new javax.swing.JTextField();
        tfEditInvSellingPrice = new javax.swing.JTextField();
        lblEditInvMsg = new javax.swing.JLabel();
        btnGrpEditInventory = new javax.swing.ButtonGroup();
        btnGrpAddInventory = new javax.swing.ButtonGroup();
        btnGroupSearch = new javax.swing.ButtonGroup();
        frameEditSales = new javax.swing.JFrame();
        panelEditSales = new javax.swing.JPanel();
        lblFirstNameEdit = new javax.swing.JLabel();
        lblDateEdit = new javax.swing.JLabel();
        lblModelNoSalesEdit = new javax.swing.JLabel();
        lblBrandSalesEdit = new javax.swing.JLabel();
        lblSalePriceEdit = new javax.swing.JLabel();
        tfLastNameEdit = new javax.swing.JTextField();
        tfDateEdit = new javax.swing.JTextField();
        tfQuantitySalesEdit = new javax.swing.JTextField();
        jBtnSaveSales = new javax.swing.JButton();
        jBtnCancelSalesEdit = new javax.swing.JButton();
        lblQuantitySalesEdit = new javax.swing.JLabel();
        tfTotalEdit = new javax.swing.JTextField();
        comboBoxModelNumberEdit = new javax.swing.JComboBox<>();
        comboBoxBrandSalesEdit = new javax.swing.JComboBox<>();
        lblDiscountEdit = new javax.swing.JLabel();
        comboBoxDiscountEdit = new javax.swing.JComboBox<>();
        tfFirstNameEdit = new javax.swing.JTextField();
        lblLastNameEdit = new javax.swing.JLabel();
        lblEditInvMsgSales = new javax.swing.JLabel();
        frameInvoice = new javax.swing.JFrame();
        panelBtn = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        panelInvoice = new javax.swing.JPanel();
        lblInvoiceFirstName = new javax.swing.JLabel();
        lblSetFirstName = new javax.swing.JLabel();
        lblInvoiceLastName = new javax.swing.JLabel();
        lblSetLastName = new javax.swing.JLabel();
        lblInvoiceDate = new javax.swing.JLabel();
        lblSetDate = new javax.swing.JLabel();
        lblInvoiceModelNumber = new javax.swing.JLabel();
        lblSetModelNumber = new javax.swing.JLabel();
        lblInvoiceBrand = new javax.swing.JLabel();
        lblSetBrand = new javax.swing.JLabel();
        lblInvoiceDiscount = new javax.swing.JLabel();
        lblInvoiceQuantity = new javax.swing.JLabel();
        lblInvoiceTotal = new javax.swing.JLabel();
        lblSetDiscount = new javax.swing.JLabel();
        lblSetQuantity = new javax.swing.JLabel();
        lblSetTotal = new javax.swing.JLabel();
        jDialogHelp = new javax.swing.JDialog();
        lblHelp = new javax.swing.JLabel();
        btnGrpSearchCombo = new javax.swing.ButtonGroup();
        panelCard = new javax.swing.JPanel();
        panelSearch = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        radioBtnCostPrice = new javax.swing.JRadioButton();
        jButtonSearchArray = new javax.swing.JButton();
        jButtonSearchCancel = new javax.swing.JButton();
        radioBtnSellingPrice = new javax.swing.JRadioButton();
        radioBtnModelName = new javax.swing.JRadioButton();
        radioBtnBrand = new javax.swing.JRadioButton();
        lblSearchError = new javax.swing.JLabel();
        panelSearchCombo = new javax.swing.JPanel();
        lblSearchCombo = new javax.swing.JLabel();
        radioBtnCostPriceCombo = new javax.swing.JRadioButton();
        jButtonSearchArrayCombo = new javax.swing.JButton();
        radioBtnSellingPriceCombo = new javax.swing.JRadioButton();
        radioBtnModelNameCombo = new javax.swing.JRadioButton();
        radioBtnCategoryCombo = new javax.swing.JRadioButton();
        lblSearchErrorCombo = new javax.swing.JLabel();
        comboBoxSearch = new javax.swing.JComboBox<>();
        panelCardSales = new javax.swing.JPanel();
        panelSearchSales = new javax.swing.JPanel();
        lblSearchSales = new javax.swing.JLabel();
        tfSearchSales = new javax.swing.JTextField();
        radioBtnTotal = new javax.swing.JRadioButton();
        jButtonSearchArraySales = new javax.swing.JButton();
        jButtonSearchCancelSales = new javax.swing.JButton();
        radioBtnCustomerName = new javax.swing.JRadioButton();
        radioBtnModelNumberSales = new javax.swing.JRadioButton();
        radioBtnBrandSales = new javax.swing.JRadioButton();
        lblSearchErrorSales = new javax.swing.JLabel();
        panelSearchSalesCombo = new javax.swing.JPanel();
        lblSearchSalesCombo = new javax.swing.JLabel();
        radioBtnTotalCombo = new javax.swing.JRadioButton();
        jButtonSearchArraySalesCombo = new javax.swing.JButton();
        radioBtnCustomerNameCombo = new javax.swing.JRadioButton();
        radioBtnModelNumberSalesCombo = new javax.swing.JRadioButton();
        radioBtnBrandSalesCombo = new javax.swing.JRadioButton();
        lblSearchErrorSalesCombo = new javax.swing.JLabel();
        comboBoxSearchSales = new javax.swing.JComboBox<>();
        btnGroupSearchSales = new javax.swing.ButtonGroup();
        btnGrpSearchSalesCombo = new javax.swing.ButtonGroup();
        panelBg = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnInvoice = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        tabbedPaneTbl = new javax.swing.JTabbedPane();
        tblPnlInventory = new javax.swing.JPanel();
        scrlPaneInventory = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        tblPnlSales = new javax.swing.JPanel();
        scrlPaneSales = new javax.swing.JScrollPane();
        tblSales = new javax.swing.JTable();
        mnuBarEmployee = new javax.swing.JMenuBar();
        jMnuFile = new javax.swing.JMenu();
        jMnuItmNew = new javax.swing.JMenuItem();
        jMnuItmOpen = new javax.swing.JMenuItem();
        jMnuItmSave = new javax.swing.JMenuItem();
        jMnuItmSaveAs = new javax.swing.JMenuItem();
        jMnuTools = new javax.swing.JMenu();
        jMnuItmAddBrand = new javax.swing.JMenuItem();
        jMnuItmRemoveBrand = new javax.swing.JMenuItem();
        jMnuAbt = new javax.swing.JMenu();
        jMnuItmAbout = new javax.swing.JMenuItem();
        jMnuItmHelp = new javax.swing.JMenuItem();
        jMnuItmExit = new javax.swing.JMenuItem();

        jDialogAbout.setTitle("About");
        jDialogAbout.setBackground(new java.awt.Color(255, 255, 255));

        lblAbt.setText("<html><center>Mobile Inventory Management<br>Developed by:<br>Anish Chaudhary<br>Ashesh Rai<br>Roshani Karki<br>Suresh Karki</center></html>");

        javax.swing.GroupLayout jDialogAboutLayout = new javax.swing.GroupLayout(jDialogAbout.getContentPane());
        jDialogAbout.getContentPane().setLayout(jDialogAboutLayout);
        jDialogAboutLayout.setHorizontalGroup(
            jDialogAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAboutLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblAbt)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jDialogAboutLayout.setVerticalGroup(
            jDialogAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAboutLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblAbt)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        frameAddInventory.setTitle("Add an entry");
        frameAddInventory.setAlwaysOnTop(true);
        frameAddInventory.setMinimumSize(new java.awt.Dimension(400, 480));
        frameAddInventory.setResizable(false);
        frameAddInventory.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                frameAddInventoryWindowLostFocus(evt);
            }
        });
        frameAddInventory.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frameAddInventoryWindowClosing(evt);
            }
        });

        panelAddInventory.setBackground(new java.awt.Color(0, 0, 51));
        panelAddInventory.setForeground(new java.awt.Color(255, 255, 255));

        lblModelNo.setForeground(new java.awt.Color(255, 255, 255));
        lblModelNo.setText("Model Number :");

        lblModelName.setForeground(new java.awt.Color(255, 255, 255));
        lblModelName.setText("Model Name :");

        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setText("Brand :");

        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category :");

        lblRecommendation.setForeground(new java.awt.Color(255, 255, 255));
        lblRecommendation.setText("Recommendation :");

        lblCostPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblCostPrice.setText("Cost Price :");

        lblSellingPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblSellingPrice.setText("Selling Price :");
        lblSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lblSellingPriceKeyTyped(evt);
            }
        });

        tfModelNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfModelNoKeyTyped(evt);
            }
        });

        tfModelName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfModelNameKeyTyped(evt);
            }
        });

        comboBoxBrand.setModel(new DefaultComboBoxModel <> (elementsForComboBoxBrand()));

        comboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basic Phone", "Feature Phone", "Smartphone" }));

        btnGrpAddInventory.add(radioBtnHigh);
        radioBtnHigh.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnHigh.setSelected(true);
        radioBtnHigh.setText("High");

        btnGrpAddInventory.add(radioBtnMid);
        radioBtnMid.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnMid.setText("Mid");

        btnGrpAddInventory.add(radioBtnLow);
        radioBtnLow.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnLow.setText("Low");

        tfCostPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCostPriceKeyTyped(evt);
            }
        });

        jButtonAddInventory.setBackground(new java.awt.Color(0, 0, 51));
        jButtonAddInventory.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonAddInventory.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddInventory.setText("Add");
        jButtonAddInventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAddInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddInventoryActionPerformed(evt);
            }
        });

        jButtonClearInventory.setBackground(new java.awt.Color(0, 0, 51));
        jButtonClearInventory.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonClearInventory.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClearInventory.setText("Clear");
        jButtonClearInventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClearInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearInventoryActionPerformed(evt);
            }
        });

        lblQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantity.setText("Quantity :");

        tfQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfQuantityKeyTyped(evt);
            }
        });

        tfSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfSellingPriceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelAddInventoryLayout = new javax.swing.GroupLayout(panelAddInventory);
        panelAddInventory.setLayout(panelAddInventoryLayout);
        panelAddInventoryLayout.setHorizontalGroup(
            panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddInventoryLayout.createSequentialGroup()
                .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddInventoryLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModelNo)
                            .addComponent(lblModelName)
                            .addComponent(lblRecommendation)
                            .addComponent(lblCostPrice)
                            .addComponent(lblSellingPrice)
                            .addComponent(lblBrand)
                            .addComponent(lblCategory)
                            .addComponent(lblQuantity))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddInventoryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonAddInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfModelNo)
                    .addComponent(tfCostPrice)
                    .addComponent(comboBoxBrand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfModelName)
                    .addGroup(panelAddInventoryLayout.createSequentialGroup()
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddInventoryLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButtonClearInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddInventoryLayout.createSequentialGroup()
                                .addComponent(radioBtnHigh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnMid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnLow))
                            .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfQuantity)
                    .addComponent(tfSellingPrice, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(70, 70, 70))
        );
        panelAddInventoryLayout.setVerticalGroup(
            panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddInventoryLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAddInventoryLayout.createSequentialGroup()
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelAddInventoryLayout.createSequentialGroup()
                                .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblModelNo)
                                    .addComponent(tfModelNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(lblModelName))
                            .addComponent(tfModelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBrand)
                            .addComponent(comboBoxBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCategory)
                            .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRecommendation)
                            .addComponent(radioBtnHigh)
                            .addComponent(radioBtnMid)
                            .addComponent(radioBtnLow))
                        .addGap(20, 20, 20)
                        .addComponent(lblCostPrice))
                    .addComponent(tfCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddInventoryLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSellingPrice)
                            .addComponent(tfSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQuantity)
                            .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddInventoryLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAddInventory)
                            .addComponent(jButtonClearInventory))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout frameAddInventoryLayout = new javax.swing.GroupLayout(frameAddInventory.getContentPane());
        frameAddInventory.getContentPane().setLayout(frameAddInventoryLayout);
        frameAddInventoryLayout.setHorizontalGroup(
            frameAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameAddInventoryLayout.setVerticalGroup(
            frameAddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frameAddSales.setTitle("Add an entry");
        frameAddSales.setAlwaysOnTop(true);
        frameAddSales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frameAddSales.setMinimumSize(new java.awt.Dimension(400, 500));
        frameAddSales.setResizable(false);
        frameAddSales.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                frameAddSalesWindowLostFocus(evt);
            }
        });
        frameAddSales.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frameAddSalesWindowClosing(evt);
            }
        });

        panelAddSales.setBackground(new java.awt.Color(0, 0, 51));
        panelAddSales.setForeground(new java.awt.Color(255, 255, 255));

        lblFirstName.setForeground(new java.awt.Color(255, 255, 255));
        lblFirstName.setText("First Name :");

        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date :");

        lblModelNoSales.setForeground(new java.awt.Color(255, 255, 255));
        lblModelNoSales.setText("Model Number :");

        lblBrandSales.setForeground(new java.awt.Color(255, 255, 255));
        lblBrandSales.setText("Brand :");

        lblSalePrice.setForeground(new java.awt.Color(255, 255, 255));
        lblSalePrice.setText("Total:");

        tfLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfLastNameKeyTyped(evt);
            }
        });

        tfDate.setToolTipText("dd-mm-yyyy, dd.mm.yyyy, dd/mm/yyyy");
        tfDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDateKeyTyped(evt);
            }
        });

        tfQuantitySales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfQuantitySalesKeyTyped(evt);
            }
        });

        jBtnAddSales.setBackground(new java.awt.Color(0, 0, 51));
        jBtnAddSales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnAddSales.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAddSales.setText("Add");
        jBtnAddSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAddSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddSalesActionPerformed(evt);
            }
        });

        jBtnClearSales.setBackground(new java.awt.Color(0, 0, 51));
        jBtnClearSales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnClearSales.setForeground(new java.awt.Color(255, 255, 255));
        jBtnClearSales.setText("Clear");
        jBtnClearSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnClearSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnClearSalesActionPerformed(evt);
            }
        });

        lblQuantitySales.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantitySales.setText("Quantity :");

        tfTotal.setEditable(false);

        comboBoxModelNumber.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboBoxModelNumber.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxModelNumberItemStateChanged(evt);
            }
        });

        comboBoxBrandSales.setModel(new DefaultComboBoxModel <> (elementsForComboBoxBrand()));
        comboBoxBrandSales.setEnabled(false);

        lblDiscount.setBackground(new java.awt.Color(0, 0, 51));
        lblDiscount.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscount.setText("Discount (%) :");

        comboBoxDiscount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "5", "10", "15", "20" }));
        comboBoxDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiscountActionPerformed(evt);
            }
        });

        tfFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFirstNameKeyTyped(evt);
            }
        });

        lblLastName.setForeground(new java.awt.Color(255, 255, 255));
        lblLastName.setText("Last Name :");

        javax.swing.GroupLayout panelAddSalesLayout = new javax.swing.GroupLayout(panelAddSales);
        panelAddSales.setLayout(panelAddSalesLayout);
        panelAddSalesLayout.setHorizontalGroup(
            panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddSalesLayout.createSequentialGroup()
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddSalesLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName)
                            .addComponent(lblDate)
                            .addComponent(lblModelNoSales)
                            .addComponent(lblBrandSales)
                            .addComponent(lblQuantitySales)
                            .addComponent(lblSalePrice)
                            .addComponent(lblDiscount)
                            .addComponent(lblLastName))
                        .addGap(35, 35, 35)
                        .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLastName)
                            .addComponent(tfQuantitySales)
                            .addComponent(tfDate)
                            .addComponent(tfTotal)
                            .addComponent(comboBoxModelNumber, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxBrandSales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxDiscount, 0, 185, Short.MAX_VALUE)
                            .addComponent(tfFirstName)))
                    .addGroup(panelAddSalesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAddSales, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jBtnClearSales, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addGap(50, 50, 50))
        );
        panelAddSalesLayout.setVerticalGroup(
            panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddSalesLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLastName))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxModelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModelNoSales))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxBrandSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrandSales))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfQuantitySales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantitySales))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiscount))
                .addGap(20, 20, 20)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalePrice)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(panelAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAddSales)
                    .addComponent(jBtnClearSales))
                .addContainerGap())
        );

        javax.swing.GroupLayout frameAddSalesLayout = new javax.swing.GroupLayout(frameAddSales.getContentPane());
        frameAddSales.getContentPane().setLayout(frameAddSalesLayout);
        frameAddSalesLayout.setHorizontalGroup(
            frameAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameAddSalesLayout.setVerticalGroup(
            frameAddSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAddSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frameEditInventory.setTitle("Edit");
        frameEditInventory.setAlwaysOnTop(true);
        frameEditInventory.setMinimumSize(new java.awt.Dimension(400, 520));
        frameEditInventory.setResizable(false);
        frameEditInventory.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                frameEditInventoryWindowLostFocus(evt);
            }
        });
        frameEditInventory.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frameEditInventoryWindowClosing(evt);
            }
        });

        panelEditInventory.setBackground(new java.awt.Color(0, 0, 51));
        panelEditInventory.setForeground(new java.awt.Color(255, 255, 255));

        lblEditInvModelNo.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvModelNo.setText("Model Number :");

        lblEditInvModelName.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvModelName.setText("Model Name :");

        lblEditInvBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvBrand.setText("Brand :");

        lblEditInvCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvCategory.setText("Category :");

        lblEditInvRecommendation.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvRecommendation.setText("Recommendation :");

        lblEditInvCostPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvCostPrice.setText("Cost Price :");

        lblEditInvSellingPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvSellingPrice.setText("Selling Price :");

        comboEditInvBrand.setModel(new DefaultComboBoxModel <> (elementsForComboBoxBrand()));

        comboEditInvCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basic Phone", "Feature Phone", "Smartphone" }));

        btnGrpEditInventory.add(radioBtnEditInvHigh);
        radioBtnEditInvHigh.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnEditInvHigh.setText("High");

        btnGrpEditInventory.add(radioBtnEditInvMid);
        radioBtnEditInvMid.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnEditInvMid.setText("Mid");

        btnGrpEditInventory.add(radioBtnEditInvLow);
        radioBtnEditInvLow.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnEditInvLow.setText("Low");

        tfEditInvCostPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEditInvCostPriceKeyTyped(evt);
            }
        });

        jButtonEditInventorySave.setBackground(new java.awt.Color(0, 0, 51));
        jButtonEditInventorySave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonEditInventorySave.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditInventorySave.setText("Save");
        jButtonEditInventorySave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEditInventorySave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditInventorySaveActionPerformed(evt);
            }
        });

        jButtonEditInventoryCancel.setBackground(new java.awt.Color(0, 0, 51));
        jButtonEditInventoryCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonEditInventoryCancel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditInventoryCancel.setText("Cancel");
        jButtonEditInventoryCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEditInventoryCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditInventoryCancelActionPerformed(evt);
            }
        });

        lblEditInvQuantity.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvQuantity.setText("Quantity :");

        tfEditInvQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEditInvQuantityKeyTyped(evt);
            }
        });

        tfEditInvSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEditInvSellingPriceKeyTyped(evt);
            }
        });

        lblEditInvMsg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEditInvMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvMsg.setText("No Changes Detected");

        javax.swing.GroupLayout panelEditInventoryLayout = new javax.swing.GroupLayout(panelEditInventory);
        panelEditInventory.setLayout(panelEditInventoryLayout);
        panelEditInventoryLayout.setHorizontalGroup(
            panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditInventoryLayout.createSequentialGroup()
                .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditInventoryLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEditInvModelNo)
                            .addComponent(lblEditInvModelName)
                            .addComponent(lblEditInvRecommendation)
                            .addComponent(lblEditInvCostPrice)
                            .addComponent(lblEditInvSellingPrice)
                            .addComponent(lblEditInvBrand)
                            .addComponent(lblEditInvCategory)
                            .addComponent(lblEditInvQuantity))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditInventoryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonEditInventorySave, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEditInvModelNo)
                    .addComponent(tfEditInvCostPrice)
                    .addComponent(comboEditInvBrand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfEditInvModelName)
                    .addGroup(panelEditInventoryLayout.createSequentialGroup()
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEditInventoryLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButtonEditInventoryCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEditInventoryLayout.createSequentialGroup()
                                .addComponent(radioBtnEditInvHigh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnEditInvMid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnEditInvLow))
                            .addComponent(comboEditInvCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfEditInvQuantity)
                    .addComponent(tfEditInvSellingPrice, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(70, 70, 70))
            .addGroup(panelEditInventoryLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(lblEditInvMsg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEditInventoryLayout.setVerticalGroup(
            panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditInventoryLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEditInventoryLayout.createSequentialGroup()
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelEditInventoryLayout.createSequentialGroup()
                                .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblEditInvModelNo)
                                    .addComponent(tfEditInvModelNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(lblEditInvModelName))
                            .addComponent(tfEditInvModelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditInvBrand)
                            .addComponent(comboEditInvBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditInvCategory)
                            .addComponent(comboEditInvCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditInvRecommendation)
                            .addComponent(radioBtnEditInvHigh)
                            .addComponent(radioBtnEditInvMid)
                            .addComponent(radioBtnEditInvLow))
                        .addGap(20, 20, 20)
                        .addComponent(lblEditInvCostPrice))
                    .addComponent(tfEditInvCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditInventoryLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditInvSellingPrice)
                            .addComponent(tfEditInvSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditInvQuantity)
                            .addComponent(tfEditInvQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditInventoryLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEditInventorySave)
                            .addComponent(jButtonEditInventoryCancel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(lblEditInvMsg)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout frameEditInventoryLayout = new javax.swing.GroupLayout(frameEditInventory.getContentPane());
        frameEditInventory.getContentPane().setLayout(frameEditInventoryLayout);
        frameEditInventoryLayout.setHorizontalGroup(
            frameEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameEditInventoryLayout.setVerticalGroup(
            frameEditInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frameEditSales.setTitle("Edit");
        frameEditSales.setAlwaysOnTop(true);
        frameEditSales.setMinimumSize(new java.awt.Dimension(400, 515));
        frameEditSales.setResizable(false);
        frameEditSales.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                frameEditSalesWindowLostFocus(evt);
            }
        });
        frameEditSales.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frameEditSalesWindowClosing(evt);
            }
        });

        panelEditSales.setBackground(new java.awt.Color(0, 0, 51));
        panelEditSales.setForeground(new java.awt.Color(255, 255, 255));

        lblFirstNameEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblFirstNameEdit.setText("First Name :");

        lblDateEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblDateEdit.setText("Date :");

        lblModelNoSalesEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblModelNoSalesEdit.setText("Model Number :");

        lblBrandSalesEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblBrandSalesEdit.setText("Brand :");

        lblSalePriceEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblSalePriceEdit.setText("Total:");

        tfLastNameEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfLastNameEditKeyTyped(evt);
            }
        });

        tfDateEdit.setToolTipText("dd-mm-yyyy, dd.mm.yyyy, dd/mm/yyyy");
        tfDateEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDateEditKeyTyped(evt);
            }
        });

        tfQuantitySalesEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfQuantitySalesEditKeyTyped(evt);
            }
        });

        jBtnSaveSales.setBackground(new java.awt.Color(0, 0, 51));
        jBtnSaveSales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnSaveSales.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSaveSales.setText("Save");
        jBtnSaveSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSaveSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveSalesActionPerformed(evt);
            }
        });

        jBtnCancelSalesEdit.setBackground(new java.awt.Color(0, 0, 51));
        jBtnCancelSalesEdit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnCancelSalesEdit.setForeground(new java.awt.Color(255, 255, 255));
        jBtnCancelSalesEdit.setText("Cancel");
        jBtnCancelSalesEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCancelSalesEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelSalesEditActionPerformed(evt);
            }
        });

        lblQuantitySalesEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantitySalesEdit.setText("Quantity :");

        tfTotalEdit.setEditable(false);

        comboBoxModelNumberEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboBoxModelNumberEdit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxModelNumberEditItemStateChanged(evt);
            }
        });

        comboBoxBrandSalesEdit.setModel(new DefaultComboBoxModel <> (elementsForComboBoxBrand()));

        lblDiscountEdit.setBackground(new java.awt.Color(0, 0, 51));
        lblDiscountEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscountEdit.setText("Discount (%) :");

        comboBoxDiscountEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "5", "10", "15", "20" }));
        comboBoxDiscountEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiscountEditActionPerformed(evt);
            }
        });

        tfFirstNameEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfFirstNameEditKeyTyped(evt);
            }
        });

        lblLastNameEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblLastNameEdit.setText("Last Name :");

        lblEditInvMsgSales.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEditInvMsgSales.setForeground(new java.awt.Color(255, 255, 255));
        lblEditInvMsgSales.setText("No Changes Detected");

        javax.swing.GroupLayout panelEditSalesLayout = new javax.swing.GroupLayout(panelEditSales);
        panelEditSales.setLayout(panelEditSalesLayout);
        panelEditSalesLayout.setHorizontalGroup(
            panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditSalesLayout.createSequentialGroup()
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditSalesLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstNameEdit)
                            .addComponent(lblDateEdit)
                            .addComponent(lblModelNoSalesEdit)
                            .addComponent(lblBrandSalesEdit)
                            .addComponent(lblQuantitySalesEdit)
                            .addComponent(lblSalePriceEdit)
                            .addComponent(lblDiscountEdit)
                            .addComponent(lblLastNameEdit))
                        .addGap(35, 35, 35)
                        .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLastNameEdit)
                            .addComponent(tfQuantitySalesEdit)
                            .addComponent(tfDateEdit)
                            .addComponent(tfTotalEdit)
                            .addComponent(comboBoxModelNumberEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxBrandSalesEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxDiscountEdit, 0, 190, Short.MAX_VALUE)
                            .addComponent(tfFirstNameEdit)))
                    .addGroup(panelEditSalesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnSaveSales, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jBtnCancelSalesEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditSalesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblEditInvMsgSales)
                .addGap(126, 126, 126))
        );
        panelEditSalesLayout.setVerticalGroup(
            panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditSalesLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstNameEdit)
                    .addComponent(tfFirstNameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLastNameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLastNameEdit))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDateEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDateEdit))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxModelNumberEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModelNoSalesEdit))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxBrandSalesEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrandSalesEdit))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfQuantitySalesEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantitySalesEdit))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxDiscountEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiscountEdit))
                .addGap(20, 20, 20)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalePriceEdit)
                    .addComponent(tfTotalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(lblEditInvMsgSales)
                .addGap(52, 52, 52)
                .addGroup(panelEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSaveSales)
                    .addComponent(jBtnCancelSalesEdit))
                .addContainerGap())
        );

        javax.swing.GroupLayout frameEditSalesLayout = new javax.swing.GroupLayout(frameEditSales.getContentPane());
        frameEditSales.getContentPane().setLayout(frameEditSalesLayout);
        frameEditSalesLayout.setHorizontalGroup(
            frameEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameEditSalesLayout.setVerticalGroup(
            frameEditSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frameInvoice.setTitle("Invoice");
        frameInvoice.setBackground(new java.awt.Color(255, 255, 255));
        frameInvoice.setMinimumSize(new java.awt.Dimension(400, 395));
        frameInvoice.setResizable(false);
        frameInvoice.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                frameInvoiceWindowLostFocus(evt);
            }
        });
        frameInvoice.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frameInvoiceWindowClosing(evt);
            }
        });

        panelBtn.setBackground(new java.awt.Color(255, 255, 255));

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/saveicon.png"))); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printericon.png"))); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBtnLayout = new javax.swing.GroupLayout(panelBtn);
        panelBtn.setLayout(panelBtnLayout);
        panelBtnLayout.setHorizontalGroup(
            panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBtnLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(320, Short.MAX_VALUE)))
        );
        panelBtnLayout.setVerticalGroup(
            panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBtnLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        panelInvoice.setBackground(new java.awt.Color(255, 255, 255));
        panelInvoice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblInvoiceFirstName.setText("First Name : ");

        lblSetFirstName.setText("FirstName");

        lblInvoiceLastName.setText("Last Name :");

        lblSetLastName.setText("LastName");

        lblInvoiceDate.setText("Date :");

        lblSetDate.setText("date");

        lblInvoiceModelNumber.setText("Model Number:");

        lblSetModelNumber.setText("ModelNumber");

        lblInvoiceBrand.setText("Brand :");

        lblSetBrand.setText("brand");

        lblInvoiceDiscount.setText("Discount :");

        lblInvoiceQuantity.setText("Quantity :");

        lblInvoiceTotal.setText("Total :");

        lblSetDiscount.setText("discount");

        lblSetQuantity.setText("quantity");

        lblSetTotal.setText("total");

        javax.swing.GroupLayout panelInvoiceLayout = new javax.swing.GroupLayout(panelInvoice);
        panelInvoice.setLayout(panelInvoiceLayout);
        panelInvoiceLayout.setHorizontalGroup(
            panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvoiceLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInvoiceFirstName)
                    .addComponent(lblInvoiceLastName)
                    .addComponent(lblInvoiceDate)
                    .addComponent(lblInvoiceModelNumber)
                    .addComponent(lblInvoiceBrand)
                    .addComponent(lblInvoiceDiscount)
                    .addComponent(lblInvoiceQuantity)
                    .addComponent(lblInvoiceTotal))
                .addGap(38, 38, 38)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSetTotal)
                    .addComponent(lblSetQuantity)
                    .addComponent(lblSetDiscount)
                    .addComponent(lblSetBrand)
                    .addComponent(lblSetModelNumber)
                    .addComponent(lblSetLastName)
                    .addComponent(lblSetFirstName)
                    .addComponent(lblSetDate))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        panelInvoiceLayout.setVerticalGroup(
            panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvoiceLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceFirstName)
                    .addComponent(lblSetFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceLastName)
                    .addComponent(lblSetLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceDate)
                    .addComponent(lblSetDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceModelNumber)
                    .addComponent(lblSetModelNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceBrand)
                    .addComponent(lblSetBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceDiscount)
                    .addComponent(lblSetDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceQuantity)
                    .addComponent(lblSetQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInvoiceTotal)
                    .addComponent(lblSetTotal))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameInvoiceLayout = new javax.swing.GroupLayout(frameInvoice.getContentPane());
        frameInvoice.getContentPane().setLayout(frameInvoiceLayout);
        frameInvoiceLayout.setHorizontalGroup(
            frameInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameInvoiceLayout.setVerticalGroup(
            frameInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameInvoiceLayout.createSequentialGroup()
                .addComponent(panelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogHelp.setTitle("Help");
        jDialogHelp.setBackground(new java.awt.Color(255, 255, 255));
        jDialogHelp.setIconImage(null);
        jDialogHelp.setMinimumSize(new java.awt.Dimension(296, 410));

        lblHelp.setText("<html><center><b>Help Section</b><br>Menu items in the menu bar contains the feature suggested by their names<br>they also can be used throughout the main window by simply pressing<br>their shortcut keys.<br><br>The buttons open up the forms corresponding to the selected table.<br>Edit and Delete button requires selection of selected row as well as selection of table.<br>Invoice button works only for the sales table.<br><br>Sales table does not accept data if there is no data inside inventory table.<br><br>It is highly recommended to use the program rather than default programs to input data<br>use of other programs can cause error while loading such files.</center></html>");

        javax.swing.GroupLayout jDialogHelpLayout = new javax.swing.GroupLayout(jDialogHelp.getContentPane());
        jDialogHelp.getContentPane().setLayout(jDialogHelpLayout);
        jDialogHelpLayout.setHorizontalGroup(
            jDialogHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHelp)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jDialogHelpLayout.setVerticalGroup(
            jDialogHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogHelpLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblHelp)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        panelCard.setMinimumSize(new java.awt.Dimension(370, 215));
        panelCard.setPreferredSize(new java.awt.Dimension(370, 215));
        panelCard.setLayout(new java.awt.CardLayout());

        panelSearch.setBackground(new java.awt.Color(0, 0, 51));
        panelSearch.setMinimumSize(new java.awt.Dimension(370, 215));
        panelSearch.setPreferredSize(new java.awt.Dimension(370, 215));

        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search :");

        radioBtnCostPrice.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearch.add(radioBtnCostPrice);
        radioBtnCostPrice.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnCostPrice.setSelected(true);
        radioBtnCostPrice.setText("Cost Price");

        jButtonSearchArray.setBackground(new java.awt.Color(0, 0, 51));
        jButtonSearchArray.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSearchArray.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchArray.setText("Search");
        jButtonSearchArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchArrayActionPerformed(evt);
            }
        });

        jButtonSearchCancel.setBackground(new java.awt.Color(0, 0, 51));
        jButtonSearchCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSearchCancel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchCancel.setText("Cancel");
        jButtonSearchCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchCancelActionPerformed(evt);
            }
        });

        radioBtnSellingPrice.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearch.add(radioBtnSellingPrice);
        radioBtnSellingPrice.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnSellingPrice.setText("Selling Price");

        radioBtnModelName.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearch.add(radioBtnModelName);
        radioBtnModelName.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnModelName.setText("Model Num");

        radioBtnBrand.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearch.add(radioBtnBrand);
        radioBtnBrand.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnBrand.setText("Category");
        radioBtnBrand.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnBrandItemStateChanged(evt);
            }
        });

        lblSearchError.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSearchError.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchError.setText("No Search Results Found");

        javax.swing.GroupLayout panelSearchLayout = new javax.swing.GroupLayout(panelSearch);
        panelSearch.setLayout(panelSearchLayout);
        panelSearchLayout.setHorizontalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSearchLayout.createSequentialGroup()
                        .addComponent(radioBtnCostPrice)
                        .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSearchLayout.createSequentialGroup()
                                .addComponent(radioBtnSellingPrice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioBtnModelName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnBrand))
                            .addGroup(panelSearchLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSearchError)
                                    .addGroup(panelSearchLayout.createSequentialGroup()
                                        .addComponent(jButtonSearchArray)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonSearchCancel)))))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panelSearchLayout.setVerticalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnCostPrice)
                    .addComponent(radioBtnSellingPrice)
                    .addComponent(radioBtnModelName)
                    .addComponent(radioBtnBrand))
                .addGap(15, 15, 15)
                .addComponent(lblSearchError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSearchArray)
                    .addComponent(jButtonSearchCancel))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelCard.add(panelSearch, "card2");

        panelSearchCombo.setBackground(new java.awt.Color(0, 0, 51));
        panelSearchCombo.setMinimumSize(new java.awt.Dimension(370, 215));
        panelSearchCombo.setPreferredSize(new java.awt.Dimension(370, 215));

        lblSearchCombo.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchCombo.setText("Search :");

        radioBtnCostPriceCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchCombo.add(radioBtnCostPriceCombo);
        radioBtnCostPriceCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnCostPriceCombo.setText("Cost Price");
        radioBtnCostPriceCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnCostPriceComboItemStateChanged(evt);
            }
        });

        jButtonSearchArrayCombo.setBackground(new java.awt.Color(0, 0, 51));
        jButtonSearchArrayCombo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSearchArrayCombo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchArrayCombo.setText("SmartPhone by Category");
        jButtonSearchArrayCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchArrayComboActionPerformed(evt);
            }
        });

        radioBtnSellingPriceCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchCombo.add(radioBtnSellingPriceCombo);
        radioBtnSellingPriceCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnSellingPriceCombo.setText("Selling Price");
        radioBtnSellingPriceCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnSellingPriceComboItemStateChanged(evt);
            }
        });

        radioBtnModelNameCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchCombo.add(radioBtnModelNameCombo);
        radioBtnModelNameCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnModelNameCombo.setText("Model Num");
        radioBtnModelNameCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnModelNameComboItemStateChanged(evt);
            }
        });
        radioBtnModelNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnModelNameComboActionPerformed(evt);
            }
        });

        radioBtnCategoryCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchCombo.add(radioBtnCategoryCombo);
        radioBtnCategoryCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnCategoryCombo.setSelected(true);
        radioBtnCategoryCombo.setText("Category");

        lblSearchErrorCombo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSearchErrorCombo.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchErrorCombo.setText("No Search Results Found");

        comboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basic Phone", "Feature Phone", "Smartphone" }));

        javax.swing.GroupLayout panelSearchComboLayout = new javax.swing.GroupLayout(panelSearchCombo);
        panelSearchCombo.setLayout(panelSearchComboLayout);
        panelSearchComboLayout.setHorizontalGroup(
            panelSearchComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchComboLayout.createSequentialGroup()
                .addGroup(panelSearchComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchComboLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblSearchCombo)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSearchComboLayout.createSequentialGroup()
                        .addComponent(radioBtnCostPriceCombo)
                        .addGroup(panelSearchComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSearchComboLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnSellingPriceCombo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioBtnModelNameCombo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnCategoryCombo))
                            .addGroup(panelSearchComboLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(lblSearchErrorCombo))
                            .addGroup(panelSearchComboLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButtonSearchArrayCombo)))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panelSearchComboLayout.setVerticalGroup(
            panelSearchComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchComboLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelSearchComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchCombo)
                    .addComponent(comboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelSearchComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnCostPriceCombo)
                    .addComponent(radioBtnSellingPriceCombo)
                    .addComponent(radioBtnModelNameCombo)
                    .addComponent(radioBtnCategoryCombo))
                .addGap(15, 15, 15)
                .addComponent(lblSearchErrorCombo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSearchArrayCombo)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelCard.add(panelSearchCombo, "card3");

        panelCardSales.setPreferredSize(new java.awt.Dimension(370, 215));
        panelCardSales.setLayout(new java.awt.CardLayout());

        panelSearchSales.setBackground(new java.awt.Color(0, 0, 51));
        panelSearchSales.setMinimumSize(new java.awt.Dimension(370, 215));

        lblSearchSales.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchSales.setText("Search :");

        radioBtnTotal.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearchSales.add(radioBtnTotal);
        radioBtnTotal.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnTotal.setSelected(true);
        radioBtnTotal.setText("Total");

        jButtonSearchArraySales.setBackground(new java.awt.Color(0, 0, 51));
        jButtonSearchArraySales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSearchArraySales.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchArraySales.setText("Search");
        jButtonSearchArraySales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchArraySalesActionPerformed(evt);
            }
        });

        jButtonSearchCancelSales.setBackground(new java.awt.Color(0, 0, 51));
        jButtonSearchCancelSales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSearchCancelSales.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchCancelSales.setText("Cancel");

        radioBtnCustomerName.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearchSales.add(radioBtnCustomerName);
        radioBtnCustomerName.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnCustomerName.setText("Last Name");

        radioBtnModelNumberSales.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearchSales.add(radioBtnModelNumberSales);
        radioBtnModelNumberSales.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnModelNumberSales.setText("Model Number");

        radioBtnBrandSales.setBackground(new java.awt.Color(0, 0, 51));
        btnGroupSearchSales.add(radioBtnBrandSales);
        radioBtnBrandSales.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnBrandSales.setText("Brand");
        radioBtnBrandSales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnBrandSalesItemStateChanged(evt);
            }
        });

        lblSearchErrorSales.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSearchErrorSales.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchErrorSales.setText("No Search Results Found");

        javax.swing.GroupLayout panelSearchSalesLayout = new javax.swing.GroupLayout(panelSearchSales);
        panelSearchSales.setLayout(panelSearchSalesLayout);
        panelSearchSalesLayout.setHorizontalGroup(
            panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchSalesLayout.createSequentialGroup()
                .addGroup(panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchSalesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblSearchSales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSearchSales, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSearchSalesLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(radioBtnTotal)
                        .addGroup(panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchSalesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSearchArraySales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearchCancelSales)
                                .addGap(34, 34, 34))
                            .addGroup(panelSearchSalesLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(radioBtnCustomerName)
                                .addGap(18, 18, 18)
                                .addComponent(radioBtnModelNumberSales)
                                .addGap(18, 18, 18)))
                        .addComponent(radioBtnBrandSales))
                    .addGroup(panelSearchSalesLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(lblSearchErrorSales)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelSearchSalesLayout.setVerticalGroup(
            panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchSalesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchSales)
                    .addComponent(tfSearchSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnTotal)
                    .addComponent(radioBtnCustomerName)
                    .addComponent(radioBtnModelNumberSales)
                    .addComponent(radioBtnBrandSales))
                .addGap(15, 15, 15)
                .addComponent(lblSearchErrorSales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSearchArraySales)
                    .addComponent(jButtonSearchCancelSales))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelCardSales.add(panelSearchSales, "card2");

        panelSearchSalesCombo.setBackground(new java.awt.Color(0, 0, 51));
        panelSearchSalesCombo.setMinimumSize(new java.awt.Dimension(370, 215));

        lblSearchSalesCombo.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchSalesCombo.setText("Search :");

        radioBtnTotalCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchSalesCombo.add(radioBtnTotalCombo);
        radioBtnTotalCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnTotalCombo.setText("Total");
        radioBtnTotalCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnTotalComboItemStateChanged(evt);
            }
        });

        jButtonSearchArraySalesCombo.setBackground(new java.awt.Color(0, 0, 51));
        jButtonSearchArraySalesCombo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonSearchArraySalesCombo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchArraySalesCombo.setText("Smartphone of a Brand");
        jButtonSearchArraySalesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchArraySalesComboActionPerformed(evt);
            }
        });

        radioBtnCustomerNameCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchSalesCombo.add(radioBtnCustomerNameCombo);
        radioBtnCustomerNameCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnCustomerNameCombo.setText("Last Name");
        radioBtnCustomerNameCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnCustomerNameComboItemStateChanged(evt);
            }
        });

        radioBtnModelNumberSalesCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchSalesCombo.add(radioBtnModelNumberSalesCombo);
        radioBtnModelNumberSalesCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnModelNumberSalesCombo.setText("Model Number");
        radioBtnModelNumberSalesCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioBtnModelNumberSalesComboItemStateChanged(evt);
            }
        });

        radioBtnBrandSalesCombo.setBackground(new java.awt.Color(0, 0, 51));
        btnGrpSearchSalesCombo.add(radioBtnBrandSalesCombo);
        radioBtnBrandSalesCombo.setForeground(new java.awt.Color(255, 255, 255));
        radioBtnBrandSalesCombo.setSelected(true);
        radioBtnBrandSalesCombo.setText("Brand");

        lblSearchErrorSalesCombo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSearchErrorSalesCombo.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchErrorSalesCombo.setText("No Search Results Found");

        comboBoxSearchSales.setModel(new DefaultComboBoxModel <> (elementsForComboBoxBrand()));

        javax.swing.GroupLayout panelSearchSalesComboLayout = new javax.swing.GroupLayout(panelSearchSalesCombo);
        panelSearchSalesCombo.setLayout(panelSearchSalesComboLayout);
        panelSearchSalesComboLayout.setHorizontalGroup(
            panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchSalesComboLayout.createSequentialGroup()
                .addGroup(panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchSalesComboLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblSearchSalesCombo)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxSearchSales, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSearchSalesComboLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(radioBtnTotalCombo)
                        .addGap(14, 14, 14)
                        .addGroup(panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSearchSalesComboLayout.createSequentialGroup()
                                .addComponent(radioBtnCustomerNameCombo)
                                .addGap(18, 18, 18)
                                .addComponent(radioBtnModelNumberSalesCombo)
                                .addGap(18, 18, 18)
                                .addComponent(radioBtnBrandSalesCombo))
                            .addGroup(panelSearchSalesComboLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSearchArraySalesCombo)
                                    .addComponent(lblSearchErrorSalesCombo))))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelSearchSalesComboLayout.setVerticalGroup(
            panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchSalesComboLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchSalesCombo)
                    .addComponent(comboBoxSearchSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelSearchSalesComboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnTotalCombo)
                    .addComponent(radioBtnCustomerNameCombo)
                    .addComponent(radioBtnModelNumberSalesCombo)
                    .addComponent(radioBtnBrandSalesCombo))
                .addGap(15, 15, 15)
                .addComponent(lblSearchErrorSalesCombo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSearchArraySalesCombo)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelCardSales.add(panelSearchSalesCombo, "card2");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Appliances Info");
        setBackground(new java.awt.Color(255, 250, 250));
        setMinimumSize(new java.awt.Dimension(360, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelBg.setBackground(new java.awt.Color(0, 0, 51));

        panelButtons.setBackground(new java.awt.Color(0, 0, 51));
        panelButtons.setForeground(new java.awt.Color(0, 0, 102));

        btnEdit.setBackground(new java.awt.Color(0, 0, 51));
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnInvoice.setBackground(new java.awt.Color(0, 0, 51));
        btnInvoice.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnInvoice.setForeground(new java.awt.Color(255, 255, 255));
        btnInvoice.setText("Invoice");
        btnInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoiceActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 0, 51));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.setToolTipText("Click Inventory/Sales to Add entries to them.");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 0, 51));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 0, 51));
        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonsLayout = new javax.swing.GroupLayout(panelButtons);
        panelButtons.setLayout(panelButtonsLayout);
        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonsLayout.createSequentialGroup()
                        .addGroup(panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInvoice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnAdd)
                .addGap(26, 26, 26)
                .addComponent(btnEdit)
                .addGap(28, 28, 28)
                .addComponent(btnDelete)
                .addGap(34, 34, 34)
                .addComponent(btnInvoice)
                .addGap(32, 32, 32)
                .addComponent(btnSearch))
        );

        tabbedPaneTbl.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneTblStateChanged(evt);
            }
        });

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Model Number", "Name", "Brand", "Category", "Recommendation", "Cost Price", "Selling Price", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventory.setRowHeight(20);
        scrlPaneInventory.setViewportView(tblInventory);

        javax.swing.GroupLayout tblPnlInventoryLayout = new javax.swing.GroupLayout(tblPnlInventory);
        tblPnlInventory.setLayout(tblPnlInventoryLayout);
        tblPnlInventoryLayout.setHorizontalGroup(
            tblPnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPaneInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        tblPnlInventoryLayout.setVerticalGroup(
            tblPnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPaneInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabbedPaneTbl.addTab("Appliances Information System", tblPnlInventory);

        tblSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "First Name", "Last Name", "Date", "Model Number", "Brand", "Quantity", "Discount", "Total Sales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSales.setRowHeight(20);
        scrlPaneSales.setViewportView(tblSales);

        javax.swing.GroupLayout tblPnlSalesLayout = new javax.swing.GroupLayout(tblPnlSales);
        tblPnlSales.setLayout(tblPnlSalesLayout);
        tblPnlSalesLayout.setHorizontalGroup(
            tblPnlSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPaneSales, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        tblPnlSalesLayout.setVerticalGroup(
            tblPnlSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPaneSales, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabbedPaneTbl.addTab("Sales Information System", tblPnlSales);

        javax.swing.GroupLayout panelBgLayout = new javax.swing.GroupLayout(panelBg);
        panelBg.setLayout(panelBgLayout);
        panelBgLayout.setHorizontalGroup(
            panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBgLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBgLayout.setVerticalGroup(
            panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBgLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(tabbedPaneTbl, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMnuFile.setText("File");

        jMnuItmNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMnuItmNew.setText("New");
        jMnuItmNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmNewActionPerformed(evt);
            }
        });
        jMnuFile.add(jMnuItmNew);

        jMnuItmOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMnuItmOpen.setText("Import/Open");
        jMnuItmOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmOpenActionPerformed(evt);
            }
        });
        jMnuFile.add(jMnuItmOpen);

        jMnuItmSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMnuItmSave.setText("Save");
        jMnuItmSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmSaveActionPerformed(evt);
            }
        });
        jMnuFile.add(jMnuItmSave);

        jMnuItmSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMnuItmSaveAs.setText("Save As");
        jMnuItmSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmSaveAsActionPerformed(evt);
            }
        });
        jMnuFile.add(jMnuItmSaveAs);

        mnuBarEmployee.add(jMnuFile);

        jMnuTools.setText("Tools");

        jMnuItmAddBrand.setText("Add Brand");
        jMnuItmAddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmAddBrandActionPerformed(evt);
            }
        });
        jMnuTools.add(jMnuItmAddBrand);

        jMnuItmRemoveBrand.setText("Remove Brand");
        jMnuItmRemoveBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmRemoveBrandActionPerformed(evt);
            }
        });
        jMnuTools.add(jMnuItmRemoveBrand);

        mnuBarEmployee.add(jMnuTools);

        jMnuAbt.setText("About");

        jMnuItmAbout.setText("About");
        jMnuItmAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmAboutActionPerformed(evt);
            }
        });
        jMnuAbt.add(jMnuItmAbout);

        jMnuItmHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMnuItmHelp.setText("Help");
        jMnuItmHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmHelpActionPerformed(evt);
            }
        });
        jMnuAbt.add(jMnuItmHelp);

        jMnuItmExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMnuItmExit.setText("Exit");
        jMnuItmExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmExitActionPerformed(evt);
            }
        });
        jMnuAbt.add(jMnuItmExit);

        mnuBarEmployee.add(jMnuAbt);

        setJMenuBar(mnuBarEmployee);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
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
            java.util.logging.Logger.getLogger(AppliancesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppliancesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppliancesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppliancesInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppliancesInfo().setVisible(true);
            }
        });
    }

    
    private void jMnuItmExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmExitActionPerformed
        if(isSaved) {
            System.exit(0);
        }
        else {
            int confirmation = JOptionPane.showConfirmDialog(rootPane, "Unsaved changes. Save?", "Overwrite?", JOptionPane.YES_NO_OPTION);
            switch (confirmation){
                case JOptionPane.YES_OPTION:
                    jMnuItmSaveActionPerformed(evt);
                    break;
                case JOptionPane.NO_OPTION:
                    AppliancesInfo dash = new AppliancesInfo();
                    dash.jMnuItmNewActionPerformed(evt);
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        }
    }//GEN-LAST:event_jMnuItmExitActionPerformed

    private void jMnuItmAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmAboutActionPerformed
        jDialogAbout.setLocationRelativeTo(null);
        jDialogAbout.setSize(300,200);
        jDialogAbout.setVisible(true);
    }//GEN-LAST:event_jMnuItmAboutActionPerformed
    
    private void clearApplicationData(){
        clearTableInventory();
            clearTableSales();
        arraylistInventory.clear();
        arraylistSales.clear();
        comboBoxModelNumber.removeAllItems();
        comboBoxModelNumberEdit.removeAllItems();
            
        
    
    }
    private void jMnuItmOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmOpenActionPerformed
       if(isSaved) {
            clearApplicationData();
            Utility util = new Utility();
            String dir = getCSVFilesDirectory();
            try {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
                JFileChooser jFileSaver = new JFileChooser(dir);
                jFileSaver.setDialogTitle("Save as");
                jFileSaver.setDialogType(JFileChooser.SAVE_DIALOG);
                jFileSaver.setFileFilter(filter);
        // Stuff like setting the required file extension, the title, ...
                int result = jFileSaver.showSaveDialog(rootPane);
                if (result == JFileChooser.APPROVE_OPTION) {
                    if (!path.contains("sales.csv")) {
                        String pathSales = path.replace(".csv", "_sales.csv");
                        File fileSale = new File(pathSales);

                        csvReaderInventory(path);
                        arraylistInventory = util.sortInventoryByCost(arraylistInventory);
                        addToInventoryTable();
                        if(fileSale.exists()) {
                            csvReaderSales(pathSales);
                            addToSalesTable();
                        }
                    }
                    else {
                        String pathSales = path;
                        String pathInventory = path.replace("_sales.csv", ".csv");
                        path = pathInventory;
                        File fileInventory = new File(path);
                        csvReaderInventory(path);
                        arraylistInventory = util.sortInventoryByCost(arraylistInventory);
                        csvReaderSales(pathSales);
                        addToInventoryTable();
                        addToSalesTable();  
                    }
                }
                
            }
            catch(NullPointerException npe) {

            }
            
            
        }
        else {
            int confirmation = JOptionPane.showConfirmDialog(rootPane, "Unsaved changes. Save?", "Save?", JOptionPane.YES_NO_OPTION);
            switch (confirmation){
                case JOptionPane.YES_OPTION:
                    jMnuItmSaveActionPerformed(evt);
                    break;
                case JOptionPane.NO_OPTION:
                    isSaved = true;
                    clearTableInventory();
                    clearTableSales();
                    arraylistInventory.clear();
                    arraylistSales.clear();
                    jMnuItmOpenActionPerformed(evt);
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        }
    }//GEN-LAST:event_jMnuItmOpenActionPerformed
       private String getCSVFilesDirectory(){
       String dir = System.getProperty("user.dir");
            if (System.getProperty("os.name").contains("Windows")) {
                dir += "\\resources\\CSV Files";
            }
            else{
                dir += "/resources/CSV Files";
            }
            return dir;
       }
    private void jMnuItmSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmSaveAsActionPerformed
        String dir =getCSVFilesDirectory();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
        JFileChooser jFileSaver = new JFileChooser(dir);
        jFileSaver.setDialogTitle("Save as");
        jFileSaver.setDialogType(JFileChooser.SAVE_DIALOG);
        jFileSaver.setFileFilter(filter);
        // Stuff like setting the required file extension, the title, ...
        int result = jFileSaver.showSaveDialog(rootPane);
        if (result == JFileChooser.APPROVE_OPTION) {
            path = jFileSaver.getSelectedFile().toString();
            if (!path.contains(".csv")) {
                path+=".csv";
            }
            File newFile = new File(path);
            boolean check;
            try{
                check = newFile.createNewFile();
                if (check) {
                    Utility util = new Utility();
                    util.csvWriter(path, arraylistInventory, arraylistSales);
                    isSaved = true;
                    JOptionPane.showMessageDialog(rootPane, "Saved successfully.", "Saved", JOptionPane.PLAIN_MESSAGE);    
                }
                else {
                   int confirmation = JOptionPane.showConfirmDialog(rootPane, "File already exists. Overwrite?", "Overwrite?", JOptionPane.YES_NO_OPTION);
                   switch (confirmation){
                        case JOptionPane.YES_OPTION:
                            Utility util = new Utility();
                            util.csvWriter(path, arraylistInventory, arraylistSales);
                            isSaved = true;
                            break;
                        case JOptionPane.NO_OPTION:
                            AppliancesInfo dash = new AppliancesInfo();
                            dash.jMnuItmNewActionPerformed(evt);
                            break;
                        case JOptionPane.CLOSED_OPTION:
                            break;
                   }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMnuItmSaveAsActionPerformed

    private void jMnuItmNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmNewActionPerformed
        if(isSaved) {
            clearApplicationData();
            
            clearTableInventory();
            clearTableSales();
            arraylistInventory.clear();
            arraylistSales.clear();
            comboBoxModelNumber.removeAllItems();
            isNew = true;
        }
        else {
            int confirmation = JOptionPane.showConfirmDialog(rootPane, "Unsaved changes. Save?", "Save?", JOptionPane.YES_NO_OPTION);
            switch (confirmation){
                case JOptionPane.YES_OPTION:
                    isSaved = true;
                    clearTableInventory();
                    clearTableSales();
                    arraylistInventory.clear();
                    arraylistSales.clear();
                    comboBoxModelNumber.removeAllItems();
                    jMnuItmSaveActionPerformed(evt);
                    break;
                case JOptionPane.NO_OPTION:
                    isSaved = true;
                    clearTableInventory();
                    clearTableSales();
                    arraylistInventory.clear();
                    arraylistSales.clear();
                    comboBoxModelNumber.removeAllItems();
                    isNew = true;
                    jMnuItmNewActionPerformed(evt);
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        }
    }//GEN-LAST:event_jMnuItmNewActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!isWindowOpen) {
                if (isInventoryTableSelected == true) {
                if (arraylistInventory.size() < 12) {
                    frameAddInventory.setVisible(true);
                    frameAddInventory.setLocationRelativeTo(null);
                    openedWindow = 1;
                    isWindowOpen = true;
                }
                else {
                    int confirmation = JOptionPane.showConfirmDialog(rootPane, "Max limit for inventory table reached. Create new file?", "New File?", JOptionPane.YES_NO_OPTION);
                       switch (confirmation){
                            case JOptionPane.YES_OPTION:
                                jMnuItmNewActionPerformed(evt);
                                break;
                            case JOptionPane.NO_OPTION:
                                break;
                            case JOptionPane.CLOSED_OPTION:
                                break;
                       }
                }
            }
            else {
                if (!arraylistInventory.isEmpty()) {
                    if (arraylistSales.size() < 12) {
                        for(InventoryManagement im:arraylistInventory){
                            comboBoxModelNumber.addItem(im.getModelNo());
                        }
                        frameAddSales.setVisible(true);                    
                        frameAddSales.setLocationRelativeTo(null);
                        openedWindow = 2;
                        isWindowOpen = true;
                    }
                    else {
                        int confirmation = JOptionPane.showConfirmDialog(rootPane, "Max limit for sales table reached. Create new file?", "New File?", JOptionPane.YES_NO_OPTION);
                           switch (confirmation){
                                case JOptionPane.YES_OPTION:
                                    jMnuItmNewActionPerformed(evt);
                                    break;
                                case JOptionPane.NO_OPTION:
                                    break;
                                case JOptionPane.CLOSED_OPTION:
                                    break;
                           }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(rootPane,"There is nothing in the inventory.", "Error!", 0);
                }
            }
        }
        else {
            windowOpenedError();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void jButtonClearInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearInventoryActionPerformed
        clearInventoryForm();
    }//GEN-LAST:event_jButtonClearInventoryActionPerformed

    private void jMnuItmSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmSaveActionPerformed
        if (path != "" && path != null) {
            Utility util = new Utility();
            util.csvWriter(path, arraylistInventory, arraylistSales);
            isSaved = true;
            JOptionPane.showMessageDialog(rootPane, "Saved.", "Success", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            jMnuItmSaveAsActionPerformed(evt); 
        }
    }//GEN-LAST:event_jMnuItmSaveActionPerformed

    private void btnInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoiceActionPerformed
        if (!isWindowOpen) {
            if (!arraylistSales.isEmpty()) {
                if(tblSales.getSelectedRow() >= 0) {
                    int selection = tblSales.getSelectedRow();
                    Sales sales = arraylistSales.get(selection);
                    String firstName = null;
                    String lastName = null;
                    String date = null;
                    String modelNumber = null;
                    String brand = null;
                    String quantity = null;
                    String discount = null;
                    String total = null;
                    try {
                        firstName = sales.getFirstName();
                        lastName = sales.getLastName();
                        date = sales.getDate();
                        modelNumber = sales.getModelNumber();
                        brand = sales.getBrand();
                        quantity = String.valueOf(sales.getQuantity());
                        discount = String.valueOf(sales.getDiscount()) + "%";
                        total = String.valueOf(sales.getTotal());
                    }
                    catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(rootPane, npe, "Error!", 0); 
                        return;
                    }
                    lblSetFirstName.setText(firstName);
                    lblSetLastName.setText(lastName);
                    lblSetDate.setText(date);
                    lblSetModelNumber.setText(modelNumber);
                    lblSetBrand.setText(brand);
                    lblSetDiscount.setText(discount);
                    lblSetQuantity.setText(quantity);
                    lblSetTotal.setText(total);
                    frameInvoice.setVisible(true);
                    frameInvoice.setLocationRelativeTo(null);
                    isWindowOpen = true;
                    openedWindow = 5;
                }
                else {
                JOptionPane.showMessageDialog(rootPane, "No row is selected in the sales table.", "Error!", 0);
                }
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "No entry in  the sales list to create an invoice.", "Error!", 0);
            }
        }
        else {
            windowOpenedError();
        }
    }//GEN-LAST:event_btnInvoiceActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (!isWindowOpen) {
            if (isInventoryTableSelected == true) {
                indexEditInv = tblInventory.getSelectedRow();
                if (indexEditInv >= 0) {
                    lblEditInvMsg.setVisible(false);
                    frameEditInventory.setVisible(true);
                    frameEditInventory.setLocationRelativeTo(null);
                    isWindowOpen = true;
                    openedWindow = 3;
                    InventoryManagement imOld = arraylistInventory.get(indexEditInv);
                    String modelNoOld = imOld.getModelNo();
                    String modelNameOld = imOld.getModelName();
                    String brandOld = imOld.getBrand();
                    String categoryOld = imOld.getCategory();
                    String recommendationOld = imOld.getRecommendation();
                    String costOld = String.valueOf(imOld.getCost());
                    String sellingPriceOld = String.valueOf(imOld.getSellingPrice());
                    String quantityOld = String.valueOf(imOld.getQuantity());

                    tfEditInvModelNo.setText(modelNoOld);
                    tfEditInvModelName.setText(modelNameOld);               
                    comboEditInvBrand.setSelectedItem(brandOld);
                    comboEditInvCategory.setSelectedItem(categoryOld);

                    if (recommendationOld.equals("High")) {
                        radioBtnEditInvHigh.setSelected(true);
                    }
                    else if (recommendationOld.equals("Mid")) {
                        radioBtnEditInvMid.setSelected(true);
                    }
                    else {
                        radioBtnEditInvLow.setSelected(true);
                    }

                    tfEditInvCostPrice.setText(costOld);               
                    tfEditInvSellingPrice.setText(sellingPriceOld);
                    tfEditInvQuantity.setText(quantityOld);
                    isSaved = false;
                }
                else {
                    JOptionPane.showMessageDialog(rootPane, "Please select row to edit.", "Error", 0);
                }
            }
            else {
                isEditSales = true;
                indexEditSales = tblSales.getSelectedRow();
                for(InventoryManagement im:arraylistInventory){
                    comboBoxModelNumberEdit.addItem(im.getModelNo());
                }
                if (indexEditSales >= 0) {
                   lblEditInvMsgSales.setVisible(false);
                   frameEditSales.setVisible(true);
                   frameEditSales.setLocationRelativeTo(null);
                   isWindowOpen = true;
                   openedWindow = 4;
                   Sales sales = arraylistSales.get(indexEditSales);
                   String firstNameOld = sales.getFirstName();
                   String lastNameOld = sales.getLastName();
                   String dateOld = sales.getDate();
                   String modelNumberOld = sales.getModelNumber();
                   String brandOld = sales.getBrand();
                   String discountOld = String.valueOf(sales.getDiscount());
                   String quantityOld = String.valueOf(sales.getQuantity());
                   String totalOld = String.valueOf(sales.getTotal());

                   tfFirstNameEdit.setText(firstNameOld);
                   tfLastNameEdit.setText(lastNameOld);
                   tfDateEdit.setText(dateOld);
                   comboBoxModelNumberEdit.setSelectedItem(modelNumberOld);
                   comboBoxBrandSalesEdit.setSelectedItem(brandOld);
                   tfQuantitySalesEdit.setText(quantityOld);
                   comboBoxDiscountEdit.setSelectedItem(discountOld);
                   tfTotal.setText(totalOld);
                }
                else {
                    JOptionPane.showMessageDialog(rootPane, "Please select row to edit.", "Error", 0);
                }
            }
        }
        else {
            windowOpenedError();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void tfCostPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCostPriceKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfCostPriceKeyTyped

    private void tfQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQuantityKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfQuantityKeyTyped

    private void tfQuantitySalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQuantitySalesKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfQuantitySalesKeyTyped

    private void tfDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDateKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE || key == '-'|| key=='/' || key=='.')) {
           
            
            evt.consume();
        }
    }//GEN-LAST:event_tfDateKeyTyped

    private void tabbedPaneTblStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneTblStateChanged
        if (tabbedPaneTbl.getSelectedIndex() == 0) {
            isInventoryTableSelected = true;
        }
        else {
            isInventoryTableSelected = false;
        }
    }//GEN-LAST:event_tabbedPaneTblStateChanged

    private Object[] doesModelOfSameBrandExist(String modelNo,String brand){
        Object[] data=new Object[2];
      
        for(int i = 0; i < arraylistInventory.size(); i++) {
            InventoryManagement im = arraylistInventory.get(i);
            if (im.getModelNo().equalsIgnoreCase(modelNo) && im.getBrand().equalsIgnoreCase(brand)) {
                data[0]=true;
                data[1]=i;
                return data;  
            }
        }
          data[0]=false;
          data[1]=-1;
          return data;
    }
    
    private void jButtonAddInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddInventoryActionPerformed
        String modelNo = tfModelNo.getText().trim();
        String modelName = tfModelName.getText().trim();
        String brand = null;
        String category = null;
        String recommendation = null;
        Utility util = new Utility();
        int costPrice = 0;
        int sellingPrice = 0;
        int quantity = 0;
        if(modelNo.isEmpty() ||modelName.isEmpty() || tfCostPrice.getText().isEmpty() || tfSellingPrice.getText().trim().isEmpty() ||tfQuantity.getText().trim().isEmpty() ){
            JOptionPane.showMessageDialog(frameAddInventory, "Empty fields found! Please check your input!", "Input Error!", JOptionPane.ERROR_MESSAGE);
        }
        else if(!modelNo.matches("[a-zA-Z0-9]*")){
            JOptionPane.showMessageDialog(frameAddInventory, "Model Number can only have alphabet and number!", "Input Error!", JOptionPane.ERROR_MESSAGE);
        
        }else if(!modelName.matches("[a-zA-Z0-9]*")){
        JOptionPane.showMessageDialog(frameAddInventory, "Model Number can only have alphabet and number!", "Input Error!", JOptionPane.ERROR_MESSAGE);
        }
        else{
        try{
            brand = comboBoxBrand.getSelectedItem().toString().trim();
            category = comboCategory.getSelectedItem().toString().trim();
            recommendation = radioBtnHigh.isSelected() == true ? "High" : radioBtnMid.isSelected() == true ? "Mid" : radioBtnLow.isSelected() == true ? "Low" : null;
            costPrice = Integer.parseInt(tfCostPrice.getText().trim());
            sellingPrice = Integer.parseInt(tfSellingPrice.getText().trim());
            quantity = Integer.parseInt(tfQuantity.getText().trim()); 
            
            if (arraylistInventory.size() >= 0 && arraylistInventory.size() < 12) {
                int index = -1;
                boolean modelExists=false;
                if(arraylistInventory.size()!=0){
                    Object[] doesModelExist = doesModelOfSameBrandExist(modelNo,brand);
                    modelExists = Boolean.parseBoolean(doesModelExist[0].toString());
                    index = Integer.parseInt(doesModelExist[1].toString());
                }
                if (!modelExists) {
                    InventoryManagement im = new InventoryManagement(modelNo, modelName, brand,category, recommendation, costPrice, sellingPrice, quantity);
                    arraylistInventory.add(im);
                    arraylistInventory = util.sortInventoryByCost(arraylistInventory);
                    clearTableInventory();
                    addToInventoryTable();
                    if(arraylistInventory.size()<12){
                        int confirmation = JOptionPane.showConfirmDialog(frameAddInventory, "Successfully Added the data,Do you want to add more?", "New?", JOptionPane.YES_NO_OPTION);
                        switch (confirmation){
                        case JOptionPane.YES_OPTION:
                            clearInventoryForm();
                            return;
                        case JOptionPane.NO_OPTION:
                            clearInventoryForm();
                            frameAddInventory.dispose();
                            break;
                        case JOptionPane.CLOSED_OPTION:
                            break;
                    }
                    
                    }else{
                        JOptionPane.showMessageDialog(frameAddInventory, "Successfully Added the data! Cannot add more data!", "Limit Reached", JOptionPane.INFORMATION_MESSAGE);
                    
                    }
                    
                }
                else {
                    if (index != -1) {
                        InventoryManagement im = arraylistInventory.get(index);
                        int newQuantity = im.getQuantity() + quantity;
                        
                        JOptionPane.showMessageDialog(frameAddInventory, modelNo + " of the " + brand + " was already in the records.\nOnly Adding the quantity to the existing records.", "Already exists", JOptionPane.INFORMATION_MESSAGE);
                        im.setQuantity(newQuantity);
                        clearTableInventory();
                        addToInventoryTable();
                        int confirmation = JOptionPane.showConfirmDialog(frameAddInventory, "Do you want to add more?", "New?", JOptionPane.YES_NO_OPTION);
                        switch (confirmation){
                            case JOptionPane.YES_OPTION:
                                clearInventoryForm();
                                return;
                            case JOptionPane.NO_OPTION:
                                clearInventoryForm();
                                frameAddInventory.dispose();
                                break;
                            case JOptionPane.CLOSED_OPTION:
                                clearInventoryForm();
                                frameAddInventory.dispose();
                                break;
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(frameAddInventory, "Unexpected Error. Check your inputs.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        isSaved = false;
        isNew=false;
        
        frameAddInventory.dispose();
            
            
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(frameAddInventory, "Please enter valid number in number fields.", "Error!", 0);
            return;
        }
    }  
    }//GEN-LAST:event_jButtonAddInventoryActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (isInventoryTableSelected == true) {
            if (tblInventory.getSelectedRow() >= 0) {
                int confirmation = JOptionPane.showConfirmDialog(rootPane, "Index " + (tblInventory.getSelectedRow() + 1) + " of Inventory table will be deleted", "Delete?", JOptionPane.YES_NO_OPTION);
                switch (confirmation){
                    case JOptionPane.YES_OPTION:
                        arraylistInventory.remove(tblInventory.getSelectedRow());
                        clearTableInventory();
                        addToInventoryTable();
                        isSaved = false;
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "No row selected.", "Error", 0);
            }
        }
        else {
            if (tblSales.getSelectedRow() >= 0) {
                int confirmation = JOptionPane.showConfirmDialog(rootPane, "Index " + (tblSales.getSelectedRow() + 1) + " of Sales table will be deleted", "Delete?", JOptionPane.YES_NO_OPTION);
                switch (confirmation){
                    case JOptionPane.YES_OPTION:
                        arraylistSales.remove(tblSales.getSelectedRow());
                        clearTableInventory();
                        addToInventoryTable();
                        isSaved = false;
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "No row selected.", "Error", 0);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (!isWindowOpen) {
            if(!arraylistInventory.isEmpty() && isInventoryTableSelected == true) {
                lblSearchErrorCombo.setVisible(false);
                lblSearchError.setVisible(false);
                frameSearch.setVisible(true);
                frameSearch.setLocationRelativeTo(null);
                isWindowOpen = true;
                openedWindow = 6;

            }
            else if (!arraylistSales.isEmpty() && isInventoryTableSelected == false) {
                lblSearchErrorSalesCombo.setVisible(false);
                lblSearchErrorSales.setVisible(false);
                frameSearchSales.setVisible(true);
                frameSearchSales.setLocationRelativeTo(null);
                isWindowOpen = true;
                openedWindow = 7;
            }
            else if (arraylistSales.isEmpty() && isInventoryTableSelected == true) {
                JOptionPane.showMessageDialog(rootPane, "There are no inventory data to perform search operation.", "Error", 0);
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "There are no sales data to perform search operation.", "Error", 0);
            }
        }
        else {
            windowOpenedError();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tfSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSellingPriceKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfSellingPriceKeyTyped

    private String getStringFromTextfield(JTextField tfName){
        if(tfName.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Empty text field.\n", "Error!", 0);
            return null;
        }
        return tfName.getText().trim();
    }
    private int getIntFromTextField(JTextField tfName){
        int data = 0;    
        if (!tfName.getText().trim().isEmpty()) {
            try{
                data = Integer.parseInt(tfName.getText().trim());
            }
            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(rootPane, "Please provide a valid number as input.\n", "Error!", 0);
                data= -1;
                
                }
            }
        else {
            JOptionPane.showMessageDialog(rootPane, "Empty text field.\n", "Error!", 0);
            data= -1;
        }
        return data;
    }
    private void jButtonSearchArrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchArrayActionPerformed
        Utility util = new Utility();
        String searchSelection = radioBtnCostPrice.isSelected() == true ? "Cost" : radioBtnSellingPrice.isSelected() == true ? "Selling" : radioBtnModelName.isSelected() == true ? "Model" : radioBtnBrand.isSelected() == true? "Brand" : null;
        if(searchSelection.equals("Cost")) { 
            int search = getIntFromTextField(tfSearch);
            if(search > -1){
                int index = binarySearch(true, search);
                if (index == -1) {
                    lblSearchError.setVisible(true);
                    TimerTask task = new TimerTask () {
                        public void run() {
                        lblSearchError.setVisible(false);
                        }
                    };
                    long delay = 1000L;
                    Timer timer = new Timer("Timer#1");
                    timer.schedule(task, delay);  
                }
                else {
                    frameSearch.dispose();
                    tfSearch.setText(null);
                    InventoryManagement im = arraylistInventory.get(index);
                    JOptionPane.showMessageDialog(rootPane,"Model Number : " + im.getModelNo()+"\nModel Name : " + im.getModelName() +"\nCategory : "+im.getCategory()+"\nBrand Name : "+im.getBrand()+"\nCost Price : "+im.getCost(), "Search results", 1);
                }
            }
        }
        else if (searchSelection.equals("Selling")) {
            int search = getIntFromTextField(tfSearch);
            if(search > -1){
                temp = util.sortInventoryTemp(arraylistInventory, temp);
                int index = binarySearch(false, search);
                if (index == -1) {
                    lblSearchError.setVisible(true);
                    TimerTask task = new TimerTask () {
                        public void run() {
                        lblSearchError.setVisible(false);
                        }
                    };
                    long delay = 1000L;
                    Timer timer = new Timer("Timer#1");
                    timer.schedule(task, delay);  
                }
                else {
                    frameSearch.dispose();
                    tfSearch.setText(null);
                    InventoryManagement im = arraylistInventory.get(index);
                    JOptionPane.showMessageDialog(rootPane,"Model Number : " + im.getModelNo()+"\nModel Name : " + im.getModelName()+"\nCategory : "+im.getCategory() +"\nBrand Name : "+im.getBrand()+"\nCost Price : "+im.getCost(), "Search results", 1);
                }
            }        
        }
        else {
            List <Integer> listInt = new ArrayList <>();
            String search = getStringFromTextfield(tfSearch);
            if(search!=null){
                listInt = util.searchInventory(false, search, arraylistInventory);
                if(!listInt.isEmpty()) {
                    int [] index = listInt.stream().mapToInt(i -> i).toArray();
                    frameSearch.dispose();
                    util.showSearchResultsInventory(index, arraylistInventory);
                    tfSearch.setText(null);
                }
                else {
                    lblSearchError.setVisible(true);
                    TimerTask task = new TimerTask () {
                        public void run() {
                        lblSearchError.setVisible(false);
                        }
                    };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
                }
            }
        }                       
    }//GEN-LAST:event_jButtonSearchArrayActionPerformed

    private void lblSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblSellingPriceKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_lblSellingPriceKeyTyped

    private void tfEditInvCostPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEditInvCostPriceKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfEditInvCostPriceKeyTyped

    private void jButtonEditInventorySaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditInventorySaveActionPerformed
        String modelNo = tfEditInvModelNo.getText().trim();
        String modelName = tfEditInvModelName.getText().trim();
        String brand = comboEditInvBrand.getSelectedItem().toString().trim();
        String category = comboEditInvCategory.getSelectedItem().toString().trim();
        String recommendation = radioBtnEditInvHigh.isSelected() == true ? "High" : radioBtnEditInvMid.isSelected() == true ? "Mid" : radioBtnEditInvLow.isSelected() == true ? "Low" : null;
        int costPrice = Integer.parseInt(tfEditInvCostPrice.getText().trim());
        int sellingPrice = Integer.parseInt(tfEditInvSellingPrice.getText().trim());
        int quantity = Integer.parseInt(tfEditInvQuantity.getText().trim());
        
        InventoryManagement im = arraylistInventory.get(indexEditInv);
        if (modelNo.equalsIgnoreCase(im.getModelNo()) && modelName.equalsIgnoreCase(im.getModelName()) && brand.equalsIgnoreCase(im.getBrand()) && 
        category.equalsIgnoreCase(im.getCategory()) && recommendation.equalsIgnoreCase(im.getRecommendation()) && costPrice == im.getCost() && sellingPrice == im.getSellingPrice() &&
        quantity == im.getQuantity()) {
            lblEditInvMsg.setVisible(true);
            TimerTask task = new TimerTask () {
                public void run() {
                    lblEditInvMsg.setVisible(false);
                }
            };
            long delay = 1000L;
            Timer timer = new Timer("Timer#1");
            timer.schedule(task, delay);
        }
        else {
            im.setModelNo(modelNo);
            im.setModelName(modelName);
            im.setBrand(brand);
            im.setCategory(category);
            im.setRecommendation(recommendation);
            im.setCost(costPrice);
            im.setSellingPrice(sellingPrice);
            im.setQuantity(quantity);
            clearTableInventory();
            addToInventoryTable();
            isSaved = false;
            frameEditInventory.dispose();
            JOptionPane.showMessageDialog(rootPane, "Edit Saved.", "Success!", 1);
        }
    }//GEN-LAST:event_jButtonEditInventorySaveActionPerformed

    private void jButtonEditInventoryCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditInventoryCancelActionPerformed
        frameEditInventory.dispose();
    }//GEN-LAST:event_jButtonEditInventoryCancelActionPerformed

    private void tfEditInvQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEditInvQuantityKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfEditInvQuantityKeyTyped

    private void tfEditInvSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEditInvSellingPriceKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfEditInvSellingPriceKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(isSaved) {
            int confirmation = JOptionPane.showConfirmDialog(rootPane, "You are exiting the application.", "Exit?", JOptionPane.YES_NO_OPTION);
            switch (confirmation){
                case JOptionPane.YES_OPTION:
                    System.exit(0);
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        }
        else {
            int confirmation = JOptionPane.showConfirmDialog(rootPane, "Unsaved changes. Save?", "Save?", JOptionPane.YES_NO_OPTION);
            switch (confirmation){
                case JOptionPane.YES_OPTION:
                    java.awt.event.ActionEvent evt1 = null;
                    jMnuItmSaveActionPerformed(evt1);
                    System.exit(0);
                    break;
                case JOptionPane.NO_OPTION:
                    dispose();
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMnuItmAddBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmAddBrandActionPerformed
        String newBrand = null;
        String dir = System.getProperty("user.dir");
        
        if (System.getProperty("user.dir").equalsIgnoreCase("Windows")) {
            dir += "\\resources\\Brands\\brands.txt";
        }
        else {
            dir += "/resources/Brands/brands.txt";
        }
        try {
            newBrand = JOptionPane.showInputDialog(rootPane, "Enter New Brand", "Add Brand", JOptionPane.INFORMATION_MESSAGE).trim();
            
            if(!newBrand.matches("^[a-zA-Z]*$") || newBrand.trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Brand name can only have alphabets,Please enter valid brand name!", "Error!", JOptionPane.ERROR_MESSAGE);
            jMnuItmAddBrandActionPerformed(evt);
            }
            else {
                if(!doesBrandExists(newBrand)){
                
                
                String capBrand = newBrand.substring(0, 1).toUpperCase() + newBrand.substring(1);

                BufferedWriter bw = new BufferedWriter(new FileWriter(dir, true));
                bw.newLine();
                bw.write(capBrand);
                bw.close();
                JOptionPane.showMessageDialog(rootPane, capBrand + " successfully added to brands.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                elementsForComboBoxBrand();
                comboBoxBrand.addItem(capBrand);
                comboBoxBrand.revalidate();
                comboBoxBrand.repaint();
                comboBoxBrandSales.addItem(capBrand);
                comboBoxBrandSales.revalidate();
                comboBoxBrandSales.repaint();
                comboBoxSearch.addItem(capBrand);
                comboBoxSearch.revalidate();
                comboBoxSearch.repaint();
                comboBoxSearchSales.addItem(capBrand);
                comboBoxSearchSales.revalidate();
                comboBoxSearchSales.repaint();
            }else{
                    JOptionPane.showMessageDialog(rootPane, "This brand has been already added to the list", "Success!", JOptionPane.INFORMATION_MESSAGE);
                
                }
            
            }
        }
        catch (IOException ie) {
                JOptionPane.showMessageDialog(rootPane, "Something went wrong! Cannot write to file!" + ie, "Error", 0);
                return;
        }
        catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_jMnuItmAddBrandActionPerformed
        
    private boolean doesBrandExists(String newBrandName){
        for(int i =0;i< comboBoxBrand.getComponentCount();i++){ 
            if( comboBoxBrand.getItemAt(i).equalsIgnoreCase(newBrandName) ){
                    return true;
            }
        }
        return false;
    }
    
    private void jBtnAddSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddSalesActionPerformed
        String firstName = null;
        String lastName = null;
        String date = null;
        String modelNumber = null;
        String brand = null;
        int quantity = 0;
        int discount = 0;
        int total = 0;
        try{
            firstName = tfFirstName.getText().trim();
            lastName = tfLastName.getText().trim();
            date = tfDate.getText().trim();
            modelNumber = comboBoxModelNumber.getSelectedItem().toString();
            brand = comboBoxBrandSales.getSelectedItem().toString();
            quantity = Integer.parseInt(tfQuantitySales.getText().trim());
            discount = Integer.parseInt(comboBoxDiscount.getSelectedItem().toString());
            total = Integer.parseInt(tfTotal.getText());
        }
        catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(frameAddSales, "Please enter all the inputs.\n" + npe, "Error!", 0);
            return;
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(frameAddSales, "Error in quantity text field.\n" + nfe, "Error!", 0);
            return;
        }
        // using regex to match patterns and determine whether the input is date or not
        String datePattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        if (!date.matches(datePattern)){
          JOptionPane.showMessageDialog(frameAddSales, "Please enter a valid Date!\nValid formats are dd-mm-yyyy, dd.mm.yyyy, dd/mm/yyyy", "Error!", 0);
          return;
        }
        else{
            if (arraylistSales.size() > 0 && arraylistSales.size() < 12) {
                for(InventoryManagement im : arraylistInventory) {
                    if(im.getModelNo().equalsIgnoreCase(modelNumber) && im.getBrand().equalsIgnoreCase(brand)) {
                        if(im.getQuantity() >= quantity) {
                            Sales sales = new Sales(firstName, lastName, date, modelNumber, brand, quantity, discount, total);
                            arraylistSales.add(sales);
                            isSaved = false;
                            clearTableSales();
                            addToSalesTable();
                            int newQuantity =im.getQuantity()-quantity;
                            im.setQuantity(newQuantity);
                            clearTableInventory();
                            addToInventoryTable();
                            int confirmation = JOptionPane.showConfirmDialog(frameAddSales, "Do you want to add more?", "New?", JOptionPane.YES_NO_OPTION);
                            switch (confirmation){
                            case JOptionPane.YES_OPTION:
                                clearSalesForm();
                                return;
                            case JOptionPane.NO_OPTION:
                                clearSalesForm();
                                frameAddSales.dispose();
                                break;
                            case JOptionPane.CLOSED_OPTION:
                                break;
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(frameAddSales, "Not enough " + modelNumber + " in the inventory.", "Error!", 0);
                            return; 
                        }
                    }
                }   
            }
            else {
                for(InventoryManagement im : arraylistInventory) {
                    if(im.getModelNo().equalsIgnoreCase(modelNumber) && im.getBrand().equalsIgnoreCase(brand)) {
                        if(im.getQuantity() >= quantity) {
                            Sales sales = new Sales(firstName, lastName, date, modelNumber, brand, discount, quantity, total);
                            arraylistSales.add(sales);
                            isSaved = false;
                            addToSalesTable();
                            int newQuantity = im.getQuantity()-quantity;
                            im.setQuantity(newQuantity);
                            clearTableInventory();
                            addToInventoryTable();
                            int confirmation = JOptionPane.showConfirmDialog(frameAddSales, "Do you want to add more?", "New?", JOptionPane.YES_NO_OPTION);
                            switch (confirmation){
                            case JOptionPane.YES_OPTION:
                                clearSalesForm();
                                return;
                            case JOptionPane.NO_OPTION:
                                clearSalesForm();
                                frameAddSales.dispose();
                                break;
                            case JOptionPane.CLOSED_OPTION:
                                break;
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(frameAddSales, "Not enough pieces in the inventory.", "Error!", 0);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jBtnAddSalesActionPerformed

    private void jBtnClearSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnClearSalesActionPerformed
        clearSalesForm();    
    }//GEN-LAST:event_jBtnClearSalesActionPerformed

    private void frameAddInventoryWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameAddInventoryWindowLostFocus
        frameAddInventory.requestFocus();
    }//GEN-LAST:event_frameAddInventoryWindowLostFocus

    private void frameAddSalesWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameAddSalesWindowLostFocus
        frameAddSales.requestFocus();
    }//GEN-LAST:event_frameAddSalesWindowLostFocus

    private void frameEditInventoryWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameEditInventoryWindowLostFocus
        frameEditInventory.requestFocus();
    }//GEN-LAST:event_frameEditInventoryWindowLostFocus

    private void jButtonSearchArraySalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchArraySalesActionPerformed
        String searchSelection = radioBtnTotal.isSelected() == true ? "Total" : radioBtnCustomerName.isSelected() == true ? "Last" : radioBtnModelNumberSales.isSelected() == true ? "Model" : radioBtnBrandSales.isSelected() == true? "Brand" : null;
        Utility util = new Utility();
        if (searchSelection.equals("Total")) {
            List <Integer> listInt = new ArrayList <>();
            int search = 0;
            try {
                search = Integer.parseInt(tfSearch.getText().trim());
            }
            catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frameSearch, "Integer input required.\n" + nfe, "Error!", 0);
                return;
            }
            listInt = util.searchSales((byte) 1, String.valueOf(search), arraylistSales);
            if(!listInt.isEmpty()) {
                int [] index = listInt.stream().mapToInt(i -> i).toArray();
                util.showSearchResultsSales(index, arraylistSales);
                frameSearchSales.dispose();
                tfSearchSales.setText(null);
            }
            else {
                lblSearchError.setVisible(true);
                TimerTask task = new TimerTask () {
                    public void run() {
                        lblSearchErrorSales.setVisible(false);
                    }
                };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
            }
        }
        else if (searchSelection.equals("Last")) {
            List <Integer> listInt = new ArrayList <>();
            String search = null;
            try {
                search = tfSearchSales.getText().trim();
            }
            catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(frameSearch, "Check your inputs.\n" + npe, "Error!", 0);
                return;
            }
            listInt = util.searchSales((byte) 2, String.valueOf(search), arraylistSales);
            if(!listInt.isEmpty()) {
                int [] index = listInt.stream().mapToInt(i -> i).toArray();
                util.showSearchResultsSales(index, arraylistSales);
                frameSearchSales.dispose();
                tfSearchSales.setText(null);
            }
            else {
                lblSearchError.setVisible(true);
                TimerTask task = new TimerTask () {
                    public void run() {
                        lblSearchErrorSales.setVisible(false);
                    }
                };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
            }
        }
        else {
            List <Integer> listInt = new ArrayList <>();
            String search = null;
            try {
                search = tfSearchSales.getText().trim();
            }
            catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(frameSearch, "Check your inputs.\n" + npe, "Error!", 0);
                return;
            }
            listInt = util.searchSales((byte) 3, search, arraylistSales);
            if(!listInt.isEmpty()) {
                int [] index = listInt.stream().mapToInt(i -> i).toArray();
                util.showSearchResultsSales(index, arraylistSales);
                frameSearchSales.dispose();
                tfSearchSales.setText(null);
            }
            else {
                lblSearchError.setVisible(true);
                TimerTask task = new TimerTask () {
                    public void run() {
                        lblSearchErrorSales.setVisible(false);
                    }
                };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
            }
        }
    }//GEN-LAST:event_jButtonSearchArraySalesActionPerformed

    private void comboBoxModelNumberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxModelNumberItemStateChanged
       if(!arraylistInventory.isEmpty()){
       if(!isEditSales && isNew) {
                String getModelNumber = comboBoxModelNumber.getSelectedItem().toString();
                if(!getModelNumber.isEmpty()) {
                    for (InventoryManagement im : arraylistInventory) {
                        if (im.getModelNo().equalsIgnoreCase(getModelNumber)) {
                            String brand = im.getBrand();
                            String setBrand = comboBoxBrandSales.getSelectedItem().toString();
                            if (!brand.equalsIgnoreCase(setBrand)) {
                                comboBoxBrandSales.setSelectedItem(brand);
                                return;
                            }
                            else if (setBrand.isEmpty()) {
                                comboBoxBrandSales.setSelectedItem("Apple");
                                return;
                            }
                            else {
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(rootPane, "No such model number exists", "Error!", 0);
                }
            }
       }
            
    }//GEN-LAST:event_comboBoxModelNumberItemStateChanged

    private void comboBoxDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDiscountActionPerformed
        try {
            String modelNum = comboBoxModelNumber.getSelectedItem().toString();
            String brand = comboBoxBrandSales.getSelectedItem().toString();
            for(InventoryManagement im : arraylistInventory) {
                String modelNumArray = im.getModelNo();
                String brandArray = im.getBrand();
                if(modelNumArray.equalsIgnoreCase(modelNum) && brandArray.equalsIgnoreCase(brand)) {
                    int cost = im.getSellingPrice();
                    int quantity = Integer.parseInt(tfQuantitySales.getText().trim());
                    double discount = Integer.parseInt(comboBoxDiscount.getSelectedItem().toString());

                    if(discount != 0) {
                        discount = discount / 100;
                    }
                    else {
                        tfTotal.setText(String.valueOf(cost * quantity));
                        return;
                    }
                    double discountAmt = (cost * quantity) * discount;
                    double total = (cost * quantity) - discountAmt;
                    tfTotal.setText(String.valueOf(Math.round(total)));
                    return;
                    }
                }
            }
        catch (NumberFormatException nfe) {
        }
        catch (NullPointerException  | ArithmeticException e) {
            if (tfQuantitySales.getText() != null || !tfQuantitySales.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frameAddSales, e, "Error!", 0);
            }
        }
    }//GEN-LAST:event_comboBoxDiscountActionPerformed

    private void tfQuantitySalesEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQuantitySalesEditKeyTyped
        char key = evt.getKeyChar();
        if (!((key >= '0') && (key <= '9') || key == evt.VK_BACK_SPACE || key == evt.VK_DELETE)) {
            evt.consume();
        }
        else {
            try {
                String modelNum = comboBoxModelNumber.getSelectedItem().toString();
                String brand = comboBoxBrandSales.getSelectedItem().toString();
                for(InventoryManagement im : arraylistInventory) {
                    String modelNumArray = im.getModelNo();
                    String brandArray = im.getBrand();
                    if(modelNumArray.equalsIgnoreCase(modelNum) && brandArray.equalsIgnoreCase(brand)) {
                        int cost = im.getSellingPrice();
                        int quantity = Integer.parseInt(tfQuantitySalesEdit.getText().trim());
                        int discountPercent = Integer.parseInt(comboBoxDiscountEdit.getSelectedItem().toString());
                        int discount = 0;
                        if(discountPercent != 0) {
                            discount = discountPercent / 100;
                        }
                        else {
                            tfTotalEdit.setText(String.valueOf(cost * quantity));
                            return;
                        }
                        int total = (cost * quantity) / discount;
                        String stringTotal = String.valueOf(total);
                        tfTotalEdit.setText(stringTotal);
                        return;
                    }
                }
            }
            catch (NullPointerException npe) {
            }
            catch (NumberFormatException nfe) {
            }
        }
    }//GEN-LAST:event_tfQuantitySalesEditKeyTyped

    private void jBtnSaveSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveSalesActionPerformed
        String firstName = tfFirstNameEdit.getText().trim();
        String lastName = tfLastNameEdit.getText().trim();
        String date = tfDateEdit.getText().trim();
        String modelNumber = comboBoxModelNumberEdit.getSelectedItem().toString().trim();
        String brand = comboBoxBrandSalesEdit.getSelectedItem().toString().trim();
        int quantity = Integer.parseInt(tfQuantitySalesEdit.getText().trim());
        int discount = Integer.parseInt(comboBoxDiscountEdit.getSelectedItem().toString().trim());
        int total = Integer.parseInt(tfTotalEdit.getText());
        
        String datePattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        if (!date.matches(datePattern)){
          JOptionPane.showMessageDialog(frameAddSales, "Please enter a valid Date!\nValid dates are dd-mm-yyyy, dd.mm.yyyy, dd/mm/yyyy", "Error!", 0);
          return;
        }
        else {
            Sales s = arraylistSales.get(indexEditSales);
            if (s.getFirstName().equalsIgnoreCase(firstName) && s.getLastName().equalsIgnoreCase(lastName) && s.getDate().equalsIgnoreCase(date)
            && s.getModelNumber().equalsIgnoreCase(modelNumber) && s.getBrand().equalsIgnoreCase(brand)
            && s.getQuantity() == quantity && s.getDiscount() == discount && s.getTotal() == total) {
                lblEditInvMsgSales.setVisible(true);
                TimerTask task = new TimerTask () {
                    public void run() {
                        lblEditInvMsgSales.setVisible(false);
                    }
                };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
            }
            else {
                if (s.getQuantity() == quantity) {
                    editSalesSave(indexEditSales, firstName, lastName, date, modelNumber, brand, quantity, discount, total);
                }
                else if (s.getQuantity() > quantity) {
                    boolean modelExists = false;
                    int removedQuantity = s.getQuantity() - quantity;
                    for (InventoryManagement im : arraylistInventory) {
                        if (im.getModelNo().equalsIgnoreCase(modelNumber) && im.getBrand().equalsIgnoreCase(brand)) {
                            removedQuantity += im.getQuantity();
                            im.setQuantity(removedQuantity);
                            editSalesSave(indexEditSales, firstName, lastName, date, modelNumber, brand, quantity, discount, total);
                            modelExists = true;
                            break;
                        }
                    }
                    if (!modelExists){
                        JOptionPane.showMessageDialog(frameEditSales, modelNumber + " of " + brand + " does not exist anymore.", "Does not exist!", 0);
                        return;
                    }
                }
                else {
                    boolean modelExists = false;
                    int addedQuantity = quantity - s.getQuantity();
                    for (InventoryManagement im : arraylistInventory) {
                        if (im.getModelNo().equalsIgnoreCase(modelNumber) && im.getBrand().equalsIgnoreCase(brand)) {
                            if (im.getQuantity() >= quantity) {
                                int newInvQuantity = im.getQuantity() - addedQuantity;
                                im.setQuantity(newInvQuantity);
                                editSalesSave(indexEditSales, firstName, lastName, date, modelNumber, brand, quantity, discount, total);
                                modelExists = true;
                                break;
                            }
                            else {
                                JOptionPane.showMessageDialog(frameEditSales, "Not enough quantity in the stock.", "Does not exist!", 0);
                                return;
                            }
                        }
                    }
                    if (!modelExists){
                        JOptionPane.showMessageDialog(frameEditSales, modelNumber + " of " + brand + " does not exist anymore.", "Does not exist!", 0);
                        return;
                    }
                }
            }
        }       
    }//GEN-LAST:event_jBtnSaveSalesActionPerformed

    private void jBtnCancelSalesEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelSalesEditActionPerformed
        frameEditSales.dispose();
    }//GEN-LAST:event_jBtnCancelSalesEditActionPerformed

    private void comboBoxModelNumberEditItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxModelNumberEditItemStateChanged
        if (!isNew) {
            
            isEditSales = false;
            String getModelNumber = comboBoxModelNumber.getSelectedItem().toString();
            if(!getModelNumber.isEmpty()) {
                for (InventoryManagement im : arraylistInventory) {
                    if (im.getModelNo().equalsIgnoreCase(getModelNumber)) {
                        String brand = im.getBrand();
                        String setBrand = comboBoxBrandSales.getSelectedItem().toString();
                        if (!brand.equalsIgnoreCase(setBrand)) {
                            comboBoxBrandSales.setSelectedItem(brand);
                            return;
                        }
                        else if (setBrand.isEmpty()) {
                            comboBoxBrandSales.setSelectedItem("Apple");
                            return;
                        }
                        else {
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "No such model number exists", "Error!", 0);
            }
        }
    }//GEN-LAST:event_comboBoxModelNumberEditItemStateChanged

    private void comboBoxDiscountEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDiscountEditActionPerformed
        try {
            String modelNum = comboBoxModelNumber.getSelectedItem().toString();
            String brand = comboBoxBrandSales.getSelectedItem().toString();
            for(InventoryManagement im : arraylistInventory) {
                String modelNumArray = im.getModelNo();
                String brandArray = im.getBrand();
                if(modelNumArray.equalsIgnoreCase(modelNum) && brandArray.equalsIgnoreCase(brand)) {
                    int cost = im.getSellingPrice();
                    int quantity = Integer.parseInt(tfQuantitySales.getText().trim());
                    double discountPercent = Double.parseDouble(comboBoxDiscount.getSelectedItem().toString());
                    if(discountPercent != 0) {
                        discountPercent = discountPercent / 100;
                        double total = (cost * quantity) * discountPercent;
                        tfTotal.setText(String.valueOf(Math.round(total)));
                        break;
                    }
                    else {
                        tfTotal.setText(String.valueOf(cost * quantity));
                        break;
                    }
                    
                }
            }
        }
        catch (NullPointerException npe) {
        }
        catch (NumberFormatException nfe) {
        }
        catch (ArithmeticException ae) {
        }
    }//GEN-LAST:event_comboBoxDiscountEditActionPerformed

    private void frameEditSalesWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameEditSalesWindowLostFocus
        frameEditSales.requestFocus();
    }//GEN-LAST:event_frameEditSalesWindowLostFocus

    private void tfFirstNameEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFirstNameEditKeyTyped
        char key = evt.getKeyChar();
        if(key == evt.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_tfFirstNameEditKeyTyped

    private void tfModelNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfModelNoKeyTyped
        char key = evt.getKeyChar();
        if(key == evt.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_tfModelNoKeyTyped

    private void tfModelNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfModelNameKeyTyped
        char key = evt.getKeyChar();
        if(key == evt.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_tfModelNameKeyTyped

    private void tfLastNameEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLastNameEditKeyTyped
        char key = evt.getKeyChar();
        if(key == evt.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_tfLastNameEditKeyTyped

    private void tfDateEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDateEditKeyTyped
        char key = evt.getKeyChar();
        if(key == evt.VK_SPACE){
            evt.consume();
        }
    }//GEN-LAST:event_tfDateEditKeyTyped

    private void frameAddSalesWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameAddSalesWindowClosing
        isWindowOpen = false;
        openedWindow = 0;
    }//GEN-LAST:event_frameAddSalesWindowClosing

    private void frameInvoiceWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameInvoiceWindowLostFocus
        frameInvoice.requestFocus();
    }//GEN-LAST:event_frameInvoiceWindowLostFocus

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Utility util = new Utility();
        util.printInvoice(panelInvoice);
        frameInvoice.dispose();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Utility util = new Utility();
        util.savePicture(panelInvoice, lblSetFirstName.getText(), lblSetLastName.getText());
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jMnuItmRemoveBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmRemoveBrandActionPerformed
        String delBrand = null;
        Utility util = new Utility();
        String dir = util.getBrandFilePath();
        try {
            delBrand = JOptionPane.showInputDialog(rootPane, "Delete a brand", "Delete", JOptionPane.INFORMATION_MESSAGE).trim();
            if(!delBrand.matches("^[a-zA-Z]*$") || delBrand.trim().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Brand name can only have alphabets,Please enter valid brand name!", "Error!", JOptionPane.ERROR_MESSAGE);
                jMnuItmAddBrandActionPerformed(evt);
            }
            else {
                boolean brandFound = false;
                
                for(int i = 0;i< comboBoxBrand.getItemCount();i++){
                    if(comboBoxBrand.getItemAt(i).toString().equalsIgnoreCase(delBrand)){
                       comboBoxBrand.removeItemAt(i);
                       comboBoxBrandSales.removeItemAt(i);
                       comboEditInvBrand.removeItemAt(i);
                       comboBoxBrandSalesEdit.removeItemAt(i);
                       comboBoxSearch.removeItemAt(i);
                       comboBoxSearchSales.removeItemAt(i);
                       brandFound = true;
                       break;
                    }
                }
                if(!brandFound){
                    JOptionPane.showMessageDialog(rootPane, "Cannot find the brand name "+delBrand, "Error!", JOptionPane.ERROR_MESSAGE);
                }else{
                    
                    File brandTextFile = new File(dir);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(brandTextFile));
                    for(int i =0;i<comboBoxBrand.getItemCount();i++){
                        writer.write(comboBoxBrand.getItemAt(i).toString());
                        writer.newLine();
                    }
                    writer.close();
                    JOptionPane.showMessageDialog(rootPane, delBrand + " delete successfully", "Deleted", 0);
                }
            }
        }
        catch (IOException ie) {
                JOptionPane.showMessageDialog(rootPane, "Something went wrong! Cannot write to file!" + ie, "Error", 0);
                return;
        }
        catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_jMnuItmRemoveBrandActionPerformed

    private void jMnuItmHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmHelpActionPerformed
        jDialogHelp.setLocationRelativeTo(null);
        jDialogHelp.setSize(300,200);
        jDialogHelp.setVisible(true);
    }//GEN-LAST:event_jMnuItmHelpActionPerformed

    private void radioBtnBrandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnBrandItemStateChanged
        cl.show(panelCard, "2");
        radioBtnCategoryCombo.setSelected(true);
    }//GEN-LAST:event_radioBtnBrandItemStateChanged

    private void jButtonSearchArrayComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchArrayComboActionPerformed
        List <Integer> listInt = new ArrayList <>();
        String search = comboBoxSearch.getSelectedItem().toString().trim();  
        Utility util = new Utility ();
        if(search!=null){
            listInt = util.searchInventory(true, search, arraylistInventory);
            if(!listInt.isEmpty()) {
                int [] index = listInt.stream().mapToInt(i -> i).toArray();
                frameSearch.dispose();
                util.showSearchResultsInventory(index, arraylistInventory);
                comboBoxSearch.setSelectedIndex(0);
            }
            else {
                lblSearchErrorCombo.setVisible(true);
                TimerTask task = new TimerTask () {
                public void run() {
                lblSearchErrorCombo.setVisible(false);
                }
                };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
            }
        }
    }//GEN-LAST:event_jButtonSearchArrayComboActionPerformed

    private void radioBtnModelNameComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnModelNameComboItemStateChanged
        cl.show(panelCard, "1");
        radioBtnModelName.setSelected(true);
    }//GEN-LAST:event_radioBtnModelNameComboItemStateChanged

    private void radioBtnSellingPriceComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnSellingPriceComboItemStateChanged
        cl.show(panelCard, "1");
        radioBtnSellingPrice.setSelected(true);
    }//GEN-LAST:event_radioBtnSellingPriceComboItemStateChanged

    private void radioBtnCostPriceComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnCostPriceComboItemStateChanged
        cl.show(panelCard, "1");
        radioBtnCostPrice.setSelected(true);
    }//GEN-LAST:event_radioBtnCostPriceComboItemStateChanged

    private void radioBtnBrandSalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnBrandSalesItemStateChanged
        clSales.show(panelCardSales, "2");
        radioBtnBrandSalesCombo.setSelected(true);
    }//GEN-LAST:event_radioBtnBrandSalesItemStateChanged

    private void jButtonSearchArraySalesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchArraySalesComboActionPerformed
        List <Integer> listInt = new ArrayList <>();
        String search = comboBoxSearchSales.getSelectedItem().toString().trim();
        Utility util = new Utility();   
        if(search!=null){
            listInt = util.searchSales((byte) 4, search, arraylistSales);
            if(!listInt.isEmpty()) {
                int [] index = listInt.stream().mapToInt(i -> i).toArray();
                util.showSearchResultsSales(index, arraylistSales);
                frameSearchSales.dispose();
                comboBoxSearchSales.setSelectedIndex(0);
            }
            else {
                lblSearchErrorSalesCombo.setVisible(true);
                TimerTask task = new TimerTask () {
                public void run() {
                lblSearchErrorSalesCombo.setVisible(false);
                }
                };
                long delay = 1000L;
                Timer timer = new Timer("Timer#1");
                timer.schedule(task, delay);
            }
        }
    }//GEN-LAST:event_jButtonSearchArraySalesComboActionPerformed

    private void radioBtnModelNumberSalesComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnModelNumberSalesComboItemStateChanged
        clSales.show(panelCardSales, "1");
        radioBtnModelNumberSales.setSelected(true);
    }//GEN-LAST:event_radioBtnModelNumberSalesComboItemStateChanged

    private void radioBtnCustomerNameComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnCustomerNameComboItemStateChanged
        clSales.show(panelCardSales, "1");
        radioBtnCustomerName.setSelected(true);
    }//GEN-LAST:event_radioBtnCustomerNameComboItemStateChanged

    private void radioBtnTotalComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioBtnTotalComboItemStateChanged
        clSales.show(panelCardSales, "1");
        radioBtnTotal.setSelected(true);
    }//GEN-LAST:event_radioBtnTotalComboItemStateChanged

    private void jButtonSearchCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchCancelActionPerformed
        frameSearch.dispose();
    }//GEN-LAST:event_jButtonSearchCancelActionPerformed

    private void tfFirstNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFirstNameKeyTyped
        char key = evt.getKeyChar();
        if (!Character.isLetter(key)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfFirstNameKeyTyped

    private void tfLastNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLastNameKeyTyped
        char key = evt.getKeyChar();
        if (!Character.isLetter(key)) {
            evt.consume();
        }
    }//GEN-LAST:event_tfLastNameKeyTyped

    private void frameAddInventoryWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameAddInventoryWindowClosing
        isWindowOpen = false;
        openedWindow = 0;
    }//GEN-LAST:event_frameAddInventoryWindowClosing

    private void frameEditInventoryWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameEditInventoryWindowClosing
        isWindowOpen = false;
        openedWindow = 0;
    }//GEN-LAST:event_frameEditInventoryWindowClosing

    private void frameEditSalesWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameEditSalesWindowClosing
        isWindowOpen = false;
        openedWindow = 0;
    }//GEN-LAST:event_frameEditSalesWindowClosing

    private void frameInvoiceWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameInvoiceWindowClosing
        isWindowOpen = false;
        openedWindow = 0;
    }//GEN-LAST:event_frameInvoiceWindowClosing

    private void radioBtnModelNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnModelNameComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnModelNameComboActionPerformed
    // adds inventory's arraylist data to the inventory table
    private void addToInventoryTable () {
        String [] arrayForTable = {null, null, null, null, null, null, null, null};
        //adding the arraylist data to an array
        for (InventoryManagement im : arraylistInventory) {
            arrayForTable[0] = im.getModelNo();
            arrayForTable[1] = im.getModelName();
            arrayForTable[2] = im.getBrand();
            arrayForTable[3] = im.getCategory();
            arrayForTable[4] = im.getRecommendation();
            arrayForTable[5] = String.valueOf(im.getCost());
            arrayForTable[6] = String.valueOf(im.getSellingPrice());
            arrayForTable[7] = String.valueOf(im.getQuantity());
            
            int rowCount = tblInventory.getRowCount();
            int nextRow = 0;
            boolean emptyRowFlag = false;
            String rowChecker;
            // checking all the rows to find the available row to enter the data
            do{
                rowChecker = (String) tblInventory.getValueAt(nextRow, 0);
                if(rowChecker != null && rowChecker.length() != 0) {
                    nextRow++;
                }
                else {
                    emptyRowFlag = true;
                }
            }   while(nextRow < rowCount && !emptyRowFlag);
            // entering the array's data into the table
            if(nextRow < rowCount) {
                int colCount = tblInventory.getColumnCount();
                for (int i = 0; i < colCount; i++) {
                    tblInventory.setValueAt(arrayForTable[i], nextRow, i);
                }
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "No empty rows found!", "Error", 0);
            }
        }
    }
    // adds sales' arraylist data to the sales table
    private void addToSalesTable() {
        String [] arrayForTable = {null, null, null, null, null, null, null, null};
        for (Sales sales : arraylistSales) {
            arrayForTable[0] = sales.getFirstName();
            arrayForTable[1] = sales.getLastName();
            arrayForTable[2] = sales.getDate();
            arrayForTable[3] = sales.getModelNumber();
            arrayForTable[4] = sales.getDate();
            arrayForTable[5] = String.valueOf(sales.getQuantity());
            arrayForTable[6] = String.valueOf(sales.getDiscount());
            arrayForTable[7] = String.valueOf(sales.getTotal());
            
            int rowCount = tblSales.getRowCount();
            int nextRow = 0;
            boolean emptyRowFlag = false;
            String rowChecker;
        
            do{
                rowChecker = (String) tblSales.getValueAt(nextRow, 0);
                if(rowChecker != null && rowChecker.length() != 0) {
                    nextRow++;
                }
                else {
                    emptyRowFlag = true;
                } 
            }   while(nextRow < rowCount && !emptyRowFlag);
            if(nextRow < rowCount) {
                int colCount = tblSales.getColumnCount();
                for (int i = 0; i < colCount; i++) {
                    tblSales.setValueAt(arrayForTable[i], nextRow, i);
                }
            
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "No empty rows found!", "Error", 0);
            }
        }
    }
    //clears the inventory table
    private void clearTableInventory() {
        // getting the row and coluumn count
        int rowCount = tblInventory.getRowCount();
        int colCount = tblInventory.getColumnCount();
        // looping through the row and columns and entering null values
        for (int i = rowCount - 1; i >=0; i--) {
            for (int j = 0; j < colCount; j++) {
                tblInventory.setValueAt(null, i, j);
            }
        }    
    }
    // clears the sales table
    private void clearTableSales() {
        int rowCount = tblSales.getRowCount();
            int colCount = tblSales.getColumnCount();
            for (int i = rowCount - 1; i >=0; i--) {
                for (int j = 0; j < colCount; j++) {
                tblSales.setValueAt(null, i, j);
                }
            }
    }
    private void editSalesSave(int index, String firstName, String lastName, String date, String modelNumber, String brand, int quantity, int discount, int total) {
        Sales sales = arraylistSales.get(index);
        sales.setFirstName(firstName);
        sales.setLastName(lastName);
        sales.setDate(date);
        sales.setModelNumber(modelNumber);
        sales.setBrand(brand);
        sales.setQuantity(quantity);
        sales.setDiscount(discount);
        sales.setTotal(total);
        frameEditSales.dispose();
        clearTableInventory();
        clearTableSales();
        addToInventoryTable();
        addToSalesTable();
        JOptionPane.showMessageDialog(rootPane, "Edit Saved.", "Success!", 1);
        isSaved = false;
    }
    // reads and returns an array for the brand's combo boxes
    private String[] elementsForComboBoxBrand () {
        String [] comboStrings = {};
        List <String> listStrings = new ArrayList <>();
        Utility util = new Utility();
        String dir = util.getBrandFilePath();
        try {
            File file= new File(dir);
            if(file.createNewFile()){
                JOptionPane.showMessageDialog(rootPane, "No brands found.", "Error", 0);
            }
            else{
                Scanner myReader = new Scanner(file);
                String data="";
            // looping through the brands.txt file and entering the data in the List
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                    if(!data.equals("")){
                        listStrings.add(data);
                    }
                }
                myReader.close();
            }
        }
        catch (IOException ie) {
            JOptionPane.showMessageDialog(rootPane, "Soemthing went wrong" + ie, "Error", 0);
        }
        // entering all the list data into the array comboStrings
        comboStrings = (String[]) listStrings.toArray(new String[listStrings.size()]);
        listStrings.clear();
        return comboStrings;
    }
    // binary search method for cost price
    private int binarySearch(boolean selection, int search) {
        int value = -1;
        Utility util = new Utility();
        if(selection) {
            value = util.binarySearchInventoryCost(0, arraylistInventory.size() - 1, search, arraylistInventory);
        }
        else {
            value = util.binarySearchInventorySelling(0, temp.size() - 1, search, temp);
        }
        return value;
    }
    // setting icons for the dialog boxes and frames
    private void setIcon() {
        setIconImage (Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/mobile.png")));
        frameAddInventory.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.JPG")));
        frameAddSales.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/add.JPG")));
        jDialogHelp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/questionmark.png")));
        jDialogAbout.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/about.png")));
        frameSearch.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/search.png")));
        frameSearchSales.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/search.png")));
    }
    // reads the inventory's csv file and adds to the arraylist
    private void csvReaderInventory(String pathInventory) { 
        try{
            arraylistInventory.clear();
            BufferedReader csvReader = new BufferedReader(new FileReader(pathInventory));
            String row = null;
            while((row = csvReader.readLine()) != null){
                String [] values = row.split(",");
                String modelNo = values[0];
                String modelName = values[1];
                String brand = values[2];
                String os = values[3];
                String range = values[4];
                int costPrice = Integer.parseInt(values[5]);
                int sellingPrice = Integer.parseInt(values[6]);
                int quantity = Integer.parseInt(values[7]);
                InventoryManagement im = new InventoryManagement(modelNo, modelName, brand, os, range, costPrice, sellingPrice, quantity);
                arraylistInventory.add(im);
                comboBoxModelNumber.addItem(modelNo);
                comboBoxModelNumberEdit.addItem(modelNo);
            }
            csvReader.close();
        }catch (FileNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Error Occured " + e, "Error!", 0);
        }
        catch (IOException ie) {
            JOptionPane.showMessageDialog(rootPane, "Error Occured " + ie, "Error!", 0);
        }
    }
    private void csvReaderSales(String pathSales) {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(pathSales));
            arraylistSales.clear();
            String row = null;
            while((row = csvReader.readLine()) != null){
                String [] values = row.split(",");
                String firstName = values[0];
                String lastName = values[1];
                String date = values[2];
                String modelNumber = values[3];
                String brand = values[4];
                int quantity = Integer.parseInt(values[5]);
                int discount = Integer.parseInt(values[6]);
                int total = Integer.parseInt(values[7]);
                Sales sales = new Sales(firstName, lastName, date, modelNumber, brand, quantity, discount, total);
                arraylistSales.add(sales);
            }
            csvReader.close();
        }
        catch (FileNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Error Occured " + e, "Error!", 0);
        }
        catch (IOException ie) {
            JOptionPane.showMessageDialog(rootPane, "Error Occured " + ie, "Error!", 0);
        }
    }
    // clear's sales frame's components
    private void clearSalesForm() {
        tfFirstName.setText(null);
        tfLastName.setText(null);
        tfDate.setText(null);
        tfTotal.setText(null);
        comboBoxDiscount.setSelectedIndex(0);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                comboBoxModelNumber.setSelectedIndex(0);
                comboBoxBrandSales.setSelectedIndex(0);
                tfQuantitySales.setText("0");
            }
        });
    }
    private void clearInventoryForm() {
        tfModelNo.setText(null);
        tfModelName.setText(null);
        comboBoxBrand.setSelectedIndex(0);
        comboCategory.setSelectedIndex(0);
        btnGrpAddInventory.clearSelection();
        radioBtnHigh.setSelected(true);
        tfCostPrice.setText(null);
        tfSellingPrice.setText(null);
        tfQuantity.setText(null); 
    }
    private void windowOpenedError() {
        if (openedWindow == 1) {
            JOptionPane.showMessageDialog(frameAddInventory,"A Window is already opened.", "Error!", 0);
        }
        else if (openedWindow == 2) {
            JOptionPane.showMessageDialog(frameAddSales,"A Window is already opened.", "Error!", 0);
        }
        else if (openedWindow == 3) {
            JOptionPane.showMessageDialog(frameEditInventory,"A Window is already opened.", "Error!", 0);
        }
        else if (openedWindow == 4) {
            JOptionPane.showMessageDialog(frameEditSales,"A Window is already opened.", "Error!", 0);
        }
        else if (openedWindow == 5) {
            JOptionPane.showMessageDialog(frameInvoice,"A Window is already opened.", "Error!", 0);
        }
        else if (openedWindow == 6) {
            JOptionPane.showMessageDialog(frameSearch,"A Window is already opened.", "Error!", 0);
        }
        else if (openedWindow == 7) {
            JOptionPane.showMessageDialog(frameSearchSales,"A Window is already opened.", "Error!", 0);
        }
        else {
            JOptionPane.showMessageDialog(null,"Unexpected Error.", "Error!", 0);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupSearch;
    private javax.swing.ButtonGroup btnGroupSearchSales;
    private javax.swing.ButtonGroup btnGrpAddInventory;
    private javax.swing.ButtonGroup btnGrpEditInventory;
    private javax.swing.ButtonGroup btnGrpSearchCombo;
    private javax.swing.ButtonGroup btnGrpSearchSalesCombo;
    private javax.swing.JButton btnInvoice;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> comboBoxBrand;
    private javax.swing.JComboBox<String> comboBoxBrandSales;
    private javax.swing.JComboBox<String> comboBoxBrandSalesEdit;
    private javax.swing.JComboBox<String> comboBoxDiscount;
    private javax.swing.JComboBox<String> comboBoxDiscountEdit;
    private javax.swing.JComboBox<String> comboBoxModelNumber;
    private javax.swing.JComboBox<String> comboBoxModelNumberEdit;
    private javax.swing.JComboBox<String> comboBoxSearch;
    private javax.swing.JComboBox<String> comboBoxSearchSales;
    private javax.swing.JComboBox<String> comboCategory;
    private javax.swing.JComboBox<String> comboEditInvBrand;
    private javax.swing.JComboBox<String> comboEditInvCategory;
    private javax.swing.JFrame frameAddInventory;
    private javax.swing.JFrame frameAddSales;
    private javax.swing.JFrame frameEditInventory;
    private javax.swing.JFrame frameEditSales;
    private javax.swing.JFrame frameInvoice;
    private javax.swing.JButton jBtnAddSales;
    private javax.swing.JButton jBtnCancelSalesEdit;
    private javax.swing.JButton jBtnClearSales;
    private javax.swing.JButton jBtnSaveSales;
    private javax.swing.JButton jButtonAddInventory;
    private javax.swing.JButton jButtonClearInventory;
    private javax.swing.JButton jButtonEditInventoryCancel;
    private javax.swing.JButton jButtonEditInventorySave;
    private javax.swing.JButton jButtonSearchArray;
    private javax.swing.JButton jButtonSearchArrayCombo;
    private javax.swing.JButton jButtonSearchArraySales;
    private javax.swing.JButton jButtonSearchArraySalesCombo;
    private javax.swing.JButton jButtonSearchCancel;
    private javax.swing.JButton jButtonSearchCancelSales;
    private javax.swing.JDialog jDialogAbout;
    private javax.swing.JDialog jDialogHelp;
    private javax.swing.JMenu jMnuAbt;
    private javax.swing.JMenu jMnuFile;
    private javax.swing.JMenuItem jMnuItmAbout;
    private javax.swing.JMenuItem jMnuItmAddBrand;
    private javax.swing.JMenuItem jMnuItmExit;
    private javax.swing.JMenuItem jMnuItmHelp;
    private javax.swing.JMenuItem jMnuItmNew;
    private javax.swing.JMenuItem jMnuItmOpen;
    private javax.swing.JMenuItem jMnuItmRemoveBrand;
    private javax.swing.JMenuItem jMnuItmSave;
    private javax.swing.JMenuItem jMnuItmSaveAs;
    private javax.swing.JMenu jMnuTools;
    private javax.swing.JLabel lblAbt;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblBrandSales;
    private javax.swing.JLabel lblBrandSalesEdit;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCostPrice;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDateEdit;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblDiscountEdit;
    private javax.swing.JLabel lblEditInvBrand;
    private javax.swing.JLabel lblEditInvCategory;
    private javax.swing.JLabel lblEditInvCostPrice;
    private javax.swing.JLabel lblEditInvModelName;
    private javax.swing.JLabel lblEditInvModelNo;
    private javax.swing.JLabel lblEditInvMsg;
    private javax.swing.JLabel lblEditInvMsgSales;
    private javax.swing.JLabel lblEditInvQuantity;
    private javax.swing.JLabel lblEditInvRecommendation;
    private javax.swing.JLabel lblEditInvSellingPrice;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblFirstNameEdit;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblInvoiceBrand;
    private javax.swing.JLabel lblInvoiceDate;
    private javax.swing.JLabel lblInvoiceDiscount;
    private javax.swing.JLabel lblInvoiceFirstName;
    private javax.swing.JLabel lblInvoiceLastName;
    private javax.swing.JLabel lblInvoiceModelNumber;
    private javax.swing.JLabel lblInvoiceQuantity;
    private javax.swing.JLabel lblInvoiceTotal;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblLastNameEdit;
    private javax.swing.JLabel lblModelName;
    private javax.swing.JLabel lblModelNo;
    private javax.swing.JLabel lblModelNoSales;
    private javax.swing.JLabel lblModelNoSalesEdit;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblQuantitySales;
    private javax.swing.JLabel lblQuantitySalesEdit;
    private javax.swing.JLabel lblRecommendation;
    private javax.swing.JLabel lblSalePrice;
    private javax.swing.JLabel lblSalePriceEdit;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchCombo;
    private javax.swing.JLabel lblSearchError;
    private javax.swing.JLabel lblSearchErrorCombo;
    private javax.swing.JLabel lblSearchErrorSales;
    private javax.swing.JLabel lblSearchErrorSalesCombo;
    private javax.swing.JLabel lblSearchSales;
    private javax.swing.JLabel lblSearchSalesCombo;
    private javax.swing.JLabel lblSellingPrice;
    private javax.swing.JLabel lblSetBrand;
    private javax.swing.JLabel lblSetDate;
    private javax.swing.JLabel lblSetDiscount;
    private javax.swing.JLabel lblSetFirstName;
    private javax.swing.JLabel lblSetLastName;
    private javax.swing.JLabel lblSetModelNumber;
    private javax.swing.JLabel lblSetQuantity;
    private javax.swing.JLabel lblSetTotal;
    private javax.swing.JMenuBar mnuBarEmployee;
    private javax.swing.JPanel panelAddInventory;
    private javax.swing.JPanel panelAddSales;
    private javax.swing.JPanel panelBg;
    private javax.swing.JPanel panelBtn;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelCardSales;
    private javax.swing.JPanel panelEditInventory;
    private javax.swing.JPanel panelEditSales;
    private javax.swing.JPanel panelInvoice;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelSearchCombo;
    private javax.swing.JPanel panelSearchSales;
    private javax.swing.JPanel panelSearchSalesCombo;
    private javax.swing.JRadioButton radioBtnBrand;
    private javax.swing.JRadioButton radioBtnBrandSales;
    private javax.swing.JRadioButton radioBtnBrandSalesCombo;
    private javax.swing.JRadioButton radioBtnCategoryCombo;
    private javax.swing.JRadioButton radioBtnCostPrice;
    private javax.swing.JRadioButton radioBtnCostPriceCombo;
    private javax.swing.JRadioButton radioBtnCustomerName;
    private javax.swing.JRadioButton radioBtnCustomerNameCombo;
    private javax.swing.JRadioButton radioBtnEditInvHigh;
    private javax.swing.JRadioButton radioBtnEditInvLow;
    private javax.swing.JRadioButton radioBtnEditInvMid;
    private javax.swing.JRadioButton radioBtnHigh;
    private javax.swing.JRadioButton radioBtnLow;
    private javax.swing.JRadioButton radioBtnMid;
    private javax.swing.JRadioButton radioBtnModelName;
    private javax.swing.JRadioButton radioBtnModelNameCombo;
    private javax.swing.JRadioButton radioBtnModelNumberSales;
    private javax.swing.JRadioButton radioBtnModelNumberSalesCombo;
    private javax.swing.JRadioButton radioBtnSellingPrice;
    private javax.swing.JRadioButton radioBtnSellingPriceCombo;
    private javax.swing.JRadioButton radioBtnTotal;
    private javax.swing.JRadioButton radioBtnTotalCombo;
    private javax.swing.JScrollPane scrlPaneInventory;
    private javax.swing.JScrollPane scrlPaneSales;
    private javax.swing.JTabbedPane tabbedPaneTbl;
    private javax.swing.JTable tblInventory;
    private javax.swing.JPanel tblPnlInventory;
    private javax.swing.JPanel tblPnlSales;
    private javax.swing.JTable tblSales;
    private javax.swing.JTextField tfCostPrice;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfDateEdit;
    private javax.swing.JTextField tfEditInvCostPrice;
    private javax.swing.JTextField tfEditInvModelName;
    private javax.swing.JTextField tfEditInvModelNo;
    private javax.swing.JTextField tfEditInvQuantity;
    private javax.swing.JTextField tfEditInvSellingPrice;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfFirstNameEdit;
    private javax.swing.JTextField tfLastName;
    private javax.swing.JTextField tfLastNameEdit;
    private javax.swing.JTextField tfModelName;
    private javax.swing.JTextField tfModelNo;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfQuantitySales;
    private javax.swing.JTextField tfQuantitySalesEdit;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfSearchSales;
    private javax.swing.JTextField tfSellingPrice;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JTextField tfTotalEdit;
    // End of variables declaration//GEN-END:variables
    //Overriding keylistener methods
    @Override
    public void keyTyped(KeyEvent e) {
        //getting keycode from the main JFrame when in focus and comparing them to call their respective methods
        int key = e.getKeyCode();
        if (key == KeyEvent.CTRL_DOWN_MASK) {
            if (key == KeyEvent.VK_N) {
                java.awt.event.ActionEvent evt1 = null;
                jMnuItmNewActionPerformed(evt1);
            }
            else if (key == KeyEvent.VK_O) {
                java.awt.event.ActionEvent evt1 = null;
                jMnuItmOpenActionPerformed(evt1);
            }
            else if (key == KeyEvent.VK_S) {
                java.awt.event.ActionEvent evt1 = null;
                jMnuItmSaveActionPerformed(evt1);
            }
            else if (key == KeyEvent.SHIFT_DOWN_MASK) {
                if (key == KeyEvent.VK_S) {
                    java.awt.event.ActionEvent evt1 = null;
                    jMnuItmSaveAsActionPerformed(evt1);
                }
                else if (key == KeyEvent.VK_X) {
                    java.awt.event.ActionEvent evt1 = null;
                    jMnuItmExitActionPerformed(evt1);
                }
            }
        }
        else if (key == KeyEvent.VK_F1) {
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //unused keylistener methods
    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}