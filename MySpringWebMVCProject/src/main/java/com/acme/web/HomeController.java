package com.acme.web;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.web.domain.Customer;
import com.acme.web.jdbc.CustomerJDBCTemplate;
import com.acme.web.service.CustomerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private CustomerJDBCTemplate customerJDBCTemplate = null;
	
	@Autowired
	@Qualifier("customerFormValidator")
	private Validator customerFormValidator;
	
	@Autowired
	private CustomerService customerService;


	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
	    
	    return "home";
	}
	
	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public String customerList(Model model) {
		
		List<Customer> customers = customerJDBCTemplate.listCustomers();
		
	    model.addAttribute("customers", customers);
	 
	    return "customerList";
	}
	
	
	// show add customer form
		@RequestMapping(value = "/customer/add", method = RequestMethod.GET)
		public String showAddCustomerForm(Model model) {

			logger.debug("showAddCustomerForm()");

			Customer customer  = new Customer();

			// set default value
			customer.setFirstName(null);
			customer.setLastName(null);
			customer.setEmail(null);
			customer.setNewCustomer(true);
			model.addAttribute("customerForm", customer);
			model.addAttribute("customerAction","/customer/add");
			populateDefaultModel(model);

			return "customerAdd";

		}

		// does nothing at the moment, PG
		// probably examine the context and delegate further for different forms
		private void populateDefaultModel(Model model) {

		;

		}	
		
		
		
		@RequestMapping(value = "/customer/add", method = RequestMethod.POST)
		public String saveOrUpdateUser(@ModelAttribute("customerForm") Customer customer,
				BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
			
			
			logger.debug("saveOrUpdateCustomer() : {}", customer);

			if (result.hasErrors()) {
				populateDefaultModel(model);
				return "customerAdd";
			} else {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(customer.isNewCustomer()){
				  redirectAttributes.addFlashAttribute("msg", "Customer added successfully!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Customer updated successfully!");
				}
				
				customerService.saveOrUpdate(customer);
			}
				
			
			return "customerList";
		}	
	
	@PostConstruct
	public void init() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		customerJDBCTemplate = (CustomerJDBCTemplate) context.getBean("customerJDBCTemplate");
		
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(customerFormValidator);
	}
	
}
