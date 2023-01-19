package example.spring.vault.config.persistence.redis

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfiguration(
    private val redisVaultProperties: RedisVaultProperties
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        logger.info("""
            ==========[Redis Configuration]==========
                Host: ${redisVaultProperties.host} 
                Port: ${redisVaultProperties.port} 
                ==============================================
        """.trimIndent())
        return LettuceConnectionFactory(redisVaultProperties.host, Integer.parseInt(redisVaultProperties.port))
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any>? {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        return redisTemplate
    }
}