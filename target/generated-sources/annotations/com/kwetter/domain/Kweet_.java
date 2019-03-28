package com.kwetter.domain;

import java.util.Date;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Kweet.class)
public abstract class Kweet_ {

	public static volatile SingularAttribute<Kweet, Date> dateCreated;
	public static volatile SingularAttribute<Kweet, User> author;
	public static volatile ListAttribute<Kweet, User> heartedBy;
	public static volatile ListAttribute<Kweet, User> mentions;
	public static volatile SingularAttribute<Kweet, Integer> reportedAmount;
	public static volatile SingularAttribute<Kweet, UUID> id;
	public static volatile SingularAttribute<Kweet, String> message;
	public static volatile ListAttribute<Kweet, Trend> trends;
	public static volatile SingularAttribute<Kweet, Date> dateUpdated;

}

