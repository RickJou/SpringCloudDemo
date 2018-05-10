package zrk.test.demo.task.dataflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import lombok.extern.slf4j.Slf4j;
import zrk.test.demo.dao.empty.UserInfo;

@Slf4j
public class MyDataFlowJob implements DataflowJob<UserInfo> {
	AtomicInteger count = new AtomicInteger(0);

	@Override
	public List<UserInfo> fetchData(ShardingContext context) {
		String str = context.getJobParameter() + ":" + context.getShardingItem() + "taskId:" + context.getTaskId();
		log.info(str);
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword("123456");
		userInfo.setUserId(1);
		userInfo.setPhone("13355554444");
		userInfo.setUserName("alan zhou");

		ArrayList<UserInfo> arr = new ArrayList<>();
		arr.add(userInfo);
		
		String shardingParam = context.getShardingParameter();

		switch (context.getShardingItem()) {
		case 0:
			return arr;
		case 1:
			return arr;
		case 2:
			return arr;
		case 3:
			return arr;
		case 4:
			return arr;
		case 5:
			return arr;
		case 6:
			return arr;
		case 7:
			return arr;
		}
		return null;
	}

	@Override
	public void processData(ShardingContext shardingContext, List<UserInfo> data) {
		log.info("process data......");
	}
}
