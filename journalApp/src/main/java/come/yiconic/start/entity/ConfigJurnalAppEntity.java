package come.yiconic.start.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class ConfigJurnalAppEntity {
    private String key;

    private String value;
}
