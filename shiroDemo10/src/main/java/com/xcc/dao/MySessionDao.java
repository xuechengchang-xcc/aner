package com.xcc.dao;

import com.xcc.utils.JdbcTemplateUtils;
import com.xcc.utils.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import java.io.Serializable;
import java.util.List;

/**
 * @create: 2019-07-05 14:31
 * @author: Aner
 * @description:
 **/
public class MySessionDao extends CachingSessionDAO {
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
    /**
    * @Author: Aner
    * @Description: 创建会话
    */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId =generateSessionId(session);
        assignSessionId(session,sessionId);
        String sql="insert into sessions(id,session) values(?,?)";
        jdbcTemplate.update(sql,sessionId, SerializableUtils.serialize(session));
        return session.getId();
    }
    /**
    * @Author: Aner
    * @Description: 更新会话：如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
    */
    @Override
    protected  void doUpdate(Session session){
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()){
            return;  //如果会话过期/停止 没必要更新了
        }
        String sql="update sessions set session=? where id=?";
        jdbcTemplate.update(sql,SerializableUtils.serialize(session),session.getId());
    }
    /**
    * @Author: Aner
    * @Description: 删除会话；当会话过期/会话停止（用户退出）会调用
    */
    @Override
    protected  void doDelete(Session session){
        String sql="delete from sessions where id=?";
        jdbcTemplate.update(sql,session.getId());
    }
    /**
    * @Author: Aner
    * @Description: 根据会话ID获取会话
    */
    @Override
    protected Session doReadSession(Serializable sessionId){
        String sql ="select session from sessions where id=?";
        List<String> sessionStrList =jdbcTemplate.queryForList(sql,String.class,sessionId);
        if (sessionStrList.size()==0) return null;
        return SerializableUtils.deserialize(sessionStrList.get(0));
    }
}
