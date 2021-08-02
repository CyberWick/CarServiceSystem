package com.main;

import com.car.Car;
import com.car.CarRepository;
import com.car.GlobalVariablesCar;
import com.customer.Customer;
import com.customer.CustomerRepository;
import com.managerunits.MainManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.car.GlobalVariablesCar.*;

public class Dashboard {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static CarRepository carRepository = new CarRepository();
    public static CustomerRepository customerRepository = new CustomerRepository();
    public static void main(String[] args) throws IOException, InterruptedException {

        onStartLoadData();

        //Adding customer to DB
        char customerCreateResponse;
        System.out.println("Enter data into DB to start interaction.");
        do{
            System.out.println("Do you want to create a customer?(y/n)");
            customerCreateResponse = reader.readLine().charAt(0);
            if(customerCreateResponse == 'y' || customerCreateResponse=='Y') {
                createCustomerDashboard();
            }
        } while(customerCreateResponse=='Y' || customerCreateResponse=='y');

        System.out.println("Welcome to car washing service!\n Enter Details to proceed:\n");
        char doYouWishToContinue;
        do {
            int cmdInput = 0;

//            System.out.println("LIST of CARS!");
////            List<Car> cars = carRepository.getAllCars();
////            for(int i=0;i<cars.size();i++) {
////                System.out.println(cars.get(i).getCarNumber());
////            }
            System.out.println("Enter your customer ID: ");
            String customerId = reader.readLine();
            List<Car> customerCars = customerRepository.findCustomerCars(customerId);
            if(customerCars.size() > 0) {
                System.out.println("Cars owned by customer : ");
                for (int i = 0; i < customerCars.size(); i++) {
                    System.out.println("Car Number: " + customerCars.get(i).getCarNumber());
                }
                List<Car> servicableCars = new ArrayList<>();
                System.out.println("Finding any cars for servicing: ");
                Thread.sleep(1000);
                for (int i = 0; i < customerCars.size(); i++) {
                    if(customerCars.get(i).getFuelRemaining()  != customerCars.get(i).getFuelCapacity()) {
                        servicableCars.add(customerCars.get(i));
                    }
                }
                if(servicableCars.size() > 0) {
                    System.out.println("Cars for servicing: ");
                    for(int i=0;i<servicableCars.size();i++) {
                        System.out.println("Car Number: " + servicableCars.get(i).getCarNumber()
                                + "\tFuel Capacity: " + servicableCars.get(i).getFuelCapacity()
                                + "\tFuel Remaining: " + servicableCars.get(i).getFuelRemaining());
                        Car updatedCar = MainManager.startService(servicableCars.get(i));
                        System.out.println("Updating car info in DB!!");
//                        Thread.sleep(2000);
                        carRepository.updateCar(updatedCar);
                    }
                }
                else {
                    System.out.println("No car found for servicing!");
                }
                Thread.sleep(1000);
            }
            else {
                System.out.println("Provided customer ID has no cars!!");
            }
            System.out.println("Do you wish to continue? (y/n)");
            String answerString = reader.readLine();
            doYouWishToContinue = answerString.charAt(0);
        } while(doYouWishToContinue=='y' || doYouWishToContinue=='Y');
    }

    public static void createCustomerDashboard() throws IOException{
        System.out.println("Enter customer ID: ");
        String customerID = reader.readLine();
        System.out.println("Enter customer name: ");
        String customerName = reader.readLine();
        System.out.println("Enter customer phone number: ");
        String customerPhoneNumber = reader.readLine();
        char carCreateResponse;
        List<Car> carsOfCustomer = new ArrayList<>();
        do{
            System.out.println("Do you want to create a car?(y/n)");
            carCreateResponse = reader.readLine().charAt(0);
            if(carCreateResponse == 'y' || carCreateResponse=='Y') {
                carsOfCustomer.add(createCarDashboard());
            }
        }while(carCreateResponse=='Y' || carCreateResponse=='y');
        String id = customerRepository.saveCustomer(customerID, customerName, customerPhoneNumber, carsOfCustomer);
    }

