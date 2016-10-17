package cn.memedai.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzCronTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
		job.getJobDataMap().put("name", "job-xiong");
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/13 * * * * ?")).build();
		trigger.getJobDataMap().put("name", "trigger-xiong");
		scheduler.scheduleJob(job,trigger);
		scheduler.start();
		
		try {  
            // 等待60秒查看效果  
            Thread.sleep(60L * 1000L);  
        } catch (Exception e) {  
        }  
        scheduler.shutdown(true);  
		
		
	}

}
