package ctrip.demo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApolloJavaClientDemo {
	public static void main(String[] args) throws InterruptedException {
		log.info("使用java client方式获取配置");
		log.info("获取公共配置");

		System.setProperty("app.id", "test-project");// appid
		System.setProperty("env", "dev");// 环境
		/* 在hosts文件(C:\Windows\System32\drivers\etc\hosts)中加入域名映射:192.168.21.26 docker-nginx*/

		publicConfig();
		privateConfig();

		while (true);
	}
	
	public static void privateConfig(){
		// 私有配置
		String privateNamespace = "application";
		Config privateConfig = ConfigService.getConfig(privateNamespace); 
		String someKey = "private-key1";
		String someDefaultValue = "defaultValue";
		String value = privateConfig.getProperty(someKey, someDefaultValue);
		log.info("私有配置"+someKey + ":" + value);
		//私有配置基础了公有配置,所以可以获取共有配置
		String somePublicNamespace = "org1.public-namespace";
		Config config = ConfigService.getConfig(somePublicNamespace); //config instance is singleton for each namespace and is never null
		someKey = "common.setting.value";
		someDefaultValue = "someDefaultValueForTheKey";
		value = config.getProperty(someKey, someDefaultValue);
		log.info("公共配置"+someKey+":"+value);
	}

	public static void publicConfig(){
		//公共配置
		String somePublicNamespace = "org1.public-namespace";
		Config config = ConfigService.getConfig(somePublicNamespace); //config instance is singleton for each namespace and is never null
		String someKey = "common.setting.value";
		String someDefaultValue = "someDefaultValueForTheKey";
		String value = config.getProperty(someKey, someDefaultValue);
		log.info("公共配置"+someKey+":"+value);
		
		//开启配置监听,接收实时推送的配置
		new Thread(new Runnable() {
			public void run() {
				config.addChangeListener(new ConfigChangeListener() {
				    @Override
				    public void onChange(ConfigChangeEvent changeEvent) {
				        log.info(String.format("监听到namespace:%s有修改", changeEvent.getNamespace()));
				        for (String key : changeEvent.changedKeys()) {
				            ConfigChange change = changeEvent.getChange(key);
				            log.info(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
				        }
				    }
				});
			}
		}).start();	
	}
}
