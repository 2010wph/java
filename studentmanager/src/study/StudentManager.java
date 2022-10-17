package study;


import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;






/*
    学生管理系统
 */

public class StudentManager {


    /*
        1:用输出语句完成主界面的编写
        2:用Scanner实现键盘录入数据
        3:用switch语句完成操作的选择
        4:用循环完成再次回到主界面
     */
    public static void main(String[] args) {
        //创建一个集合对象用于储存学生数据
        ArrayList<Student> array = new ArrayList<>();
        //用循环完成再次回到主界面
        while (true) {

            //用输出语句完成主界面的编写
            System.out.println("--------欢迎来到学生管理系统--------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有学生");
            System.out.println("5 查询学生");
            System.out.println("6 退出");
            System.out.println("请输入您的选择：");

            //用Scanner实现键盘录入数据
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();

            //用switch语句完成操作的选择
            switch (line) {
                case "1" ->
//                    System.out.println("添加学生");
                        addStudent(array);
                case "2" ->
//                    System.out.println("删除学生");
                        deleteStudent(array);
                case "3" ->
//                    System.out.println("修改学生");
                        updateStudent(array);
                case "4" ->
//                    System.out.println("查看所有学生");
                        printALL(array);
                case "5" ->
//                      System.out.println("查询学生");
                        getStudentByIds(array);
                case "6" -> {
                    System.out.println("谢谢使用");
//                    break;
                    System.exit(0); //JVM(java虚拟机)退出
                }
            }
        }

    }

    //定义一个方法，用于添加学生信息
    public static void addStudent(ArrayList<Student> array) {
        //键盘录入学生对象所需要的数据，显示提示信息，提示要输入何种信息
        Scanner sc = new Scanner(System.in);

        //为了能使得sid能在while循环外面被访问到，我们就把他定义到了循环外面
        String sid;

        while (true) {
            System.out.println("请输入学生学号：");
            sid = sc.nextLine();

            boolean flag = isUsed(array, sid);
            if (flag) {
                System.out.println("你输入的学号已经被使用，请重新输入");
            } else {
                break;
            }
        }

        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();
        System.out.println("请输入学生居住地：");
        String address = sc.nextLine();

        //创建学生对象，把键盘录入的数据赋值给学生对象成员变量
        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);

        //将学生对象添加到集合中
        array.add(s);

