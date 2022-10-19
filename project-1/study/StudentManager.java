package study;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StudentManager {

    private Map<String, Student> students;

    public StudentManager(Map<String, Student> students) {
        this.students = students;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }

    public void refresh() {
        for (String key : this.students.keySet()) {
            Student student = this.students.get(key);
            student.setAverageScore();
            student.setTotalScore();
        }
    }

    public Student getStudentById() {
        System.out.println("查询的学生：");
        Scanner scanner = new Scanner(System.in);
        String Id = scanner.next();
        for (String key : this.students.keySet()) {
            if (Id.equals(key)) {
                return this.students.get(key);
            }
        }
        return null;
    }

    public void findByClass(String classType, int minScore, int maxScore) {
        for (Student student: this.students.values()) {
            if (classType.equals("语文") && minScore <= student.getChineseScore() && student.getChineseScore() <= maxScore) {
                System.out.printf("学号: %s 姓名: %3s 语文: %d\n"
                        , student.getId(), student.getName(), student.getChineseScore());
            } else if (classType.equals("数学") && minScore <= student.getMathScore() && student.getMathScore() <= maxScore) {
                System.out.printf("学号: %s 姓名: %3s 数学: %d\n"
                        , student.getId(), student.getName(), student.getMathScore());
            } else if (classType.equals("英语") && minScore <= student.getMathScore() && student.getMathScore() <= maxScore) {
                System.out.printf("学号: %s 姓名: %3s 英语: %d\n"
                        , student.getId(), student.getName(), student.getEnglishScore());
            }
        }
    }

    public void findByScore(int minScore, int maxScore) {
        for (Student student: this.students.values()) {
            if (minScore <= student.getAverageScore() && student.getAverageScore() <= maxScore) {
                System.out.print(student);
            }
        }
    }

    public void saveAs(String txtName) throws IOException {
        Map<String, Student> map = this.getStudents();
        String base = "C:\\students\\student.txt";
        String fileName = base + txtName + ".txt";
        System.out.println(fileName);
        FileWriter writer = new FileWriter(fileName);
        for (String key : map.keySet()) {
            Student student = map.get(key);
            writer.write(student.toString());
        }
        writer.close();
        System.out.printf("已成功保存到:%s%n", fileName);
    }
}
