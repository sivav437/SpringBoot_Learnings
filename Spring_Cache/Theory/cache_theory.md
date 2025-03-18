## SPRING CACHE

>> cache is a layer between an application and a database.
>> frequently used data is stored inside a cache , so it made easy of data fetching.
>> it helps to save time.

## WHY USE CACHE ?
    - Reduce Latency : Faster access to data compared to data access from database.
    - Decrease Load : reduces the no. of calls to backend s/m or to database.
    - Improves Scalability : helps application handle higher traffic loads efficiently

## STEPS TO USE CACHE ARE

    - first , enable cache in springboot. the annotation here is @EnableCaching
    - to enable , add the above annotation on top of "configuration class" or "main class".

    code:

        @Configuration
        @EnableCaching //enables caching
        public class CacheConfig{
            //statements
        }

        (or)

        @SpringBootApplication
        @EnableCachig
        public class mainApplication{
            //statements
        }

    - can cache the output of a method by using annotation called @Cacheable

    code : 
        # placed inside a service.
       @Cacheable("keyName")
       public String getCacheValue(string inputValue){
        //statements
       }

    - to understand what's the flow behind the scenes we use CacheManager service.

    code:
        @Service
        public class CacheInspectionService{

            @Autowired
            private CacheManager cacheManager;

            public void printCacheContents(String cacheName){
                Cache cache=cacheManager.getCache(cacheName);
                if(cache !=null){
                    System.out.println("Cache Contents ");
                    System.out.println(Objects.requireNonNull(cache.getNativeCache()).toString())
                }else{
                    System.out.println("No Cace: "+cacheName);
                }
            }
        }

    - here data is stored in a concrent hashMap

    - To update cache is @CachePut

    - To delete cache is @CacheEvict

## Types of Caching :

    -> InMemory : cache , created inside memory of your app ( part of application)

    - But Cons: consider an app which is deployed in prod and used horizontal scalling ( instances of app) so each instance has it's own cache. so it maintains isolation.
    (leads to data inconsistence )

    - InMemory cache is fine if we have only one Instance.

    -> Distributed Cache :
        - a single cache for all the instances of app, (outside app) eg: reddis.
        - how we connect to reddis?
    

## ANNOTATIONS ARE

    - @EnableCaching
    - @Cacheable(value="KeyName",key="KEYVALUE")
    - @CachePut(value="KeyName",key="KEYVALUE")
    - @CacheEvict(value="keyName")

NOTE: added key for updating the cache instead of creating new entry ( as we are providing consistent keys it updates the cache)
    