        //给出添加成功提示
        System.out.println("添加学生成功");
    }

    //定义一个方法，判断学号是否被使用
    public static boolean isUsed(ArrayList<Student> array, String sid) {
        //如果与集合中的某一学生学号相同，返回ture;如果都不相同，返回false
        boolean flag = false;

        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //定义一个方法，用于查看学生信息
    public static void printALL(ArrayList<Student> array) {
        //判断集合中是否有数据，如果没有显示提示信息
        if (array.size() == 0) {
            System.out.println("无信息，请先添加信息再查询");
        } else {
            //显示表头信息
            //\t 其实就是tab键的位置
            System.out.println("学号\t\t\t\t姓名\t\t年龄\t\t居住地");

            //将集合中的元素输出显示
            for (int i = 0; i < array.size(); i++) {
                Student s = array.get(i);
                System.out.println(s.getSid() + "\t" + s.getName() + "\t" + s.getAge() + "岁\t" + s.getAddress());
            }
        }
    }

    public static Student getStudentById(ArrayList<Student> array, String sid) {

        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            //判断查找的学号是否在集合中
            if (student.getSid().equals(sid)) {
                //返回学生对象
                return student;
            }
        }
        //没有查到
        return null;
    }


    public static void getStudentByIds(ArrayList<Student> array) {

        Scanner sc = new Scanner(System.in);


        String sid = sc.nextLine();
        System.out.println("输入你要查询学生的id:");
        String id = sc.next();
        Student student = getStudentById(array, sid);
        if (student == null) {
            System.out.println("没有此学生！");
        } else {
            System.out.println(student.getSid() + "\t" + student.getName() + "\t" + student.getAge() + "\t" + student.getAddress());

        }

    }

    //定义一个方法，用于删除学生信息
    public static void deleteStudent(ArrayList<Student> array) {
        //键盘录入要删除的学生学号，显示提示信息
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你要删除的学生学号：");
        String sid = sc.nextLine();


        //在删除学生操作前，对学号是否存在进行判断
        //如果不存在，显示提示信息
        //遍历集合将对应学生对象从集合中删除
        int index = -1;

        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
//                array.remove(i);
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("该信息不存在，请重新输入");
        } else {
            array.remove(index);
            //给出提示删除学生成功
            System.out.println("删除学生成功");
        }
    }

    class StudentFileReader extends StudentAbstractReader {

        private File file;

        public StudentFileReader(File file) {
            this.setFile(file);
        }

        @Override
        public InputStream getInputStream() throws FileNotFoundException {
            return new FileInputStream(this.getFile());
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }
    }

    public interface IStudentReader  {

        public List<Student> read() throws IOException;

    }


    abstract class StudentAbstractReader implements IStudentReader {

        public abstract InputStream getInputStream() throws IOException;

        public List<Student> read() throws IOException {
            List<Student> students = new ArrayList<>();
            try (InputStreamReader inputStreamReader = new InputStreamReader(this.getInputStream()))
            {
                try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        if (line.trim().isEmpty())  continue;
                        Student student = this.createStudent(line);
                        students.add(student);
                    }
                }
            }
            return students;
        };

        private Student createStudent(String line) {
            String[] vals = line.split("\t");
            Student student = new Student();
            student.setSid(vals[0]);
            student.setName(vals[1]);
            return student;
        }

    }


    public class StudentHttpReader extends StudentAbstractReader {

        private URL url;

        public StudentHttpReader(URL url) {
            this.setUrl(url);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            URLConnection connection = this.getUrl().openConnection();
            InputStream inputStream = connection.getInputStream();
            return inputStream;
        }

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }

    }

    public class StudentReaderFactory {
        public IStudentReader create(String uri) throws MalformedURLException, MalformedURLException {
            StudentAbstractReader reader;
            if (uri.toLowerCase().startsWith("http://139.186.26.196/javaweb/data/math.txt")) {
                reader = new StudentHttpReader(new URL(uri));
            } else {
                reader = new StudentFileReader(new File(uri));
            }
            return reader;
        }
    }

    public class StudentReaderFactory2 {
        public IStudentReader create(String uri) throws MalformedURLException, MalformedURLException {
            StudentAbstractReader reader;
            if (uri.toLowerCase().startsWith("http://139.186.26.196/javaweb/data/chinese.txt")) {
                reader = new StudentHttpReader(new URL(uri));
            } else {
                reader = new StudentFileReader(new File(uri));
            }
            return reader;
        }
    }

    public class StudentReaderFactory3 {
        public IStudentReader create(String uri) throws MalformedURLException, MalformedURLException {
            StudentAbstractReader reader;
            if (uri.toLowerCase().startsWith("http://139.186.26.196/javaweb/data/english.txt")) {
                reader = new StudentHttpReader(new URL(uri));
            } else {
                reader = new StudentFileReader(new File(uri));
            }
            return reader;
        }
    }



    //定义一个方法，用于修改学生信息
    public static void updateStudent(ArrayList<Student> array) {
        //键盘输入要修改的学生学号，显示提示信息
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你要修改的学生学号：");
        String sid = sc.nextLine();

        int index = -1;
        //遍历结合修改学生的对应信息
        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            if (student.getSid().equals(sid)) {
                index = i;
//                array.set(i,s);
                break;
            }
        }
        if (index == -1) {
            System.out.println("不存在要修改的学生学号，请重新输入");
        } else {
            //键盘录入要修改的学生信息
            System.out.println("请输入学生新的姓名：");
            String name = sc.nextLine();
            System.out.println("请输入学生新的年龄：");
            String age = sc.nextLine();
            System.out.println("请输入学生新的居住地：");
            String address = sc.nextLine();

            //创建一个学生对象
            Student s = new Student();
            s.setSid(sid);
            s.setName(name);
            s.setAge(age);
            s.setAddress(address);

            array.set(index, s);
            //给出修改成功提示
            System.out.println("修改学生信息成功");
        }
    }

}


