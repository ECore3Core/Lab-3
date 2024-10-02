import java.time.LocalDate;
import Vehicle.Vehicle;
import java.io.*;
import Exceptions.*;

class Motorcycle implements Vehicle{
    private class Model implements Serializable{
        String name;
        double price;
        Model next = null;
        Model prev = null;
        public Model(){}
        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
        public String getName(){
            return name;
        }
        public void setName(String newName){
            name = newName;
        }
        public double getPrice(){
            return price;
        }
        public void setPrice(double newPrice){
            price = newPrice;
        }
    }
    private String brand;
    public String getBrand(){return brand;}
    public void setBrand(String newBrand){brand = newBrand;}
    private int size;
    private Model head = new Model();
    {
        head.next = head;
        head.prev = head;
        lastModified = LocalDate.now();
    }
    private LocalDate lastModified;
    private void updateLastModified(){
        lastModified = LocalDate.now();
    }
    public Motorcycle(){}
    public Motorcycle(String brandName){
        brand = brandName;
    }
    public Motorcycle(String brand, int modelLength){
        this.brand = brand;
        size = modelLength;
        //Model p = head;
        for(int i = 0; i < modelLength; i++){
            Model model = new Model("Moto " + i, 100 + i);
            model.next = head;
            model.prev = head.prev;
            head.prev.next = model;
            head.prev = model;
          //  p = p.next;
        }
    }
    private Model findModelByName(String name){
        Model result = null;
        Model p = head.next;
        while(p != head){
            if(p.getName().equals(name)){
                result = p;
                p = head;
            }
             else p = p.next;
        }
        return result;
    }
    public void setModelName(String originalName, String newName) throws NoSuchModelNameException, DuplicateModelNameException{
        Model model  = findModelByName(originalName);
        if(model == null){
            throw new NoSuchModelNameException("Машины под названием " + originalName + " не существует.\n");
        }
        else if(findModelByName(newName) != null){
            throw new DuplicateModelNameException("Машина под названием " + newName + " уже существует.\n");
        }
        else{
            model.setName(newName);
            updateLastModified();
        }
    }
    public String[] getModelsNames(){
        Model p = head.next;
        String[] result = new String[size];
        int i = 0;
        while(p != head){
            result[i] = p.getName();
            i++;
            p = p.next;
        }
        return result;
    }
    public double getPriceByName(String name) throws NoSuchModelNameException{
        Model model = findModelByName(name);
        double result = 0;
        if(model == null){
            throw new NoSuchModelNameException("Машина под названием " + name + " уже существует.\n");
        }
        result = model.getPrice();
        return result;
    }
    public void setPrice(String modelName, double newPrice) throws NoSuchModelNameException{
        Model currentModel = findModelByName(modelName);
        if(currentModel == null){
            throw new NoSuchModelNameException("Машины под названием " + modelName + " не существует.\n");
        }
        else if(newPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена не может быть меньше либо равна 0.\n");
        }
        else{
            currentModel.setPrice(newPrice);
        }
    }
    public double[] getModelsPrices(){
        Model p = head.next;
        double[] result = new double[size];
        int i = 0;
        while(p != head){
            result[i] = p.getPrice();
            i++;
            p = p.next;
        }
        return result;
    }
    public void addModel(String modelName, double price) throws DuplicateModelNameException{
        if(price <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может быть равна или меньше нуля.\n");
        }
        if(findModelByName(modelName) != null){
            throw new DuplicateModelNameException("Машина под названием " + modelName + " уже существует.\n");
        }
        Model model = new Model(modelName, price);
        model.next = head;
        model.prev = head.prev;
        head.prev.next = model;
        head.prev = model;
        size++;
        updateLastModified();
    }
    public void deleteModel(String modelName) throws NoSuchModelNameException{
        Model model = findModelByName(modelName);
        if(model == null){
            throw new NoSuchModelNameException("Машина под названием " + modelName + " не существует.\n");
        }
        model.prev.next = model.next;
        model.next.prev = model.prev;
        updateLastModified();
        size--;
    }
    public int getModelsSize(){
        return size;
    }
}