package basics;

public class ThreadNums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable t1=()->{
			for(int i=6;i<=10;i++) {
				System.out.println("Thread "+Thread.currentThread().getName()+" prints :"+i);
			}
		};
		new Thread(t1).start();;
		for(int i=0;i<=5;i++) {
			System.out.println("Thread "+Thread.currentThread().getName()+"prints :"+i);
		}

	}

}
