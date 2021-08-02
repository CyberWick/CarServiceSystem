package com.customer;

import com.car.Car;
import com.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerRepository {

    public Customer findCustomerById(String customerId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, customerId);
        session.getTransaction().commit();
        session.close();
        return customer;
//        int customerFound = (Integer) session.byId(customerId);
    }

    public String saveCustomer(String customerId, String customerName, String customerPhoneNumber, List<Car> carsOfCustomer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = new Customer();
        customer.setCustomerID(customerId);
        customer.setCustomerName(customerName);
        customer.setCustomerPhoneNumber(customerPhoneNumber);
        customer.setCarsOfCustomer(carsOfCustomer);
        String id = (String) session.save(customer);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public List<Car> findCustomerCars(String customerId) {
        String joinSql = "from Car where customer_id = :customer_id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(joinSql);
        query.setParameter("customer_id", customerId);
        List<Car> carsOfCustomers = query.list();
        session.getTransaction().commit();
        session.close();
        return carsOfCustomers;
    }

    public List<Customer> listAllCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Customer> customers = session.createQuery("from customer").list();
        session.getTransaction().commit();
        session.close();
        return customers;
    }
}
