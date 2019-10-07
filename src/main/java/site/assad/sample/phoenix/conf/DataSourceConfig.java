package site.assad.sample.phoenix.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * phoenix 数据源配置
 *
 * @author Al-assad
 * @since 2019/10/7
 */
@Configuration
@EnableConfigurationProperties(PhoenixDataSourceProperties.class)
@ConditionalOnClass(JdbcTemplate.class)
public class DataSourceConfig {
    
    private PhoenixDataSourceProperties phoenixDataSourceProperties;
    
    @Autowired
    public void setPhoenixDataSourceProperties(PhoenixDataSourceProperties phoenixDataSourceProperties) {
        this.phoenixDataSourceProperties = phoenixDataSourceProperties;
    }
    
    /**
     * phoenix 数据源配置
     */
    @Bean
    public DataSource phoenixDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(phoenixDataSourceProperties.getDriverClassName());
        dataSource.setJdbcUrl(phoenixDataSourceProperties.getUrl());
        // 由于 phoenix 驱动不会自动 commit，需要加入默认的 auto commit 设置，否则更新操作的数据会缓存在本地知直到缓冲区满
        dataSource.setAutoCommit(true);
        return dataSource;
    }
    
    /**
     * Phoenix JdbcTemplate 配置
     */
    @Bean
    public JdbcTemplate phoenixTemplate(DataSource phoenixDataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(phoenixDataSource);
        return jdbcTemplate;
    }
}
