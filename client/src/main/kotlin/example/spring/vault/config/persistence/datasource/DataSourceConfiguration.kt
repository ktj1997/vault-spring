package example.spring.vault.config.persistence.datasource

import com.fasterxml.jackson.databind.ObjectMapper
import example.spring.vault.config.vault.DataSourceVaultCredentials
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.vault.client.VaultResponses
import org.springframework.vault.core.VaultTemplate
import org.springframework.vault.support.VaultResponseSupport
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(
    @Value("\${vault.path.datasource}")
    private val path: String,
    private val vaultTemplate: VaultTemplate,
    private val objectMapper: ObjectMapper
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Bean
    fun dataSource(): DataSource {
        val response = vaultTemplate.read(path)
        val credentialMap = response.data?:throw Exception("Get Credential From $path Fail")
        val credentials = objectMapper.convertValue(credentialMap["data"],DataSourceVaultCredentials::class.java)

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
                .driverClassName(credentials.driverClassName)
                .build()
    }
}