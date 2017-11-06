package com.corleone.file.base.impl;

import com.corleone.file.base.FileDao;
import com.corleone.file.entity.FileInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by jackie on
 * 2017/11/5.
 */
@Repository
@Transactional
public class FIleDaoImpl implements FileDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(FileInfo fileInfo) throws Exception {
        if (fileInfo == null) {
            return null;
        }
        Date currentDate = new Date();
        Method updateTimeMethod = ReflectionUtils.findMethod(fileInfo.getClass(), "setUpdateTime", Date.class);
        if (updateTimeMethod != null) {
            updateTimeMethod.invoke(fileInfo, currentDate);
        }
        String fileId = fileInfo.getId();
        if (StringUtils.isEmpty(fileId)) {
            Method createTimeMethod = ReflectionUtils.findMethod(fileInfo.getClass(), "setCreateTime", Date.class);
            if (createTimeMethod != null) {
                createTimeMethod.invoke(fileInfo, currentDate);
            }
        }
        return getSession().save(fileInfo);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
