package com.xn.common.constant;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import com.xn.manager.model.ConfigModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * @Description TODO
 * @Date 2020/9/16 17:55
 * @Version 1.0
 */

public class SpringConfig {
    public  static ConfigModel getConfifModel() throws IOException {
        ConfigModel  configModel  = new ConfigModel();
        InputStream stream = ManagementAssertion.Setting.class.getClassLoader().getResourceAsStream("classpath:config.properties");
        Properties p = new Properties();
        p.load(stream);
        String foreignAddress = p.getProperty("img.foreignAddress");
        String storeAddress = p.getProperty("img.storeAddress");
        configModel.setForeignAddress(foreignAddress);
        configModel.setForeignAddress(storeAddress);
        stream.close();
        return configModel;
    }


}