    public static Car createCarDashboard() throws IOException {
        int cmdInput;
//            System.out.println("Enter customer ID: ");
//            String customerID = reader.readLine();
//            if(customerRepository.findCustomerById(customerID) == null) {
//                System.out.println("Wrong cutomer ID please check again!");
//                return null;
//            }
            System.out.println("Enter car number: ");
            String carNumber = reader.readLine();
            do {
                System.out.println("1. Type of car: \n1. Hatchback \t2. Sedan \t3. SUV\n:");
                cmdInput = takeAndCheckInput();
                System.out.println(cmdInput);
            } while (!(cmdInput > 0 && cmdInput < 4));
            String type = GlobalVariablesCar.carType[cmdInput - 1];
            System.out.println("Enter fuel capacity of car: ");
            int fuelCapacity = takeAndCheckInput();
            System.out.println("Enter fuel left in car: ");
            int fuelRemaining;// = Integer.MAX_VALUE;
            do {
                fuelRemaining = takeAndCheckInput();
                try {
                    if (fuelRemaining > fuelCapacity)
                        throw new FuelIncorrectException();
                } catch (FuelIncorrectException e) {
                    System.out.println("Incorrect fuel entered. Try again!");
                }
            } while (fuelRemaining > fuelCapacity);
            Car car = new Car();
            car.setCarNumber(carNumber);
            car.setType(type);
            car.setFuelCapacity(fuelCapacity);
            car.setFuelRemaining(fuelRemaining);
            return car;
//            String id = carRepository.saveCar(carNumber, type, customerID, fuelCapacity, fuelRemaining);
    }

    public static int takeAndCheckInput() {
        int cmdInput;
        try {
            cmdInput = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Enter a proper number again!");
            return -1;
        }
        return cmdInput;
    }

    public static void onStartLoadData () {
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        customer.setCustomerID("123");
        customer.setCustomerName("XYZ");
        customer.setCustomerPhoneNumber("123321");

        customer1.setCustomerID("124");
        customer1.setCustomerName("ABC");
        customer1.setCustomerPhoneNumber("125671");

        customer2.setCustomerID("125");
        customer2.setCustomerName("UVW");
        customer2.setCustomerPhoneNumber("126789");

        Car car = new Car();
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        car.setCarNumber("MH46 CC 2020");
        car.setType(HATCHBACK);
        car.setFuelCapacity(120);
        car.setFuelRemaining(20);

        car1.setCarNumber("MH43 AB 2021");
        car1.setType(SEDAN);
        car1.setFuelCapacity(180);
        car1.setFuelRemaining(40);

        car2.setCarNumber("MH12 FG 2112");
        car2.setType(SUV);
        car2.setFuelCapacity(220);
        car2.setFuelRemaining(40);

        car3.setCarNumber("MH04 QW 3100");
        car3.setType(HATCHBACK);
        car3.setFuelCapacity(80);
        car3.setFuelRemaining(0);

        car4.setCarNumber("MH06 TH 4310");
        car4.setType(SUV);
        car4.setFuelCapacity(220);
        car4.setFuelRemaining(220);

        List<Car> carOfCustomer = new ArrayList<>();
        carOfCustomer.add(car);
        carOfCustomer.add(car1);
        carOfCustomer.add(car2);

        customerRepository.saveCustomer(customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerPhoneNumber(), carOfCustomer);

        List<Car> carOfCustomer1 = new ArrayList<>();
        carOfCustomer1.add(car3);
        carOfCustomer1.add(car4);

        customerRepository.saveCustomer(customer1.getCustomerID(), customer1.getCustomerName(), customer1.getCustomerPhoneNumber(), carOfCustomer1);

        customerRepository.saveCustomer(customer2.getCustomerID(), customer2.getCustomerName(), customer2.getCustomerPhoneNumber(), null);

        System.out.println("Data(pre-loaded) loaded into DB");
    }
}
