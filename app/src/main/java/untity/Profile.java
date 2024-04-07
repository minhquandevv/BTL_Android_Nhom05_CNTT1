package untity;

public class Profile {
    private String name;
    private boolean gender;
    private float age;
    private float height;
    private float weight;
    private int aimCalorie;

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAimCalorie() {
        return aimCalorie;
    }

    public void setAimCalorie(int aimCalorie) {
        this.aimCalorie = aimCalorie;
    }

    public Profile(String name, boolean gender, float age, float height, float weight, int aimCalorie) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.aimCalorie = aimCalorie;
    }
    public float calculateBMI(float weight, float height){
        return Math.round(weight/(height*height/10000)*100.0f)/100.0f;
    }
    public float calculateMyCalories(boolean gender, float weight, float height, float age) {
        if (gender == true) //gender = true => Men , this is BMR
            return (float) Math.round(((6.25 * height) + (10 * weight) - (5 * age) + 5)*100.0f)/100.0f;
        else
            return (float) Math.round(((6.25 * height) + (10 * weight) - (5 * age) - 161)*100.0f)/100.0f;
    }
    public float ideaWeight(float height, boolean gender){
        if(gender == true){
            return Math.round((height * height * 23 / 10000)*100.0f)/100.0f;
        }else{
            return (float) Math.round((height * height * 21.5 / 10000)*100.0f)/100.0f;
        }
    }
}
