package sample.model;

public class Employee {

    private int idEmployee;
    private String name;
    private String middleName;
    private String lastName;
    private String post;
    private String liability;
    private int numberPhone;
    private String allNameEmployee;
    private Enterprise enterprise = new Enterprise();
    private Technique technique = new Technique();

    public Employee(){
    }

    public Employee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
    }

    public Employee(int idEmployee, String name, String middleName) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.middleName = middleName;
    }

    public Employee(int idEmployee, String name, String middleName, String lastName) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Employee(int idEmployee, String name, String middleName, String lastName, String post) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.post = post;
    }

    public Employee(int idEmployee, String name, String middleName, String lastName, String post, String liability) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.post = post;
        this.liability = liability;
    }

    public Employee(int idEmployee, String name, String middleName, String lastName, String post, String liability, int numberPhone) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.post = post;
        this.liability = liability;
        this.numberPhone = numberPhone;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public Technique getTechnique() {
        return technique;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getLiability() {
        return liability;
    }

    public void setLiability(String liability) {
        this.liability = liability;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAllNameEmployee() {
        return allNameEmployee;
    }

    public void setAllNameEmployee(String allNameEmployee) {
        this.allNameEmployee = allNameEmployee;
    }
}