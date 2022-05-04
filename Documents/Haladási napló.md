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

7. hét: Az előző heti tesztelési problémát sikerült orvosolni a security kikapcsolásával (`@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])`), így már sikerült postmanban tesztelni a regisztrációt, ami sikeres is volt, viszont a get user végpontál van valmi mapper probléma erre még keresem a megoldást. Ezen kívül a héten folytattam a controllerek implementálását a GET végpontok megvalósításával, felvettem az ehhez szükséges új osztályokat. Utána néztem a jwt tokenes user autentikációnak, illetve a képek kezelésének, de sajnos a héten nem sikerült úgy haladni, ahogy szerettem volna, így ezek még nem kerültek implementálásra. Következő hétre igyekszem pótolni az elmaradást.

8. hét: Befejeztem az összes végpontot megírását, összekötöttük Mátéval az alakalmazást. Először nála lokálisan beüzemeltük, majd utána kiraktam az appot [Herkokura](https://family-app-kotlin-backend.herokuapp.com/api). 
9. hét: Átkötöttem az alkalmazást PostgreSQL adatbázisra, mivel így bankkártya megadás nélkül ingyenesen tudtam csinálni Herokun egy PostgreSQL adatbázist is. Máté csinált tesztetek az apihoz, ezeket lefuttatattuk, hogy lássuk min kell még javítani. (8. és 9. hét tavaszi szünet miatt egy sprint volt.)

10. hét: Máté api tesztjei alapján javítgattam az alakalmazást, végére elértük, hogy az [összes teszt sikeresen lefusson](https://github.com/VPeterB/family-app-backend/blob/master/Documents/successful_tests.png), így valószínű, hogy megfelelően működik az alkalmazás. A Cascadetype-ot átírtam ALLról PERSIST-re így a delete műveletek is megfelelően működnek (pl. family törlésnél nem törlődik az összes familyben lévő user, hanem család nélkül, de megmaradnak). Lecseréltem a nagybetűs ID-kat kisbetűs id-ra, ezzel megoldódott sok json parsolásból fakadó probléma. Ezeken kívül még a műveletek logikáin is végeztem finomításokat, ahol indokolt volt, illetve megcsináltam a jwt tokenes authentikációt loginnál, és ez szükséges a többi végpont eléréséhez is, néhol ez biztosítja a userid-t is. Mindezt úgy valósítottam meg, hogy a jwt token cookieban utazik.

11. hét: Máté kérésére beépítettem egy időpont változót az összes modelbe, ami frissül, ha bármiféle modosítás történik, így könnyebben tudja követni, illetve frissíteni az adatait. Bevezettem korlátozásokat a végpontokra ott, ahol ez logikusnak tünt, jwt token segítségével. Javítottam az időközben felmerült hibákat. Elkészítettem az események backendoldali [specifikációját](https://github.com/VPeterB/family-app-backend/blob/master/Documents/Specifikáció.pdf) és az ehhez kapcsolódó [végpontok terveit](https://github.com/VPeterB/family-app-backend/blob/master/Documents/Végpontok.txt).