package multitenantapp

import com.app.Family
import grails.gorm.DetachedCriteria
import org.grails.datastore.mapping.multitenancy.AllTenantsResolver
import org.grails.datastore.mapping.multitenancy.TenantResolver
import org.grails.datastore.mapping.multitenancy.exceptions.TenantNotFoundException
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletWebRequest

import javax.servlet.http.HttpServletRequest

//class AppTenantResolver implements AllTenantsResolver{
class AppTenantResolver implements TenantResolver{
//    @Override
//    Iterable<Serializable> resolveTenantIds() {
//        Family.withTransaction(readOnly: true) {
//            new DetachedCriteria(Family)
//                    .distinct('familyName')
//                    .list()  as Iterable<Serializable>
//        }
//    }

    @Override
    Serializable resolveTenantIdentifier() throws TenantNotFoundException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()
        if(requestAttributes instanceof ServletWebRequest) {

            HttpServletRequest httpServletRequest = ((ServletWebRequest) requestAttributes).getRequest()
            String token = httpServletRequest.getHeader("familyName")
            if ( !token ) {
                token = "multitenantapp_db"
//                throw new TenantNotFoundException("Tenant could not be resolved from HTTP Header: familyName")
            }
            token = token.replaceAll("\\s", "_").toLowerCase()
            String familyName = token ==  "saanie_family" ? "multitenantapp_db" : token
            if ( familyName ) {
                return familyName
            }
            throw new TenantNotFoundException("Tenant could not be resolved from HTTP Header: familyName")
        }
        throw new TenantNotFoundException("Tenant could not be resolved outside a web request")
    }
}
