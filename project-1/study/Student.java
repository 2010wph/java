package study;

import java.text.Format;
import java.util.Map;


public class Student {
    private String name;
    private String id;
    private int ChineseScore;
    private int MathScore;
    private int EnglishScore;

    private int totalScore;

    private double averageScore;

    public Student() {}

    public Student(String name, String id, int chineseScore, int mathScore, int englishScore) {
        this.name = name;
        this.id = id;
        ChineseScore = chineseScore;
        MathScore = mathScore;
        EnglishScore = englishScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getChineseScore() {
        return ChineseScore;
    }

    public void setChineseScore(int chineseScore) {
        ChineseScore = chineseScore;
    }

    public int getMathScore() {
        return MathScore;
    }

    public void setMathScore(int mathScore) {
        MathScore = mathScore;
    }

    public int getEnglishScore() {
        return EnglishScore;
    }

    public void setEnglishScore(int englishScore) {
        EnglishScore = englishScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore() {
        this.totalScore = this.ChineseScore + this.MathScore + this.EnglishScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore() {
        this.averageScore = (this.ChineseScore + this.MathScore + this.EnglishScore) / 3.0;
    }

    @Override
    public String toString() {
        return String.format("学号:%s, 姓名:%3s, 语文:%3d, 数学:%3d, 英语:%3d, 总分:%3d, 平均分:%3.2f\n"
                , id, name, ChineseScore, MathScore, EnglishScore, totalScore, averageScore);
    }


}
