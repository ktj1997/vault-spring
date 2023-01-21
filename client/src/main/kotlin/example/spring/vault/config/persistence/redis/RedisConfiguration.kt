package example.spring.vault.config.persistence.redis

import example.spring.vault.config.vault.DataSourceVaultCredentials
import example.spring.vault.config.vault.RedisVaultCredentials
import example.spring.vault.config.vault.VaultHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfiguration(
    @Value("\${vault.path.redis}")
    private val path: String,
    private val vaultHandler: VaultHandler
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {

        val credentials  = vaultHandler.get(path, RedisVaultCredentials::class.java)
        logger.info("""
            ==========[Redis Configuration]==========
                Host: ${credentials.host} 
                Port: ${credentials.port} 
                ==============================================
        """.trimIndent())
        return LettuceConnectionFactory(credentials.host, credentials.port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>? {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        return redisTemplate
    }
}