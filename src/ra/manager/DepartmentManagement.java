package ra.manager;

import ra.bussiness.design.IDepartmentDesign;
import ra.bussiness.impl.DepartmentImplement;

import java.util.Scanner;

public class DepartmentManagement {
    static IDepartmentDesign departmentDesign = new DepartmentImplement();

    public void displayDepartment(){
        while (true){
            System.out.println("================Department Menu===================");
            System.out.println("1- Hiển thi \n" +
                    "2- thêm mới\n" +
                    "3- sửa \n" +
                    "4- xóa \n" +
                    "5- Quay lại ");

            System.out.println("Nhập lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:
                    departmentDesign.displayAll();
                    break;
                case 2:
                    departmentDesign.addNew();
                    break;
                case 3:
                    departmentDesign.update();
                    break;
                case 4:
                    departmentDesign.delete();
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Nhập lựa chọn ko chính xác ");
            }
        }
    }
}
