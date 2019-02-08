package datalayer;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

import net.minidev.json.JSONArray;

public class MongoDBConnection {

	public String getVendorAvailability(String data) throws JSONException {  
	      
	      	// Creating a Mongo client 
			@SuppressWarnings("deprecation")
			Mongo mongo = new Mongo("localhost", 27017);
	      
			// Accessing the database 
			@SuppressWarnings("deprecation")
			DB db = mongo.getDB("chatbot");
			
			// Retrieving a collection
			DBCollection collection = db.getCollection("vendoravailability");
			
			JSONObject queryData =  new JSONObject(data);
			
			BasicDBObject query =  new BasicDBObject();
			ArrayList<BasicDBObject> condition = new ArrayList<BasicDBObject>();
			condition.add(new BasicDBObject("system", queryData.getString("system").toLowerCase()));
			condition.add(new BasicDBObject("supplier", queryData.getString("supplier").toLowerCase()));
			query.put("$and", condition);

			BasicDBObject filters = new BasicDBObject();
			filters.put("_id", 0);
			filters.put("system", 0);
			filters.put("supplier", 0);
			
			
			DBCursor cursor = collection.find(query,filters);
	      
	      	JSONArray result =  new JSONArray();
	      
	      	while (cursor.hasNext()) {
			 	result.add(cursor.next());
			 }
	      	if (result.isEmpty()) {
	      		return null;
			}else {
		      	return result.toString().substring(1, result.toString().length()-1);
			}
	      
	   } 
	
	public String getPoStatus(String data) throws JSONException {

		// Creating a Mongo client 
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo("localhost", 27017);
      
		// Accessing the database 
		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("chatbot");
		
		// Retrieving a collection
		DBCollection collection = db.getCollection("postatus");
					
		JSONObject queryData =  new JSONObject(data);
		
		BasicDBObject query =  new BasicDBObject("po_number",queryData.getString("po_number").toLowerCase());
		
		BasicDBObject filters = new BasicDBObject();
		filters.put("_id", 0);
		filters.put("po_number", 0);
		
		DBCursor cursor = collection.find(query,filters);
	      
      	JSONArray result =  new JSONArray();
      
      	while (cursor.hasNext()) {
		 	result.add(cursor.next());
		 }
      	if (result.isEmpty()) {
      		return null;
		}else {
	      	return result.toString().substring(1, result.toString().length()-1);
		}
		
	}
	
	public String getPRApprover(String data) throws JSONException {

		// Creating a Mongo client 
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo("localhost", 27017);
      
		// Accessing the database 
		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("chatbot");
		
		// Retrieving a collection
		DBCollection collection = db.getCollection("PRApprover");
					
		JSONObject queryData =  new JSONObject(data);
		
		BasicDBObject query =  new BasicDBObject("pr_number",queryData.getString("pr_number").toUpperCase());
		
		BasicDBObject filters = new BasicDBObject();
		filters.put("pr_number", 0);
		filters.put("_id", 0);
		
		DBCursor cursor = collection.find(query,filters);  
      	
		JSONArray result =  new JSONArray();
      
      	while (cursor.hasNext()) {
		 	result.add(cursor.next());
		 }
      	if (result.isEmpty()) {
      		return null;
		}else {
	      	return result.toString().substring(1, result.toString().length()-1);
		}
		
	}
	
	public String getPRDetails(String data) throws JSONException {
		// Creating a Mongo client 
				@SuppressWarnings("deprecation")
				Mongo mongo = new Mongo("localhost", 27017);
		      
				// Accessing the database 
				@SuppressWarnings("deprecation")
				DB db = mongo.getDB("chatbot");
				
				// Retrieving a collection
				DBCollection collection = db.getCollection("prdetails");
							
				JSONObject queryData =  new JSONObject(data);
				
				BasicDBObject query =  new BasicDBObject("pr_number",queryData.getString("pr_rumber").toUpperCase());
				
				BasicDBObject filters = new BasicDBObject();
				filters.put("pr_number", 0);
				filters.put("_id", 0);
				
				DBCursor cursor = collection.find(query,filters);  
		      	
				JSONArray result =  new JSONArray();
		      
		      	while (cursor.hasNext()) {
				 	result.add(cursor.next());
				 }
		      	if (result.isEmpty()) {
		      		return null;
				}else {
			      	return result.toString().substring(1, result.toString().length()-1);
				}
		
	}
	
	public String getVendorDetails(String inputX, String inputY) {
		
		// Creating a Mongo client 
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo("localhost", 27017);
      
		// Accessing the database 
		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("chatbot");
		
		// Retrieving a collection
		DBCollection collection = db.getCollection("vendordetails");
					
		BasicDBObject filters = new BasicDBObject();
		filters.put("_id", 0);
				
				
		if (inputY == null) {
			BasicDBObject query =  new BasicDBObject("countries.vendorID",inputX);
			
			DBCursor cursor = collection.find(query,filters);  
	      	
			JSONArray result =  new JSONArray();
	      
	      	while (cursor.hasNext()) {
			 	result.add(cursor.next());
			 }
	      	if (result.isEmpty()) {
	      		return null;
			}else {
		      	return result.toString().substring(1, result.toString().length()-1);
			}
		}else if (inputY != null && !inputY.isEmpty() ){
			BasicDBObject query =  new BasicDBObject();
			filters.put("vendorName", 0);
			filters.put("countries.country", 0);
			
			ArrayList<BasicDBObject> condition = new ArrayList<BasicDBObject>();
			condition.add(new BasicDBObject("vendorName", inputX.toLowerCase()));
			condition.add(new BasicDBObject("countries.country", inputY.toUpperCase()));
			query.put("$and", condition);
			
			DBCursor cursor = collection.find(query,filters);  
	      	
			JSONArray result =  new JSONArray();
	      
	      	while (cursor.hasNext()) {
			 	result.add(cursor.next());
			 }
	      	if (result.isEmpty()) {
	      		return null;
			}else {
		      	return result.toString();
			}
			
		}else{
			return null;
		}
	}
	
	public static void main(String args[]) throws JSONException {
		
		 JSONObject data= new JSONObject();
		 	data.put("system","sap");
		  	data.put("supplier","poppulo");
		  	data.put("countres", "boston");
		 	//data.put("po_number", "po1234");
		 	//data.put("pr_no", "PR1234");

		  	//data.put("pr_number", "PR1234");
			//data.put("vendorID", "213");
			//data.put("country", "US");
			//data.put("vendorName", "sap");
			
		 
		MongoDBConnection obj = new MongoDBConnection();
		System.out.println(obj.getVendorAvailability(data.toString()));
		//System.out.println(obj.getPoStatus(data.toString()));;
		//System.out.println(obj.getPRApprover(data.toString()));;
		//System.out.println(obj.getVendorDetails("sap", "us"));
		//System.out.println(obj.getPRDetails(data.toString()));
		
	}


}
