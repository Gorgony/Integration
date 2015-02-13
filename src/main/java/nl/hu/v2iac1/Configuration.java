package nl.hu.v2iac1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Roelant Ossewaarde on 2/9/15.
 */
public class Configuration {
    public enum Key {
        PUBLIC, SECRET, TOPSECRET, VERYSECRET, ACCOUNT, USERID, PASSWORD
    }

    private final static String propFileName = "local.properties";
    private Map<Key, String> parameters;

    private Map<Key, String> getPropertyValues() throws IOException {
        Map<Key, String> map = new HashMap<Key, String>();
        Properties prop = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        for (Key key : Key.values()) {
            String value;
            if (null != (value = prop.getProperty(key.toString().toLowerCase()))) {
                map.put(key, value);
            }
        }
        return map;
    }

    public String getValue(Key key) {
        if (parameters == null) {
            try {
                parameters = getPropertyValues();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (parameters == null ? "" : parameters.get(key));
    }

}

