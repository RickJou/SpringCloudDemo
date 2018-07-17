#!/bin/sh
#执行程序用户(不推荐使用root)
RUNNING_USER=root

#执行程序名称
PROGRAM_NAME="unified-eureka-register.jar"
PORT=10001

#spring cloud启动配置相关
SPRING_CLOUD_PROFILE="--server.port="${PORT}
SPRING_CLOUD_PROFILE=${SPRING_CLOUD_PROFILE}" --spring.profiles.active=registry1"
SPRING_CLOUD_PROFILE=${SPRING_CLOUD_PROFILE}" --spring.cloud.inetutils.preferredNetworks[0]=eurekaconfig1.com"
SPRING_CLOUD_PROFILE=${SPRING_CLOUD_PROFILE}" --eureka.client.serviceUrl.defaultZone=http://eurekaconfig1.com:10001/eureka/"

#java环境相关
JAVA_HOME=/usr/local/jdk8
JAVA_OPTS="-Xms2048m -Xmx2048m -Xss256k -XX:MetaspaceSize=860m -XX:MaxMetaspaceSize=860m -XX:NewSize=1100m -XX:MaxNewSize=1100m-XX:SurvivorRatio=22"  


export JAVA_HOME=$JAVA_HOME  
export PATH=$PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin 


start()
{

    #检验JAVA_HOME是否有过设置
    if [ -z "${JAVA_HOME}" ] ; then
        echo "请设置JAVA_HOME"
        echo "Please set JAVA_HOME"
        exit 1
    fi

    #检验JAVA_HOME/bin/java是否存在
    if [ ! -x "${JAVA_HOME}/bin/java" ] ; then
        echo "JAVA_HOME设置错误"
        echo "Can not find Java VM"
        exit 1
    fi

    #端口是否被占用
    if [ -z $(netstat -nptul|grep 22|head -n 1|awk '{print $4}') ] ; then
        echo "端口${PORT}已经占用"
        exit 1
    fi

    #该程序该端口是否已经存在
    local pid=$(ps -ef|grep java|grep ${PROGRAM_NAME}|grep ${PORT}|awk '{print $2}')
    if [ -n "${pid}" ] ; then
        echo "程序${PROGRAM_NAME}所在端口${PORT}已经占用,pid:"${pid}
        exit 1
    fi
	  
    echo "Using JAVA_HOME ${JAVA_HOME}"
    echo "Using CLASSPATH ${CLASSPATH}"

    #拼接运行命令
    #JAVA_CMD="nohup $JAVA_HOME/bin/java -jar ./$PROGRAM_NAME $JAVA_OPTS $SPRING_CLOUD_PROFILE >/dev/null 2>&1 & echo $!>$DIALUP_PID"
    JAVA_CMD="nohup $JAVA_HOME/bin/java -jar ./$PROGRAM_NAME $JAVA_OPTS $SPRING_CLOUD_PROFILE >console.log 2>&1 &"
 
    su $RUNNING_USER -c "${JAVA_CMD}"
    
    echo "program start pid:"$(ps -ef|grep java|grep ${PROGRAM_NAME}|grep ${PORT}|awk '{print $2}')
}

stop()
{
    local pid=$(ps -ef|grep java|grep ${PROGRAM_NAME}|grep ${PORT}|awk '{print $2}')
    if [ -z "$pid" ] ; then
        echo "程序${PROGRAM_NAME}所在端口${PORT}服务未开启,无需关闭";
        exit 1
    fi
    echo "stop ${PROGRAM_NAME}:${PORT} ......pid:${pid}"
    if [ -n "${pid}" ] ; then
	 kill -15 ${pid}
         echo "kill -15 "${pid}
    fi
}

restart()
{
    echo "restart ... "
    stop
    start
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    *)
echo $"Usage: $0 {start|stop|restart}"
RETVAL=1
esac
exit $RETVAL 