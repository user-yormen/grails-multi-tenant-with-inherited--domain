import multitenantapp.AppTenantResolver
import multitenantapp.FamilyInsertListener

// Place your Spring DSL code here
beans = {
    familyInsertListener(FamilyInsertListener)
    appTenantResolver(AppTenantResolver)
}
