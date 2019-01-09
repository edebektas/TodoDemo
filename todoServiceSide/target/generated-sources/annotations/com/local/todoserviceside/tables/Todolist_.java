package com.local.todoserviceside.tables;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-09T13:42:06")
@StaticMetamodel(Todolist.class)
public class Todolist_ { 

    public static volatile SingularAttribute<Todolist, Date> crtDate;
    public static volatile SingularAttribute<Todolist, String> name;
    public static volatile SingularAttribute<Todolist, Integer> id;
    public static volatile SingularAttribute<Todolist, Integer> ownerId;

}