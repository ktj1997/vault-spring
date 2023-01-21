package example.spring.vault.config.vault

class DataSourceVaultCredentials(credentialMap: Map<*, *>) {
    val url:String
    val userName: String
    val password: String

    init {
        url = credentialMap["url"] as String
        userName = credentialMap["username"] as String
        password = credentialMap["password"] as String
    }
}

class RedisVaultCredentials(credentialMap: Map<*, *>) {

    val host: String

    val port : Int

    init {
        host = credentialMap["host"] as String
        port = Integer.parseInt(credentialMap["port"] as String)
    }
}