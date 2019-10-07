package site.assad.sample.phoenix.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.phoenix")
public class PhoenixDataSourceProperties {
    
    private String url;
    private String driverClassName;
    
    public PhoenixDataSourceProperties() {
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDriverClassName() {
        return driverClassName;
    }
    
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}

