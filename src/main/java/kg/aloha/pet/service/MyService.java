package kg.aloha.pet.service;

import kg.aloha.pet.model.*;
import kg.aloha.pet.model.model.Aktivist;
import kg.aloha.pet.model.model.Profession;
import kg.aloha.pet.model.model.Profession_Count;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99670 on 30.10.2020.
 */

@Repository
@Transactional
public class MyService {
    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory session;



    public Frakciya save(Frakciya frakciya){
        session.getCurrentSession().save(frakciya);
        return frakciya;
    }
    public boolean save(ArrayList<Deputat> arrayList){
        Session session1 = session.openSession();
        Transaction transaction = session1.beginTransaction();
        for(int i=0;i<arrayList.size();i++){
            Deputat deputat = arrayList.get(i);
            session1.save(deputat);
            if(i%20==0){
                session1.flush();
                session1.clear();
            }
        }
        transaction.commit();
        session1.close();
        return true;
    }

    public void save(Comment comment){
        session.getCurrentSession().save(comment);
    }
    public Aktivist save(Aktivist aktivist){
        session.getCurrentSession().save(aktivist);
        return aktivist;
    }
    public Profession save(Profession profession){
        session.getCurrentSession().save(profession);
        return profession;
    }
    public Profession_Count save(Profession_Count profession_count){
        session.getCurrentSession().save(profession_count);
        return profession_count;
    }
    public void save(List<Prezident> prezidents){
        try{
            Session session1 = session.openSession();
            Transaction transaction = session1.beginTransaction();
            for(int i=0;i<prezidents.size();i++){
                Prezident prezident = prezidents.get(i);
                session1.save(prezident);
                if(i%20==0){
                    session1.flush();
                    session1.clear();
                }
            }
            transaction.commit();
            session1.close();
        }catch (Exception e){e.printStackTrace();}
    }
    public Address save(Address address){
        session.getCurrentSession().save(address);
        return address;
    }
    public Family save(Family family){
        session.getCurrentSession().save(family);
        return family;
    }
    public Imushestvo save(Imushestvo imushestvo){
        session.getCurrentSession().save(imushestvo);
        return imushestvo;
    }
//    ============================================================
//    ============================================================
//    ============================================================
    public Golos saveGolosAct(Golos golos ){
        Criteria criteria = session.getCurrentSession().createCriteria(Golos.class);
        criteria.add(Restrictions.eq("u_id", golos.getU_id()));
        criteria.add(Restrictions.eq("a_id", golos.getP_id()));
        Golos golos1 = (Golos) criteria.uniqueResult();
        if(golos1!=null){
            session.getCurrentSession().save(golos);
        }else{
            session.getCurrentSession().remove(golos);
            session.getCurrentSession().save(golos);
        }
        return golos;
    }





















    public Golos saveGolosPrezident(Golos golos){
        Criteria criteria = session.getCurrentSession().createCriteria(Golos.class);
        criteria.add(Restrictions.eq("u_id",golos.getU_id()));
        criteria.add(Restrictions.eq("p_id",golos.getP_id()));
        Golos golos1 = (Golos) criteria.uniqueResult();
        if(golos1!=null){
            session.getCurrentSession().remove(golos1);
            session.getCurrentSession().save(golos);
        }else{
            session.getCurrentSession().save(golos);
        }
        return golos;
    }




























    public Golos saveGolosDep(Golos golos){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("delete * from Golos_minus g where g.d_id = "+golos.getP_id()+" and g.u_id = "+golos.getU_id());
        sqlQuery.executeUpdate();
        session.getCurrentSession().save(golos);
        return golos;
    }
//    ============================================================
//    ============================================================
//    ============================================================



    public List getGolos4Activist(long a_id){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select count(g.a_id) cnt,g.a_id from Golos g group by g.a_id");
        List list =  sqlQuery.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }




    public List getGolos4Deputat(){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select count(g.d_id) cnt,g.p_id from Golos g group by g.d_id");
        List list =  sqlQuery.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }



    public List getGolos4Prezident(){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select count(g.p_id) cnt,g.p_id from Golos g group by g.p_id");
        List list =  sqlQuery.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }





    public List getAllDep(){
        Criteria criteria = session.getCurrentSession().createCriteria(Deputat.class);
        List list =  criteria.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }

    public List getAllPrezident(){
        Criteria criteria = session.getCurrentSession().createCriteria(Prezident.class);
        List list =  criteria.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }
    public List getAllAktivist(){
        Criteria criteria = session.getCurrentSession().createCriteria(Aktivist.class);
        List list =  criteria.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }



    public List getMyCommentsToAct(long a_id,long u_id){
        Criteria criteria =  session.getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("a_id",a_id));
        criteria.add(Restrictions.eq("u_id",u_id));
        criteria.setMaxResults(500);
        criteria.addOrder(Order.desc("c_id"));

        List l = criteria.list();
        if(l!=null){
            if(l.size()!=0){
                return l;
            }
        }
        return null;
    }

    public List getAllCommentsToAct(long a_id){
        Criteria criteria =  session.getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("a_id",a_id));
        criteria.setMaxResults(500);
        criteria.addOrder(Order.desc("c_id"));
        List l = criteria.list();
        if(l!=null){
            if(l.size()!=0){
                return l;
            }
        }
        return null;
    }





    public List getMyCommentsToDep(long d_id,long u_id){
       Criteria criteria =  session.getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("d_id",d_id));
        criteria.add(Restrictions.eq("u_id",u_id));
        criteria.setMaxResults(500);
        criteria.addOrder(Order.desc("c_id"));

        List l = criteria.list();
        if(l!=null){
            if(l.size()!=0){
                return l;
            }
        }
        return null;
    }

    public List getAllCommentsToDep(long d_id){
        Criteria criteria =  session.getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("d_id",d_id));
        criteria.addOrder(Order.desc("c_id"));
        criteria.setMaxResults(500);
        List l = criteria.list();
        if(l!=null){
            if(l.size()!=0){
                return l;
            }
        }
        return null;
    }




    public List getMyCommentsToPrezident(long p_id,long u_id){
        Criteria criteria =  session.getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("p_id",p_id));
        criteria.add(Restrictions.eq("u_id",u_id));
        criteria.addOrder(Order.desc("c_id"));
        List l = criteria.list();
        if(l!=null){
            if(l.size()!=0){
                return l;
            }
        }
        return null;
    }
    public List getAllCommentsToPrezident(long p_id){
        Criteria criteria =  session.getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("p_id",p_id));
        criteria.addOrder(Order.desc("c_id"));
        List l = criteria.list();
        if(l!=null){
            if(l.size()!=0){
                return l;
            }
        }
        return null;
    }



    public int getCountUsers(){
     Criteria criteria = session.getCurrentSession().createCriteria(Pet_user.class);
     List l = criteria.list();
        if(l!=null){
            return l.size();
        }
        return 0;
    }




    public List searchDep(String fio){
        Criteria criteria = session.getCurrentSession().createCriteria(Deputat.class);
        criteria.add(Restrictions.sqlRestriction("UPPER(fio) LIKE ?", "%"+fio.toUpperCase()+"%", StringType.INSTANCE));
        List list =  criteria.list();
        if(list!=null){
            if(list.size()!=0){
                return list;
            }
        }
        return null;
    }




}
