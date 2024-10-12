package Vehicle;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import java.io.Serializable;

public interface Vehicle extends Serializable{
    String getBrand();
    void setBrand(String newBrand);
    void setModelName(String originalName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;
    String[] getModelsNames();
    double getPriceByName(String modelName) throws NoSuchModelNameException;
    void setPrice(String modelName, double newPrice) throws NoSuchModelNameException;
    double[] getModelsPrices();
    void addModel(String modelName, double modelPrice) throws DuplicateModelNameException;
    void deleteModel(String modelName) throws NoSuchModelNameException;
    int getModelsSize();
    void printInfo();
}
