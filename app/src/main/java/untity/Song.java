package untity;

import java.io.Serializable;

public class Song implements Serializable {

    private String tenBaiHat;
    private String finalTenBaiHat;
    private int file;
    private String path;

    public Song(String tenBaiHat, int file) {
        this.tenBaiHat = tenBaiHat;
        this.file = file;
    }

    public Song() {
    }

    public Song(String songName, String path) {
    }

    public Song(String finalTenBaiHat, String string, String fullPath) {
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public String getFinalTenBaiHat() {
        return finalTenBaiHat;
    }

    public void setFinalTenBaiHat(String finalTenBaiHat) {
        this.finalTenBaiHat = finalTenBaiHat;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
