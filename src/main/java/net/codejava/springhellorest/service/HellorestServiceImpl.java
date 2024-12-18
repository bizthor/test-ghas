package net.codejava.springhellorest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.codejava.springhellorest.Product;
import net.codejava.springhellorest.integration.response.HelloRestResponse;

@Service
public class HellorestServiceImpl implements HellorestService{

    // URL de conexión a la base de datos
    @Value("${datasource.url}")
    private String DATABASE_URL;

    @Value("${datasource.username}")
    private String DATABASE_USER;

    @Value("${datasource.username}")
    private String DATABASE_PASSWORD;

    @Override
    public HelloRestResponse addProductToDB(Product product) throws Exception {
        HelloRestResponse restResponse = new HelloRestResponse();
        //Insert in DB
        try{

            insertData(product.getId(), product.getName(), product.getPrice());

        }catch (Exception e){
            throw new Exception("Exception");
        }

        return restResponse;


        
    }


    // Método para insertar datos
    public void insertData(int id, String nombre, float precio) {
        String insertSQL = "INSERT INTO tabla_productos (id, nombre, precio) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(insertSQL)) {
            
            // Configurar los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setFloat(3, precio);

            // Ejecutar la consulta
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas insertadas: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
