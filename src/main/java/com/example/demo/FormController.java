package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	@Autowired
	CustomerRepo repo;

	@GetMapping("/")
	public String customer() {
		return "Customer";
	}

	@RequestMapping("/details")
	public String details(Customers customers) {
		repo.save(customers);
		return "Customer";
	}

	@RequestMapping("/getDetails")
	public String getDeatils() {
		return "ViewCustomer";
	}

	
	@PostMapping("/getDetails")
	public ModelAndView viewDetails(@RequestParam("cId")int cId) { 
		ModelAndView mv = new ModelAndView("Retrieve");
		Customers customers = repo.findById(cId).orElse(null);
		mv.addObject(customers);
		return mv; 
	}
	
	@RequestMapping("/customers")
	@ResponseBody
	public List<Customers> getAllDeatils() {
		return repo.findAll();
	}
	
	@RequestMapping("/customers/{cId}")
	@ResponseBody
	public Optional<Customers> getAllDeatils2(@PathVariable("cId") int cId) {
		return repo.findById(cId);
	}
	
	@PostMapping("/customers")
	@ResponseBody
	public Customers getAllDeatils3(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
	}
	
	@DeleteMapping("/customers/{cId}")
	public Customers getAllDeatils4(@PathVariable("cId") int cId) {
		@SuppressWarnings("deprecation")
		Customers customers = repo.getOne(cId);
		repo.delete(customers);
		return customers;
	}

	@PutMapping(path="/customers",consumes="application/json")
	public Customers getAllDeatils5(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
	}

	
	/*
	 * @PostMapping("/getDetails") public String
	 * viewDetails(@RequestParam("cId")String cId,
	 * 
	 * @RequestParam("cName")String cName,
	 * 
	 * @RequestParam("cEmail")String cEmail,ModelMap modelmap) { modelmap.put("cId",
	 * cId); modelmap.put("cName", cName); modelmap.put("cEmail", cEmail); return
	 * "viewcustomer"; }
	 */
}
