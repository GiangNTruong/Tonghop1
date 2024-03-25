package ra.manager;

import ra.bussiness.design.IEmployeeDesign;
import ra.bussiness.impl.EmployeeImplement;

import java.util.Scanner;

public class EmployeeManagement {
    static IEmployeeDesign employeeDesign = new EmployeeImplement();
    public void displayEmployeeMenu(){
        while (true){
            System.out.println("================Employee Menu===================");
            System.out.println("1- Hiển thi \n" +
                    "2- thêm mới\n" +
                    "3- sửa \n" +
                    "4- xóa \n" +
                    "5- xem chi tiết thông tin "+
                    "6- Quay lại ");

            System.out.println("Nhập lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:employeeDesign.displayAll();
                break;
                case 2 : employeeDesign.addNew();
                break;
                case 3 : employeeDesign.update();
                break;
                case 4:employeeDesign.delete();
                break;
                case 5:employeeDesign.detailInfo();
                break;
                case 6:
                    return;
                default:
                    System.err.println("Nhap lua chon ko chinh xác");
            }
        }
    }
}
