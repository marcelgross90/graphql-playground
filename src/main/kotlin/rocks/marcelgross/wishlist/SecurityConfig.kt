package rocks.marcelgross.wishlist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import rocks.marcelgross.wishlist.user.UserRepository

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@EnableWebSecurity
class SecurityConfig(val userRepository: UserRepository) : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val authentication = auth.inMemoryAuthentication()
        userRepository.findAll().forEach {
            val roles: Array<String> = if (it.name.startsWith("user")) arrayOf("USER") else arrayOf("ADMIN", "USER")
            authentication.withUser(it.name)
                .password("{noop}${it.name}")
                .roles(*roles)
        }
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            /**
             * Resources for Graphiql must be enabled Start
             */
            .antMatchers("/graphiql").permitAll()
            .antMatchers("/subscriptions").permitAll()
            .antMatchers("/vendor/*").permitAll()
            /**
             * Resources for Graphiql must be enabled END
             */
            .antMatchers("/graphql").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
    }
}
