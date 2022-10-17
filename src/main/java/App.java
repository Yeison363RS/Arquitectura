
import models.Conversor;
import models.PrecisionEnum;

public class App {
    public static void main(String[] args) {
        Conversor conver=new Conversor(PrecisionEnum.SIMPLE);
        conver.convertToIEEE(-43.676);
        conver.printIEEEFormat();
    }
}
