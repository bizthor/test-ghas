package net.codejava.springhellorest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);



	@RequestMapping("/hello")
	public String hello() {
		return "Hello World RESTful with Spring Boot";
	}

	@RequestMapping("/hello2")
	public String hello2(@RequestParam(name = "name", defaultValue = "World") String name) {

		logger.debug("Hola", name);
		return "Hello " + name;
	}

	@GetMapping("/getproduct")
	public Product getProduct() {
		logger.debug("Hola getproduct");
		return new Product(1, "iPhone", 999.99f);
	}

	@PostMapping("/addproduct")
	public void addProduct(@RequestBody Product product) {
		logger.debug("Hola getproduct", product.getName());
		System.out.println(product);
	}
	
	@PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void updateProduct(@RequestBody Product product) {
		logger.debug("Hola getproduct", product.getId());
		System.out.println(product);
	}
	
	@GetMapping(value = "/getproduct2", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Product getProduct2() {
		return new Product(2, "Kindle Fire", 19.99f);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		Product product = null;
		
		if (id == 3) {
			product = new Product(3, "XBOX 360", 299.89f);
		}
		
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {		
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/productsv2/{name}")
	public ResponseEntity<Product> getProduct(@PathVariable String name) {
		Product product = null;
		logger.debug("Hola name", name);
		if (name.equals("pelota")) {
			product = new Product(3, name, 299.89f);
		}else{
			System.out.println(name);
		}
		
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {		
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
}
