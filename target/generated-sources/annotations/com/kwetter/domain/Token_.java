package com.kwetter.domain;

import java.util.Date;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Token.class)
public abstract class Token_ {

	public static volatile SingularAttribute<Token, Date> expireDate;
	public static volatile SingularAttribute<Token, UUID> id;
	public static volatile SingularAttribute<Token, User> user;

}

