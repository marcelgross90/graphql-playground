package rocks.marcelgross.wishlist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
       auth.inMemoryAuthentication()
           .withUser("admin")
           .password("{noop}admin")
           .roles("ADMIN")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        super.configure(http)
        configureHttpSecurity(http)
    }


    companion object {
        fun configureHttpSecurity(http: HttpSecurity): ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/graphiql").permitAll()
                .antMatchers("/graphql").permitAll()
                .anyRequest().authenticated()
        }
    }
}