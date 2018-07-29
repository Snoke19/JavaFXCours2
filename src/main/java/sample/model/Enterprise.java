package sample.model;

public class Enterprise {

    private int idEnterprise;
    private String nameEnterprise;
    private String city;
    private String address;
    private String nameDirector;
    private String middleNameDirector;
    private String lastNameDirector;
    private String nameAccountant;
    private String middleNameAccountant;
    private String lastNameAccountant;
    private String allNameDirector;
    private String allNameAccountant;
    private String allAddressEnterprise;

    public Enterprise(){}

    public Enterprise(int idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public Enterprise(String nameEnterprise) {
        this.nameEnterprise = nameEnterprise;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address, String nameDirector) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
        this.nameDirector = nameDirector;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address, String nameDirector, String middleNameDirector) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
        this.nameDirector = nameDirector;
        this.middleNameDirector = middleNameDirector;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address, String nameDirector, String middleNameDirector, String lastNameDirector) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
        this.nameDirector = nameDirector;
        this.middleNameDirector = middleNameDirector;
        this.lastNameDirector = lastNameDirector;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address, String nameDirector, String middleNameDirector, String lastNameDirector, String nameAccountant) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
        this.nameDirector = nameDirector;
        this.middleNameDirector = middleNameDirector;
        this.lastNameDirector = lastNameDirector;
        this.nameAccountant = nameAccountant;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address, String nameDirector, String middleNameDirector, String lastNameDirector, String nameAccountant, String middleNameAccountant) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
        this.nameDirector = nameDirector;
        this.middleNameDirector = middleNameDirector;
        this.lastNameDirector = lastNameDirector;
        this.nameAccountant = nameAccountant;
        this.middleNameAccountant = middleNameAccountant;
    }

    public Enterprise(int idEnterprise, String nameEnterprise, String city, String address, String nameDirector, String middleNameDirector, String lastNameDirector, String nameAccountant, String middleNameAccountant, String lastNameAccountant) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.city = city;
        this.address = address;
        this.nameDirector = nameDirector;
        this.middleNameDirector = middleNameDirector;
        this.lastNameDirector = lastNameDirector;
        this.nameAccountant = nameAccountant;
        this.middleNameAccountant = middleNameAccountant;
        this.lastNameAccountant = lastNameAccountant;
    }

    public String getAllAddressEnterprise() {
        return allAddressEnterprise;
    }

    public void setAllAddressEnterprise(String allAddressEnterprise) {
        this.allAddressEnterprise = allAddressEnterprise;
    }

    public int getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(int idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public String getNameEnterprise() {
        return nameEnterprise;
    }

    public void setNameEnterprise(String nameEnterprise) {
        this.nameEnterprise = nameEnterprise;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameDirector() {
        return nameDirector;
    }

    public void setNameDirector(String nameDirector) {
        this.nameDirector = nameDirector;
    }

    public String getMiddleNameDirector() {
        return middleNameDirector;
    }

    public void setMiddleNameDirector(String middleNameDirector) {
        this.middleNameDirector = middleNameDirector;
    }

    public String getLastNameDirector() {
        return lastNameDirector;
    }

    public void setLastNameDirector(String lastNameDirector) {
        this.lastNameDirector = lastNameDirector;
    }

    public String getNameAccountant() {
        return nameAccountant;
    }

    public void setNameAccountant(String nameAccountant) {
        this.nameAccountant = nameAccountant;
    }

    public String getMiddleNameAccountant() {
        return middleNameAccountant;
    }

    public void setMiddleNameAccountant(String middleNameAccountant) {
        this.middleNameAccountant = middleNameAccountant;
    }

    public String getLastNameAccountant() {
        return lastNameAccountant;
    }

    public void setLastNameAccountant(String lastNameAccountant) {
        this.lastNameAccountant = lastNameAccountant;
    }


    public String getAllNameDirector() {
        return allNameDirector;
    }

    public void setAllNameDirector(String allNameDirector) {
        this.allNameDirector = allNameDirector;
    }

    public String getAllNameAccountant() {
        return allNameAccountant;
    }

    public void setAllNameAccountant(String allNameAccountant) {
        this.allNameAccountant = allNameAccountant;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(nameEnterprise);
        return sb.toString();
    }
}
