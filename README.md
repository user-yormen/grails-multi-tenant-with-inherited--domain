# grails-multi-tenant-with-inherited-domain
This projects will implement grails multi-tenancy with domain models that inherit from other domain

In this project, i will create a simple projects that looks to implement multi tenancy using grails.
There will be three domain models.
  * Family
  * Parent
  * Children
  * 
The projects look to create each database schema from the name of the family created.
* ** Model
* *** Parent belongs to family
* *** Children inherits from Parents
