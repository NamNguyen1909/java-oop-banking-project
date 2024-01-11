/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author THANH_NAM
 */
public class Bank {

    private List<Customer> customerList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();

    private List<UnlimitedAccount> unlimitedAccountList = new ArrayList<>();
    private List<EAccount> employeeAccountList = new ArrayList<>();

    private Person signedInPer = null;

    public Bank() {
    }

    public void addCustomer(Customer... customers) {
        this.getCustomerList().addAll(Arrays.asList(customers));
    }

    public void removeCustomer(Customer customer) {
        this.getCustomerList().remove(customer);
        //so sanh bang equals nen phai override equals
    }

    public void addEmployee(Employee... employees) {
        this.getEmployeeList().addAll(Arrays.asList(employees));
    }

    public void removeEmployee(Employee employees) {
        this.getEmployeeList().remove(employees);
    }

    public void addUnlimitedAccount(UnlimitedAccount... accounts) {
        this.getUnlimitedAccountList().addAll(Arrays.asList(accounts));
    }

    public void removeUnlimitedAccount(UnlimitedAccount account) {
        this.getUnlimitedAccountList().remove(account);
        //so sanh bang equals nen phai override equals
    }

    public void addEmAcc(EAccount... accounts) {
        this.getEmployeeAccountList().addAll(Arrays.asList(accounts));
    }

    public void displayCustomerList() {
        System.out.println("\nDanh sach khach hang : ");

        this.getCustomerList().forEach(u -> u.display());
    }

    public void displayEmployeeList() {
        System.out.println("\nDanh sach nhan vien : ");

        this.getEmployeeList().forEach(e -> e.display());
    }

    public void displayAccountList() {
        System.out.println("\nDanh sach tai khoan: ");

        this.getUnlimitedAccountList().forEach(u -> u.display());
    }

    //Tra cứu khách hàng theo họ tên và mã số khách hàng.
    public List<Customer> searchCustomer(String kw) {
        List<Customer> result = this.getCustomerList().stream()
                .filter(c -> c.getFullName().contains(kw) || c.getCustomerID().equals(kw))
                .collect(Collectors.toList());

        // Kiểm tra xem danh sách có rỗng hay không
        if (result.isEmpty()) {
            return null;  // hoặc trả về một giá trị đặc biệt để biểu thị không tìm thấy
        } else {
            return result;
        }
    }

    //Tra cứu danh sách tài khoản của một khách hàng theo mã số khách hàng.
    public List<Account> searchCusAcc(String kw) {
        return this.getCustomerList().stream()
                .filter(c -> c.getCustomerID().equals(kw))
                .findFirst()
                .map(Customer::getAccList)
                .orElse(Collections.emptyList());
    }

    public void sortCustomersByTotalDepositDescending() {
        this.getCustomerList().sort((c1, c2) -> {
            float totalDeposit1 = c1.getTotalDeposite();
            float totalDeposit2 = c2.getTotalDeposite();
            // Sắp xếp giảm dần
            return Float.compare(totalDeposit2, totalDeposit1);
        });
    }

    public static void menu() throws InterruptedException {
        Thread.sleep(1000);

        System.out.println("\n\t\t===== MENU =====");
        System.out.println("1. Mo tai khoan");
        System.out.println("2. Dang nhap");
        System.out.println("3. Gui/Rut tien");
        System.out.println("4. Tinh tien lai");
        System.out.println("5. Thoat");
    }

    public static void cusMenu2() {
        System.out.println("\n1. Thong tin ca nhan");
        System.out.println("2. Danh sach tai khoan");
        System.out.println("3. Doi mat khau");
        System.out.println("4. Tao tai khoan co ky han");
        System.out.println("5. Gui tien");
        System.out.println("6. Rut tien");
        System.out.println("7. Tinh tien lai");
        System.out.println("8. Dang xuat");
    }

    public static void emMenu2() {
        System.out.println("\n1. Danh sach tat ca khach hang");
        System.out.println("2. Danh sach tat ca tai khoan");
        System.out.println("3. Them khach hang");
        System.out.println("4. Xoa khach hang");
        System.out.println("5. Them tai khoan");
        System.out.println("6. Xoa tai khoan");
        System.out.println("7. Tra cuu khach hang theo ho ten hoac ma so");
        System.out.println("8. Tra cuu danh sach tai khoan theo ma khach hang");
        System.out.println("9. Sap xep khach hang theo tong so tien gui giam dan");
        System.out.println("10. Dang xuat");

    }

    public static int getUserSelection(int min, int max) {
        String input = Configuration.sc.nextLine();

        if (input.matches("\\d+") && !input.isEmpty()) {
            int x = Integer.parseInt(input);
            if (min <= x && x <= max) {
                return x;
            }
        }

        System.out.println("Vui long chon lai!");
        return -1;
    }

