package uth.cine.view.swing;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author El Especialista
 */
public class SendFileToEmail extends SwingWorker<Void, Void> {

    private String fileName = "";
    private String emailTo = "";
    private String emailFrom = "";
    private String emailPWSFrom = "";
    private String smtp = "";
    private String port = "";
    private String messageText = "";
    private String asunto = "";
    public String logStatus = "";

    public SendFileToEmail(String emailFrom, String emailPWSFrom, String emailTo, String asunto , String messageText, String fileName, String smtp, String port  ) {
        this.fileName = fileName;
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.emailPWSFrom = emailPWSFrom;
        this.smtp = smtp;
        this.port = port;
        this.messageText = messageText;
        this.asunto = asunto;
        
    }

    
     public void sendSalesThread() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    doInBackground();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }
    public  Void doInBackground() {

        int progress = 0;
        if (!fileName.equals("")) {
            System.out.println(" Archivo adjunto... ");
            logStatus += "\nIniciando...  ";
            boolean debug = false; 
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtp);
            props.put("mail.smtp.starttls.enable", "true"); 
            props.put("mail.smtp.port", port); 
            props.put("mail.smtp.auth", "true");  
            String username = emailFrom;
            String password = emailPWSFrom;//Ini.EMAIL_PASS;//"";
            System.out.println(" Agregando properties...  ");
            logStatus += " AG. ";
            SMTPAuthenticationEmail auth = new SMTPAuthenticationEmail(username, password);

            System.out.println("Iniciando session....  ");
            logStatus += "\nIniciando session.... ";
            //Session session = Session.getInstance(props, null);
            Session session = Session.getInstance(props, auth);
            session.setDebug(debug);

            try {
                // create a message
                System.out.println("Creando correo...");
                logStatus += "\nCreando correo...  ";
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(emailFrom));
                InternetAddress[] address = {new InternetAddress(emailTo)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(asunto);
                MimeBodyPart mbp1 = new MimeBodyPart();// create and fill the first message part
                mbp1.setText(messageText);
                MimeBodyPart mbp2 = new MimeBodyPart();// create the second message part
                logStatus += " AAdj. ";
                 System.out.println("Adjunto archivo...");
                mbp2.attachFile(fileName);// attach the file to the message
                Multipart mp = new MimeMultipart();// create the Multipart and add its parts to it
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);
                msg.setContent(mp);// add the Multipart to the message
                msg.setSentDate(new Date());// set the Date: header
                System.out.println("Enviando correo...");
                logStatus += "\nEnviando correo... ";
                Transport.send(msg);                // send the message
                System.out.println("\nTermino el envio del correo.");
                logStatus += "\n-------- FINALIZO EL PROCESO DE ENVIO ------\n";
                JOptionPane.showMessageDialog(null, "El archivo seleccionado fue enviado satisfactoriamente        ");
            } catch (MessagingException mex) {
                logStatus += "\nOcurrio un error: " +  mex.getMessage();
                mex.printStackTrace();                
            } catch (IOException ioex) {
                logStatus += "\nOcurrio un error: " +  ioex.getMessage();
                ioex.printStackTrace();//            this.dlg.setCursor(null);
            }
        } else {
            System.out.println("EL ARCHIVO NO SE HA ESPECIFICADO");
        }

        return null;
    }/*------- end doInBackgroud -------*/


    @Override
    protected void done() {
        String salida = ("-------- FINALIZO EL PROCESO DE ENVIO ------");
        logStatus += "\n-------- FINALIZO EL PROCESO DE ENVIO ------\n";
    }

    public class SMTPAuthenticationEmail extends javax.mail.Authenticator {

        private String username;
        private String password;

        public SMTPAuthenticationEmail(String username, String password) {
            System.out.println("Authentication 1");
            logStatus += " AUT1.   ";
            this.password = password;
            this.username = username;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
//           se procesa
            System.out.println(" Autentication 2   ");
            logStatus += " AUT2    ";
            return new PasswordAuthentication(username, password);

        }
    }
}

//String to = "i@hotmail.com"; // Ini.EMAIL_ADM;
//            String from = "i@gmail.com"; //Ini.EMAIL;
//            String host = "smtp.gmail.com";//Ini.SMTP;
//            String filename = this.file;//"c:\\yyy.yyy";
//             String msgText1 = "INFORMACIÓN DE TIENDA.\nENVIADA POR: " + Ini.USUARIO;
//            String subject = "DESCUENTO DE PRODUCTOS DE TIENDA " + Ini.STORE_NAME;

