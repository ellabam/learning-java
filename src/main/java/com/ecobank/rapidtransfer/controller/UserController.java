package com.ecobank.rapidtransfer.controller;

import com.ecobank.rapidtransfer.obj.UserInfo;
import com.ecobank.rapidtransfer.utils.Utilities;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping(value = "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserRecord() {


        List<UserInfo> userInfoList = new ArrayList<>();

        UserInfo userInfo = null;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            conn = Utilities.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM RT_USER");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setEmail(resultSet.getString("EMAIL_ADDRESS"));
                userInfo.setFirstName(resultSet.getString("FIRST_NAME"));
                userInfo.setLastName(resultSet.getString("LAST_NAME"));
                userInfo.setUserId(resultSet.getString("USER_ID"));
                userInfoList.add(userInfo);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userInfoList);

    }


    //http://localhost:8080/users?id=23456789
    @GetMapping("/users")
    public ResponseEntity<?> getUserRecordById(@RequestParam("id") String id){


        UserInfo userInfo = null;

        Connection conn = null;
        OracleCallableStatement callableStatement = null;
        ResultSet resultSet = null;


        try {
            conn = Utilities.getConnection();
            conn = conn.unwrap(OracleConnection.class);
            callableStatement = (OracleCallableStatement)conn.prepareCall("{? =call get_user(?)}");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setString(2, id);

            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            while (resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setEmail(resultSet.getString("EMAIL_ADDRESS"));
                userInfo.setFirstName(resultSet.getString("FIRST_NAME"));
                userInfo.setLastName(resultSet.getString("LAST_NAME"));
                userInfo.setUserId(resultSet.getString("USER_ID"));

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);

    }
    //http://localhost:8080/users?userid=23456789
    @GetMapping(value = "/users1")
    public ResponseEntity<?> getUserRecordUserId(@RequestParam("userid") String userid) {


        List<UserInfo> userInfoList = new ArrayList<>();

        UserInfo userInfo = null;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            conn = Utilities.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM RT_USER where USER_ID = ?");
            preparedStatement.setString(1,userid);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setEmail(resultSet.getString("EMAIL_ADDRESS"));
                userInfo.setFirstName(resultSet.getString("FIRST_NAME"));
                userInfo.setLastName(resultSet.getString("LAST_NAME"));
                userInfo.setUserId(resultSet.getString("USER_ID"));
                userInfoList.add(userInfo);
            }
           
                


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
         boolean value = userInfoList.isEmpty();
            if (value == true) {
                
                String failure = "No record found";
               return ResponseEntity.status(HttpStatus.OK).body(failure );
            } 

        return ResponseEntity.status(HttpStatus.OK).body(userInfoList);

    }
}
