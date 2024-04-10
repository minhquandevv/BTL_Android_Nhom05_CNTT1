package untity;

public class Muscle {
    private String name;
    private String number1;
    private String number2;
    private String information;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Muscle(String name, String number1, String number2) {
        this.name = name;
        this.number1 = number1;
        this.number2 = number2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public Muscle() {
    }

    @Override
    public String toString() {
        return "Muscle{" +
                "information='" + information + '\'' +
                '}';
    }
}