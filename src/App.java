
import models.Conversor;

public class App {
    public static void main(String[] args) throws Exception {
        Conversor conver=new Conversor();
        conver.convertSimpleFormat(321.21,1);
        conver.printIEEEFormat();
    }
}
