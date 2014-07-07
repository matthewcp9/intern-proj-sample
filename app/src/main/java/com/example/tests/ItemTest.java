package com.example.tests;

import android.test.InstrumentationTestCase;

import com.example.savingetc.app.Item;

import org.json.*;


public class ItemTest extends InstrumentationTestCase {
	static JSONObject jsonTest = new JSONObject();

	public static void test() {
        try {
            jsonTest.put("name", "foo");
            jsonTest.put("color", "blue");
            jsonTest.put("brand", "levi's");
            jsonTest.put("size", "Medium");
            jsonTest.put("type", "pants");
            jsonTest.put("id", 1);
            jsonTest.put("price", 14.95);
            Item itemEx = new Item(jsonTest);
            System.out.println(itemEx.getBrand());
            assertTrue(itemEx.getBrand().equals("levi's"));
            assertTrue(itemEx.getType().equals("pants"));
            //return true;
        }
        catch (JSONException json) {
            json.printStackTrace();
        }
	}

    public static void main(String[] args)
    {
        test();
    }
							
}
