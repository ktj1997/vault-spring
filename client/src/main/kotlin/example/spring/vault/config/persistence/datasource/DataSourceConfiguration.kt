package example.spring.vault.config.persistence.datasource

import org.slf4j.LoggerFactory
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(
    private val dataSourceVaultProperties : DataSourceVaultProperties
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Bean
    fun dataSource() : DataSource {

        logger.info("""
                ==========[DataSource Configuration]==========
                URL: ${dataSourceVaultProperties.url} 
                Driver: ${dataSourceVaultProperties.driverClassName} 
                UserName: ${dataSourceVaultProperties.username} 
                Password: ${dataSourceVaultProperties.password}
                ==============================================""")

        return DataSourceBuilder.create()
            .url(dataSourceVaultProperties.url)
            .username(dataSourceVaultProperties.username)
            .password(dataSourceVaultProperties.password)
            .driverClassName(dataSourceVaultProperties.driverClassName)
            .build()
    }
}