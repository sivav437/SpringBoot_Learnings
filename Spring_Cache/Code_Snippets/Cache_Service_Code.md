## CODE 

@Service
public class MyService {

    @Cacheable(value = "myCache", key = "#id")
    public String getData(String id) {
        // Simulate fetching data from a slow source
        System.out.println("Fetching data for ID: " + id);
        return "Data for ID: " + id;
    }

    @CachePut(value = "myCache", key = "#id")
    public String updateData(String id, String newData) {
      // Simulate updating data in a slow source
      System.out.println("Updating data for ID: " + id);
      return "Updated data for ID: " + id + " is: " + newData;
    }

    @CacheEvict(value = "myCache", key = "#id")
    public void deleteData(String id) {
        System.out.println("Deleting data for ID: " + id);
        // Simulate deleting data from a slow source
    }
}