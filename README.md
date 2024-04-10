# grails-multi-tenant-with-inherited-domain
This projects will implement grails multi-tenancy with domain models that inherit from other domain

In this project, i will create a simple projects that looks to implement multi tenancy using grails.
There will be three domain models.
  * Family
  * Parent
  * Children
  * 
The projects look to create each database schema from the name of the family created.
* Model
* Parent belongs to family
* Children inherits from Parents



# I have an issue with the app, basically it's like a fight between Grails & Hibernate
## Here we go

I have an already existing Grails app that i have added the multi tenancy implementation so that each user in my app has it own family database schema.
Family has a property of string familyName. The family name is used to created the database schema during insert.
Parent belongs to a family
Children has parent, so, it inherits from Parents.

My issues is whenever i create a family, the database gets created alright but the data does not get persisted in the new created database, at the end i get an error

Caused by: java.lang.NullPointerException: Cannot invoke "org.grails.orm.hibernate.cfg.Mapping.getBatchSize()" because "m" is null

After reading around, I keep getting response that this is an inheritance issue from Hibernate affecting grails. Has anyone faced this issue or how could i resolve this

For more insight on my set up, I have uploaded the app on github -> https://github.com/user-yormen/grails-multi-tenant-with-inherited-domain