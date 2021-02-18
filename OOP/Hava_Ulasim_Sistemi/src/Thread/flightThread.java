package Thread;

import Business.Flight;
import Business.SystemDateHandler;
import Report.MessageCollector;

public class flightThread extends Thread {
	private SystemDateHandler sdh;
	private Flight flight;
	private MessageCollector mc;
	
	public flightThread(SystemDateHandler sdh, Flight flight, MessageCollector mc) {
		this.sdh = sdh;
		this.flight = flight;
		this.mc = mc;
	
	}
	
	public Flight getFlight() {
		return this.flight;
	}


	public void run()  {//The threads waits when their land time occurs
						//they waits untill user give them permission
						//when permisson given. The appropriate threads will be notified.
		
	
			try {
				super.sleep(sdh.calculateElapsedMiliSeconds(flight.getDepartureDate(), flight.getArrivalDate()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			mc.addMessage("The flight "+flight.getFlightNumber()+" is ready to land");
			flight.setWaiting(true);

			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flight.setWaiting(false);
			mc.addMessage("The flight "+flight.getFlightNumber()+" is landed successfuly");
			flight.setLanded(true);


	}
	


}
