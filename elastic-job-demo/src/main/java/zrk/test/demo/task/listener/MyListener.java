package zrk.test.demo.task.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyListener implements ElasticJobListener{
	private Long timeMillis;
	@Override
	public void afterJobExecuted(ShardingContexts arg0) {
		
		log.info("任务"+arg0.getJobName()+"后置执行......timeMillis:"+timeMillis+"\n\n");
	}

	@Override
	public void beforeJobExecuted(ShardingContexts arg0) {
		timeMillis = System.currentTimeMillis();
		log.info("\n\n");
		log.info("任务"+arg0.getJobName()+"前置执行......timeMillis:"+timeMillis);
	}

}
