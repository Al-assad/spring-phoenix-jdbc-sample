package site.assad.sample.phoenix.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import site.assad.sample.phoenix.po.UserPO;

import java.util.List;

/**
 * 测试 DAO
 * @author Al-assad
 * @since 2019/10/7
 */
@Repository
public class SampleDAO {
    
    @Autowired
    @Qualifier("phoenixTemplate")
    private JdbcTemplate phoenixTemplate;
    
    public void addUser(UserPO po) {
        String sql = "UPSERT INTO TEST.USER VALUES(?, ?, ?, ?)";
        phoenixTemplate.update(sql, po.getRowKey(), po.getName(), po.getAge(), po.getBirthday());
    }
    
    public void deleteUser(String rowKey){
        String sql = "DELETE FROM TEST.USER WHERE ROWKEY = ?";
        phoenixTemplate.update(sql, rowKey);
    }
    
    public UserPO getUserByRowKey(String rowKey){
        String sql = "SELECT * FROM TEST.USER WHERE ROWKEY = ?";
        List<UserPO> userPOS = phoenixTemplate.query(sql, new Object[]{rowKey}, (resultSet, i) ->{
            UserPO po = new UserPO();
            po.setRowKey(resultSet.getString("ROWKEY"));
            po.setName(resultSet.getString("NAME"));
            po.setAge(resultSet.getInt("AGE"));
            po.setBirthday(resultSet.getDate("BIRTHDAY"));
            return po;
        });
        if (userPOS == null || userPOS.isEmpty()) {
            return null;
        }
        return userPOS.get(0);
    }
    
}
