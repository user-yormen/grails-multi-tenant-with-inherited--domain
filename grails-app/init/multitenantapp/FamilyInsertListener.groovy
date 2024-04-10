package multitenantapp

import com.app.DatabaseConfigService
import com.app.DatabaseConfiguration
import com.app.Family
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.orm.hibernate.HibernateDatastore
import org.hibernate.cfg.AvailableSettings
import org.hibernate.cfg.Environment
import org.springframework.beans.factory.annotation.Autowired
import grails.events.annotation.gorm.Listener
import org.grails.datastore.mapping.engine.event.PostInsertEvent

@CompileStatic
@Slf4j
class FamilyInsertListener {
    @Autowired
    HibernateDatastore hibernateDatastore

    @Autowired
    DatabaseConfigService databaseProvisioningService

    @Listener(Family)
    void onUserPostInsertEvent(PostInsertEvent event) {
        String familyName = event.getEntityAccess().getPropertyValue("familyName")
        DatabaseConfiguration databaseConfiguration = databaseProvisioningService.findDatabaseConfigurationByFamilyName(familyName)
//        hibernateDatastore.addTenantForSchema(Environment.OMIT_JOIN_OF_SUPERCLASS_TABLES)
//        hibernateDatastore.addTenantForSchema(AvailableSettings.OMIT_JOIN_OF_SUPERCLASS_TABLES)
        familyName = familyName.replaceAll("\\s", "_").toLowerCase()
        familyName = familyName == "saanie_family" ? "multitenantapp_db" : familyName
        hibernateDatastore.addTenantForSchema(familyName)
        hibernateDatastore.getConnectionSources().addConnectionSource(databaseConfiguration.dataSourceName, databaseConfiguration.configuration)
    }
}
