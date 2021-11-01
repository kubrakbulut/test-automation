package common.helpers;

import common.framework.BaseConfiguration;

public class Util {


    public static String getProperty(String property, boolean addEnv) {
        BaseConfiguration baseConfiguration = new BaseConfiguration();

        if (addEnv)
            return baseConfiguration.getProperty(Util.getEnvironment() + "-" + property);
        else
            return baseConfiguration.getProperty(property);
    }

    public static String getEnvironment() {
        BaseConfiguration baseConfiguration = new BaseConfiguration();

        if (System.getProperty("env") != null)
            return System.getProperty("env");
        else
            return baseConfiguration.getProperty("env");
    }

    public static String getURL() {
        if (System.getProperty("URL") != null)
            return System.getProperty("URL");
        else
            return Util.getProperty("URL", true);
    }

}