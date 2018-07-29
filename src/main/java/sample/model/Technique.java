package sample.model;

public class Technique {

    private int idTechnique;
    private String type;
    private String nameTech;
    private int inventoryNumbers;
    private float startCost;
    private float actualCost;

    public Technique(){
    }

    public Technique(int idTechnique) {
        this.idTechnique = idTechnique;
    }

    public Technique(int idTechnique, String type) {
        this.idTechnique = idTechnique;
        this.type = type;
    }

    public Technique(int idTechnique, String type, String nameTech) {
        this.idTechnique = idTechnique;
        this.type = type;
        this.nameTech = nameTech;
    }

    public Technique(int idTechnique, String type, String nameTech, int inventoryNumbers) {
        this.idTechnique = idTechnique;
        this.type = type;
        this.nameTech = nameTech;
        this.inventoryNumbers = inventoryNumbers;
    }

    public Technique(int idTechnique, String type, String nameTech, int inventoryNumbers, float startCost) {
        this.idTechnique = idTechnique;
        this.type = type;
        this.nameTech = nameTech;
        this.inventoryNumbers = inventoryNumbers;
        this.startCost = startCost;
    }

    public Technique(int idTechnique, String type, String nameTech, int inventoryNumbers, float startCost, float actualCost) {
        this.idTechnique = idTechnique;
        this.type = type;
        this.nameTech = nameTech;
        this.inventoryNumbers = inventoryNumbers;
        this.startCost = startCost;
        this.actualCost = actualCost;
    }

    public int getIdTechnique() {
        return idTechnique;
    }

    public void setIdTechnique(int idTechnique) {
        this.idTechnique = idTechnique;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameTech() {
        return nameTech;
    }

    public void setNameTech(String nameTech) {
        this.nameTech = nameTech;
    }

    public int getInventoryNumbers() {
        return inventoryNumbers;
    }

    public void setInventoryNumbers(int inventoryNumbers) {
        this.inventoryNumbers = inventoryNumbers;
    }

    public float getStartCost() {
        return startCost;
    }

    public void setStartCost(float startCost) {
        this.startCost = startCost;
    }

    public float getActualCost() {
        return actualCost;
    }

    public void setActualCost(float actualCost) {
        this.actualCost = actualCost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(nameTech);
        return sb.toString();
    }
}
