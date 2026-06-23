import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeBodyPart;
import java.io.File;

public class JavaMail1 {

    public static void main(String[] args) {
        
        // --- Setting Properties ---
        Properties prop = new Properties();
        
        // Set the required properties for SMTPS
        prop.setProperty("mail.smtps.auth", "true"); // enable authentication
        prop.setProperty("mail.smtps.host", "smtp.gmail.com"); // gmail server address
        prop.setProperty("mail.smtps.port", "465"); // port used for secure communication
        prop.setProperty("mail.smtps.ssl.protocols", "TLSv1.2"); // Forces modern encryption
        
        // --- Creating Session ---
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        
        // --- Compose the email ---
        try {
            // 1. Create a new, blank MimeMessage
            Message message = new MimeMessage(session);
            
            // 2. Set the "From" address 
            message.setFrom(new InternetAddress("YOUR_EMAIL_HERE@gmail.com"));
            
            // 3. Set the "To" address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("RECEIVER_EMAIL_HERE@gmail.com"));
            
            // 4. Set the Subject line
            message.setSubject("Testing JavaMail API");
            
            MimeMultipart multipart = new MimeMultipart();
            
            // --- 7.1 Add HTML content ---
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<body>\r\n"
                    + "    <div class=\"container\">\r\n"
                    + "        <div class=\"header\">\r\n"
                    + "            <div class=\"logo\">♥</div>\r\n"
                    + "            <h1>Celebrate Love, Find Forever</h1>\r\n"
                    + "        </div>\r\n"
                    + "        <div class=\"content\">\r\n"
                    + "            <p>Dear Friend,</p>\r\n"
                    + "            <p>Love is the most beautiful journey of life. At <span class=\"highlight\">MatrimonyHeart</span>, we believe every love story deserves to be celebrated, honored, and cherished.</p>\r\n"
                    + "            <p>Our exclusive matrimonial platform is dedicated to connecting soulmates who are ready to build a life filled with joy, understanding, and endless love. Whether you're looking for a life partner or helping a loved one find theirs, we create meaningful connections that last a lifetime.</p>\r\n"
                    + "            <div class=\"pdf-section\">\r\n"
                    + "                <strong>📖 Special Gift for You:</strong><br>\r\n"
                    + "                We've attached a beautiful PDF guide about \"The Art of Lasting Love\" filled with insights, stories, and wisdom to inspire your journey. \r\n"
                    + "                <br><br>\r\n"
                    + "                <a href=\"#\" style=\"color: #d6336c; font-weight: bold;\">Download Your Free Love Guide (PDF)</a>\r\n"
                    + "            </div>\r\n"
                    + "            <p>Join thousands of happy couples who found their perfect match through our trusted community.</p>\r\n"
                    + "            <div style=\"text-align: center;\">\r\n"
                    + "                <a href=\"#\" class=\"cta-button\">Start Your Love Story Today</a>\r\n"
                    + "            </div>\r\n"
                    + "            <p>Because love isn't just about finding someone—it's about celebrating the beautiful journey together.</p>\r\n"
                    + "            <p>Warmly,<br>\r\n"
                    + "            The MatrimonyHeart Team</p>\r\n"
                    + "        </div>\r\n"
                    + "        <div class=\"footer\">\r\n"
                    + "            <p>© 2024 MatrimonyHeart. Celebrating love, one connection at a time.</p>\r\n"
                    + "            <p><a href=\"#\" style=\"color: #6c757d;\">Unsubscribe</a> | <a href=\"#\" style=\"color: #6c757d;\">Privacy Policy</a></p>\r\n"
                    + "        </div>\r\n"
                    + "    </div>\r\n"
                    + "</body>";
            
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);
            
            // --- 7.2 Add Text and Attachment ---      
            MimeBodyPart textpart = new MimeBodyPart();
            textpart.setText("Hello, please find your invoice attached to this email.");
            multipart.addBodyPart(textpart);
            
            MimeBodyPart attachmentPart = new MimeBodyPart();
            // REPLACED LOCAL PATH WITH PLACEHOLDER
            File myFile = new File("PATH_TO_YOUR_PDF_FILE.pdf");
            System.out.println("PDF exists: " + myFile.exists());
            attachmentPart.attachFile(myFile);
            multipart.addBodyPart(attachmentPart);
            
            // --- 7.3 Add an Image ---
            MimeBodyPart imagepart = new MimeBodyPart();
            // REPLACED LOCAL PATH WITH PLACEHOLDER
            File myImage = new File("PATH_TO_YOUR_IMAGE_FILE.png");
            System.out.println("Image exists: " + myImage.exists());
            imagepart.attachFile(myImage);
            multipart.addBodyPart(imagepart);
            
            // Pack everything into the message
            message.setContent(multipart);
            
            // --- Send the email ---
            System.out.println("Preparing to connect to Gmail...");
            Transport transport = session.getTransport("smtps");
            
            // DANGER ZONE: Never hardcode real passwords here when uploading to GitHub!
            transport.connect("smtp.gmail.com", "YOUR_EMAIL_HERE@gmail.com", "YOUR_16_DIGIT_APP_PASSWORD_HERE");
            
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
            System.out.println("Email sent successfully!");
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}