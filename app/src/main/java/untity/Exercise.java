package untity;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Exercise {
    private int id;
    private String name;
    private int duration;
    private float caloBurn;
    private int videoResource;

    public Exercise() {
    }

    public Exercise(int id, String name, int duration, float caloBurn, int videoResource) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.caloBurn = caloBurn;
        this.videoResource = videoResource;
    }

    public Exercise(String name, int duration, float caloBurn) {
        this.name = name;
        this.duration = duration;
        this.caloBurn = caloBurn;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public float getCaloBurn() {
        return caloBurn;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCaloBurn(float caloBurn) {
        this.caloBurn = caloBurn;
    }

//    public float CarloriesFormula(int duration, int caloBurn, int newVal) {
//        float result = ((float) newVal / (float) duration) * caloBurn;
//        return Math.round(result * 10.0f) / 10.0f;
//    }

    public float CarloriesFormula(float time) {
        return Math.round((((float) caloBurn / (float) duration) * time) * 10.0f) / 10.0f;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Exercise)
            return name.equalsIgnoreCase(((Exercise) obj).getName());
        return false;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", caloBurn=" + caloBurn +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("duration", duration);
        result.put("caloBurn", caloBurn);
        return result;
    }

    public int getVideoResource() {
        return videoResource;
    }

    public void setVideoResource(int videoResource) {
        this.videoResource = videoResource;
    }


}
