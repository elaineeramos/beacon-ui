package net.magis.BeaconPH.Controller;

import net.magis.BeaconPH.Data.Defs;
import net.magis.BeaconPH.Data.Location;
import net.magis.BeaconPH.Data.LocationResponse;
import net.magis.BeaconPH.Data.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseHandler
{
	private static final String _locTypeString[] = { "",
													"School",
													"Church",
													"FireStation",
													"PoliceStation",
													"PublicOffice",
													"Hospital" };
	
	public ResponseHandler()
	{
		return;
	}
	
	public Response getResponse(String respStr)
	{
		Response response = null;
		/* Determine the type of response we are dealing with */
		switch(getResponseType(respStr))
		{
			case Defs.RESPONSE_TYPE_LOCATION:
				response = parseLocationResponse(respStr);
				break;
			case Defs.RESPONSE_TYPE_PERSON:
				response = parsePersonResponse(respStr); /* TODO */
				break;
			case Defs.RESPONSE_TYPE_UNKNOWN:
			default:
				break;
		}
		
		return response;
	}
	
	private int getResponseType(String respStr)
	{
		JSONObject response;
		try {
			response = new JSONObject(respStr);
			
			JSONArray values = response.getJSONArray("Values");
			
			if (values.length() <= 0)
			{
				Util.log(this.getClass().getSimpleName(), "Error: Internal JSON array is empty");
				return Defs.RESPONSE_TYPE_UNKNOWN;
			}

			JSONObject child = values.getJSONObject(0);

			JSONArray ja = child.names();
			String fields[] = new String[ja.length()];
			
			for (int j = 0; j < ja.length(); j++)
			{
				/* Util.log("DEBUG", ja.getString(j)); */
				fields[j] = ja.getString(j);
			}
			
			for (int i = 0; i < fields.length; i++)
			{
				if (fields[i].contains("Id"))
				{
					Util.log(this.getClass().getSimpleName(), "Found Id field: " + fields[i]);
					if (fields[i].contains("PublicOffice") ||
						fields[i].contains("Church") ||
						fields[i].contains("School") ||
						fields[i].contains("FireStation") ||
						fields[i].contains("PoliceStation") ||
						fields[i].contains("Hospital"))
					{
						Util.log(this.getClass().getSimpleName(), "Info: Response matched to type: LOCATION");
						return Defs.RESPONSE_TYPE_LOCATION;
					}
					else if (fields[i].contains("Person"))
					{
						Util.log(this.getClass().getSimpleName(), "Info: Response matched to type: PERSON");
						return Defs.RESPONSE_TYPE_PERSON;
					}
					else
					{
						/* Response type is unknown */
						break;
					}
				}
			}
			
		} catch (JSONException e) {
			Util.log(this.getClass().getSimpleName(), "Error: Malformed JSON object!");
		}

		Util.log(this.getClass().getSimpleName(), "Info: Response type is UNKNOWN");
		return Defs.RESPONSE_TYPE_UNKNOWN;
	}
	
	private Response parseLocationResponse(String respStr)
	{
		LocationResponse locResponse = new LocationResponse();
		
		/* Perform JSON parsing */
		try {
			JSONObject response = new JSONObject(respStr);
			JSONArray values = response.getJSONArray("Values");
			
			/* Add all location elements to the locResponse object */
			for (int i = 0; i < values.length(); i++)
			{
				
				JSONObject jsonLoc = values.getJSONObject(i);
				
				/* Locate the id field and extract its value */
				int locId = 0;
				int j;
				
				JSONArray ja = jsonLoc.names();
				String fields[] = new String[ja.length()];
				
				for (j = 0; j < ja.length(); j++)
				{
					fields[j] = ja.getString(j);
				}
				
				for (j = 0; j < fields.length; j++)
				{
					if (fields[j].contains("Id"))
					{
						locId = jsonLoc.getInt(fields[j]);
						break;
					}
				}
				
				/* Handle the case where the Id field was not found */
				if (j >= fields.length)
				{
					Util.log(this.getClass().getSimpleName(), 
							 "Error: No Id fields found for record " + i + ". Skipping...");
					continue;
				}
				
				int locType = getLocTypeCode(fields[6]);
				
				String locName = "";
				if (jsonLoc.has("BuildingName"))
				{
					locName = jsonLoc.getString("BuildingName");
				}
				else if (jsonLoc.has("Name"))
				{
					locName = jsonLoc.getString("Name");
				}
				
				String locHouseNo = jsonLoc.has("HouseNumber") ? "(" + jsonLoc.getString("HouseNumber") + ") " : "" ;
				String locStreet = jsonLoc.has("Street") ? jsonLoc.getString("Street") : "" ;
				String locCity = jsonLoc.has("CityMunicipality") ? jsonLoc.getString("CityMunicipality") : "";
				String locAddr = locHouseNo + locStreet + (!locStreet.equals("") && !locCity.equals("") ? ", " : "") + locCity;
				
				JSONObject jsonCoords = jsonLoc.getJSONObject("Location");
				double locLat = jsonCoords.getDouble("Latitude") + Defs.LAT_ERROR_OFFSET;
				double locLon = jsonCoords.getDouble("Longitude") + Defs.LON_ERROR_OFFSET;
				
				/* Add to locResponse object */
				locResponse.addLocation(new Location(locId, locType, locName, 
													 locAddr, locLat, locLon));
			}
			
		} catch (JSONException e) {
			//e.printStackTrace();
			Util.log(this.getClass().getSimpleName(), "Error: Malformed JSON object!");
			locResponse = null;
		}
		
		return locResponse;
	}
	
	private int getLocTypeCode(String locType)
	{
		for (int locCode = 0; locCode < _locTypeString.length; locCode++)
		{
			if (_locTypeString[locCode].contains(locType))
			{
				return locCode;
			}
		}
		
		return Location.TYPE_UNKNOWN;
	}
	
	private Response parsePersonResponse(String respStr)
	{
		return null; /* TODO */
	}
}
