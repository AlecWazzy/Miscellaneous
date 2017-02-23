/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.businesslogic;

import java.util.List;
import sait.dataaccess.RoleRepository;
import sait.dataaccess.UserRepository;
import sait.domainmodel.Role;
import sait.domainmodel.User;

public class AdminService {
    public User get(String userName) throws Exception {
        UserRepository ur = new UserRepository();
        return ur.getUser(userName);
    }
  
    //This method must either handle, or throw SQLException
    public List<User> getAll() throws Exception {
        UserRepository ur = new UserRepository();
        return ur.getAll();
    }
                                                       
    public int update(String userName, String firstName, String lastName, String email, String userPassword) throws Exception {
        UserRepository ur = new UserRepository();
        User user = ur.getUser(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserPassword(userPassword);
        user.setEmail(email);
    	return ur.update(user);
    }
    
    public int delete(String userName, User currentUser) throws Exception {
        if(!currentUser.getUserName().equals(userName))
        {
            UserRepository ur = new UserRepository();
            User user = ur.getUser(userName);
            return ur.delete(user);
        }
        return -1;
    }
    
    public int insert(String userName, String firstName, String lastName, String email, String userPassword) throws Exception {
        UserRepository ur = new UserRepository();
    	User user = new User(userName, firstName, lastName, email, userPassword);
    	return ur.insert(user);
    }
    
    public int promote(String userName, User currentUser) throws Exception {
        if(!currentUser.getUserName().equals(userName))
        {
            UserRepository ur = new UserRepository();
            RoleRepository rr = new RoleRepository();
            User user = ur.getUser(userName);       //gets the user from database
            
            Role role = rr.getRole(1);              //get role for admin(1)
            List<User> users = role.getUserList();  //retrieves lists of admins  from database
            users.add(user);
            role.setUserList(users);  

            if(rr.update(role) == 1)                //Stores roleId and userName to userrole table
            {
                List<Role> userOwnedRoleList =  user.getRoleList(); //Gets user owned role from database
                userOwnedRoleList.add(role);
                user.setRoleList(userOwnedRoleList);                //Allow admin access
                return ur.update(user);
            }
        }
        return -1;
    }
    
    public int demote(String userName, User currentUser) throws Exception {
        if(!currentUser.getUserName().equals(userName))
        {
            UserRepository ur = new UserRepository();
            RoleRepository rr = new RoleRepository();
            User user = ur.getUser(userName);       //gets the user from database

            Role role = rr.getRole(1);              //get role for admin(1)
            List<User> users = role.getUserList();  //retrieves lists of admins  from database
            users.remove(user);
            role.setUserList(users);  

            if(rr.update(role) == 1)                //Removes roleId and userName from userrole table
            {
                List<Role> userOwnedRoleList =  user.getRoleList(); //Gets user owned role from database
                userOwnedRoleList.remove(role);
                user.setRoleList(userOwnedRoleList);                //Discontinue admin access
                return ur.update(user);
            }
        }
        return -1;
    }
}
