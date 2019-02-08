package controller;

import javax.servlet.MultipartConfigElement;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.MongoService;

@Controller
@EnableAutoConfiguration
@RestController
public class MongoController {
   
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("124MB");
        factory.setMaxRequestSize("124MB");
        return factory.createMultipartConfig();
    }
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value ="vendorAvailability", method = RequestMethod.POST)
	public String getVendorAvailability(@RequestBody String data) throws JSONException {
		
		MongoService obj = new MongoService();
		String response = obj.getVendorAvailability(data);
		
		return response;
	}
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value ="poStatus", method = RequestMethod.POST)
	public String getPoStatus(@RequestBody String data) throws JSONException {
		
		MongoService obj = new MongoService();
		String response = obj.getPoStatus(data);
		return response;
	}
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value ="vendorDetails", method = RequestMethod.POST)
	public String getVendorDetails(@RequestBody String data) throws JSONException {
		
		MongoService obj = new MongoService();
		String response = obj.getVendorDetails(data);
		return response;
	}
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value ="prApprover", method = RequestMethod.POST)
	public String getPRApprover(@RequestBody String data) throws JSONException {
		
		MongoService obj = new MongoService();
		String response = obj.getPRApprover(data);
		return response;
	}
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value ="prDetails", method = RequestMethod.POST)
	public String getPRDetails(@RequestBody String data) throws JSONException {
		
		MongoService obj = new MongoService();
		String response = obj.getPRDetails(data);
		return response;
	}
	public static void main(String args[]) throws JSONException {
		
		//JSONArray countries =  new JSONArray();
		//countries.add("UK");
		//countries.add("US");
		
		 JSONObject data= new JSONObject();
		   data.put("system","unix");
		   data.put("supplier","tcs");
		   data.put("countries", "boston");
		   //data.put("pr_number","pr1234");
		   
		   MongoController obj = new MongoController();
		   System.out.println(data.toString());
		   System.out.println( obj.getVendorAvailability(data.toString()));
		   //System.out.println(obj.getPRApprover(data.toString()));;
	}
	
}
