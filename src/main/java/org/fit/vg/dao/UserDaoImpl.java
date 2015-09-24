package org.fit.vg.dao;

import org.fit.vg.model.Users;
import org.springframework.stereotype.Repository;

@Repository("userDao")

public class UserDaoImpl extends AbstractDao<Users> implements UserDao{

}
