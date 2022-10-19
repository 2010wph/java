package study;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.*;

public class main {
    private StudentManager studentManager = null;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        main Main = new main();
        Main.startUp();
        Main.execute();
        Main.shutdown();
    }

    public void startUp() throws IOException {
        this.studentManager = loadData();
    }

    public void printMenu() {
        System.out.println("--------欢迎来到学生管理系统--------");
        System.out.println("1. 查看所有学生");
        System.out.println("2. 查询学生（学号）");
        System.out.println("3. 查询学生（课程类别、分值范围）");
        System.out.println("4. 查询学生（三科平均分）");
        System.out.println("5. 查看学生排名");
        System.out.println("6. 保存为文件");
        System.out.println("7. 退出");
        System.out.println("请输入您的选择：");
    }

    public void execute() throws IOException {
        this.printMenu();
        int command = this.scanner.nextInt();
        while (command != 7) {
            switch (command) {
                case 1 ->
                        this.printAll();
                case 2 ->
                        this.findStudentById();
                case 3 ->
                        this.findByClass();
                case 4 ->
                        this.findByScore();
                case 5 ->
                        this.sortByTotal();
                case 6 ->
                        this.saveAs();
            }
            this.printMenu();
            command = this.scanner.nextInt();
        }
    }

    public void shutdown() {
        System.out.println("再见！欢迎下次使用。");
    }

    public StudentManager loadData() throws IOException {
        String localFile = "C:\\students\\student.txt";
        String chineseUri = "http://139.186.26.196/javaweb/data/chinese.txt";
        String mathUri = "http://139.186.26.196/javaweb/data/math.txt";
        String englishUri = "http://139.186.26.196/javaweb/data/english.txt";
        IStudentReader iStudentReader = StudentReaderFactory.create(localFile);
        StudentManager studentManager = new StudentManager(iStudentReader.read());
        studentManager.setStudents(StudentReaderFactory.create(chineseUri).loadChinese(studentManager.getStudents()));
        studentManager.setStudents(StudentReaderFactory.create(mathUri).loadMath(studentManager.getStudents()));
        studentManager.setStudents(StudentReaderFactory.create(englishUri).loadEnglish(studentManager.getStudents()));
        studentManager.refresh();
        return studentManager;
    }



    public void printAll() {
        for (String key : this.studentManager.getStudents().keySet()) {
            Student student = this.studentManager.getStudents().get(key);
            System.out.print(student);
        }
    }


    public void sortByTotal() {

        List<Student> students = new ArrayList<>();
        Map<String, Student> map = this.studentManager.getStudents();
        for (String key : map.keySet()) {
            students.add(map.get(key));
        }

        students.sort((o1, o2) -> o2.getTotalScore() - o1.getTotalScore());
        for (Student s: students)
            System.out.print(s);
    }

    public void findStudentById() {
        Student student = this.studentManager.getStudentById();
        if (student == null) {
            System.out.println("查询失败");
        } else {
            System.out.println("查询成功");
            System.out.print(student);
        }
    }

    public void findByClass() {
        System.out.println("查询的课程：");
        String classType = this.scanner.next();
        System.out.println("初始分数：");
        int minScore = this.scanner.nextInt();
        System.out.println("末尾分数：");
        int maxScore = this.scanner.nextInt();
        this.studentManager.findByClass(classType, minScore, maxScore);

    }

    public void findByScore() {
        System.out.println("初始分数：");
        int minScore = this.scanner.nextInt();
        System.out.println("末尾分数：");
        int maxScore = this.scanner.nextInt();
        this.studentManager.findByScore(minScore, maxScore);
    }




    public void saveAs() throws IOException {
        System.out.println("请输入文件名称:");
        String txtName = this.scanner.next();
        this.studentManager.saveAs(txtName);
    }
}
