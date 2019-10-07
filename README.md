# spring-phoenix-jdbc-sample

Apache Phoenix5.0 通过 JdbcTemplate 的方式集成到 Spring-Boot 的示例代码

<br>

### 各组件版本

* Spring-Boot： 2.1.5.RELEASE
* Apache Phoenix：5.0.0
* Hadoop：2.7.4

<br>

### 数据源配置说明

数据源使用了 Spring-Boot 默认的 `HikariDataSource` ，主要工作在于对 `phoenix`  配置一个数据源，并将该数据源关联到 `JdbcTemplate` 的一个单例上。

这部分的代码如下：

```java
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
```

