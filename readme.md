Run tests:
`./gradlew test`

```shell
# specific class
mvn test -Dtest=MyTestClass

# multiple classes 
mvn test -Dtest=TestClassOne,TestClassTwo,com.example.another.MySpecificTest

# with wildcards 
mvn test -Dtest=TestCi*le

mvn test -Dtest=MyTestClass#myTestMethod
mvn test -Dtest=MyTestClass#testOne+testTwo

# Excluding Specific Test Classes or Methods
mvn test -Dtest=!MyTestClass
mvn test -Dtest=MyTestClass#!myExcludedMethod
```

Run with parameters:
`./gradlew test -Dbrowser=firefox -Dheadless=true -DbaseUrl=https://prod-site.com`
```shell
./gradlew test --tests "dev.demo.ui.ShadowRootTest" -Dbrowser=firefox
```

Generate Allure report:
`./gradlew allureReport then ./gradlew openAllureReport`