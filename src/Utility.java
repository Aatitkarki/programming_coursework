
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Utility {
    public ArrayList<InventoryManagement> sortInventoryByCost (ArrayList<InventoryManagement> arraylistInventory) {
        for (int i = 0; i < arraylistInventory.size(); i++) {
            for (int j = i + 1 ; j < arraylistInventory.size(); j++) {
                if (i!= j) {
                    InventoryManagement imFirst = arraylistInventory.get(i);
                    InventoryManagement imSecond = arraylistInventory.get(j);
                    if (imFirst.getCost() > imSecond.getCost()) {
                        Collections.swap(arraylistInventory, i, j);
                    }
                }
            }
        }
        return arraylistInventory;
    }
    public ArrayList<InventoryManagement> sortInventoryTemp(ArrayList <InventoryManagement> arraylistInventory, ArrayList <InventoryManagement> temp) {
        temp.addAll(arraylistInventory);
//      Collections.copy(temp, arraylistInventory);
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i + 1 ; j < temp.size(); j++) {
                if (i != j) {
                    InventoryManagement imFirst = temp.get(i);
                    InventoryManagement imSecond = temp.get(j);
                    if (imFirst.getSellingPrice() > imSecond.getSellingPrice()) {
                    Collections.swap(temp, i, j);
                    }
                }
            }
        }
        return temp;
    }
    public int binarySearchInventoryCost(int low, int high, int search, ArrayList <InventoryManagement> arraylistInventory) {
        int values = -1;
        if (high >= low) {
            int mid = (low + high) / 2;
            InventoryManagement im = arraylistInventory.get(mid);
   
            if (im.getCost() == search){
                values = mid;
                return values;
            }

            if (im.getCost() > search) 
                return binarySearchInventoryCost(low, mid - 1, search, arraylistInventory);
            
            
            return binarySearchInventoryCost(mid + 1, high, search, arraylistInventory);
            
        }
        return values;
    }
    public int binarySearchInventorySelling(int low, int high, int search, ArrayList <InventoryManagement> arraylistInventory) {
        int values = -1;
        if (high >= low) {
            int mid = (low+high) / 2;
            InventoryManagement im = arraylistInventory.get(mid);
            if (im.getSellingPrice() == search){
                values = mid;
                return values;
            }
            if (im.getCost() > search) 
                return binarySearchInventorySelling(low, mid - 1, search, arraylistInventory);
            return binarySearchInventorySelling(mid + 1, high, search, arraylistInventory);
        }
        return values;
    }
    public String getBrandFilePath() {
        String dir = System.getProperty("user.dir");
        if (System.getProperty("user.dir").equalsIgnoreCase("Windows")) {
               dir += "\\resources\\Brands\\brands.txt";
        }
        else {
               dir += "/resources/Brands/brands.txt";
        }
        return dir;
    }
    public void csvWriter(String path, ArrayList<InventoryManagement> arraylistInventory, ArrayList<Sales> arraylistSales) {
        try {
            String pathSales = path.replace(".csv", "_sales.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            BufferedWriter bws = new BufferedWriter(new FileWriter(pathSales, false));
            for (InventoryManagement im : arraylistInventory) {
                String lines = null;
                lines = im.getModelNo();
                lines += "," + im.getModelName();
                lines += "," + im.getBrand();
                lines += "," + im.getCategory();
                lines += "," + im.getRecommendation();
                lines += "," + String.valueOf(im.getCost());
                lines += "," + String.valueOf(im.getSellingPrice());
                lines += "," + String.valueOf(im.getQuantity());
                bw.write(lines);
                bw.newLine();
            }
            bw.close();
            if (!arraylistSales.isEmpty()) {
                for (Sales sales : arraylistSales) {
                    String lines = null;
                    lines = sales.getFirstName();
                    lines += "," + sales.getLastName();
                    lines += "," + sales.getDate();
                    lines += "," + sales.getModelNumber();
                    lines += "," + sales.getBrand();
                    lines += "," + String.valueOf(sales.getQuantity());
                    lines += "," + String.valueOf(sales.getDiscount());
                    lines += "," + String.valueOf(sales.getTotal());
                    bws.write(lines);
                    bws.newLine();
                }
            }
            bws.close();
        }
        catch (FileNotFoundException f) {
            JOptionPane.showMessageDialog(null, "File not found.\n" + f, "Error!", 0);
        }
        catch (IOException ie) {
            JOptionPane.showMessageDialog(null, "IO Exception.\n" + ie, "Error!", 0);
        }
    }
    public void checkDirs() {
        //creating all the directories for csv files, brands, and images file
        String csvPath = System.getProperty("user.dir");
        if(System.getProperty("os.name").contains("Windows")){
           csvPath += "\\resources\\CSV Files";
        }
        else {
            csvPath += "/resources/CSV Files";
        }
        File csvDir = new File(csvPath);
        if(!csvDir.exists()) {
            csvDir.mkdirs();
        }
        String brandPath = System.getProperty("user.dir");
        if(System.getProperty("os.name").contains("Windows")){
           brandPath += "\\resources\\Brands";
        }
        else {
            brandPath += "/resources/Brands";
        }
        File brandDir = new File(brandPath);
        if(!brandDir.exists()) {
            try {
                File brandTxt = new File(brandPath + "brands.txt");
                if(!brandTxt.exists()) {
                    brandTxt.createNewFile();
                }
                brandDir.mkdirs();
            }
            catch (IOException ie) {
                JOptionPane.showMessageDialog(null, "Error while creating a brands.txt file." + ie, "Error!", 0);
            }
        }
        String imgPath = System.getProperty("user.dir");
        if(System.getProperty("os.name").contains("Windows")){
            imgPath += "\\resources\\Images";
        }
        else {
            imgPath += "/resources/Images";
        }
        File imgDir = new File(imgPath);
        if(!imgDir.exists()) {
            imgDir.mkdirs();
        }
    }
    // calls the operating system's print services
    public void printInvoice (JPanel print) {
        // Using printer to print out the invoice
        PrinterJob printer = PrinterJob.getPrinterJob();
        printer.setJobName("Invoice");
        printer.setPrintable(new Printable () {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                //Checks if there are printable content or not
                if (pageIndex > 0) {
                        return Printable.NO_SUCH_PAGE;
                }
                // making,setting, and scaling 2d graphics to map the panel
                Graphics2D graphics2D = (Graphics2D)graphics;
                graphics2D.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY());
                graphics2D.scale(0.5, 0.5);
                // painting panel
                print.paint(graphics2D);
                // Returns if page exists
                return Printable.PAGE_EXISTS;
            }
        });
        boolean returnedResult = printer.printDialog();
        if (returnedResult) {
            try {
                // Send job to the operating system's printer services
                printer.print();

            }
            catch (PrinterException pe) {
                JOptionPane.showMessageDialog(null, "Print error.\n" + pe.getMessage(), "Error!", 0);
            }
        }
    }
        // saves the invoice as a picture
    public void savePicture(JPanel panel, String first, String last) {
        //getting the size of the panel and setting the image size to it
        Dimension size = panel.getSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        //using graphics2d to create the graphics
        Graphics2D graphics = image.createGraphics();
        panel.paint(graphics);
        String imagePath = System.getProperty("user.dir");
        if (System.getProperty("os.name").contains("Windows")) {
            imagePath += "\\resources\\Images\\" + first + last + ".png";
        }
        else {
            imagePath += "/resources/Images/" + first + last + ".png";
        }
        try {
            ImageIO.write(image, "png", new File(imagePath));
            JOptionPane.showMessageDialog(null, "Invoice saved as image", "Saved!", 0);
        }
        catch(IOException | HeadlessException ie) {
            JOptionPane.showMessageDialog(null, "Failed to save.\n" + ie, "Error!", 0);
        }
    }
    //Multiple search output displayd method for inventory
    public void showSearchResultsInventory(int [] array, ArrayList <InventoryManagement> arraylistInventory) {
        String modelNumber = "";
        String brand = "";
        String os = "";
        String costPrice = "";
        for (int i = 0; i < array.length; i++) {
            InventoryManagement im = arraylistInventory.get(array[i]);
            modelNumber += im.getModelNo() + ", ";
            brand += im.getBrand() + ", ";
            os += im.getCategory()+ ", ";
            costPrice += String.valueOf(im.getCost()) + ", ";
        }
        modelNumber = modelNumber.substring(0, modelNumber.length() - 2);
        brand = brand.substring(0, brand.length() - 2);
        os = os.substring(0, os.length() - 2);
        costPrice = costPrice.substring(0, costPrice.length() - 2);
        JOptionPane.showMessageDialog(null, array.length + " entries found." + "\nModel Numbers : " + modelNumber + "\nBrands : " + brand + "\nOS : " + os + "\nCost : " + costPrice, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }
    //Multiple search output display method
    public void showSearchResultsSales(int [] array, ArrayList <Sales> arraylistSales) {
        String firstName = "";
        String lastName = "";
        String brand = "";
        String modelNumber = "";
        String quantity = "";
        String discount = "";
        String total = "";
        for (int i = 0; i < array.length; i++) {
            Sales sales = arraylistSales.get(array[i]);
            firstName += sales.getFirstName() + ", ";
            lastName += sales.getLastName() + ",";
            brand += sales.getBrand() + ", ";
            modelNumber += sales.getModelNumber() + ", ";
            quantity += sales.getQuantity() + ", ";
            discount += sales.getDiscount() + ", ";
            total += sales.getTotal() + ", ";
        }
        firstName = firstName.substring(0, firstName.length() - 2);
        lastName = lastName.substring(0, lastName.length() - 2);
        brand = brand.substring(0, brand.length() - 2);
        modelNumber = modelNumber.substring(0, modelNumber.length() - 2);
        quantity = quantity.substring(0, quantity.length() - 2);
        discount = discount.substring(0, discount.length() - 2);
        total = total.substring(0, total.length() - 2);
        JOptionPane.showMessageDialog(null, array.length + " entries found." + "\nFirst Name : " + firstName + "\nLast Name : " + lastName + "\nBrand : " + brand + "\nModel Number : " + modelNumber + "\nQuantity : " + quantity + "\nDiscount : " + discount + "\nTotal : " + total, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }
    //Search method for sales frame
    public List<Integer> searchSales(byte selection, String search, ArrayList <Sales> arraylistSales) {
        List <Integer> listInt = new ArrayList <>();
        if (selection == 1) {
            int intSearch = Integer.parseInt(search);
            for (int i = 0; i < arraylistSales.size(); i++) {
                Sales sales = arraylistSales.get(i);
                if (sales.getTotal() == intSearch) {
                    listInt.add(i);
                }
            }
        }
        else if (selection == 2) {
            for (int i = 0; i < arraylistSales.size(); i++) {
                Sales sales = arraylistSales.get(i);
                if (sales.getLastName().equalsIgnoreCase(search)) {
                    listInt.add(i);
                }
            }
        }
        else if (selection == 3) {
            for (int i = 0; i < arraylistSales.size(); i++) {
                Sales sales = arraylistSales.get(i);
                if (sales.getModelNumber().equalsIgnoreCase(search)) {
                    listInt.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < arraylistSales.size(); i++) {
                Sales sales = arraylistSales.get(i);
                if (sales.getBrand().equalsIgnoreCase(search)) {
                    listInt.add(i);
                }
            }
        }
        return listInt;
    }
    public List<Integer> searchInventory(boolean selection, String search, ArrayList <InventoryManagement> arraylistInventory) {
        List <Integer> listInt = new ArrayList <>();
        if (selection) {
            for (int i = 0; i < arraylistInventory.size(); i++) {
                InventoryManagement im = arraylistInventory.get(i);
                if (im.getCategory().equals(search)) {
                    listInt.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < arraylistInventory.size(); i++) {
                InventoryManagement im = arraylistInventory.get(i);
                if (im.getModelNo().equals(search)) {
                        listInt.add(i);
                }
            }
        }
        return listInt;
    }
}