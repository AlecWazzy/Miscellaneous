package sait.domainmodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sait.domainmodel.Note;
import sait.domainmodel.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-07T09:50:21")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile ListAttribute<User, Note> noteList;
    public static volatile SingularAttribute<User, String> resetPasswordUUID;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile ListAttribute<User, Role> roleList;
    public static volatile SingularAttribute<User, String> email;

}