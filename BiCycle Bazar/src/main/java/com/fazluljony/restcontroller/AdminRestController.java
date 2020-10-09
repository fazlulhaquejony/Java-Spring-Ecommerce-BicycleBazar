package com.fazluljony.restcontroller;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fazluljony.model.Product;
import com.fazluljony.service.ProductService;

import java.io.File;
import java.nio.file.*;

@RestController
public class AdminRestController {
	
	@Autowired
	private ProductService productService;
	

	@RequestMapping("/rest/admin")
    public String adminShow()
    {
   	 return "admin";
    }
	
	@RequestMapping("/rest/admin/productInventory")
	public List<Product> productInventory(Model model)
	{
		List<Product> product = productService.getProductList();
		
        
		
		return product;
	}
	
	@RequestMapping("/rest/admin/productInventory/productAdd")
	public String productAdd(Model model)
	{
		Product product = new Product();
		model.addAttribute("product", product);
		return "productAdd";
	}
	
	@PostMapping("rest/admin/productInventory/productAdd")
	public String addProductpost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request ) {
		
		if(result.hasErrors())
		{
			return "productAdd";
		}
		
		productService.addProduct(product);
		 
		 MultipartFile productImage = product.getProductImage();
		 
		 //String rootDirectory= request.getSession().getServletContext().getRealPath("/");
		Path path = Paths.get("F:\\JavaEE Project\\eMusicStore\\src\\main\\webapp\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");
		 
		 if(productImage!=null && !productImage.isEmpty())
		 {
			 try {
				 productImage.transferTo(new File(path.toString()));
			 }
			 catch(Exception e)
			 {
				 throw new RuntimeException("Product Image saving failed",e );
			 }
		 }
		 
		return "redirect:/rest/admin/productInventory";
	}
	
	@RequestMapping("/rest/admin/productInventory/{id}")
	public String deleteProduct(@PathVariable("id") String id , Model model, HttpServletRequest request ) {
		
		Path path = Paths.get("F:\\JavaEE Project\\eMusicStore\\src\\main\\webapp\\WEB-INF\\resources\\images\\"+id+".png");
		
		if(Files.exists(path))
		{
			try {
				Files.delete(path);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
		int Id = Integer.parseInt(id);
		productService.delete(Id);
		return "redirect:/rest/admin/productInventory";
	}
	
	@RequestMapping("/rest/admin/productInventory/productEdit/{id}")
	public Product editProduct(@PathVariable("id") String id) {
		
		int Id = Integer.parseInt(id);
		Product product = productService.getProductById(Id);
		return product;
		
	}
	
	
	
	@PostMapping("rest/admin/productInventory/productEdit")
	public Product editProductpost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request ) {
		
		if(result.hasErrors())
		{
			return product;
		}
		
		productService.addProduct(product);
		 
		 MultipartFile productImage = product.getProductImage();
		 
		 //String rootDirectory= request.getSession().getServletContext().getRealPath("/");
		Path path = Paths.get("F:\\JavaEE Project\\eMusicStore\\src\\main\\webapp\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");
		 
		 if(productImage!=null && !productImage.isEmpty())
		 {
			 try {
				 productImage.transferTo(new File(path.toString()));
			 }
			 catch(Exception e)
			 {
				 throw new RuntimeException("Product Image sediting failed",e );
			 }
		 }
		 
		return product;
	}
	


}
