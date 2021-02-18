package Business;

import java.util.Date;

public class SystemDateHandler {
	
	private Date start;
	private Date current;


	public SystemDateHandler() {
		this.start = new Date();
		this.setCurrent(start);
	
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getCurrent() {
		return current;
	}
	
	public void keepAlive() {
		setCurrent(new Date(current.getTime() + 120));
	}
	
		
	public long calculateElapsedMiliSeconds(Date from, Date to) {
		return (to.getTime() - from.getTime())*60;
	}
	
	public void setCurrent(Date current) {
		this.current = current;
	}
	
	
	public static void main(String[] args) {
		Date start = new Date();

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SystemDateHandler sdh = new SystemDateHandler();
		Date end = new Date();
		long milisec = sdh.calculateElapsedMiliSeconds(start, end);
		System.out.println(milisec);
	}

	
	
}


