/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecobank.rapidtransfer.controller;

import com.ecobank.rapidtransfer.obj.Login;
import com.ecobank.rapidtransfer.obj.ResponseObject;
import com.ecobank.rapidtransfer.obj.UserInfo;
import com.ecobank.rapidtransfer.obj.UserRecord;
import com.ecobank.rapidtransfer.utils.Utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author emmanuellabamgbose
 */
@RestController
public class TestController {

    @PostMapping(value = "/insertRecords", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getInputRecord(@RequestBody() UserRecord userRecord) {
        
        ResponseObject responseObject = new ResponseObject();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int inserted = 0;
         boolean isInserted = false;
        try {
            conn = Utilities.getConnection();
            Statement statement = conn.createStatement();
            inserted = statement.executeUpdate("insert into user_record_test values (" + "'" + userRecord.getUserName() + "'" + "," + "'" + userRecord.getFirstName() + "'" + "," + "'" + userRecord.getLastName() + "'" + ")");
            
            System.out.println("INSERTED +++ " + inserted);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
//                if (inputSet != null) {
//                    inputSet.close();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (inserted > 0) {
             responseObject.setStatus("success");
             responseObject.setMessage("Record added succesfully");
        
               return ResponseEntity.status(HttpStatus.OK).body(responseObject);
          }
        
        responseObject.setStatus("failed");
        responseObject.setMessage("Record not added succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }
    
    
}
