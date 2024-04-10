package multitenantapp

import com.app.DatabaseConfigService
import com.app.DatabaseConfiguration
import org.grails.orm.hibernate.HibernateDatastore

class BootStrap {
    HibernateDatastore hibernateDatastore
    DatabaseConfigService databaseConfigService

    def init = { servletContext ->
        for (DatabaseConfiguration databaseConfiguration : databaseConfigService.findAllDatabaseConfiguration() ) {
            hibernateDatastore.getConnectionSources().addConnectionSource(databaseConfiguration.dataSourceName, databaseConfiguration.configuration)
        }

    }
    def destroy = {
    }
}
