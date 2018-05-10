package zrk.test.demo.task.simple;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySimpleJob implements SimpleJob {

	@SuppressWarnings("static-access")
	@Override
	public void execute(ShardingContext context) {
		String str = context.getJobParameter()+":"+context.getShardingItem()+"taskId:"+context.getTaskId();
		log.info(str);
		switch (context.getShardingItem()) {
		case 0:
			break;
		case 1:
			// do something by sharding item 1
			break;
		case 2:
			// do something by sharding item 2
			break;
		// case n: ...
		}
		try {
			//do some things
			log.info(str+"do some thing .....");
			Thread.currentThread().sleep(2000);
			log.info(str+"complete");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
