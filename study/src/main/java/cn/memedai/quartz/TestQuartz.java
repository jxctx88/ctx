package cn.memedai.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		Date runTime = DateBuilder.evenMinuteDate(new Date());
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		try{
			Thread.sleep(65L*1000);
		}catch(Exception e){
			
		}
		scheduler.shutdown(true);
	}
	

}
