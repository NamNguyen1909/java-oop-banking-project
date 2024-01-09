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

    private List<Account> accountList = new ArrayList<>();
    private List<Account> emAccList = new ArrayList<>();

    private Person signedInPer = null;
    private Account signedInAcc = null;

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

    public void addAccount(Account... accounts) {
        this.getAccountList().addAll(Arrays.asList(accounts));
    }

    public void removeAccount(Account account) {
        this.getAccountList().remove(account);
        //so sanh bang equals nen phai override equals
    }

    public void addEmAcc(EAccount... accounts) {
        this.getEmAccList().addAll(Arrays.asList(accounts));
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

        this.getAccountList().forEach(u -> u.display());
    }

    //Tra cứu khách hàng theo họ tên và mã số khách hàng.
    public List<Customer> searchCustomer(String kw) {
        return this.getCustomerList().stream()
                .filter(c -> c.getFullName().contains(kw) || c.getCustomerID().equals(kw))
                .collect(Collectors.toList());
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

        for (Account account : this.getAccountList()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                // Lưu thông tin của người đăng nhập
                signedInPerson = account.getUser();
                setSignedInAcc(account);
                System.out.println("Dang nhap thanh cong, Chao mung " + account.getUser().getFullName());
                break;
            }
        }

        // Nếu không tìm thấy trong accountList, kiểm tra trong emAccList
        if (signedInPerson == null) {
            for (Account emAccount : this.getEmAccList()) {
                if (emAccount.getUsername().equals(username) && emAccount.getPassword().equals(password)) {
                    // Lưu thông tin của người đăng nhập
                    signedInPerson = emAccount.getUser();
                    setSignedInAcc(emAccount);
                    System.out.println("Dang nhap thanh cong, Chao mung " + emAccount.getUser().getFullName());
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
        this.setSignedInAcc(null);
        this.setSignedInPer(null);
    }
    //---------------------------------------------------------------------------

    public void writeCustomerListToFile(List<Customer> customerList, String filePath) {
        //,false de khong ghi tiep ma ghi de
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,false))) {
            for (Customer customer : customerList) {
                // Ghi thông tin của mỗi khách hàng vào tệp tin
                writer.write(customer.getFullName() + ";"
                        + customer.getGender() + ";"
                        + customer.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" // Chuyển định dạng ngày
                        + customer.getHometown() + ";"
                        + customer.getIDCard() + ";"
                        + customer.getCustomerID());
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
                if (parts.length == 5 || parts.length == 6) { // Kiểm tra có đủ thông tin hay không
                    String fullName = parts[0];
                    String gender = parts[1];
                    String dateOfBirth = parts[2];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng mong muốn
                    String hometown = parts[3];
                    String IDCard = parts[4];
                    String customerID = (parts.length == 6) ? parts[5] : null;

                    // Tạo đối tượng Customer và thêm vào danh sách
                    Customer customer = new Customer(fullName, gender, dateOfBirth, hometown, IDCard);

                    // Nếu customerID được đặt trong file, thiết lập cho đối tượng Customer
                    if (customerID != null && !customerID.isEmpty()) {
                        customer.setCustomerID(customerID);
                    }

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,false))) {
            for (Employee employee : employeeList) {
                // Ghi thông tin của mỗi nhân viên vào tệp tin
                writer.write(employee.getFullName() + ";"
                        + employee.getGender() + ";"
                        + employee.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" // Chuyển định dạng ngày
                        + employee.getHometown() + ";"
                        + employee.getIDCard() + ";"
                        + employee.getEmployeeID());
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
                if (parts.length == 5 || parts.length == 6) { // Kiểm tra có đủ thông tin hay không
                    String fullName = parts[0];
                    String gender = parts[1];
                    String dateOfBirth = parts[2];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng mong muốn
                    String hometown = parts[3];
                    String IDCard = parts[4];
                    String employeeID = (parts.length == 6) ? parts[5] : null;

                    // Tạo đối tượng Employee và thêm vào danh sách
                    Employee employee = new Employee(fullName, gender, dateOfBirth, hometown, IDCard);

                    // Nếu employeeID được đặt trong file, thiết lập cho đối tượng Employee
                    if (employeeID != null && !employeeID.isEmpty()) {
                        employee.setEmployeeID(employeeID);
                    }

                    employeeList.add(employee);
                }
            }
            System.out.println("Danh sach nhan vien da duoc doc tu file.");
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach nhan vien tu file: " + e.getMessage());
        }
    }

    public void writeAccountListToFile(List<Account> accountList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,false))) {
            for (Account account : accountList) {
                // Ghi thông tin của mỗi tài khoản vào tệp tin
                writer.write(account.getUsername() + ";"
                        + account.getPassword() + ";"
                        + account.getBalance());
                writer.newLine();
            }
            System.out.println("Danh sach tai khoan da duoc ghi vao file.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi danh sach tai khoan vao file: " + e.getMessage());
        }
    }

    public void readAccountListFromFile(List<Account> accountList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin từ dòng đọc được
                String[] parts = line.split(";");
                if (parts.length == 3) { // Kiểm tra có đủ thông tin hay không
                    String username = parts[0];
                    String password = parts[1];
                    float balance = Float.parseFloat(parts[2]);

                    // Tìm customer có customerId giống với username
                    Customer customer = this.getCustomerList()
                            .stream()
                            .filter(c -> c.getCustomerID().equals(username))
                            .findFirst()
                            .orElse(null);
                    if (customer != null) {
                        Account account = new Account(customer);
                        account.setUsername(username);
                        account.setPassword(password);
                        account.setBalance(balance);
                        accountList.add(account);
                    } else {
                        System.out.println("Khong tim thay khach hang ung voi ID: " + username);
                    }

                }
            }
            System.out.println("Danh sach tai khoan da duoc doc tu file.");
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach tai khoan tu file: " + e.getMessage());
        }
    }

    public void writeEmAccListToFile(List<Account> emAccList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,false))) {
            for (Account emAccount : emAccList) {
                // Ghi thông tin của mỗi tài khoản của nhân viên vào tệp tin
                writer.write(emAccount.getUsername() + ";"
                        + emAccount.getPassword());
                writer.newLine();
            }
            System.out.println("Danh sach tai khoan nhan vien da duoc ghi vao file.");
        } catch (IOException e) {
            System.err.println("Loi khi ghi danh sach tai khoan nhan vien vao file: " + e.getMessage());
        }
    }

    public void readEmAccListFromFile(List<Account> emAccList, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin từ dòng đọc được
                String[] parts = line.split(";");
                if (parts.length == 2) { // Kiểm tra có đủ thông tin hay không
                    String username = parts[0];
                    String password = parts[1];

                    Employee employee = this.getEmployeeList()
                            .stream()
                            .filter(e -> e.getEmployeeID().equals(username))
                            .findFirst().orElse(null);
                    if (employee != null) {
                        // Tạo đối tượng EAccount và thêm vào danh sách
                        EAccount emAccount = new EAccount(employee, password);
                        emAccount.setUsername(username);

                        emAccList.add(emAccount);
                    }
                     else {
                        System.out.println("Khong tim thay nhan vien ung voi ID: " + username);
                    }

                }
            }
            System.out.println("Danh sach tai khoan nhan vien da duoc doc tu file.");
        } catch (Exception e) {
            System.err.println("Loi khi doc danh sach tai khoan nhan vien tu file: " + e.getMessage());
        }
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
     * @return the accountList
     */
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * @param accountList the accountList to set
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * @return the emAccList
     */
    public List<Account> getEmAccList() {
        return emAccList;
    }

    /**
     * @param emAccList the emAccList to set
     */
    public void setEmAccList(List<Account> emAccList) {
        this.emAccList = emAccList;
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

    /**
     * @return the signedInAcc
     */
    public Account getSignedInAcc() {
        return signedInAcc;
    }

    /**
     * @param signedInAcc the signedInAcc to set
     */
    public void setSignedInAcc(Account signedInAcc) {
        this.signedInAcc = signedInAcc;
    }

}
