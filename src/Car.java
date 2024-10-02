import java.util.Arrays;
import Exceptions.*;
import Vehicle.Vehicle;
import java.io.*;

class Car implements Vehicle{
    private String brand;
    public String getBrand(){return brand;}
    public void setBrand(String newBrand){brand = newBrand;}
    class Model implements Serializable{
        private String name;
        private double price;
        public Model(String model, double price){
            this.name = model;
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
    private Model[] models;
    public Car(){}
    public Car(String brandName){
        brand = brandName;
        models = new Model[0];
    }
    public Car(String brand, int modelsLength){
        this.brand = brand;
        models = new Model[modelsLength];
        for(int i = 0; i < modelsLength; i++){
            models[i] = new Model("Car " + i, 100 + i);
        }
    }
    private int findIndexOfModel(String modelName){
        int index = -1;
        for(int i = 0; i < models.length; i++){
            if(models[i].getName().equals(modelName)){
                index = i;
            }
        }
        return index;
    }
    public void setModelName(String originalModel, String newName) throws DuplicateModelNameException, NoSuchModelNameException{
        int indexOfOriginalModel = findIndexOfModel(originalModel);
        if(indexOfOriginalModel == -1){
            throw new NoSuchModelNameException("Машины под названием " + originalModel + " не существует.\n");
        }
        else if(findIndexOfModel(newName) != -1){
            throw new DuplicateModelNameException("Машина под названием " + newName + " уже существует.\n");
        }
        else{
            models[indexOfOriginalModel].setName(newName);
        }
    }
    public String[] getModelsNames(){
        String[] modelsNames = new String[models.length];
        for(int i = 0; i < modelsNames.length; i++){
            modelsNames[i] = models[i].getName();
        }
        return modelsNames;
    }
    public double getPriceByName(String modelName) throws NoSuchModelNameException{
        double price = 0;
        int index = findIndexOfModel(modelName);
        if(index == -1){
            throw new NoSuchModelNameException("Машины c моделью " + modelName + "не существует.\n");
        }
        price = models[index].getPrice();
        return price;
    }
    public void setPrice(String modelName, double newPrice) throws NoSuchModelNameException{
        if(newPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена не может быть меньше либо равна 0.\n");
        }
        int index = findIndexOfModel(modelName);
        if(index == -1){
            throw new NoSuchModelNameException("Машины c моделью " + modelName + "не существует.\n");
        }
        models[index].setPrice(newPrice);
    }
    public double[] getModelsPrices(){
        double[] modelsPrices = new double[models.length];
        for(int i = 0; i < modelsPrices.length; i++){
            modelsPrices[i] = models[i].getPrice();
        }
        return modelsPrices;
    }
    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException{
        if(modelPrice <= 0){
            throw new ModelPriceOutOfBoundsException("Цена модели не может быть меньше 0.\n");
        }
        if(findIndexOfModel(modelName) != -1){
            throw new DuplicateModelNameException("Машина под названием " + modelName + " уже существует.\n");
        }
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, modelPrice);
        
    }
    public void deleteModel(String modelName) throws NoSuchModelNameException{
        int index = findIndexOfModel(modelName);
        if(index == -1){
            throw new NoSuchModelNameException("Машины с моделью " + modelName + "не существует.\n");
        }
        else{
            //Model[] newModels = new Model[models.length - 1];
            //System.arraycopy(models, 0, newModels, 0, index);
            System.arraycopy(models, index + 1, models, index, models.length - index - 1);
            models = Arrays.copyOf(models, models.length - 1);
        }
    }
    public int getModelsSize(){
        return models.length;
    }
}