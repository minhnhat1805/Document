package net.guides.springboot2.springboot2crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import net.guides.springboot2.springboot2crud.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  public  class ProductControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllProducts() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/products",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetProductById() {
		Product product =  restTemplate.getForObject(getRootUrl() + "/products/1", Product.class);
		System.out.println((product.getTitle()));
		assertNotNull(product);
	}

	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setTitle("admin");
		product.setDescription("wonderful");
		product.setPrice(100);

		ResponseEntity<Product> postResponse = restTemplate.postForEntity(getRootUrl() + "/products", product, Product.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateproduct() {
		int id = 1;
		Product product = restTemplate.getForObject(getRootUrl() + "/products/" + id, Product.class);
		product.setTitle("admin1");
		product.setPrice(100);

		restTemplate.put(getRootUrl() + "/products/" + id, product);

		Product updatedProduct = restTemplate.getForObject(getRootUrl() + "/products/" + id, Product.class);
		assertNotNull(updatedProduct);
	}

	@Test
	public void testDeleteProduct() {
		int id = 2;
		Product product = restTemplate.getForObject(getRootUrl() + "/products/" + id, Product.class);
		assertNotNull(product);

		restTemplate.delete(getRootUrl() + "/products/" + id);

		try {
			product = restTemplate.getForObject(getRootUrl() + "/products/" + id, Product.class);
		} catch (final HttpClientErrorException e) {

		}
	}
}
