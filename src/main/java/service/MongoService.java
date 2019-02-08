package service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import datalayer.MongoDBConnection;


public class MongoService {

	public String getVendorAvailability(String data) throws JSONException {
		
		
		MongoDBConnection obj = new MongoDBConnection();
		String dbResult = obj.getVendorAvailability(data);
		
		if (dbResult != null && !dbResult.isEmpty()) {
			JSONObject countries =  new JSONObject(dbResult);
			JSONArray result = null;
			try {
				result =  new JSONArray(countries.getJSONArray("countries").toString());
			} catch (Exception e) {
				result = new JSONArray().put(countries.getString("countries"));
			}
			JSONObject response =  new JSONObject();
			JSONObject input =  new JSONObject(data);
			if (!input.isNull("countries"))
			{
			    Object item = input.get("countries"); 
			    if (item instanceof JSONArray)
			    {
			    	JSONArray inputArray = (JSONArray) item;
			    	
			    	for (int i = 0; i < inputArray.length(); i++) {
						for (int j = 0; j < result.length(); j++) {
							if (inputArray.getString(i).toLowerCase().equals(result.getString(j).toLowerCase())) {
								response.put(result.getString(j), "true");
							} 
						}
					}
			    }
			    else
			    {
			    	for (int i = 0; i < result.length(); i++) {
						if (input.getString("countries").toLowerCase().equals(result.getString(i).toLowerCase())) {
							response.put(input.getString("countries"), "true");
						}
					}
			    }
			}
			response.put("countries", result);
			return response.toString();
		}
		else {
			JSONObject response = new JSONObject().put("exception", "Data is not"
					+ " available for specified parameteres");
			return response.toString();
		}	
		
	}

	public String getPoStatus(String data) throws JSONException {
		MongoDBConnection dbObject = new MongoDBConnection();
		String response = dbObject.getPoStatus(data);
		
		if (response != null && !response.isEmpty()) {
			return response;
		}else{
			JSONObject out = new JSONObject();
			out.put("exception", "PO number does not exist");
			return out.toString();
		}
		
	}
	
	public String getPRApprover(String data) throws JSONException {
		MongoDBConnection dbObject = new MongoDBConnection();
		String response = dbObject.getPRApprover(data);
		
		if (response != null && !response.isEmpty()) {
			return response;
		}else{
			JSONObject out = new JSONObject();
			out.put("exception", "PR number does not exist");
			return out.toString();
		}
		
	}
	
	public String getVendorDetails(String data) throws JSONException {
		
		JSONObject inputData = new JSONObject(data);
		
		if (inputData.getString("vendorID") != null && !inputData.getString("vendorID").isEmpty() ) {
			MongoDBConnection dbObject = new MongoDBConnection();
			String response = dbObject.getVendorDetails(inputData.getString("vendorID"),null);
			if(response ==  null)
				return "VenodID is not available";
			else
				return response;
			
		}else if(inputData.getString("vendorName") != null && !inputData.getString("vendorName").isEmpty() 
				&& inputData.getString("country") != null && !inputData.getString("country").isEmpty()){
			MongoDBConnection dbObject = new MongoDBConnection();
			String result = dbObject.getVendorDetails(inputData.getString("vendorName"),
														inputData.getString("country"));
			if(result != null){
			JSONArray out =  new JSONArray(result);
			JSONArray response = new JSONArray();
			for(int i=0;i<out.length();i++){
				response.put(out.getJSONObject(i).getJSONObject("countries"));
			}
			return new JSONObject().put("vendorIDs", response).toString();
			}
			else
				return "Vendor name/country is not available";
		}else{
			JSONObject out = new JSONObject();
			out.put("exception", "Vendor details for given parameteres does not exist");
			return out.toString();
		}
		
	}
	
	public String getPRDetails(String data) throws JSONException {
		MongoDBConnection dbObject = new MongoDBConnection();
		String response = dbObject.getPRDetails(data);
		
		if (response != null && !response.isEmpty()) {
			return response;
		}else{
			JSONObject out = new JSONObject();
			out.put("exception", "PR number does not exist");
			return out.toString();
		}
	}

	public static void main(String args[]) throws JSONException {
		
		JSONArray countries =  new JSONArray();
		countries.put("US");
		countries.put("IND");
		countries.put("AUS");
		
		JSONObject data= new JSONObject();
		   data.put("system","sap");
		   data.put("supplier","poppulo");
		   data.put("countries","nl");
		   //data.put("countries",countries);
		   
			//data.put("vendorID", "");
			//data.put("country", "US");
			//data.put("vendorName", "sap");
			
		  // data.put("pr_no", "PR1234");
		  
		   
		   MongoService obj = new MongoService();
		   System.out.println( obj.getVendorAvailability(data.toString()));
		   //System.out.println(obj.getPoStatus(data.toString()));
		   //System.out.println(obj.getPRApprover(data.toString()));;
		   //System.out.println(obj.getVendorDetails(data.toString()));
	}

	

	
}
