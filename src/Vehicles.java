import java.io.*;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Vehicle.Vehicle;

public class Vehicles {
    public static double getArithmeticalMeanOfModelPrices(Vehicle vehicle){
        double sum = 0;
        for(double i : vehicle.getModelsPrices()){
            sum += i;
        }
        return sum/vehicle.getModelsSize();
    }
    public static void printAllModels(Vehicle vehicle){
        for(String s : vehicle.getModelsNames()){
            System.out.println(s);
        }
    }
    public static void printAllPrices(Vehicle vehicle){
        for(double i : vehicle.getModelsPrices()){
            System.out.print(i + " ");
        }
    }
    public static void outputVehicle(Vehicle v, OutputStream out) throws IOException{
        DataOutputStream outputStream = new DataOutputStream(out);

        byte[] bytes = v.getBrand().getBytes();
        int n = bytes.length;
        outputStream.writeInt(n);
        for(int i = 0; i < n; i++){
            outputStream.writeByte(bytes[i]);
        }

        n = v.getModelsSize();
        outputStream.writeInt(n);
        String[] allModelsNames = v.getModelsNames();
        double[] allPrices = v.getModelsPrices();

        for(int i = 0; i < n; i++){
            int nameLength = allModelsNames[i].length();
            bytes = allModelsNames[i].getBytes();
            outputStream.writeInt(nameLength);
            for(int j = 0; j < nameLength; j++){
                outputStream.writeByte(bytes[j]);
            }
            outputStream.writeDouble(allPrices[i]);
        }
    }
    public static Vehicle inputVehicle(InputStream in) throws IOException, NoSuchModelNameException, DuplicateModelNameException{
        DataInputStream inputStream = new DataInputStream(in);

        int size = inputStream.readInt();
        
        byte[] bytes = new byte[size];
        for(int i = 0; i < size; i++){
            bytes[i] = inputStream.readByte();
        }
        String brandName = new String(bytes);

        size = inputStream.readInt();
        Vehicle vehicle = new Car(brandName);

        for(int i = 0; i < size; i++){
            int nameLength = inputStream.readInt();
            bytes = new byte[nameLength];
            for(int j = 0; j < nameLength; j++){
                bytes[j] = inputStream.readByte();
            }
            String name = new String(bytes);

            double price = inputStream.readDouble();

            vehicle.addModel(name, price);
        }
        return vehicle;
    }
    public static void writeVehicle(Vehicle vehicle, Writer out){
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(vehicle.getBrand());
        for(int i = 0; i < vehicle.getModelsSize(); i++){
            printWriter.print(vehicle.getModelsNames()[i] + " ");
            printWriter.println(vehicle.getModelsPrices()[i]);
        }
        printWriter.flush();
    }
    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException{
        BufferedReader bfReader = new BufferedReader(in);
        String brandName = bfReader.readLine();
        Vehicle vehicle = new Car(brandName);
        String nextModel = bfReader.readLine();
        while(nextModel != null){
            String[] info = nextModel.split(" ");
            String modelName = "";
            for(int i = 0; i < info.length - 1; i++){
                modelName += " " + info[i];
            }
            vehicle.addModel(modelName, Double.parseDouble(info[info.length - 1]));
            nextModel = bfReader.readLine();
        }
        bfReader.close();
        return vehicle;
    }
}   
