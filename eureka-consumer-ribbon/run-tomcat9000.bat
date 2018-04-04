title ribbon消费者一 9000
java -Xms1024m -Xmx1024m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -XX:NewSize=650m -XX:MaxNewSize=650m -XX:SurvivorRatio=22 -jar ./target/eureka-consumer-ribbon.jar ^
--server.port=9000 ^
--spring.cloud.inetutils.preferredNetworks[0]=172.20