package model;

public class Person {
    private String name;
    private String weight;
    private String height;
    private String gender;
    private double bmi;

    public Person(String name, String weight, String height, String gender) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