    public void signIn() {
        System.out.print("Ten dang nhap: ");
        String username = Configuration.sc.nextLine();
        System.out.print("Mat khau: ");
        String password = Configuration.sc.nextLine();

        // Tạo một biến để lưu thông tin đăng nhập
        Person signedInPerson = null;

        for (Customer customer : this.getCustomerList()) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                // Lưu thông tin của người đăng nhập
                signedInPerson = customer;
                System.out.println("Dang nhap thanh cong, Chao mung " + customer.getFullName());
                break;
            }
        }

        // Nếu không tìm thấy trong customerList, kiểm tra trong employeeList
        if (signedInPerson == null) {
            for (Employee employee : this.getEmployeeList()) {
                if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                    // Lưu thông tin của người đăng nhập
                    signedInPerson = employee;
                    System.out.println("Dang nhap thanh cong, Chao mung " + employee.getFullName());
                    break;
                }
            }
        }

        // Kiểm tra nếu người đăng nhập là khách hàng hoặc nhân viên
        if (signedInPerson instanceof Customer) {
            // Lưu thông tin của khách hàng đã đăng nhập
            setSignedIn((Customer) signedInPerson);
        } else if (signedInPerson instanceof Employee) {
            // Lưu thông tin của nhân viên đã đăng nhập
            setSignedIn((Employee) signedInPerson);
        } else {
            System.out.println("Ten dang nhap hoac mat khau khong dung. Vui long thu lai!");
        }

    }

    public void signOut() {
        this.setSignedInPer(null);
    }
    //---------------------------------------------------------------------------

    public void writeCustomerListToFile(List<Customer> customerList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Customer customer : customerList) {
                // Ghi thông tin của mỗi khách hàng vào tệp tin
                writer.write(customer.getFullName() + ";"
                        + customer.getGender() + ";"
                        + customer.getDateOfBirth().format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)) + ";" // Chuyển định dạng ngày
                        + customer.getHometown() + ";"
                        + customer.getIDCard() + ";"
                        + customer.getCustomerID() + ";"
                        + customer.getUsername() + ";"
                        + customer.getPassword());
                writer.newLine();
            }
            System.out.println("Danh sach khach hang da duoc ghi vao file.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi danh sach khach hang vao file: " + e.getMessage());
        }
    }

    public void readCustomerListFromFile(List<Customer> customerList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin từ dòng đọc được
                String[] parts = line.split(";");
                if (parts.length == 8) { // Kiểm tra có đủ thông tin hay không
                    String fullName = parts[0];
                    String gender = parts[1];
                    String dateOfBirth = parts[2];
                    String hometown = parts[3];
                    String IDCard = parts[4];
                    String customerID = parts[5];
                    String username = parts[6];
                    String password = parts[7];

                    // Tạo đối tượng Customer và thêm vào danh sách
                    Customer customer = new Customer(fullName, gender, dateOfBirth, hometown, IDCard, customerID, username, password);
                    customerList.add(customer);
                }
            }
            System.out.println("Danh sach khach hang da duoc doc tu file.");
//        } catch (IOException | Exception e) {
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach khach hang tu file: " + e.getMessage());
        }
    }

    public void writeEmployeeListToFile(List<Employee> employeeList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Employee employee : employeeList) {
                // Ghi thông tin của mỗi nhân viên vào tệp tin
                writer.write(employee.getFullName() + ";"
                        + employee.getGender() + ";"
                        + employee.getDateOfBirth().format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)) + ";" // Chuyển định dạng ngày
                        + employee.getHometown() + ";"
                        + employee.getIDCard() + ";"
                        + employee.getEmployeeID() + ";"
                        + employee.getUsername() + ";"
                        + employee.getPassword());
                writer.newLine();
            }
            System.out.println("Danh sach nhan vien da duoc ghi vao file.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi danh sach nhan vien vao file: " + e.getMessage());
        }
    }

    public void readEmployeeListFromFile(List<Employee> employeeList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin từ dòng đọc được
                String[] parts = line.split(";");
                if (parts.length == 8) { // Kiểm tra có đủ thông tin hay không
                    String fullName = parts[0];
                    String gender = parts[1];
                    String dateOfBirth = parts[2];
                    String hometown = parts[3];
                    String IDCard = parts[4];
                    String employeeID = parts[5];
                    String username = parts[6];
                    String password = parts[7];

                    // Tạo đối tượng Employee và thêm vào danh sách
                    Employee employee = new Employee(fullName, gender, dateOfBirth, hometown, IDCard, employeeID, username, password);

                    employeeList.add(employee);
                }
            }
            System.out.println("Danh sach nhan vien da duoc doc tu file.");
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach nhan vien tu file: " + e.getMessage());
        }
    }

    public void writeEmployeeAccountListToFile(List<EAccount> employeeAccountList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (EAccount employeeAccount : employeeAccountList) {
                // Ghi thông tin của mỗi tài khoản nhân viên vào tệp tin
                writer.write(employeeAccount.getAccountID() + ";"
                        + employeeAccount.getUser().getEmployeeID());
                writer.newLine();
            }
            System.out.println("Danh sach tai khoan nhan vien da duoc ghi vao file.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi danh sach tai khoan nhan vien vao file: " + e.getMessage());
        }
    }

    public void readEmployeeAccountListFromFile(List<EAccount> employeeAccountList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin từ dòng đọc được
                String[] parts = line.split(";");
                if (parts.length == 2) { // Kiểm tra có đủ thông tin hay không
                    String accountID = parts[0];
                    String employeeID = parts[1];

                    // Tìm nhân viên trong danh sách nhân viên
                    Employee employee = getEmployeeByID(employeeID);

                    if (employee != null) {
                        // Tạo đối tượng EAccount và thêm vào danh sách
                        EAccount employeeAccount = new EAccount(employee, accountID);
                        employeeAccountList.add(employeeAccount);
                    }
                }
            }
            System.out.println("Danh sach tai khoan nhan vien da duoc doc tu file.");
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach tai khoan nhan vien tu file: " + e.getMessage());
        }
    }

