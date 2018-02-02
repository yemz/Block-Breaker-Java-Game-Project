
public class Animate implements Runnable {
	
	BlockBreakerPanel bp; //to pass parameter to Animate constructor
	//constructor that takes BlockBrakerPanel
	Animate (BlockBreakerPanel b){
		bp = b;
	}
	public void run(){
		//while it is running update blockbreakerpanel
		while(true){
			bp.update();
			try {
				Thread.sleep(10);//animation 10 milli seconds
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
