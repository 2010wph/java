package study;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentReaderFactory {

    public static IStudentReader create(String uri) throws MalformedURLException {
        IStudentReader iStudentReader;
        if (uri.toLowerCase().startsWith("http")) {
            iStudentReader = new StudentHttpReader(new URL(uri));
        } else {
            iStudentReader = new StudentFileReader(new File(uri));
        }
        return iStudentReader;
    }
}
