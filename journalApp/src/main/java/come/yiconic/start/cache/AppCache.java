package come.yiconic.start.cache;

import come.yiconic.start.entity.ConfigJurnalAppEntity;
import come.yiconic.start.repository.ConfigAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigAppRepo configAppRepo;
    public Map<String,String> appcache;


    @PostConstruct  //by  using this when the bean is created the method is invoke
            //when we start the application the bean is created and the init method is called
    //hence only once we call the method and retrive thedata from the databases and store it in the memory
    //this procese is called inmemory cache.
    public void init(){
        List<ConfigJurnalAppEntity> all=configAppRepo.findAll();
        for(ConfigJurnalAppEntity configJurnalAppEntity:all){
            appcache.put(configJurnalAppEntity.getKey(),configJurnalAppEntity.getValue());
        }

    }

}
