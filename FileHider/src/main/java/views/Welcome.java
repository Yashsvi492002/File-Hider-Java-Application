package views;

import dao.UserDAO;
import models.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import static service.SendOTPService.sendOTP;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the App");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signUp");
        System.out.println("Press 0 to Exist the App");

        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Enter your Email:");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        sendOTP(email, genOTP);
        System.out.println("Enter the OTP:");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response){
                case 0-> System.out.println("User Registered");
                case 1 -> System.out.println("User already exists");

            }
        }else{
            System.out.println("Wrong Otp entered");
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Email:");
        String email = sc.nextLine();
        try{
            if(UserDAO.isExists(email)){
                String genOTP = GenerateOTP.getOTP();
                sendOTP(email, genOTP);
                System.out.println("Enter the OTP:");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                    new UserView(email).home();

                }else{
                    System.out.println("Wrong Otp entered");
                }
            }else{
                System.out.println("User not found");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }



    }
}
