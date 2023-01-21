package example.spring.vault.config.vault

class DataSourceVaultCredentials(
    val url: String,
    val userName: String,
    val password: String,
    val driverClassName: String
)


class RedisVaultCredentials(
    val host: String,
    val port: Int
)