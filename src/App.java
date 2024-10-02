import java.io.*;
import Vehicle.*;
import Exceptions.*;

public class App {
    public static void main(String[] args) throws IOException, NoSuchModelNameException, DuplicateModelNameException{
        // // Vehicle moto = Vehicles.readVehicle(new InputStreamReader(System.in));
        // // OutputStream out = new FileOutputStream("Lab 3/src/output.bin");
        // // Vehicles.outputVehicle(moto, out);
        // // out.close();
        // // InputStream in = new FileInputStream("Lab 3/src/output.bin");
        // // Vehicle car1 = Vehicles.inputVehicle(in);
        // // System.out.println(car1.getBrand());
        // // for(String s : car1.getModelsNames()){
        // //     System.out.println(s);
        // // }
        // Car car = new Car("BMW", 10);
        // Writer out = new PrintWriter("Lab 3/src/output.txt");
        // Vehicles.writeVehicle(car, out);
        // Reader in = new FileReader("Lab 3/src/output.txt");
        // Vehicle moto = Vehicles.readVehicle(in);
        // System.out.println();
        Car car = new Car("BMW", 10000);
        Vehicles.outputVehicle(car, new FileOutputStream("output.bin"));
        Vehicle car1 = Vehicles.inputVehicle(new FileInputStream("output.bin"));
        System.out.println(car1.getBrand());
        for(String s : car1.getModelsNames()){
            System.out.println(s);
        }
    }
}
