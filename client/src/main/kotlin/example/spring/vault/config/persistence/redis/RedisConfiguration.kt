package example.spring.vault.config.persistence.redis

import com.fasterxml.jackson.databind.ObjectMapper
import example.spring.vault.config.vault.DataSourceVaultCredentials
import example.spring.vault.config.vault.RedisVaultCredentials
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.vault.core.VaultOperations
import org.springframework.vault.core.VaultTemplate


@Configuration
class RedisConfiguration(
    @Value("\${vault.path.redis}")
    private val path: String,
    private val vaultTemplate: VaultTemplate,
    private val objectMapper: ObjectMapper
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val response = vaultTemplate.read(path)
        val credentialMap = response.data?:throw Exception("Get Credential From $path Fail")
        val credentials = objectMapper.convertValue(credentialMap["data"],
            RedisVaultCredentials::class.java)
        logger.info("""
            ==========[Redis Configuration]==========
                Host: ${credentials.host} 
                Port: ${credentials.port} 
                ==============================================
        """)
        return LettuceConnectionFactory(credentials.host!!, credentials.port!!)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>? {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        return redisTemplate
    }
}