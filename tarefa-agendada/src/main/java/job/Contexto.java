/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Deivid
 */
@WebListener
public class Contexto implements ServletContextListener {

    private Scheduler sh = null;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            sh = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException ex) {
            Logger.getLogger(Contexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sh.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(Contexto.class.getName()).log(Level.SEVERE, null, ex);
        }

        JobDetail job = JobBuilder.newJob(Tarefa.class).withIdentity("quartzhora").build();
        ScheduleBuilder<?> sb = CronScheduleBuilder.cronSchedule("0 0/60 * * * ?");

        Trigger t = TriggerBuilder.newTrigger().withIdentity("quartztrigguer").
                withSchedule(sb).startNow().build();

        try {
            sh.scheduleJob(job, t);
        } catch (SchedulerException ex) {
            Logger.getLogger(Contexto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {            
            sh.shutdown();            
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SchedulerException ex) {
            Logger.getLogger(Contexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
