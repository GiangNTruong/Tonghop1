package ra.bussiness.impl;

import ra.bussiness.design.IDepartmentDesign;
import ra.bussiness.model.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import  static ra.bussiness.impl.EmployeeImplement.employeeList;
public class DepartmentImplement implements IDepartmentDesign {
    static List<Department> departmentList = new ArrayList<>();
    @Override
    public void displayAll() {
        if (departmentList.isEmpty()){
            System.err.println("Danh sach trống");
        }else {
            departmentList.forEach(Department::displayData);
        }
    }

    @Override
    public void addNew() {
        System.out.println("Nhap so luong phong ban muon them");
        byte count  =new Scanner(System.in).nextByte();
        for (int i = 1; i <=count ; i++) {
            System.out.println("Nhap thong tin cho phong ban thư "+i);
            Department department = new Department();
            department.inputData(true,departmentList);
            departmentList.add(department);
        }
        System.out.println("Đã them moi thanh cong ");
    }

    @Override
    public void update() {
        System.out.println("Hãy chọn department muốn update");
        String id = new Scanner(System.in).nextLine();
        Department edit = findById(id);
        if (edit==null){
            System.err.println("id không tim thấy");
            return;
        }

        System.out.println("Thong tin cũ là ");
        edit.displayData();

        // yêu cầu nhập thông tin mới
        System.out.println("Hãy nhap thong tin mơi ");
        edit.inputData(false,departmentList);
        System.out.println("Cập nhật thành công");
    }

    @Override
    public void delete() {
        System.out.println("Hãy chọn department muốn xóa");
        String id = new Scanner(System.in).nextLine();
        Department delete = findById(id);
        if (delete==null){
            System.err.println("id không tim thấy");
            return;
        }

        // kiểm  tra xem có nhân viên hay ko
        if (employeeList.stream().anyMatch(e->e.getDepartment().getDepartmentId().equals(id))){
            // có nhân viên
            System.err.println("Phong ban này có nhân viên, ko thể xóa");
            return;
        }

        departmentList.remove(delete);
        System.out.println("Xóa thành công");
    }

    @Override
    public Department findById(String id) {
        for (Department d : departmentList){
            if (d.getDepartmentId().equals(id))
                return d;
        }
        return null;
    }
}
