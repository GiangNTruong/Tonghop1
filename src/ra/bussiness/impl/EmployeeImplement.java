package ra.bussiness.impl;

import ra.bussiness.design.IEmployeeDesign;
import ra.bussiness.model.Department;
import ra.bussiness.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static ra.bussiness.impl.DepartmentImplement.departmentList;

public class EmployeeImplement implements IEmployeeDesign {
    static List<Employee> employeeList = new ArrayList<>();
    @Override
    public void detailInfo() {
        System.out.println("hãy chọn employee bạn muốn xem chi tiết");
        String id = new Scanner(System.in).nextLine();
        Employee employee = findById(id);
        if (employee == null){
            System.err.println("Id ko tìm thấy");
            return;
        }
        System.out.println("CHi tiết thông tin");
        employee.displayDetailData();
    }

    //+	Hiển thị danh sách nhân viên của phòng ban theo mã phòng
    @Override
    public void getListEmployeeByDepartment() {
        System.out.println("Danh sách phòng ban ");
        departmentList.forEach(department -> System.out.printf("| ID : %-5s | DepartmentName : %-10s |\n",department.getDepartmentId(),department.getDepartmentName()));
        System.out.println("Hãy chọn department bạn muốn xem danh sách ");
        String id = new Scanner(System.in).nextLine();
        if(departmentList.stream().noneMatch(t->t.getDepartmentId().equals(id))){
            System.err.println("id ko tồn tại");
            return;
        }
        //tìm thấy
        List<Employee> filterList = employeeList.stream().filter(e->e.getDepartment().getDepartmentId().equals(id)).collect(Collectors.toList());
        if (filterList.isEmpty()){
            System.err.println("Phòng ban này trống");
        }else {
            filterList.forEach(Employee::displayData);
        }
    }

    //+	Thống kê số lượng nhân viên trung bình của mỗi phòng
    @Override
    public void getAvgEmployeesPerDepartment() {
        System.out.println("So lượng nhân viên trung bình là : " + (employeeList.size()/departmentList.size()));
    }

    //+	Tìm ra người quản lý nhiều  nhân viên nhất
    @Override
    public void findMostManager() {
        Comparator<Employee> comparator = ((o1, o2) -> Long.compare(countEmployeeManager(o2),countEmployeeManager(o1)));
        Optional<Employee> employeeOptional = employeeList.stream().max(comparator);
        Employee manager = employeeOptional.orElseThrow(()-> new RuntimeException("Ko tim thay"));
        System.out.println(" Người quản lí có nhiều nhan viên nhất là \n");
        manager.displayData();
        System.out.println("Số lượng nhân viên ma " +manager.getEmployeeName() + "quản lí là " + countEmployeeManager(manager) );

    }
    private long countEmployeeManager(Employee manager){
       return employeeList.stream().filter(e-> e.getManager()!=null && e.getManager().equals(manager)).count();
    }

    //+	Tìm ra 5 nhân viên có tuổi cao nhất công ty
    @Override
    public void find5OldestEmployee() {
        employeeList.stream().sorted(Comparator.comparing(Employee::getBirthday)).limit(5).forEach(Employee::displayData);
    }

    //+	Tìm ra 5 nhân viên hưởng lương cao nhất
    @Override
    public void find5HighestSalary() {
        employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).limit(5).forEach(Employee::displayData);
    }

    //+	Tìm ra 5 phòng có số lượng nhân viên đông nhất
    //Nhóm các nhân viên theo getDepartment():collect(Collectors.groupingBy(Employee::getDepartment)
    @Override
    public void find5DepartmentCrowded() {
        Map<Department,Long> departmentLongMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        departmentLongMap.entrySet().stream().sorted(Map.Entry.<Department,Long>comparingByValue().reversed()).limit(5).forEach(entry-> System.out.println("Department: "+entry.getKey().getDepartmentName()+ ", Số nhân viên : " +entry.getValue()));
    }



    @Override
    public void displayAll() {
        if (employeeList.isEmpty()) {
            System.err.println("Danh sach trống");
        } else {
            employeeList.forEach(Employee::displayData);
        }
    }

    @Override
    public void addNew() {
        if (departmentList.isEmpty()) {
            System.err.println("chưa có phong ban, them phong ban trươc");
            return;
        }
        System.out.println("Nhap so luong nhan vien muon them");
        byte count = new Scanner(System.in).nextByte();
        for (int i = 1; i <= count; i++) {
            System.out.println("Nhap thong tin cho nhan vien thư " + i);
            Employee employee = new Employee();
            employee.inputData(true, employeeList, departmentList);
            employeeList.add(employee);
        }
        System.out.println("Đã them moi thanh cong ");
    }

    @Override
    public void update() {
        System.out.println("Hãy chọn department muốn update");
        String id = new Scanner(System.in).nextLine();
        Employee edit = findById(id);
        if (edit == null) {
            System.err.println("id không tim thấy");
            return;
        }

        System.out.println("Thông tin cũ ");
        edit.displayData();

        // trừ số lương phòng ban cũ đi 1
        Department old = edit.getDepartment();
        old.setTotalMembers(old.getTotalMembers() - 1);

        System.out.println("Nhập thông tin mới");
        edit.inputData(false, employeeList,departmentList);
        System.out.println("Cập nhật thành công");
    }

    @Override
    public void delete() {

        // kieemr tra xem cos quan li dua nao ko
        // xoa xong phai giam so nhan vien cua phong ban di 1
        System.out.println("Hãy chọn department muốn xoa");
        String id = new Scanner(System.in).nextLine();
        Employee delete = findById(id);
        if (delete == null) {
            System.err.println("id không tim thấy");
            return;
        }

        employeeList.remove(delete);

        System.out.println("Xóa thành cong");

    }

    @Override
    public Employee findById(String id) {
        for (Employee e : employeeList) {
            if (e.getEmployeeId().equals(id))
                return e;
        }
        return null;
    }
}
