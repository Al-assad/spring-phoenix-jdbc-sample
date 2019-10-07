package site.assad.sample.phoenix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.assad.sample.phoenix.dao.SampleDAO;
import site.assad.sample.phoenix.po.UserPO;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PhoenixTest {
    
    @Autowired
    private SampleDAO sampleDAO;
    
    @Test
    public void testAddUser(){
        UserPO po = new UserPO();
        po.setRowKey("key125");
        po.setName("Nil");
        po.setAge(33);
        po.setBirthday(new Date());
        sampleDAO.addUser(po);
    }
    
    @Test
    public void testDeleteUser(){
        sampleDAO.deleteUser("key125");
    }
    
    @Test
    public void testGetUserByRowKey(){
        UserPO po = sampleDAO.getUserByRowKey("key125");
        if (po != null) {
            System.out.println(po.toString());
        }
    }
    
}
