# Haladási napló

1. hét: Létrehoztam ezt a GitHub repot. Írtam egy béta feladat specifikációt. Ez alapján elkezdtem kidolgozni az
   adatmodellt és a végpontokat.

2. hét: A specifikáció adatmodell és architechtúra része javításra került Mátéval és a konzultáción megbeszéltek
   szerint. Elkészítettük
   a [végpontok tervét](https://github.com/VPeterB/family-app-backend/blob/master/Documents/Végpontok.txt). Ez alapján
   elkezdtem megtervezni az API-t Swaggerben.

3. hét: Elkészítettem a Swagger API-t,
   ami [itt](https://github.com/VPeterB/family-app-backend/blob/master/Documents/Swagger.txt) érhető el txt formátumban.
   Generáltam belőle kotlin - server kódot (Kotlin -Spring generátort nem találtam), itt elakadtam, mivel a választott
   technológia eredetileg Kotlin - SpringBoot és a generált kód nem SpringBoot. A MariaDB adatbázist is elkezdtem
   felkonfigurálni egyenlőre localhoston.

4. hét: Javítottam az API-t a konzultáción és a Mátéval megbeszéltek szerint. Legeneráltam a kódot [ennek](https://openapi-generator.tech/docs/generators/kotlin-spring/) a toolnak a segítségével, átnéztem és igyekeztem megérteni a generált kódnak a működését. Elkezdtem átalakítani a model osztályokat az adatbázis sémával kompatibilisre. Mivel még vannak benne errorok, amire keresem a megoldást, ezt még nem pusholtam. Elkészítettem egy kezdetleges adatbázis létrehzó sqlt, ami [itt](https://github.com/VPeterB/family-app-backend/blob/master/src/main/resources/sql/schema.sql) található. (5.hét: Rájöttem, hogy ez nem is kell.)

5. hét: Elkészítettem a model osztályokat, megtaláltam a másodlagos konstruktorral kapcsolatos error megoldását. A generált controllereket szétszedtem feladatcsoport szerinti osztályokra. Az apiban javítottam az előző konzultáción megbeszéltek szerint a feleslegesen utazó userID-kat. Valamiért nem indul a Spring Boot, erre még mindig keresem a megoldást. Miután meglettek a model osztályok, tesztelni akartam őket az adatbázissal, ekkor észleltem a problémát. A generált kódbol eltávolítottam a gandlet, de ez nem hozott megoldást, viszont a projekt tisztább lett tőle.

6. hét: Csináltam egy új projektet, mivel az előzőben volt egy indítás után megjelenő error amire sehogy sem találtam megoldást. Ez még azért is jó volt mivel a generált kódban voltak olyan dependency-k meg beállítások, amik elég zavarosak voltak. Mivel már megvolt elég sok dolog, azokat átemeltem ebbe a projektbe. Ezen kívül a user controllereket írtam meg, illetve a bejelentkezést és a regisztrációt. Egyenlőre kezdetlegesen, próbáltam valami tesztelhető részt csinálni, password elrejtés és jwt token nélkül, csak sima mentéssel. A user get végponthoz csináltam egy DTO-t illetve egy Mappert, hogy a response-ban a Mátéval megbeszélt módon ne egy User objektum utazzon Family, Invite meg ShoppingLists objektumokkal, hanem egy hasonló, amiben ezek helyett, csak az ID-jaik vannak.