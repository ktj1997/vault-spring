package example.spring.vault.config.persistence.datasource

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.vault.annotation.VaultPropertySource


@Component
@VaultPropertySource(value = ["\${vault.path.datasource}"])
class DataSourceVaultProperties{
    @Value("\${url}")
    lateinit var url: String
    @Value("\${username}")
    lateinit var username: String
    @Value("\${password}")
    lateinit var password: String
    @Value("\${driverClassName}")
    lateinit var driverClassName: String
}