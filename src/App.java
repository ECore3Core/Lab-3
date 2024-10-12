import java.io.*;
import Vehicle.*;
import Exceptions.*;

public class App {
    public static void main(String[] args) throws IOException, NoSuchModelNameException, DuplicateModelNameException, ClassNotFoundException{
        //FileInputStream, FileOutputStream, FileReader и FileWriter
        
        
        //тест outputVehicle
        FileOutputStream out = new FileOutputStream("output1.bin");
        Motorcycle car1 = new Motorcycle("BMW", 30);
        Vehicles.outputVehicle(car1, out);

        //тест inputVehicle
        FileInputStream in = new FileInputStream("output1.bin");
        Motorcycle car12 = (Motorcycle) Vehicles.inputVehicle(in);
        //вывод
        car12.printInfo();

        //тест writeVehicle
        FileWriter printIn = new FileWriter("output2.txt");
        Motorcycle moto2 = new Motorcycle("Kawasaki", 59);
        Vehicles.writeVehicle(moto2, printIn);

        //тест readVehicle
        FileReader readIn = new FileReader("output2.txt");
        Motorcycle moto22 = (Motorcycle)Vehicles.readVehicle(readIn);
        //вывод
        moto22.printInfo();

        //тест Serializable
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("output3.bin"));
        Car car3 = new Car("LADA", 50);
        objOut.writeObject(car3);//записал

        ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("output3.bin"));
        Car car32 = (Car)objIn.readObject();//считал
        //вывод
        car32.printInfo();

        Vehicle moto42 = (Vehicle)Vehicles.readVehicle(new InputStreamReader(System.in));
        Vehicles.writeVehicle(moto42, new OutputStreamWriter(System.out));
    }
}
