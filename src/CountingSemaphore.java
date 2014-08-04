import java.util.concurrent.Semaphore;

public class CountingSemaphore {

    Semaphore binary = new Semaphore(2);
  
    public static void main(String args[]) {
        final CountingSemaphore cs = new CountingSemaphore();
        new Thread(){
            @Override
            public void run(){
              cs.mutualExclusion();
            }
        }.start();
      
        new Thread(){
            @Override
            public void run(){
              cs.mutualExclusion(); 
            }
        }.start();
      
    }
  
    private void mutualExclusion() {
        try {
            binary.acquire();

            //mutual exclusive region
            System.out.println(Thread.currentThread().getName() + " inside mutual exclusive region");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            binary.release();
            System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");
        }
    } 
  
}