package com.az.postgreDatabaseJPAHibernate.mappers;



public interface Mappers<A,B> {

    B mapTo(A a);

    A mapFrom(B b);
}
