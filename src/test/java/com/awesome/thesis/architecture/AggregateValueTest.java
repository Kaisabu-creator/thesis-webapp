package com.awesome.thesis.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.awesome.thesis.annotations.AggregateValue;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Überprüft, ob nur Aggregate Values mit {@link AggregateValue} annotiert sind.
 */
@AnalyzeClasses(packages = "com.awesome.thesis.logic.domain.model..")
public class AggregateValueTest {
  @ArchTest
  static final ArchRule alleAttributeSollenFinalSein = classes()
      .that()
      .areAnnotatedWith(AggregateValue.class)
      .should()
      .haveOnlyFinalFields();
}
