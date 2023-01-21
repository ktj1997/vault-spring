package example.spring.vault.config.vault

import org.springframework.stereotype.Component
import org.springframework.vault.core.VaultTemplate


@Component
class VaultHandler(
    private val vaultTemplate: VaultTemplate
) {

    fun <T> get(path: String, clazz: Class<T>): T {
        val response = vaultTemplate.read(path)
        val credentialMap = response.data?.get("data") as Map<*, *>

        val constructor = clazz.getConstructor(Map::class.java)

        return constructor.newInstance(credentialMap)
    }
}
