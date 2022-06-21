package example.spring.vault.config.vault

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.vault.authentication.ClientAuthentication
import org.springframework.vault.authentication.TokenAuthentication
import org.springframework.vault.client.VaultEndpoint
import org.springframework.vault.config.AbstractVaultConfiguration
import org.springframework.vault.config.EnvironmentVaultConfiguration
import java.net.URI

@Configuration
@Import(EnvironmentVaultConfiguration::class)
class VaultConfiguration() : AbstractVaultConfiguration() {

    override fun vaultEndpoint(): VaultEndpoint {
        return VaultEndpoint.from(URI.create(environment.getProperty("vault.uri")!!))
    }

    override fun clientAuthentication(): ClientAuthentication {
        return TokenAuthentication(environment.getProperty("vault.token")!!)
    }
}