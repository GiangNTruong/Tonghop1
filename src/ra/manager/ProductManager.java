package ra.manager;

import ra.model.Product;

import java.util.Scanner;

public class ProductManager {
    private static Product[] products = new Product[100];
    private static int productCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("***********************MENU**************************");
            System.out.println("1. Nhập thông tin cho n sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo khoảng giá bán");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm theo mã SP");
            System.out.println("10. Xóa theo mã SP");
            System.out.println("0. Thoát");
            System.out.println("***************************************************");
            System.out.println("Vui lòng chọn một chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Nhập số lượng sản phẩm cần thêm : ");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        Product product = new Product();
                        product.inputData(scanner, products);
                        products[productCount++] = product;
                    }
                    break;
                case 2:
                    for (int i = 0; i < productCount; i++) {
                        products[i].displayData();
                    }
                    break;
                case 3:
                    for (int i = 0; i < productCount; i++) {
                        double profit = products[i].calProfit();
                        System.out.println("Product ID: " + products[i].getproductId()+ ", Profit: "+profit);
                    }
                    break;
                case 4:
                    for (int i = 0; i < productCount; i++) {
                        for (int j = 0; j < productCount-i-1; j++) {
                            if (products[i].calProfit()<products[j+1].calProfit()){
                                Product temp = products[j];
                                products[j]= products[j+1];
                                products[j+1]=temp;

                            }
                        }
                    }
                    for (int i = 0; i < productCount; i++) {
                        products[i].displayData();
                    }
                    break;
                case 5:
                    System.out.println("Nhập khoảng giá từ : ");
                    double formPrice = Double.parseDouble(scanner.nextLine());
                    System.out.println("Nhập giá đến");
                    double toPrice = Double.parseDouble(scanner.nextLine());
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getExportPrice() >= formPrice && products[i].getExportPrice()<=toPrice){
                            products[i].displayData();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Nhập tên sản phẩm cần tìm ");
                    String name = scanner.nextLine();
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getProductName().equalsIgnoreCase(name)){
                            products[i].displayData();
                        }
                    }
                    break;
                case 7:
                    System.out.println("Nhập mã sản phẩm cần nhập : ");
                    String productid1= scanner.nextLine();
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getproductId().equals(productid1)){
                            System.out.println("Nhập số lượng sản pẩm câ nhập : ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            products[i].setQuantity(products[i].getQuantity() + quantity);
                        }
                    }
                    break;
                case 8:
                    System.out.println("Nhập tên sản phẩm cần bán : ");
                    String productName = scanner.nextLine();
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getProductName().equalsIgnoreCase(productName)&& products[i].isStatus()){
                            System.out.println("Nhập số lương sản phẩm cần bán : ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity<=products[i].getQuantity()){
                                products[i].setQuantity(products[i].getQuantity()-quantity);
                            }else {
                                System.out.println("Số lượng sản phẩm không đủ để bán");
                            }
                        }
                    }
                    break;
                case 9:
                    System.out.println("Nhập mã sản phẩm cần cập nhật trạng thái : ");
                    String id = scanner.nextLine();
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getproductId().equals(id)){
                            products[i].setStatus(!products[i].isStatus());
                            break;
                        }
                    }
                    break;
                case 10:
                    System.out.println("Nhập mã sản phẩm cần xóa:");
                    String deleteId = scanner.nextLine();
                    for (int i = 0; i < productCount; i++) {
                        if (products[i].getproductId().equals(deleteId)){
                            for (int j = 0; j < productCount-1; j++) {
                                products[j]=products[j+1];
                            }
                            productCount--;
                            break;
                        }
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
