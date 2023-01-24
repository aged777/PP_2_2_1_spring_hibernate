package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List<User> userByCarModelAndSeries(String model, int series) {
      /*
      Query q = s.createQuery("from foo Foo as foo where foo.name=:name and foo.size=:size");
      q.setProperties(fooBean); // fooBean имеет getName() и getSize()
      List foos = q.list();
       */
      Query q = sessionFactory.getCurrentSession().createQuery("FROM User as u where u.car.model=:model and u.car.series=:series");
      q.setParameter("model", model);
      q.setParameter("series", series);
      List<User> allUsersByCarModelAndSeries = q.getResultList();
      return allUsersByCarModelAndSeries;
   }

}
