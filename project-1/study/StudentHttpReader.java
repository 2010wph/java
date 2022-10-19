package study;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



    public class StudentHttpReader extends StudentAbstractReader{
        private URL url;

        public StudentHttpReader(URL url) {
            this.url = url;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return this.getUrl().openConnection().getInputStream();
        }

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }
    }


