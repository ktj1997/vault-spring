package example.spring.vault.config.datasource

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DataSourceConfiguration(
    private val dataSourceProperties : DataSourceProperties
) {
    @Bean
    fun dataSource() : DataSource {
        return DataSourceBuilder.create()
            .url(dataSourceProperties.url)
            .username(dataSourceProperties.username)
            .password(dataSourceProperties.password)
            .driverClassName(dataSourceProperties.driverClassName)
            .build();
    }
}