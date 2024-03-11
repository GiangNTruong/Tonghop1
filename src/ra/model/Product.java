package ra.model;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;

    private double importPrice;
    private  double exportPrice;
    private double profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product(){

    }

    public Product(String productId, String productName, double importPrice, double exportPrice, double profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = calProfit();
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct){
        System.out.println("Mã sản phẩm độ dài 4 ký tự, bắt đầu là ký tự P");
        boolean firstInput = true;
        while (true){
            this.productId = scanner.nextLine().trim();
            if (this.productId.length() == 4 && this.productId.startsWith("P")) {
                boolean isDuplicate = false;
                for (Product product : arrProduct) {
                    if (product != null && product.productId.equals(this.productId)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    break;
                } else {
                    System.out.println("Mã sản phẩm đã bị trùng, vui lòng nhập lại: ");
                }
            } else {
                if (!firstInput) {
                    System.out.println("Mã sản phẩm không hợp lệ, vui lòng nhập lại: ");
                }
                firstInput = false;
            }
        }
        System.out.println("Nhập tên sản phẩm(5-50 ký tự");
            while (true){
                this.productName = scanner.nextLine().trim();
                if (this.productName.length()>=5 && this.productName.length()<=50){
                    break;
                }else {
                    System.out.println("Tên sản phẩm ko hợp lệ, Nhập lại");
                }
            }
        System.out.println("Nhập giá (lớn hơn 0) : ");
            while (true){
                this.importPrice = Double.parseDouble(scanner.nextLine());
                if (this.importPrice>0){
                    break;
                }else {
                    System.out.println("Giá nhập không hợp lệ, vui lòng nhập lại");
                }
            }
        System.out.println("Nhập giá xuất(ít nhất lớn hơn 20% so với giá nhập : ");
            while (true){
                this.exportPrice = Double.parseDouble(scanner.nextLine());
                if (this.exportPrice>importPrice*1.2){
                    break;
                }else {
                    System.out.println("Giá xuất không hợp le, Nhập lại : ");
                }
            }
            this.profit = calProfit();
        System.out.println("Nhập số lượng sản phẩm(lớn hơn 0) : ");
            while (true){
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity>0){
                    break;
                }else {
                    System.out.println("Số lượng không hợp lệ , Nhập lại");
                }
            }
        System.out.println("Nhập mô tả sản phẩm ");
            this.descriptions = scanner.nextLine();
        System.out.println("Nhập trạng thái sản phẩm (true là đang bán , false là không bán");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }
    public void displayData() {
        System.out.println("Product ID: " + productId + ", Product Name: " + productName + ", Import Price: " + importPrice + ", Export Price: " + exportPrice + ", Profit: " + profit + ", Quantity: " + quantity + ", Descriptions: " + descriptions + ", Status: " + (status?"Đang bán":"Chưa bán"));
    }
    public double calProfit(){
        return exportPrice - importPrice;
    }

    public String getproductId() {
        return productId;
    }

    public void setproductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
