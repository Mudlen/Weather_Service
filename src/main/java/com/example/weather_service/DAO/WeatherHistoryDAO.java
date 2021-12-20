package com.example.weather_service.DAO;
import com.example.weather_service.Entity.WeatherHistoryEntity;
import com.example.weather_service.Util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.Date;

public class WeatherHistoryDAO {

    public void save(WeatherHistoryEntity weatherHistoryEntity){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(weatherHistoryEntity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void update(WeatherHistoryEntity weatherHistoryEntity){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(weatherHistoryEntity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void delete(WeatherHistoryEntity weatherHistoryEntity){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(weatherHistoryEntity);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public WeatherHistoryEntity getWeatherByDate(Date date){
        WeatherHistoryEntity weatherHistoryEntity = null;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            weatherHistoryEntity = session.createQuery
                    ("FROM WeatherHistoryEntity weatherHistory " +
                                    "WHERE weatherHistory.weatherDate = '"+date+"'"
                            ,WeatherHistoryEntity.class).getSingleResult();
            transaction.commit();
            return weatherHistoryEntity;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return weatherHistoryEntity;
    }
}
