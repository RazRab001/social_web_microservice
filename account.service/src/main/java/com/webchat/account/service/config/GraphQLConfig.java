package com.webchat.account.service.config;

import com.webchat.account.service.domain.graphql.resolver.AccountQueryResolver;
import graphql.schema.GraphQLSchema;
import graphql.kickstart.tools.SchemaParser;
import graphql.kickstart.tools.SchemaParserBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {
    private final AccountQueryResolver accountQueryResolver;

    public GraphQLConfig(AccountQueryResolver accountQueryResolver) {
        this.accountQueryResolver = accountQueryResolver;
    }

    @Bean
    public GraphQLSchema graphQLSchema() {
        System.out.println("GraphQL Schema configuration loaded");
        return SchemaParser.newParser()
                .file("graphql\\schema.graphqls")
                .resolvers(accountQueryResolver)
                .build()
                .makeExecutableSchema();
    }
}
