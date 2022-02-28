package service;

import com.google.gson.Gson;
import model.SystemProperties;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class SystemConfig {

    private static SystemConfig systemConfig;
    private static Properties properties;
    private static Map<String,String> SYSTEM_CREDENTIALS;

    private final static Logger logger = Logger.getLogger(SystemConfig.class.getName());

    public static SystemConfig getInstance() {
        if (Objects.isNull(systemConfig)) {
            systemConfig = new SystemConfig();
        }
        return systemConfig;
    }

    private SystemConfig(){
        try {
            loadProperties();
            loadSystemCredentials();
        } catch (Exception ex) {
            logger.info("failed to load system properties");
        }
    }

    private void loadProperties() throws IOException {
        InputStream inputStream = null;
        try {
            properties = new Properties();
            String propFileName = "application.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (Objects.nonNull(inputStream)) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception ex) {
            logger.warning("failed to load application properties {}"+ex.getMessage());
        } finally {
            if(Objects.nonNull(inputStream)){
                inputStream.close();
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }

    private void loadSystemCredentials() throws Exception {
        StringBuffer jsonStr = new StringBuffer();
        try {
            logger.info("System Resource loading ::" + Calendar.getInstance().getTime());
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/system_credentials.json")));

            String oneLine;
            while ((oneLine = reader.readLine()) != null) {
                jsonStr.append(oneLine);
            }
            logger.info("System Resource :: {}" + jsonStr);
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw e;
        }
        SystemProperties systemProperties = new Gson().fromJson(jsonStr.toString(), SystemProperties.class);
        SYSTEM_CREDENTIALS = systemProperties.getSystemCredentials();
        logger.info("System credentials & service codes are loaded :: " + Calendar.getInstance().getTime());
    }

    public String getEncryptedPassword(String username) {
        if (SYSTEM_CREDENTIALS.containsKey(username)) {
            return SYSTEM_CREDENTIALS.get(username);
        }
        throw new RuntimeException("No credentials found!");
    }
}
