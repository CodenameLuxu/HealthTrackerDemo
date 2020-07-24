//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.JSON;

import java.io.InputStream;

public class FileHandle {
    
    public static InputStream inputStreamFromFile(String path) {
        try {
            InputStream inputStream = FileHandle.class.getResourceAsStream(path);
            return inputStream;
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return null;
    }
    
}
