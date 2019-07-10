package rocks.marcelgross.wishlist

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rocks.marcelgross.wishlist.security.UserContext
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping(
    path = ["/test"]
)
class TestResource(
    private val userContext: UserContext
) {

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    fun test(): String = "${userContext.user?.email}"
}
