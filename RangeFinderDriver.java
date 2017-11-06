class RangeFinderDriver {
    public static void main (String... args) throws InterruptedException {

        HC_SR04 rangeFinder = new HC_SR04();

        while(true) {
            try{
                System.out.println("Distance : " + rangeFinder.getDistance());
                Thread.sleep(1000);                
            } catch (Exception ex) { 
                ex.printStackTrace();
            }

        }
    }
}