import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Solution {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		JSONArray attendance = new JSONArray();
		try {
			attendance = (JSONArray) parser.parse(new FileReader("AttendanceRegister.json"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (ParseException e) {
			System.out.println(e);
		}

		SimpleDateFormat workingHoursFormat = new SimpleDateFormat("HH:mm");

		Scanner input = new Scanner(System.in);
		String employeName = input.nextLine();

		for (Object documents : attendance) {

			JSONObject entry = (JSONObject) documents;

			if (employeName.equals(entry.get("employeName"))) {
				Date checkInTime = null;
				try {
					checkInTime = workingHoursFormat.parse((String) entry.get("checkinTime"));
				} catch (java.text.ParseException e) {
					System.out.println(e);
				}

				Date checkOutTime = null;
				try {
					checkOutTime = workingHoursFormat.parse((String) entry.get("checkouttime"));
				} catch (java.text.ParseException e) {
					System.out.println(e);
				}

				long workingHour = checkOutTime.getTime() - checkInTime.getTime();
				workingHour = (workingHour / 1000) / 60;
				long minutes = workingHour % 60;
				workingHour = workingHour / 60;

				System.out.println("EmployeName: " + entry.get("employeName"));
				System.out.println("Date: " + entry.get("date"));
				System.out.println("CheckInTime: " + entry.get("checkinTime"));
				System.out.println("CheckOuttime: " + entry.get("checkouttime"));
				System.out.println("Department: " + entry.get("dept"));
				System.out.println("Working Hours : " + workingHour + " Hours And " + minutes + " Minutes ");
				System.out.println();
				System.out.println();
			}

		}
	}
}