## What is Caching ?

    - Cache is a part of temporary memory (RAM). It lies between the application and the persistent database.

        APPLICATION ==> CACHE ==> DATA PERSISTENT

    - Caching is a mechanism used to increase the performance of a system. It is a process to store and access data from the cache.

    - It stores the recently used data. This helps to reduces the number of database hits as much as possible.

## Why should we use the cache ?

    - It make data access faster and less expensive
    - It improves the performance of the application.
    - It gives responses quickly.
    - Data access from memory is always faster than fetching from database.
    - It reduces the costly backend requests.

## Types of Caching?
    - In-memory Caching.
    - Database Caching.
    - Web Server Caching.
    - CDN Caching

    In-memory Caching:
        -> a technique which is widely used where data is stored in RAM.
        -> Memcached (simple) and Redis (adv) are examples of in-memory caching.

    Database Caching:
        -> Database caching includes cache in database , It improves the performance by distributing a query workload.
        -> Hibernate first level cache is an example of database caching.

    Web Server Caching:
        -> stores data for reuse.
        -> cached for the first time when a user visits the page. If the user requests the same next time, the cache serves a copy of the page.

    CDN Caching:
        -> The CDN stands for Content Delivery Network. It is a component used in modern web application.
        -> It improves delivery of the content by replicating common requested files such as Html Pages, images, videos, etc. across distributed set of caching servers.

## What are the types of Cache?
    - InMemory Cache
    - Distributed Cache

    others> server cache (**) , client cache , database cache

## What is the default cache and explain?
    - InMemory is the default cache , it uses ConcurrentHashMap , to cache our frequently used data.
    - it is limited to single server and isolated as well.
    - data is stored in key-value pairs.

## Why Distributed Cache is comes in?
    - it makes to maintain single cache( outside the application) to all it's server instances. 
    - eg: reddis

## What Data should be cached?
    - The data that do not change frequently.
    - The data that is needed to read frequently.

## explain about @EnableCaching Annotation?
    - @EnableCaching is  a class level Annotation , used at either main class or any one of config class
    - it enables caching in our application when we run it.

## explain about @Cacheable Annotation?
    - @Cacheable is a method Level Annotation , used to create a cache and store it in InMemory(ConcurrentHashMap).
    - eg: @Cacheable(cacheNames = {"studentCache"}, key = "#studentId")
    - Spring Boot framework to store the returning value of a method inside a ConcurrentHashMap which named as studentCache with studentId as it’s key.
    - springboot uses  specified parameter of the method as the key
    - when the method is called for the firsttime ,check if the value is present inside the ConcurrentHashMap with the given key. if not then it creates a cache and stores there and the next comings calls will fetch data from cache.

    --> Request ==> Cache (checks if exist) ==> Method Executed and return value is stored (if not exist in cache)

## What if we want to Update or Delete an entry from the database?

    - @CachePut Annotation ( for update)
        : @CachePut(cacheNames = {"studentCache"}, key="#student.studentId")
    - @CacheEvict Annotation ( for delete )
        : @CacheEvict(cacheNames = {"studentCache"}, key = "#studentId")

    - Both are Method Level Annotations.

## What is #?
    - #student.studentId and #studentId syntaxes are coming from Spring Expression Language. 

## Giving CrossOrigin ?
    -@CrossOrigin(origins = "*") at classLevel.

## WHY CACHE?
    - it drastically improves performance of an application
    - It Reduces the Backend Calls and database hits.
    - helps application to scale.

## Cache Mechanism ?
    - Spring Boot's caching mechanism provides an abstraction layer that allows developers to easily integrate caching into their applications, improving performance by reducing the need to repeatedly fetch data from slower sources like databases. 
    - It works by intercepting method calls and storing the results in a cache.
    - Subsequent calls with the same parameters retrieve the result from the cache, avoiding the execution of the original method.

## Cache Components?
    Key Components: 
    # Cache Abstraction: 
            Spring provides a consistent API for interacting with different cache providers.
    # Annotations:
            Spring uses annotations to define caching behavior, making it easy to add caching to methods.
    # Cache Providers:
            Spring supports various cache providers, including in-memory caches (like ConcurrentHashMap), and external caches (like Redis, EhCache, and Caffeine).

## How It Works ?
    - Enable Caching:
        Use the @EnableCaching annotation in a configuration class to enable caching.
    - Annotate Methods:
        Use @Cacheable, @CachePut, and @CacheEvict annotations to specify caching behavior for methods.

        @Cacheable: Caches the result of a method based on its parameters. If the result is already in the cache, it returns the cached value instead of executing the method. 

        @CachePut: Updates the cache with the result of the method execution. It always executes the method and updates the cache. 

        @@CacheEvict: Removes one or more entries from the cache.

## CHOOSING CACHE PROVIDER :
    
    In-memory Cache:
        Suitable for simple applications or development environments where data persistence is not critical.
    
    External Cache:
        Recommended for production environments where scalability, reliability, and data persistence are important.

## Best Practices
    Cache only frequently accessed data: Avoid caching data that is rarely used or changes frequently.
    
    Choose an appropriate cache eviction policy: Configure how and when cache entries are removed to prevent stale data.
    
    Monitor cache performance: Track cache hits and misses to optimize caching strategy.

## What is key in cache?
    -   It uniquely identifies each entry in the cache.
    - If we do not specify the key then Spring uses the default mechanism to create the key.

## What is keyGenerator ?
    - It is used to define your own key generation mechanism. We need to create custom key generator class.
    @Cacheable(value=”employees”, keyGenerator=”customKeyGenerator”)

## What is cacheManager ?

    - It specifies the name of cache manager. It is used define your own cache manager and do not want to use spring’s default cache manager

    @Cacheable(value=”employees”, cacheManager=”customCacheManager”)

## condition ?
    We can apply a condition in the attribute by using the condition attribute. We can call it as conditional caching.
    - eg: @Cacheable(value=”employees”, condition="#name.length < 20")

## unless ?
    - It specifies the object to to cached if it matches certain condition.
    - SpEL provides a context variable #result which refers to the object that is fetched and we can apply condition on on its value.

    @Cacheable(value=”employees”, unless=”#result.length < 20”)

## dif b/w cacheput and cacheable

    @Cacheable annotation skips the method execution while the @CachePut annotation runs the method and put its result in the cache.

## for evict:
    It provides parameter called allEntries=true. It evicts all entries rather one entry based on the key.

    @CacheEvict(value=”employee”, allEntries=true)

## @Caching Annotation:
    - It is allows multiple nested caching annotations on the same method. It is used when we want to use multiple annotations of the same type
    - Java does not allow multiple annotations of same type to be declared for given method. To avoid this problem, we use @Caching annotation.

    @Caching(evict = {
  @CacheEvict(“address”), 
  @CacheEvict(value=“employee”, key=”#employee.id”)
})

## @CacheConfig