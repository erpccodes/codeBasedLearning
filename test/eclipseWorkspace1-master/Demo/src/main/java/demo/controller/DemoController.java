package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping("/home")
	public String home() {
		System.out.println("This is home url");
		return "index";
	}
	
	@RequestMapping("/demo")
	public String demo() {
		System.out.println("This is home url");
		return "demo";
	}
	
}
