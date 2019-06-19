package rocks.marcelgross.wishlist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

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
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/test").permitAll()
            .antMatchers("/graphiql").permitAll()
            .antMatchers("/subscriptions").permitAll()
            .antMatchers("/vendor/*").permitAll()
            .antMatchers("/graphql").permitAll()
            .anyRequest().authenticated()

    }

}