package example.spring.vault.config.datasource

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.vault.annotation.VaultPropertySource


@Component
@VaultPropertySource(value = ["\${vault.path.datasource}"], propertyNamePrefix = "spring.datasource.")
class DataSourceProperties{
    @Value("\${spring.datasource.url}")
    lateinit var url: String
    @Value("\${spring.datasource.username}")
    lateinit var username: String
    @Value("\${spring.datasource.password}")
    lateinit var password: String
    @Value("\${spring.datasource.driverClassName}")
    lateinit var driverClassName: String
}