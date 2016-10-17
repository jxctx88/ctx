package cn.memedai.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out
				.println(context.getJobDetail().getJobDataMap().get("name"));
		System.out.println(context.getMergedJobDataMap().get("name"));;
		String jobName = context.getJobDetail().getKey().getName();
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		System.out.println("任务Key:"+ jobName + "，任务正在执行，执行时间：" + dataFormat.format(Calendar.getInstance().getTime()));
	}

}
