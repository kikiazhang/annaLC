package amazonOA;

public class Test {

	public static void main(String[] args) {
//		Thread t = new MyThread() {
//			public void run() {
//				System.out.print("foo");
//			}
//		};
//		t.start();
		for(int i=0;i<100000000;i++) {
			
		}

	}

}

class MyThread extends Thread {
	MyThread() {
		System.out.print("My Thread");
	}

	public void run() {
		System.out.print("bar");
	}

	public void run(String s) {
		System.out.print("baz");
	}

}
