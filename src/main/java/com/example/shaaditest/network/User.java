package com.example.shaaditest.network;


import com.example.shaaditest.model.Person;
import com.example.shaaditest.network.response.PersonResponse;
import com.example.shaaditest.utils.APIKeyConstants;
import com.example.shaaditest.utils.CoreConstants;
import com.example.shaaditest.utils.Logger;
import com.example.shaaditest.utils.URLConstants;
import com.example.shaaditest.utils.WebserviceReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    WebserviceReader ws = null;
    String strResponse = null;

    //MASTER MATERIAL API CALL..
    public PersonResponse getPersons(){

        PersonResponse response = new PersonResponse();

        ArrayList<Person> personList = new ArrayList<>();
        Person person =null ;

        try{

            ws = new WebserviceReader();

            strResponse = ws.WebserviceCallServicerGet
                    (URLConstants.GET_RESULTS);

            Logger.e("person Response","API Response - "+strResponse);

            Logger.e("=====persona Response Code :" + CoreConstants.PageCode);

            if(CoreConstants.PageCode.equals("200")) {
                JSONObject jsonResponse = new JSONObject(strResponse);


                JSONArray JArray = jsonResponse.getJSONArray(APIKeyConstants.KEY_ResponseObject);

                for(int i = 0 ; i<JArray.length(); i++){

                    JSONObject jObject = JArray.getJSONObject(i);

                    person = new Person();
                    person.setGender(jObject.getString("gender"));
                    person.setEmail(jObject.getString("email"));
                    person.setPhone(jObject.getString("phone"));
                    person.setCell(jObject.getString("cell"));

                    //PARSING NAME OBJECT..
                    JSONObject nameJobj = jObject.getJSONObject("name");
                    person.setTitle_name(nameJobj.getString("title"));
                    person.setFirst_nmae(nameJobj.getString("first"));
                    person.setLast_name(nameJobj.getString("last"));

                    //PARSING LOCATION OBJECT..
                    JSONObject locationObj = jObject.getJSONObject("location");
                    person.setCity(locationObj.getString("city"));
                    person.setState(locationObj.getString("state"));
                    person.setCountry(locationObj.getString("country"));

                    //PARSING LOGIN INFO.
                    JSONObject loginObj = jObject.getJSONObject("login");
                    person.setLogin_username(loginObj.getString("username"));
                    person.setLogin_pwd(loginObj.getString("password"));

                    //PARSING DATE OF BIRTH AND AGE..
                    JSONObject dobObj = jObject.getJSONObject("dob");
                    person.setDob(dobObj.getString("date"));
                    person.setAge(dobObj.getString("age"));

                    //PARSING PICTURE..
                    JSONObject picObj = jObject.getJSONObject("picture");
                    person.setPic_large(picObj.getString("large"));
                    person.setPic_medium(picObj.getString("medium"));
                    person.setPic_thimb(picObj.getString("thumbnail"));
                    personList.add(person);
                }
                response.setPersonList(personList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  response;
    }



}
