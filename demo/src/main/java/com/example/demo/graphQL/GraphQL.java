package com.example.demo.graphQL;

import graphql.schema.DataFetchingEnvironment;

public interface GraphQL<T> {
    T get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception;
}
