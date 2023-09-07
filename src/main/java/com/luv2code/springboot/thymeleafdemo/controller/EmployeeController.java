package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

//	private List<Employee> theEmployees;
////
////	@PostConstruct
////	private void loadData() {
////
////		// create employees
////		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
////		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
////		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
////
////		// create the list
////		theEmployees = new ArrayList<>();
////
////		// add to the list
////		theEmployees.add(emp1);
////		theEmployees.add(emp2);
////		theEmployees.add(emp3);
////	}

	// add mapping for "/list"

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		this.employeeService = theEmployeeService;
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee theEmployee = new Employee();

		// add to the spring model
		theModel.addAttribute("employee", theEmployee);

		return "employees/add-employee";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee")
							   Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		//redirect
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String updateEmployee(@RequestParam("employeeId") int theId,
								 Model theModel) {

		// save the employee
		Employee theEmployee = employeeService.findById(theId);
		theModel.addAttribute("employee", theEmployee);

		//redirect
		return "employees/add-employee";
	}

	@GetMapping("/showFormForDelete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {

		// save the employee
		employeeService.deleteById(theId);

		//redirect
		return "redirect:/employees/list";
	}
}









