# Haladási napló

1. hét: Létrehoztam ezt a GitHub repot. Írtam egy béta feladat specifikációt. Ez alapján elkezdtem kidolgozni az adatmodellt és a végpontokat.

2. hét: A specifikáció adatmodell és architechtúra része javításra került Mátéval és a konzultáción megbeszéltek szerint. Elkészítettük a [végpontok tervét](https://github.com/VPeterB/family-app-backend/blob/master/Documents/Végpontok.txt). Ez alapján elkezdtem megtervezni az API-t Swaggerben.

3. hét: Elkészítettem a Swagger API-t, ami [itt](https://github.com/VPeterB/family-app-backend/blob/master/Documents/Swagger.txt) érhető el txt formátumban. Generáltam belőle kotlin - server kódot (Kotlin -Spring generátort nem találtam), itt elakadtam, mivel a választott technológia eredetileg Kotlin - SpringBoot és a generált kód nem SpringBoot. A MariaDB adatbázist is elkezdtem felkonfigurálni egyenlőre localhoston.

4. hét: Legeneráltam a kódot [ennek](https://openapi-generator.tech/docs/generators/kotlin-spring/) a toolnak a segítségével, átnéztem és igyekeztem megérteni a generált kódnak a működését. Elkezdtem átalakítani a model osztályokat az adatbázis sémával kompatibilisre. Mivel még vannak benne errorok, amire keresen a megoldást, ezt még nem pusholtam. Elkészítettem az adatbázis létrehzó sqlt, ami [itt](https://github.com/VPeterB/family-app-backend/blob/master/src/main/resources/sql/schema.sql) található.
