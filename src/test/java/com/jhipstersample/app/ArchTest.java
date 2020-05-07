package com.jhipstersample.app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.jhipstersample.app");

        noClasses()
            .that()
            .resideInAnyPackage("com.jhipstersample.app.service..")
            .or()
            .resideInAnyPackage("com.jhipstersample.app.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.jhipstersample.app.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
