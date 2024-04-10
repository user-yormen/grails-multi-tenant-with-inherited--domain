package com.app

import grails.compiler.GrailsCompileStatic
import grails.gorm.multitenancy.WithoutTenant
import grails.gorm.transactions.Transactional
import org.grails.datastore.mapping.multitenancy.web.SubDomainTenantResolver
import org.grails.orm.hibernate.HibernateDatastore

@Transactional
class DatabaseConfigService {

    HibernateDatastore hibernateDatastore

    @WithoutTenant
    DatabaseConfiguration findDatabaseConfigurationByFamilyName(String familyName) {
        String firstName = Family.findByFamilyName(familyName)?.familyName
        createDatabaseConfigObject(familyName)
    }

    @WithoutTenant
    List<DatabaseConfiguration> findAllDatabaseConfiguration() {
        List<String> firstNames = Family.list([:]).collect{it.familyName}
        firstNames.collect { createDatabaseConfigObject(it) }
    }

    DatabaseConfiguration createDatabaseConfigObject(String familyName) {
        familyName = familyName.replaceAll("\\s", "_").toLowerCase()
        familyName = familyName == "saanie_family" ? "multitenantapp_db" : familyName
        return new DatabaseConfiguration(dataSourceName: familyName, configuration: configurationBySubDomain(familyName))
    }

    Map<String, Object> configurationBySubDomain(String familyName) {
        String url = "jdbc:mariadb://localhost:3306/${familyName}?autoReconnect=true&zeroDateTimeBehavior=convertToNull&characterEncoding=utf8"
        [
                'hibernate.hbm2ddl.auto':'update', // same as dbCreate = "update"
                'username': "root",
                'password': "root",
                'url': url
        ] as Map<String, Object>
    }

    def updateHibernateDatastore(DatabaseConfiguration databaseConfiguration){
        if (hibernateDatastore != null) {
            def connectionSources = hibernateDatastore.getConnectionSources()
            if (connectionSources != null) {
                String defaultName
                def dataStore = connectionSources.find {
                    println "it.name: ${it.name}"
                    defaultName = it.name
                    it.name == databaseConfiguration?.dataSourceName
                }
                if (!dataStore && databaseConfiguration != null && databaseConfiguration.dataSourceName != null && databaseConfiguration.configuration != null) {
//                    SubDomainTenantResolver
                    connectionSources.addConnectionSource(databaseConfiguration.dataSourceName, databaseConfiguration.configuration)
                } else {
                    return dataStore
                }
            } else {
                throw new IllegalStateException("ConnectionSources is null")
            }
        } else {
            throw new IllegalStateException("HibernateDatastore is not initialized")
        }
    }

}

@GrailsCompileStatic
class DatabaseConfiguration {
    String dataSourceName
    Map configuration
}
