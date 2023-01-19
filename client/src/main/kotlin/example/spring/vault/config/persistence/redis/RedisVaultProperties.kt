package example.spring.vault.config.persistence.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.vault.annotation.VaultPropertySource

@Component
@VaultPropertySource(value = ["\${vault.path.redis}"])
class RedisVaultProperties {

    @Value("\${host}")
    lateinit var host: String

    @Value("\${port}")
    lateinit var port: String
}