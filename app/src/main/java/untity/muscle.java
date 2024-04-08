package untity;

import android.os.Parcelable;

public class muscle {
    private String nameMuscle;
    private String number1;
    private String number2;

    public muscle(String nameMuscle, String number1, String number2) {
        this.nameMuscle = nameMuscle;
        this.number1 = number1;
        this.number2 = number2;
    }

    public muscle(){

    }

    public String getNameMuscle() {
        return nameMuscle;
    }

    public String getNumber1() {
        return number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNameMuscle(String nameMuscle) {
        this.nameMuscle = nameMuscle;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }
}

