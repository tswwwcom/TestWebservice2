package com.sjt.test;

import java.util.HashMap;
import java.util.Map;

public class TestWebservice {

	public static void main(String[] args) {
		String url = "http://191.254.1.191:9096/QXQuery.asmx?wsdl";
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "text/xml; charset=utf-8");
        String soapData = "\"<soapenv:Envelope xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope/ \n" +
                "\n" +
                "\\\" xmlns:tem=\\\"http://tempuri.org/ \n" +
                "\n" +
                "\\\">\\n\" +\n" +
                "\"   <soapenv:Header/>\\n\" +\n" +
                "\"   <soapenv:Body>\\n\" +\n" +
                "\"      <tem:QueryObtMind>\\n\" +\n" +
                "\"         <para>{\\\"siteCode\\\":\\\"G1142\\\",\\\"beginTime\\\":\\\"2019-01-17\\\",\\\"endTime\\\":\\\"2019-01-18\\\"}</para>\\n\" +\n" +
                "\"      </tem:QueryObtMind>\\n\" +\n" +
                "\"   </soapenv:Body>\\n\" +\n" +
                "\"</soapenv:Envelope>\"\n";
        String resp = HttpClientUtils.doPost(url, soapData, header);
        System.out.println(resp);
	}

}
