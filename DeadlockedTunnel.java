package assign4_JC_CP;

import java.util.Random;

class LeftBoundCars {
	private int carNumber = 0;

	public int getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(int i) {
		this.carNumber = i;
	}
}

class RightBoundCars {
	private int carNumber = 1;

	public int getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(int i) {
		this.carNumber = i;
	}
}

class Util {
	// Util class to sleep a thread
	static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Tunnel {
	// odd car number means right-bound while even car number is left-bound
	synchronized void tunnelTime(int carNumber) {// synchronized method
		try {
			Random r = new Random();
			Util.sleep(r.nextInt(500));
		} catch (Exception e) {
			System.out.println(e);
		}
		if (carNumber % 2 == 0) {
			
			System.out.println("Left-bound Car " + carNumber + " is in the tunnel.");
			
		} else {
			
			System.out.println("Right-bound Car " + carNumber + " is in the tunnel.");
			
		}
		try {
			Random r = new Random();
			Util.sleep(r.nextInt(1500));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

public class DeadlockedTunnel {
	
	public final static Tunnel tunnel = new Tunnel();// only one Tunnel
	
	public static void main(String args[]) {
		
		
		LeftCarThread leftCar = new LeftCarThread();
		RightCarThread rightCar = new RightCarThread();
		
		
		leftCar.start();
		rightCar.start();
		
	}

	private static class LeftCarThread extends Thread {
		public void run() {
			LeftBoundCars lbc = new LeftBoundCars();
			
			while(true) {
				System.out.println("Left-bound Car " + lbc.getCarNumber() + " wants to enter the tunnel.");
				try {
					Random r = new Random();
					Util.sleep(r.nextInt(1500));
				} catch (Exception e) {
					System.out.println(e);
				}
				tunnel.tunnelTime(lbc.getCarNumber());
				System.out.println("Left-bound Car " + lbc.getCarNumber() + " has left the tunnel.");
				lbc.setCarNumber(lbc.getCarNumber() + 2);
				try {
					Random r = new Random();
					Util.sleep(r.nextInt(1500));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}
	}

	private static class RightCarThread extends Thread {
		public void run() {
			RightBoundCars rbc = new RightBoundCars();
			
			while(true) {
				System.out.println("Right-bound Car " + rbc.getCarNumber() + " wants to enter the tunnel.");
				try {
					Random r = new Random();
					Util.sleep(r.nextInt(1500));
				} catch (Exception e) {
					System.out.println(e);
				}
				tunnel.tunnelTime(rbc.getCarNumber());
				System.out.println("Right-bound Car " + rbc.getCarNumber() + " has left the tunnel.");
				rbc.setCarNumber(rbc.getCarNumber() + 2);
				try {
					Random r = new Random();
					Util.sleep(r.nextInt(1500));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
