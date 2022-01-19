package com.example.demo.graphQL;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static final List<Map<String,String>> users = Arrays.asList(
            ImmutableMap.of("id","11","name","user11","age","11","gender", "MALE","accountId","11"),
            ImmutableMap.of("id","12","name","user12","age","12","gender", "FEMALE","accountId","12"),
            ImmutableMap.of("id","13","name","user13","age","13","gender", "MALE","accountId","13"),
            ImmutableMap.of("id","14","name","user14","age","14","gender", "FEMALE","accountId","14")
    );

    private static final List<Map<String,String>> accounts = Arrays.asList(
            ImmutableMap.of("id","11","username","username11","password","pwd11"),
            ImmutableMap.of("id","12","username","username12","password","pwd12"),
            ImmutableMap.of("id","13","username","username13","password","pwd13"),
            ImmutableMap.of("id","14","username","username14","password","pwd14")
    );


    public DataFetcher getUserById() {
        return dataFetchingEnvironment -> {
            String userId = dataFetchingEnvironment.getArgument("id");
            return users
                    .stream()
                    .filter(user -> user.get("id").equals(userId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAccountById() {
        return dataFetchingEnvironment -> {
            Map<String,String> user = dataFetchingEnvironment.getSource();
            String accountId = user.get("accountId");
            return accounts
                    .stream()
                    .filter(account -> account.get("id").equals(accountId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
