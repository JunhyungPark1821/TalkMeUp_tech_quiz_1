import java.util.LinkedList; 
import java.util.Queue; 
  
public class TalkMeUp_tech_quiz_1 {
    public static void main (String[] args) throws InterruptedException {
        final ProducerConsumer test = new ProducerConsumer();

        Thread th1 = new Thread() {
            public void run() {
                try {
                    test.produce();
                }
                catch (InterruptedException e){
                    e.printStackTrace(); 
                }
            }
        };

        Thread th2 = new Thread() {
            public void run() {
                try {
                    test.consume();
                }
                catch (InterruptedException e){
                    e.printStackTrace(); 
                }
            }
        };

        th1.start();
        th2.start();

        th1.join();
        th2.join();
    }

    public static class ProducerConsumer {
        Queue<Integer> q1 = new LinkedList<Integer>(); //Queue is an inteface => Concrete implementation is necessary
        Queue<Integer> q2 = new LinkedList<Integer>();
        Queue<Integer> q3 = new LinkedList<Integer>();

        public void produce() throws InterruptedException {
            int value = 0;
            
            while (true) {
                //System.out.println ("Producer produced " + value);
                if (q1.size() < q2.size()) {
                    if (q1.size() < q3.size()){
                        q1.add(value);
                        int temp1 = q1.peek();
                        //System.out.println ("Product " + temp1 + "is stored at Queue 1");
                    }
                    else { 
                        q3.add (value);
                        int temp3 = q3.peek();
                        //System.out.println ("Product " + temp3 + "is stored at Queue 3");
                    }
                }
                else {
                    q2.add (value);
                    int temp2 = q2.peek();
                    //System.out.println ("Product " + temp2 + "is stored at Queue 2");
                }
                value++;

                Thread.sleep (100);
            }
        }

        public void consume() throws InterruptedException {
            while (true) {
                if (q1.size() != 0) {
                    int remVal1 = q1.remove();
                    System.out.println ("Queue 1 Consumer consumes " + remVal1);
                }
    
                if (q2.size() != 0) {
                    int remVal2 = q2.remove();
                    System.out.println ("Queue 2 Consumer consumes " + remVal2);
                }
                
                if (q3.size() != 0) {
                    int remVal3 = q3.remove();
                    System.out.println ("Queue 3 Consumer consumes " + remVal3);
                }
    
                Thread.sleep (1000);
            }
        }
    }
}