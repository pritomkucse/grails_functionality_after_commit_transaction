dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
    dbCreate = "update"
    username = "root"
    password = ""
    logSql = false
    loggingSql = false
    properties {
        maxActive = 1000
        maxIdle = 100
        minIdle = 50
        initialSize = 1
        minEvictableIdleTimeMillis = 60000
        timeBetweenEvictionRunsMillis = 60000
        numTestsPerEvictionRun = 3
        maxWait = 10000
        testOnBorrow = true
        testWhileIdle = true
        testOnReturn = true
        validationQuery = "SELECT 1"
        minEvictableIdleTimeMillis = 1800000
        timeBetweenEvictionRunsMillis = 1800000
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
    format_sql = false
    use_sql_comments = false
}

// environment specific settings
environments {
    development {
        dataSource {
            url = "jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8"
            logSql = true
            loggingSql = true
        }
    }
    test {
        dataSource {
            url = "jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8"
            logSql = true
            loggingSql = true
        }
    }
    production {
        dataSource {
            url = "jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8"
        }
    }
}
log4j = {
    debug 'org.hibernate.SQL'
    trace 'org.hibernate.type.descriptor.sql.BasicBinder'
}