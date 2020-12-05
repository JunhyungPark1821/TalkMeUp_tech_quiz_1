import java.util.LinkedList; 
import java.util.Queue; 
  
public class TalkMeUp_tech_quiz_1 {
    public static void main (String[] args) throws InterruptedException {
        final ProducerConsumer test = new ProducerConsumer();

        test.produce();

    }

    public static class ProducerConsumer {
        Queue<Integer> q1 = new LinkedList<Integer>(); //Queue is an inteface => Concrete implementation is necessary
        Queue<Integer> q2 = new LinkedList<Integer>();
        Queue<Integer> q3 = new LinkedList<Integer>();

        public void produce() throws InterruptedException {
            int value = 0;
            
            while (true) {
                System.out.println ("Producer produced " + value);
                Thread.sleep (1000);
                if (q1.size() < q2.size()) {
                    if (q1.size() < q3.size()){
                        q1.add(value);
                        int temp1 = q1.peek();
                        System.out.println ("Product " + temp1 + "is stored at Queue 1");
                    }
                    else { 
                        q3.add (value);
                        int temp3 = q3.peek();
                        System.out.println ("Product " + temp3 + "is stored at Queue 3");
                    }
                }
                else {
                    q2.add (value);
                    int temp2 = q2.peek();
                    System.out.println ("Product " + temp2 + "is stored at Queue 2");
                }
                value++;

                notify();

                Thread.sleep (1000);
            }
        }
    }
}