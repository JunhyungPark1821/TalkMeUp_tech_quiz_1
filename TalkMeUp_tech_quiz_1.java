import java.util.LinkedList; 
import java.util.Queue;
import java.util.Random;
  
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

        //Concurency

        th1.join();
        th2.join();
    }

    public static class ProducerConsumer {
        Queue<Integer> q1 = new LinkedList<Integer>(); //Queue is an inteface => Concrete implementation is necessary
        Queue<Integer> q2 = new LinkedList<Integer>();
        Queue<Integer> q3 = new LinkedList<Integer>();

        public void produce() throws InterruptedException {
            int value = 0;

            Random rand = new Random();
            
            while (true) {
                value++;
                if (q1.size() == q2.size() && q2.size() == q3.size()) {
                    int int_random = rand.nextInt(3);

                    System.out.println (int_random);

                    if (int_random == 0) {
                        q1.add(value);
                        System.out.println ("Product " + value + " is stored at Queue 1");
                    }
                    else {
                        if (int_random == 1) {
                            q2.add (value);
                            System.out.println ("Product " + value + " is stored at Queue 2");
                        }
                        else {
                            q3.add (value);
                            System.out.println ("Product " + value + " is stored at Queue 3");
                        }
                    }
                }

                else {
                    if (q1.size() < q2.size()) {
                        if (q1.size() < q3.size()){
                            q1.add(value);
                            System.out.println ("Product " + value + " is stored at Queue 1");
                        }
                        else {
                            if (q3.size() == q1.size()) {
                                int int_random = rand.nextInt(1);
                                System.out.println (int_random);
    
                                if (int_random == 0) {
                                    q1.add(value);
                                    System.out.println ("Product " + value + " is stored at Queue 1");
                                }
                                else {
                                    q3.add (value);
                                    System.out.println ("Product " + value + " is stored at Queue 3");
                                }                               
                            }
                            else {
                                q3.add (value);
                                System.out.println ("Product " + value + " is stored at Queue 3");
                            }
                        }
                    }
                    else {
                        if (q1.size() < q3.size()){
                            if (q1.size() == q2.size()) {
                                int int_random = rand.nextInt(2);
                                System.out.println (int_random);

                                if (int_random == 0) {
                                    q1.add(value);
                                    System.out.println ("Product " + value + " is stored at Queue 1");
                                }
                                else {
                                    q2.add (value);
                                    System.out.println ("Product " + value + " is stored at Queue 2");
                                }                      
                            }
                            else {
                                q2.add(value);
                                System.out.println ("Product " + value + " is stored at Queue 2");
                            }
                        }
                        else {
                            if (q2.size() == q3.size()) {
                                int int_random = rand.nextInt(2);
                                System.out.println (int_random);

                                if (int_random == 0) {
                                    q2.add(value);
                                    System.out.println ("Product " + value + " is stored at Queue 2");
                                }
                                else {
                                    q3.add (value);
                                    System.out.println ("Product " + value + " is stored at Queue 3");
                                }            
                            }
    
                            else {
                                if (q2.size() < q3.size()) {
                                    q2.add(value);
                                    System.out.println ("Product " + value + " is stored at Queue 2");
                                }
                                else {
                                    q3.add (value);
                                    System.out.println ("Product " + value + " is stored at Queue 3");
                                }
                            }
                        }
                    }
                }

                
                Thread.sleep (5000);
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
    
                Thread.sleep (100);
            }
        }
    }
}