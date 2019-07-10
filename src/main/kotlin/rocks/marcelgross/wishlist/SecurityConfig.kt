package rocks.marcelgross.wishlist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}admin")
            .roles("ADMIN", "USER")
            .and()
            .withUser("user")
            .password("{noop}user")
            .roles("USER")
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