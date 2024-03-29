package example.spring.vault.config.vault

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.vault.authentication.ClientAuthentication
import org.springframework.vault.authentication.TokenAuthentication
import org.springframework.vault.client.VaultEndpoint
import org.springframework.vault.config.AbstractVaultConfiguration
import org.springframework.vault.config.EnvironmentVaultConfiguration
import org.springframework.vault.core.VaultOperations
import org.springframework.vault.core.VaultTemplate
import java.net.URI


@Configuration
class VaultConfiguration(
    @Value("\${vault.uri}")
    private val uri: String,

    @Value("\${vault.token}")
    private val token: String
): AbstractVaultConfiguration() {

    override fun vaultEndpoint(): VaultEndpoint {
        return VaultEndpoint.from(URI.create(uri))
    }

    override fun clientAuthentication(): ClientAuthentication {
        return TokenAuthentication(token)
    }
}