# JavaMail Sender 📧

A simple, secure Java application that sends automated emails (including HTML formatting, plain text, and file attachments) using the Jakarta Mail API and Google's SMTPS protocol.

## Features
* **Secure Connection:** Uses Port 465 and forces implicit SSL (`TLSv1.2`) to bypass standard firewall blocks.
* **Multipart Messages:** Capable of sending plain text, complex HTML designs, and multiple file attachments in a single email.
* **Hardened Security:** Sanitized codebase designed to keep credentials local and secure.

## Prerequisites
Before running this application, you will need:
1. **Java Development Kit (JDK):** Version 8 or higher.
2. **Jakarta Mail:** Ensure the `jakarta.mail` JAR files are in your classpath.
3. **A Gmail Account with 2-Step Verification enabled.**
4. **A Google App Password:** You cannot use your standard Gmail password. You must generate a 16-digit App Password from your Google Account Security settings.

## Setup & Usage

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YourUsername/YourRepositoryName.git](https://github.com/YourUsername/YourRepositoryName.git)
Configure your credentials:
Open JavaMail1.java and locate the transport.connect method at the bottom of the file. Replace the placeholder text with your actual email and your 16-digit App Password (without spaces):

Java
transport.connect("smtp.gmail.com", "your_email@gmail.com", "your16digitpassword");
Note: Also remember to update the "From" and "To" email addresses in the message.setFrom() and message.setRecipients() methods.



2.Configure your file paths:
If you are testing the attachment features, update the File paths in the code to point to real files on your local computer.

Java
File myFile = new File("C:/path/to/your/document.pdf");


3.Run the application:
Compile and run the JavaMail1.java file. Watch the console for the debug logs and the final "Email sent successfully!" message.