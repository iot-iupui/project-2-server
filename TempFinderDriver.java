class TempFinderDriver {
    public static void main (String... args) throws InterruptedException {

        TempControlPWM tempFinder = new TempControlPWM();

        while(true) {
            try{
                System.out.println("Temperature : " + tempFinder.getTemp());
                Thread.sleep(1000);
            } catch (Exception ex) { 
                ex.printStackTrace();
            }
        }
    }
}