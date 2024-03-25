package ra.bussiness.model;
import ra.bussiness.config.ShopConfig;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Employee {
    private String employeeId,employeeName;
    private LocalDate birthday;
    private boolean sex;
    private double salary;
    private Employee manager;
    private Department department;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, LocalDate birthday, boolean sex, double salary, Employee manager, Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.birthday = birthday;
        this.sex = sex;
        this.salary = salary;
        this.manager = manager;
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void inputData(boolean isAdd,List<Employee> employeeList,List<Department> departmentList){
        if (isAdd) {
            this.employeeId = getInputEmployeeId(employeeList);
        }
        this.employeeName = getInputEmployeeName();
        this.birthday = getInputBirthDay();
        System.out.println("Nhập lương :");
        this.salary = new Scanner(System.in).nextDouble();
        System.out.println("nhập giới tính true : nam, false:nữ");
        this.sex=new Scanner(System.in).nextBoolean();
        System.out.println("có chon quản lí hay không ?");
        System.out.println("1. Có");
        System.out.println("2. Không");
        while (true){
            System.out.println("Nhap lựa chọn");
            byte choice = new Scanner(System.in).nextByte();
            switch (choice){
                case 1:
                    this.manager= getInputManager(employeeList);
                    break;
                case 2:
                    break;
                default:
                    System.err.println("ko hợp lệ, nhập la");
            }
            if (choice==1 || choice ==2){
                break;
            }
        }
        this.department = getInputDepartment(departmentList);


    }
    private String getInputEmployeeId(List<Employee> employeeList){
        final String regex= "^E\\w{4}$";
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Nhập vào mã nhân viên");
            String employeeIdInput = sc.nextLine();
            if (employeeIdInput.matches(regex)){
                // đúng định dạng -> kiểm tra trùng lặp
                if (employeeList.stream().noneMatch(t->t.getEmployeeId().equals(employeeIdInput))){
                    // trùng lặp
                    return employeeIdInput;
                }
                System.err.println("Id đã tồn tại, vui long nhập giá trị khác");
            }else {
                System.err.println("Không đúng định dạng (E____)");
            }
        }
    }
    private LocalDate getInputBirthDay(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Nhập vào ngày sinh dd/MM/yyyy");
            String employeeDateInput = sc.nextLine();
            try {
                return LocalDate.parse(employeeDateInput,ShopConfig.DTF);
            }catch (DateTimeParseException e){
                System.err.println("Koong dung dinh dang");
            }
        }
    }
    private Department getInputDepartment(List<Department> departmentList){
        Scanner sc = new Scanner(System.in);
        // hiển thị danh sách phòng ban
        System.out.println("Danh sach phòng ban");
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.printf("| STT : %d | Name : %-15s |\n",i+1,departmentList.get(i).getDepartmentName());
        }
        while (true){
            System.out.println("Nhập vào vị trí phòng ban (theo STT)");
            int index = sc.nextInt();
            if (index>=1 && index<= departmentList.size()){
                Department department1 = departmentList.get(index - 1);
                // tăng số nhân viên lên 1
                department1.setTotalMembers(department1.getTotalMembers()+1);
                return department1;
            }
            System.err.println("Vi tri nhap khong hơp lẹ, vui lòng chọn lại");
        }
    }
    private Employee getInputManager(List<Employee> managerList){
        Scanner sc = new Scanner(System.in);
        // Kiểm tra xem danh sách quản lý có rỗng không
        if(managerList.isEmpty()) {
            System.out.println("Danh sách quản lý rỗng");
            return null;
        }
        // Hiển thị danh sách người quản lý
        System.out.println("Danh sách quản lý");
        for (int i = 0; i < managerList.size(); i++) {
            System.out.printf("| STT : %d | Name : %-15s |\n",i+1,managerList.get(i).employeeName);
        }

        while (true){
            System.out.println("Nhập vào quản lý của nhân viên (theo STT)");
            int index = sc.nextInt();
            if (index>=1 && index<= managerList.size()){
                return managerList.get(index-1);
            }
            System.err.println("Vị trí nhập không hợp lệ, vui lòng chọn lại");
        }
    }
    private String getInputEmployeeName(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Nhập vào tên nhân veen");
            String departmentNameInput = sc.nextLine();
            if (!departmentNameInput.isBlank()){
                return departmentNameInput;
            }else {
                System.err.println("Không được để trống");
            }
        }
    }


    public void displayData(){
        System.out.printf("| ID : %-5s | Name : %-15s |\n",
                employeeId,employeeName);
        System.out.println("----------------------------------------------------------------------------------");
    }
    public void displayDetailData(){
        System.out.printf("| ID : %-5s | Name : %-15s | DoB : %-10s | Sex : %-3s | Salary : %-10s | Manager: %-15s | Department : %-15s |\n",
                employeeId,employeeName,birthday.format(ShopConfig.DTF),sex?"Nam":"Nữ",salary,manager!=null?manager.getEmployeeName():"null",department.getDepartmentName());
        System.out.println("----------------------------------------------------------------------------------");
    }


}
