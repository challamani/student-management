package service;

import util.SystemConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.logging.Logger;

public class DatabaseService {

    private static DatabaseService databaseService;
    private static SystemConfig systemConfig;
    private final static Logger logger = Logger.getLogger(DatabaseService.class.getName());


    public static DatabaseService getInstance(){
        if(Objects.isNull(databaseService)){
            databaseService = new DatabaseService();
            systemConfig = SystemConfig.getInstance();
        }
        return databaseService;
    }

    public Connection getConnection(){
        try {
            Object driverClass =  systemConfig.getProperties().get(SystemConstant.db_driver_class);
            Class.forName(driverClass.toString());

            String dbUrl = systemConfig.getProperties().get(SystemConstant.db_url).toString();
            String dbUser = systemConfig.getProperties().get(SystemConstant.db_user).toString();
            String dbPassword = systemConfig.getProperties().get(SystemConstant.db_pwd).toString();
            logger.info(dbUrl+", user: "+dbUser);
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            return connection;
        } catch (Exception ex) {
            logger.warning("DB connection not acquired {}" + ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
