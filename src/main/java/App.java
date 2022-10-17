
import models.IEEEConverter;
import models.PrecisionEnum;

public class App {
    public static void main(String[] args) {
        IEEEConverter conver = new IEEEConverter(PrecisionEnum.SIMPLE);
        conver.convertToIEEE(-43.676);
        conver.printIEEEFormat();
    }
}
