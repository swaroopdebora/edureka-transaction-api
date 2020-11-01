package com.demoaut.newtours.MavenDemo;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utils.Constants;
public class BaseTest {
	private static ITestContext context;
	WebDriver driver;  // driver object declaration
	public LoginPage_1 login;  // pageclass object declaration
	public SpecialOffer_2 SpecialOffer ; // pageclass object declaration
	public ProductDesc_3 proddesc; // pageclass object declaration
	public SignOff_7 signoff; // pageclass object declaration
	public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
		iTestContext.setAttribute("driver", driver);
		return iTestContext;
	}
@BeforeTest()
 public void openBrowser(ITestContext iTestContext) {
	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	driver = new ChromeDriver(); // driver object instantiation 
	context = setContext(iTestContext, driver);
	driver.manage().window().maximize();
	driver.get("https://www.advantageonlineshopping.com/#/");
	// pageclass object instantiation by constructor of pageclass with baseclass driver instance
	login = new LoginPage_1(driver);  
	SpecialOffer = new SpecialOffer_2(driver); // pageclass object instantiation by constructor of pageclass with baseclass driver instance
	proddesc = new ProductDesc_3(driver); // pageclass object instantiation by constructor of pageclass with baseclass driver instance
	signoff = new SignOff_7(driver); // pageclass object instantiation by constructor of pageclass with baseclass driver instance
					}
  @AfterTest() public void closeBrowser() { driver.close(); }
  @AfterSuite()
	public void tearDown() {
	sendReportByGMail("ananyabaddipudi@gmail.com", Constants.EMAIL_PASSWORD, "debora11.2k@gmail.com", "TestCase Report", "");
	sendReportByGMail("ananyabaddipudi@gmail.com", Constants.EMAIL_PASSWORD, "swaroop.2k@gmail.com", "TestCase Report", "");
	}

	/** 	 * Send email using java 	 * 	 * @param from 	 * @param pass 	 * @param to 	 * @param subject 	 * @param body 	 */
	private static void sendReportByGMail(String from, String pass, String to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			// Set from address
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set subject
			message.setSubject(subject);
			message.setText(body);

			BodyPart objMessageBodyPart = new MimeBodyPart();

			objMessageBodyPart.setText("Please Find The Attached Report File!");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(objMessageBodyPart);

			objMessageBodyPart = new MimeBodyPart();

			// Set path to the pdf report file
			String filename = System.getProperty("user.dir") + "\\TestReport\\Test-Automation-Report.html";
			// Create data source to attach the file in mail
			DataSource source = new FileDataSource(filename);

			objMessageBodyPart.setDataHandler(new DataHandler(source));

			objMessageBodyPart.setFileName(filename);

			multipart.addBodyPart(objMessageBodyPart);

			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
