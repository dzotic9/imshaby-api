package by.imsha.domain;

import by.imsha.utils.ServiceUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * Represents city class
 *
 */
@Document
@ApiModel
public class City {

    @Id
    private String id;

    @NotNull
    @NotEmpty
    @Indexed(unique = true)
    private String name;

//    @JsonIgnore
    @ApiModelProperty(value = "key <*> is language code")
    private Map<String, LocalizedBaseInfo> localizedInfo = new HashMap<>();

    public String getName() {
        return this.name;
    }

    public String getLocalizedName() {
        LocalizedBaseInfo localizedBaseInfo = getLocalizedInfo().get(ServiceUtils.fetchUserLangFromHttpRequest());
        String calculatedName = name;
        if(localizedBaseInfo != null){
            calculatedName = ((LocalizedCity) localizedBaseInfo).getName();
        }
        return calculatedName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public City(String name) {
        this.name = name;
    }

    public City() {
    }

    public Map<String, LocalizedBaseInfo> getLocalizedInfo() {
        return localizedInfo;
    }

    public void setLocalizedInfo(Map<String, LocalizedBaseInfo> localizedInfo) {
        this.localizedInfo = localizedInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!name.equals(city.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
