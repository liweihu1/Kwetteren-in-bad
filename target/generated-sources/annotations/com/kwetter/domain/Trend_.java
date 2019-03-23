package com.kwetter.domain;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Trend.class)
public abstract class Trend_ {

	public static volatile SingularAttribute<Trend, String> name;
	public static volatile ListAttribute<Trend, Kweet> kweets;
	public static volatile SingularAttribute<Trend, UUID> id;

}

