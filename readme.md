Run tests:
`./gradlew test`

Run with parameters:
`./gradlew test -Dbrowser=firefox -Dheadless=true -DbaseUrl=https://prod-site.com`

Generate Allure report:
`./gradlew allureReport then ./gradlew openAllureReport`