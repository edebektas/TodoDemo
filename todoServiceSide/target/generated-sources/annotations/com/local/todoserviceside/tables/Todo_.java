package com.local.todoserviceside.tables;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-08T20:25:54")
@StaticMetamodel(Todo.class)
public class Todo_ { 

    public static volatile SingularAttribute<Todo, Integer> listId;
    public static volatile SingularAttribute<Todo, Date> crtdate;
    public static volatile SingularAttribute<Todo, String> dependedTodoId;
    public static volatile SingularAttribute<Todo, Date> dueDate;
    public static volatile SingularAttribute<Todo, String> name;
    public static volatile SingularAttribute<Todo, Integer> id;
    public static volatile SingularAttribute<Todo, String> detail;
    public static volatile SingularAttribute<Todo, String> status;

}