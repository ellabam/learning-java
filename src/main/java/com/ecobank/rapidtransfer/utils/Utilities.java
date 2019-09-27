package com.ecobank.rapidtransfer.utils;

import com.ecobank.rapidtransfer.obj.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import javax.validation.constraints.Email;

public class Utilities {

    public static Connection getConnection() throws Exception {
        Connection conn = null;
        String host = "";
        String passwords = "";
        String Instance = "";
        String usernames = "";

        host = "localhost";
        usernames = "ebam";
        passwords = "ebam";
        Instance = "ORCLPDB1";

        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        System.out.println("Connecting to the database..." + host + " : ---- : " + usernames);
        //conn = DriverManager.getConnection(host, usernames, (passwords));
        conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@" + host + ":1521/" + Instance + "", usernames, passwords);
        System.out.println("Connected to the database");
        return conn;
    }

    private void sendPost() throws Exception {
        
        EmailSender emailSender = new EmailSender();
        emailSender.setEmails(Arrays.asList("jeze@ecobank.com", "ebamgbose@ecobank.com"));
        emailSender.setSenderDisplayName("Test");
        emailSender.setSenderAddress("ebamgbose@ecobank.com");
        emailSender.setSubject("your First Mail");
        emailSender.setTr_ref("ref101");
        emailSender.setMessage("this is your first email API");
        
        
        
        ObjectMapper mapper = new ObjectMapper();
      

        String url = "http://10.4.139.51:1759/notify/sendemail";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
