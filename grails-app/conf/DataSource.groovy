dataSource {
  pooled = true
  driverClassName = "org.h2.Driver"
  username = "sa"
  password = ""
}
hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = false
  cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
  development {
    dataSource {
      dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
      url = "jdbc:h2:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
    }
  }
  test {
    dataSource {
      dbCreate = "update"
      url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
    }
  }
  production {
    dataSource {
      pooled = true
      driverClassName = "com.mysql.jdbc.Driver"
      dbCreate = "update"
      username = System.getProperty("PARAM1")
      password = System.getProperty("PARAM2")
      dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'
      url = System.getProperty("JDBC_CONNECTION_STRING")
      properties {
        maxActive = -1
        minEvictableIdleTimeMillis = 1000 * 60 * 30
        timeBetweenEvictionRunsMillis = 1000 * 60 * 30
        numTestsPerEvictionRun = 3
        testOnBorrow = true
        testWhileIdle = true
        testOnReturn = true
        validationQuery = "SELECT 1"
      }
    }
  }
}
