package com.alex.tasktable.config;

import com.alex.tasktable.utils.DataSourceProperties;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class YamlConfig {
    public static DataSourceProperties dataSourceProperties;

    static {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YamlConfig.class
                .getClassLoader()
                .getResourceAsStream("project.yml")) {
            if (inputStream == null) {
                throw new IllegalStateException("project.yml file not found");
            }
            Map<String, Object> yamlData = yaml.load(inputStream);

            dataSourceProperties = mapToObject((Map<String, Object>) yamlData.get("data-source"), DataSourceProperties.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        if (clazz.equals(DataSourceProperties.class)) {
            dataSourceProperties = new DataSourceProperties();
            dataSourceProperties.setDriverClassName((String) map.get("driver-class-name"));
            dataSourceProperties.setUrl((String) map.get("url"));
            dataSourceProperties.setUserName((String) map.get("user-name"));
            dataSourceProperties.setUserPassword((String) map.get("user-password"));
            return clazz.cast(dataSourceProperties);
        }
        throw new IllegalArgumentException("Unsupported class type: " + clazz);
    }

}
