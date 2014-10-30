/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Deivid
 */
public class Tarefa implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        StringBuilder string = new StringBuilder();
        GregorianCalendar calendar = new GregorianCalendar();

        string.append(calendar.get(GregorianCalendar.HOUR_OF_DAY));
        string.append(":");
        string.append(calendar.get(GregorianCalendar.MINUTE));
        string.append(":");
        string.append(calendar.get(GregorianCalendar.SECOND));

        String hora = string.toString();
        System.out.println(hora);
        backupBanco();
    }

    public void enviarEmail() {
        System.out.println("enviando email");
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Hotmail
         */
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("deivid6291@hotmail.com", "senha");
                    }
                });

        /**
         * Ativa Debug para sessão
         */
        //session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("deivid6291@hotmail.com")); //Remetente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("deivid6291@hotmail.com")); //Destinatário(s)
            message.setSubject("Enviando email com JavaMail");//Assunto
            message.setText("Enviei este email utilizando JavaMail com minha conta Hotmail!");
            /**
             * Método para enviar a mensagem criada
             *
             */
            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = System.getProperty("user.home") + "\\banco.backup";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("banco.backup");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }

    }

    public void backupBanco() {
        try {

            ProcessBuilder pb;
            Process p;
            pb = new ProcessBuilder("C:\\Program Files (x86)\\PostgreSQL\\9.3\\bin\\pg_dump.exe", "-i", "-h",
                    "localhost", "-p", "5432", "-U", "deivid", "-F", "c", "-b", "-v", "-f", 
                    System.getProperty("user.home") + "\\banco.backup", "banco");
            pb.environment().put("PGPASSWORD", "deivid");

            //pb.redirectErrorStream(true);
            p = pb.start();

            final Process process = pb.start();

            try (BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line = r.readLine();
                while (line != null) {
                    System.err.println(line);
                    line = r.readLine();
                }
            }

            process.waitFor();
            process.destroy();
            enviarEmail();
        } catch (IOException | InterruptedException e) {
        }
    }

}
