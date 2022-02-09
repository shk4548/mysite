### Mysite04, 05 Package Structure


[src]
   |--- [main]
   |	|--- [java]
   |	|         |--- com
   |	|         |        |--- poscoict
   |	|         |        |          | --- config
   |	|         |        |          |          | --- app
   |	|         |        |          |          |        |--- DBConfig.java
   |	|         |        |          |          |        |--- MyBatisConfig.java
   |	|         |        |          |          | --- web
   |	|         |        |          |          |        |--- MVCConfig.java
   |	|         |        |          |          |        |--- SecurityConfig.java
   |	|         |        |          |          |        |--- MessageConfig.java
   |	|         |        |          |          |        |--- FileuploadConfig.java
   |	|         |        |          | --- mysite
   |	|         |        |          |          | --- controller
   |	|         |        |          |          | --- service
   |	|         |        |          |          | --- repository
   |	|         |        |          |          | --- vo
   |	|         |        |          |          | --- exception
   |	|         |        |          |          | --- aop
   |	|         |        |          |          | --- config
   |	|         |        |          |          |          | --- AppConfig.java
   |	|         |        |          |          |          | --- WebConfig.java

   |	|--- [resource]
   |	|         |--- logback.xml
   |	|         |--- com
   |	|         |        |--- poscoict
   |	|         |        |          | --- mysite
   |	|         |        |          |          | --- config
   |	|         |        |          |          |          | --- app
   |	|         |        |          |          |          |        | ----- jdbc.properties
   |	|         |        |          |          |          | --- app
