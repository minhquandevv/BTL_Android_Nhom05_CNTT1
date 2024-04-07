package untity;

public class Fitness {

    public static final int TYPE_1 = 1;
    public static final int TYPE_2= 2;
    public static final int TYPE_3 = 3;
    public static final int TYPE_4 = 4;

    private String title;
    private String name;
    private int type;


    public Fitness(String title, String name, int type) {
        this.title = title;
        this.name = name;
        this.type = type;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
