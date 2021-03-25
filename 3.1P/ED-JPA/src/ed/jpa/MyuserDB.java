/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

import entity.Myuser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Cyrus
 */
public class MyuserDB {

    private EntityManager em = null;

    public MyuserDB() {
        // using default persistence unit defined in the persistence.xml file
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ED-EntityPU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManger() {
        return em;
    }

    public Myuser findMyuser(String userid) {
        return em.find(Myuser.class, userid);
    }

    public boolean createMyuser(Myuser myuser) throws Exception {
        try {
            if (findMyuser(myuser.getUserid()) != null) {
                // myuser exists already
                return false;
            }
            // myuser does not exist in database
            em.getTransaction().begin();
            em.persist(myuser); // to add an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean createRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTO2DAO(myuserDTO);
        boolean result = false;
        try {
            result = this.createMyuser(myuser);
        } catch (Exception ex) {
        }
        return result;
    }

    public MyuserDTO getRecord(String userId) {
        try {
            Myuser myuser = findMyuser(userId);
            
            if (myuser == null) {
                return null;
            } else {
                return myDAO2DTO(myuser);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public boolean updateRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTO2DAO(myuserDTO);
        try {
            if (findMyuser(myuser.getUserid()) == null) {
                // myuser does not exist
                return false;
            }
            // myuser does exist in database
            em.getTransaction().begin();
            em.merge(myuser); // to update an object in the database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public boolean deleteRecord(String userId) {
        try {
            Myuser myuser = findMyuser(userId);
            
            if (myuser == null) {
                return false;
            } else {
                try {
                    em.getTransaction().begin();
                    em.remove(myuser); // to delete an object in the database
                    em.getTransaction().commit();
                    return true;
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private Myuser myDTO2DAO(MyuserDTO myDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myDTO.getUserid());
        myuser.setName(myDTO.getName());
        myuser.setPassword(myDTO.getPassword());
        myuser.setEmail(myDTO.getEmail());
        myuser.setPhone(myDTO.getPhone());
        myuser.setAddress(myDTO.getAddress());
        myuser.setSecqn(myDTO.getSecQn());
        myuser.setSecans(myDTO.getSecAns());
        return myuser;
    }

    private MyuserDTO myDAO2DTO(Myuser myuser) {
        MyuserDTO myDTO = new MyuserDTO(myuser.getUserid(), myuser.getName(),
                myuser.getPassword(), myuser.getEmail(), myuser.getPhone(),
                myuser.getAddress(), myuser.getSecqn(), myuser.getSecans());
        return myDTO;
    }
}
