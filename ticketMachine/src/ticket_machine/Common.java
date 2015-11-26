package ticket_machine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {

	public int[] refund(int all){
		int[] wons = new int[4];//nums of 1,2,5,10
		wons[3] = all/10;
		all = all%10;
		wons[2] = all/5;
		all = all%5;
		wons[1] = all/2;
		all = all%2;
		wons[0] = all;
		return wons;
	}
	
	public int ticketPrice(int begin, int end, int all){
		int distance = Math.min(Math.abs(begin - end), all - Math.abs(begin - end));
		if(distance>0 && distance<=3)
			return 2;
		if(distance<=6)
			return 4;
		return 6;
	}
	
	public int searchStation(String name){//search is there any station named name
		if(null == ReadConfig.stationNames){
			return -2;
		}
		String[] tmp = ReadConfig.stationNames;
		for(int i=0; i<tmp.length; i++){
			if(name.equals(tmp[i]))
				return i;
		}
		return -1;
	}
	
	public Date string2Date(String dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	public String date2String(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}
	
	public Date dayBefore(Date date, int x){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 0-x);
		Date theDay = calendar.getTime();
		return theDay;
	}
	
	public static void main(String[] args) {
		Common common = new Common();
		int[] w = common.refund(16);
		for(int i=0; i<w.length; i++)
			System.out.println(w[i]);
	}

}
