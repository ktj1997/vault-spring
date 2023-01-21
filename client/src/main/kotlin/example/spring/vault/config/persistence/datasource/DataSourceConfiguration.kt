package example.spring.vault.config.persistence.datasource

import example.spring.vault.config.vault.DataSourceVaultCredentials
import example.spring.vault.config.vault.VaultHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(
    @Value("\${vault.path.datasource}")
    private val path: String,
    private val vaultHandler: VaultHandler
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Bean
    fun dataSource(): DataSource {
        val credentials  = vaultHandler.get(path, DataSourceVaultCredentials::class.java)

        logger.info(
            """
                ==========[DataSource Configuration]==========
                URL: ${credentials.url}
                UserName: ${credentials.userName}
                =============================================="""
        )

        return DataSourceBuilder.create()
            .url(credentials.url)
            .username(credentials.userName)
            .password(credentials.password)
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build()
    }
}