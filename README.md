# Coursework of CS5004NT Emerging Programming Platforms and Technology
public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    
    # Coursework of CS5004NT Emerging Programming Platforms and Technology
public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    public class AppliancesInfo extends javax.swing.JFrame implements KeyListener{
    private int indexEditInv;
    private int indexEditSales;
    private boolean isInventoryTableSelected = true;
    private boolean isSaved = true;
    private boolean isEditSales = false;
    private boolean isNew = true;
    private String path = "";
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
    
   

   