// Helper method to get Employee by ID
    private Employee getEmployeeByID(String employeeID) {
        for (Employee employee : this.getEmployeeList()) {
            if (employee.getEmployeeID().equals(employeeID)) {
                return employee;
            }
        }
        return null;
    }

    public void writeUnlimitedAccountListToFile(List<UnlimitedAccount> unlimitedAccountList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (UnlimitedAccount account : unlimitedAccountList) {
                // Ghi thông tin của mỗi tài khoản UnlimitedAccount vào tệp tin
                writer.write(account.getAccountID() + ";"
                        + account.getUser().getCustomerID() + ";"
                        + account.getBalance());
                writer.newLine();
            }
            System.out.println("Danh sach tai khoan UnlimitedAccount da duoc ghi vao file.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi danh sach tai khoan UnlimitedAccount vao file: " + e.getMessage());
        }
    }

    public void readUnlimitedAccountListFromFile(List<UnlimitedAccount> unlimitedAccountList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin từ dòng đọc được
                String[] parts = line.split(";");
                if (parts.length == 3) { // Kiểm tra có đủ thông tin hay không
                    String accountID = parts[0];
                    String customerID = parts[1];
                    double balance = Double.parseDouble(parts[2]);

                    // Tìm khách hàng trong danh sách khách hàng
                    Customer customer = getCustomerByID(customerID);

                    if (customer != null) {
                        // Tạo đối tượng UnlimitedAccount và thêm vào danh sách
                        UnlimitedAccount unlimitedAccount = new UnlimitedAccount(customer, balance, accountID);
                        unlimitedAccountList.add(unlimitedAccount);
                    }
                }
            }
            System.out.println("Danh sach tai khoan UnlimitedAccount da duoc doc tu file.");
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach tai khoan UnlimitedAccount tu file: " + e.getMessage());
        }
    }

// Helper method to get Customer by ID
    private Customer getCustomerByID(String customerID) {
        for (Customer customer : this.getCustomerList()) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    //---------------------------------------------------------------------------
    /**
     * @return the customerList
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * @return the employeeList
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * @return the unlimitedAccountList
     */
    public List<UnlimitedAccount> getUnlimitedAccountList() {
        return unlimitedAccountList;
    }

    /**
     * @param accountList the unlimitedAccountList to set
     */
    public void setUnlimitedAccountList(List<UnlimitedAccount> accountList) {
        this.unlimitedAccountList = accountList;
    }

    /**
     * @return the employeeAccountList
     */
    public List<EAccount> getEmployeeAccountList() {
        return employeeAccountList;
    }

    /**
     * @param employeeAccountList the employeeAccountList to set
     */
    public void setEmployeeAccountList(List<EAccount> employeeAccountList) {
        this.employeeAccountList = employeeAccountList;
    }

    /**
     * @return the signedInPer
     */
    public Person getSignedInPer() {
        return signedInPer;
    }

    /**
     * @return the signedInPer
     */
    public Customer getSignedInCustomer() {
        if (signedInPer instanceof Customer) {
            return (Customer) signedInPer;
        }
        return null;
    }

    /**
     * @return the signedInPer
     */
    public Employee getSignedInEmployee() {
        if (signedInPer instanceof Employee) {
            return (Employee) signedInPer;
        }
        return null;
    }

    /**
     * @param signedInPer the signedInPer to set
     */
    public void setSignedInPer(Person signedInPer) {
        this.signedInPer = signedInPer;
    }

    /**
     * @param signedIn the signedInPer to set
     */
    public void setSignedIn(Customer signedIn) {
        this.signedInPer = signedIn;
    }

    /**
     * @param signedIn the signedInPer to set
     */
    public void setSignedIn(Employee signedIn) {
        this.signedInPer = signedIn;
    }

}
