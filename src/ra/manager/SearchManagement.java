package ra.manager;

import java.util.Scanner;

import static ra.manager.EmployeeManagement.employeeDesign;


public class SearchManagement {
    public void displayDepartmentMenu() {
        while (true) {
            System.out.println("================Department Menu===================");
            System.out.println("1- Hiển thị danh sách nhân viên của phòng ban theo mã phòng");
            System.out.println("2- Thống kê số lượng nhân viên trung bình của mỗi phòng ");
            System.out.println("3- Tìm ra 5 phòng có số lượng nhân viên đông nhất\n" +
                    "4- Tìm ra người quản lý nhiều  nhân viên nhất\n" +
                    "5- Tìm ra 5 nhân viên có tuổi cao nhất công ty\n" +
                    "6- Tìm ra 5 nhân viên hưởng lương cao nhất");
            System.out.println("7- Quay lại");

            System.out.println("Nhập lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice) {
                case 1:
                    employeeDesign.getListEmployeeByDepartment();
                    break;
                case 2:
                    employeeDesign.getAvgEmployeesPerDepartment();
                    break;
                case 3:
                    employeeDesign.find5DepartmentCrowded();
                    break;
                case 4:
                    employeeDesign.findMostManager();
                    break;
                case 5:
                    employeeDesign.find5OldestEmployee();
                    break;
                case 6:
                    employeeDesign.find5HighestSalary();
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Nhap lua chon ko chinh xác");
            }
        }
    }
}
