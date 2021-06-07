package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.models.Product;
import com.desafiospring.DesafioSpring.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final String pathUser = "./src/main/java/com/desafiospring/DesafioSpring/json/product.json";

    private List<Product> loadJSON() {
        File file = new File(pathUser);
        List<Product> lista = null;
        if(file.length() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<>(){};

            try{
                lista = objectMapper.readValue(file, typeReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    private void writeJSON(List<Product> lista){
        try {
            File file = new File(pathUser);
            TypeReference<List<Product>> typeReference = new TypeReference<>(){};
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, lista);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int id) {
        List<Product> productList = loadJSON();
        if(productList == null)
            return null;
        Product product = productList.stream().filter(u -> u.getProduct_id() == id).findFirst().orElse(null);
        return product;
    }

    @Override
    public void addProduct(Product product) {
        List<Product> listaProduct = loadJSON();
        if(listaProduct == null)
            listaProduct = new ArrayList<>();
        listaProduct.add(product);
        writeJSON(listaProduct);
    }
}
