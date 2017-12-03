package helpful;

import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
// for testing it http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?WSDL

public class SoapCurrenciesBrowser {
	private ArrayList<Currency> list;

	public SoapCurrenciesBrowser() {
		try {
			// ������� ����������
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();

			// ����� ������� ��������� �� �����
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
			SOAPPart soapPart = message.getSOAPPart();

			StreamSource preppedMsgSrc = new StreamSource(new FileInputStream(
					//"C:\\Users\\������\\git\\myproj\\src\\main\\resources\\prepped.msg"));
					"WEB-INF\\classes\\prepped.msg"));
			soapPart.setContent(preppedMsgSrc);
			message.saveChanges();

			// ��������� ��������
			String destination = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
			// ��������
			SOAPMessage response = connection.call(message, destination);

			// ��������� � answer ������ � �������
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			response.writeTo(out);
			String answer = out.toString();// ��������� ���� ����������! ��� ����������� utf-8 ������ ������. ������� ����� �� ��������
			System.out.println("Ok, i posted soap message and got the answer: ");
			System.out.println(answer);
			connection.close();

			// ����, ���� �� ������� ����������� ����������, �� ��� ������ ����� ���������
			SoapParser parser = new SoapParser(answer);
			list = parser.getresult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Currency> getresult() {
		return list;
	}
}