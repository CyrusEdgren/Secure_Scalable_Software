/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Myuser;
import entity.MyuserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cyrus
 */
@Stateless
public class MyuserFacade implements MyuserFacadeRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-JEE-DTO-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Myuser myuser) {
        em.persist(myuser);
    }

    private void edit(Myuser myuser) {
        em.merge(myuser);
    }

    private void remove(Myuser myuser) {
        em.remove(em.merge(myuser));
    }

    private Myuser find(Object id) {
        return em.find(Myuser.class, id);
    }

    @Override
    public boolean createRecord(MyuserDTO myuserDTO) {
        if (find(myuserDTO.getUserid()) != null) {
            // user whose userid can be found
            return false;
        }
        // user whose userid could not be found
        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            this.create(myuser); // add to database
            return true;
        } catch (Exception ex) {
            return false; // something is wrong, should not be here though
        }
    }
    
    @Override
    public MyuserDTO getRecord(String userId) {
        Myuser myuser = find(userId);
        
        if (myuser == null) {
            return null;
        } else {
            return myDAO2DTO(myuser);
        }
    }
    
    @Override
    public boolean updateRecord(MyuserDTO myuserDTO) {
        if (find(myuserDTO.getUserid()) == null) {
            return false;
        }
        
        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            this.edit(myuser);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean deleteRecord(String userId) {
        Myuser myuser = find(userId);
        
        if (myuser == null) {
            return false;
        }
        
        try {
            this.remove(myuser);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }   
    
    private Myuser myDTO2DAO(MyuserDTO myuserDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myuserDTO.getUserid());
        myuser.setName(myuserDTO.getName());
        myuser.setPassword(myuserDTO.getPassword());
        myuser.setEmail(myuserDTO.getEmail());
        myuser.setPhone(myuserDTO.getPhone());
        myuser.setAddress(myuserDTO.getAddress());
        myuser.setSecqn(myuserDTO.getSecQn());
        myuser.setSecans(myuserDTO.getSecAns());
        return myuser;
    }
    
    private MyuserDTO myDAO2DTO(Myuser myuser) {
        MyuserDTO myDTO = new MyuserDTO(myuser.getUserid(), myuser.getName(),
                myuser.getPassword(), myuser.getEmail(), myuser.getPhone(),
                myuser.getAddress(), myuser.getSecqn(), myuser.getSecans());
        return myDTO;
    }

    @Override
    public List<MyuserDTO> getRecordsByAddress(String address) {
        javax.persistence.Query query;
        query = em.createNamedQuery("Myuser.findByAddress").setParameter("address", address);
        List<Myuser> daoList = query.getResultList();
        if (daoList.isEmpty()) {
            return null;
        } else {
            List<MyuserDTO> dtoList = new ArrayList<MyuserDTO>();
            for (Myuser myuser : daoList) {
                MyuserDTO myuserDTO = myDAO2DTO(myuser);
                dtoList.add(myuserDTO);
            }
            
            return dtoList;
        }
    }
}
