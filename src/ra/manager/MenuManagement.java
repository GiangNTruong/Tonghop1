package ra.manager;

import java.util.Scanner;

public class MenuManagement {
    private static EmployeeManagement employeeManagement = new EmployeeManagement();
    private static DepartmentManagement departmentManagement = new DepartmentManagement();
    private static SearchManagement searchManagement = new SearchManagement();
    public static void main(String[] args) {
        while (true){
            System.out.println("================MENU===================");
            System.out.println("1- Quản trị phòng ban : \n" +
                    "2- Quản lý nhân viên\n" +
                    "3- Tìm kiếm \n" +
                    "4.Thoát ");

            System.out.println("Nhập lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:
                    departmentManagement.displayDepartment();
                    break;
                case 2:
                    employeeManagement.displayEmployeeMenu();
                    break;
                case 3:
                    searchManagement.displayDepartmentMenu();
                    break;
                case 4:
                    System.out.println("Thoát");
                    return;
                default:
                    System.err.println("Nhap lua chon ko chinh xác");
            }
        }
    }
}
