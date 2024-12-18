package net.codejava.springhellorest.service;

import net.codejava.springhellorest.Product;
import net.codejava.springhellorest.integration.response.HelloRestResponse;

public interface HellorestService {
    HelloRestResponse addProductToDB(Product product) throws Exception;

}
