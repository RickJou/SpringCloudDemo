title springfox-swagger api ×ÜÁ¿ 9080
java -Xms1024m -Xmx1024m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -XX:NewSize=650m -XX:MaxNewSize=650m -XX:SurvivorRatio=22 -jar ./target/swaggerui-eureka.jar ^
--server.port=9080 ^
--spring.cloud.inetutils.preferredNetworks[0]=172.20^
--eureka.client.serviceUrl.defaultZone=http://192.168.21.26:10001/eureka/,http://192.168.21.26:10002/eureka/,http://192.168.21.26:10003/eureka/