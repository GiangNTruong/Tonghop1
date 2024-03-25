package ra.bussiness.model;

import java.util.List;
import java.util.Scanner;

public class Department {
    private String departmentId,departmentName ;
    private int totalMembers; // giá trị mặc định = 0

    public Department() {
    }

    public Department(String departmentId, String departmentName, int totalMembers) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.totalMembers = totalMembers;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public void inputData(boolean isAdd,List<Department> departmentList){
        if (isAdd) {
            this.departmentId = getInputDepartmentId(departmentList);
        }
        this.departmentName = getInputDepartmentName(departmentList);
    }
    private String getInputDepartmentId(List<Department> departmentList){
        final String regex= "^D\\w{3}$";
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Nhập vào mã phòng ban");
            String departmentIdInput = sc.nextLine();
            if (departmentIdInput.matches(regex)){
                // đúng định dạng -> kiểm tra trùng lặp
                if (departmentList.stream().noneMatch(t->t.getDepartmentId().equals(departmentIdInput))){
                    // trùng lặp
                    return departmentIdInput;
                }
                System.err.println("Id đã tồn tại, vui long nhập giá trị khác");
            }else {
                System.err.println("Không đúng định dạng (D___)");
            }
        }
    }
    private String getInputDepartmentName(List<Department> departmentList){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Nhập vào tên phòng ban");
            String departmentNameInput = sc.nextLine();
            if (!departmentNameInput.isBlank()){
                // đúng định dạng -> kiểm tra trùng lặp
                if (departmentList.stream().noneMatch(t->t.getDepartmentName().equals(departmentNameInput))){
                    // trùng lặp
                    return departmentNameInput;
                }
                System.err.println("Tên đã tồn tại, vui long nhập giá trị khác");
            }else {
                System.err.println("Không được để trống");
            }
        }
    }
    public void displayData(){
        System.out.printf("| ID : %-5s | Name : %-15s | TotalMembers : %-3s |\n",
                departmentId,departmentName,totalMembers);
        System.out.println("------------------------------------------------------------------------");
    }



}
