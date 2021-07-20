package br.org.ciag.estagiario;

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
            .importPackages("br.org.ciag.estagiario");

        noClasses()
            .that()
            .resideInAnyPackage("br.org.ciag.estagiario.service..")
            .or()
            .resideInAnyPackage("br.org.ciag.estagiario.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.org.ciag.estagiario.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
