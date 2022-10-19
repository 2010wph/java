package study;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public abstract class StudentAbstractReader extends IStudentReader {
    public abstract InputStream getInputStream() throws IOException;

    public BufferedReader getFinalReader() throws IOException {
        InputStream inputStream = this.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }
    public Map<String, Student> read() throws IOException {
        Map<String, Student> students = new TreeMap<>();
        BufferedReader bufferedReader = this.getFinalReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty())
                continue;
            Student student = this.createStudent(line);
            students.put(student.getId(), student);
        }
        return students;
    }

    public Student createStudent(String line) {
        Student student = new Student();
        String[] info = line.split("\t");
        student.setId(info[0]);
        student.setName(info[1]);
        return student;
    }

    public Map<String, Student> loadChinese(Map<String, Student> students) throws IOException {
        BufferedReader bufferedReader = this.getFinalReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty())
                continue;
            addChinese(students, line);
        }
        return students;
    }

    public void addChinese(Map<String, Student> students, String line) {
        String[] info = line.split("\t");
        Student student = students.get(info[0]);
        student.setChineseScore(Integer.parseInt(info[1]));
    }

    public Map<String, Student> loadMath(Map<String, Student> students) throws IOException {
        BufferedReader bufferedReader = this.getFinalReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty())
                continue;
            addMath(students, line);
        }
        return students;
    }

    public void addMath(Map<String, Student> students, String line) {
        String[] info = line.split("\t");
        Student student = students.get(info[0]);
        student.setMathScore(Integer.parseInt(info[1]));
    }

    public Map<String, Student> loadEnglish(Map<String, Student> students) throws IOException {
        BufferedReader bufferedReader = this.getFinalReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty())
                continue;
            addEnglish(students, line);
        }
        return students;
    }

    public void addEnglish(Map<String, Student> students, String line) {
        String[] info = line.split("\t");
        Student student = students.get(info[0]);
        student.setEnglishScore(Integer.parseInt(info[1]));
    }
}
