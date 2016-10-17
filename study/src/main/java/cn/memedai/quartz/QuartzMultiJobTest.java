package cn.memedai.quartz;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMultiJobTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		Date runTime = DateBuilder.nextGivenSecondDate(null, 13);
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1","group1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.startAt(runTime).build();
		scheduler.scheduleJob(job, trigger);
		
		job = JobBuilder.newJob(MyJob.class).withIdentity("job2","group1").build();
		trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
				.startAt(runTime).build();
		scheduler.scheduleJob(job, trigger);
		//每3秒重复执行5次，总共6次
		job = JobBuilder.newJob(MyJob.class).withIdentity("job3", "group1").build();
		trigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(5).withIntervalInSeconds(3))
				.startAt(runTime).build();
		scheduler.scheduleJob(job, trigger);
		
		scheduler.start();
		try{
			Thread.sleep(20*1000);
		}catch (Exception e) {  
  
        } 
		scheduler.shutdown();
		System.out.println(scheduler.getMetaData());
	}

}
