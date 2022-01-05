package com.example.Test1.Users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path= "users")
public class UserController {

    ArrayList<User> users = new ArrayList<>();
    private double bankBalance = 1000000;

    @GetMapping
    public List<User> getUsers(){
        return users;
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user){

        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).getId() == user.getId()){
                return ResponseEntity.status(400).body("User id must be unique");
            }
            if(users.get(i).getUserName().equals(user.getUserName())){
                return ResponseEntity.status(400).body("UserName must be unique");
            }
            if(users.get(i).getEmail().equals(user.getEmail())){
                return ResponseEntity.status(400).body("Email must be unique");
            }
            if(user.getPassword().length() < 6){
                return ResponseEntity.status(400).body("Password must be more than 6");
            }
        }


        users.add(user);
        return ResponseEntity.status(200).body("User added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delUser(@PathVariable String id){
        int userId = Integer.valueOf(id);
        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).getId() == userId){
                if(users.get(i).getLonaAmount() > 0){
                    return ResponseEntity.status(400).body("The loan must be paid off first");
                }
                bankBalance += users.get(i).getBalance();
                users.remove(i);
                return ResponseEntity.status(200).body("User deleted");
            }
        }

        return ResponseEntity.status(400).body("User not found");
    }


    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody User user){

        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).getId() == user.getId()){continue;}
            if(users.get(i).getId() == user.getId()){
                return ResponseEntity.status(400).body("User id must be unique");
            }
            if(users.get(i).getUserName().equals(user.getUserName())){
                return ResponseEntity.status(400).body("UserName must be unique");
            }
            if(users.get(i).getEmail().equals(user.getEmail())){
                return ResponseEntity.status(400).body("Email must be unique");
            }
            if(user.getPassword().length() < 6){
                return ResponseEntity.status(400).body("Password must be more than 6");
            }
        }


        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).getId() == Integer.valueOf(id)){
                users.set(i, user);
                return ResponseEntity.status(200).body("User updated");
            }
        }
        return ResponseEntity.status(400).body("User not found");
    }


    @PutMapping("withdraw/{id}")
    public ResponseEntity withdrawMoney(@PathVariable String id, @RequestBody User user){
        int userId = Integer.valueOf(id);
        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).getId() == userId){
                if(! user.getPassword().equals(users.get(i).getPassword())){
                    return ResponseEntity.status(400).body("Password Error");
                }
                if(users.get(i).getBalance() - user.getBalance() < 0){
                   return ResponseEntity.status(400).body("Account balance is not enough");
                }else{
                    users.get(i).setBalance(users.get(i).getBalance() - user.getBalance());
                    return ResponseEntity.status(200).body("The amount has been deducted");}
            }
        }

        return ResponseEntity.status(400).body("User not found");
    }


    @PutMapping("deposit/{id}")
    public ResponseEntity DepositMoney(@PathVariable String id, @RequestBody User user){
        int userId = Integer.valueOf(id);

        for(int i = 0 ; i < users.size() ; i++){

            if(users.get(i).getId() == userId){

                if(! user.getPassword().equals(users.get(i).getPassword())){
                    return ResponseEntity.status(400).body("Password Error");
                }

                users.get(i).setBalance(users.get(i).getBalance() + user.getBalance());
                return ResponseEntity.status(200).body("Amount has been added");}

        }

        return ResponseEntity.status(400).body("User not found");
    }







    @PutMapping("lona/{id}")
    public ResponseEntity lonaBank(@PathVariable String id, @RequestBody User user){

        if (bankBalance - user.getLonaAmount() < 0){
            return ResponseEntity.status(400).body("The bank cannot lend you");
        }

        int userId = Integer.valueOf(id);

        for(int i = 0 ; i < users.size() ; i++){

            if(users.get(i).getId() == userId){

                if(! user.getPassword().equals(users.get(i).getPassword())){
                    return ResponseEntity.status(400).body("Password Error");
                }

                users.get(i).setLonaAmount(users.get(i).getLonaAmount() + user.getLonaAmount());
                bankBalance -= user.getLonaAmount();
                return ResponseEntity.status(200).body("The loan has been registered");}

        }

        return ResponseEntity.status(400).body("User not found");
    }



    @PutMapping("RepayLoan/{id}")
    public ResponseEntity RepayLoan(@PathVariable String id, @RequestBody User user){



        int userId = Integer.valueOf(id);

        for(int i = 0 ; i < users.size() ; i++){

            if(users.get(i).getId() == userId){

                if(! user.getPassword().equals(users.get(i).getPassword())){
                    return ResponseEntity.status(400).body("Password Error");
                }

                if(users.get(i).getBalance() - users.get(i).getLonaAmount() < 0){
                    return ResponseEntity.status(400).body("Your balance does not allow the transaction to be completed");
                }

                users.get(i).setBalance(users.get(i).getBalance() - users.get(i).getLonaAmount());
                users.get(i).setLonaAmount(0);
                return ResponseEntity.status(200).body("The loan has been paid");}

        }

        return ResponseEntity.status(400).body("User not found");
    }



}

