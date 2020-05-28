package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Информация о шарде клиента
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class ClientShardingInfoDto {

    @JsonProperty("roles")
    @Valid
    private List<String> roles = new ArrayList<String>();

    @JsonProperty("routeName")
    private String routeName = null;

    @JsonProperty("shardName")
    private String shardName = null;

    @JsonProperty("storage")
    @Valid
    private Map<String, String> storage = null;

    public ClientShardingInfoDto roles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public ClientShardingInfoDto addRolesItem(String rolesItem) {
        this.roles.add(rolesItem);
        return this;
    }

    /**
     * Роли клиента
     *
     * @return roles
     **/
    @NotNull

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public ClientShardingInfoDto routeName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    /**
     * Название маршрута
     *
     * @return routeName
     **/
    @NotNull

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public ClientShardingInfoDto shardName(String shardName) {
        this.shardName = shardName;
        return this;
    }

    /**
     * Название шарда
     *
     * @return shardName
     **/
    @NotNull

    public String getShardName() {
        return shardName;
    }

    public void setShardName(String shardName) {
        this.shardName = shardName;
    }

    public ClientShardingInfoDto storage(Map<String, String> storage) {
        this.storage = storage;
        return this;
    }

    public ClientShardingInfoDto putStorageItem(String key, String storageItem) {
        if (this.storage == null) {
            this.storage = new HashMap<String, String>();
        }
        this.storage.put(key, storageItem);
        return this;
    }

    /**
     * Ключ - значение (канал, ос, версия и т.п)
     *
     * @return storage
     **/
    public Map<String, String> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, String> storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientShardingInfoDto clientShardingInfoDto = (ClientShardingInfoDto) o;
        return Objects.equals(this.roles, clientShardingInfoDto.roles) &&
                Objects.equals(this.routeName, clientShardingInfoDto.routeName) &&
                Objects.equals(this.shardName, clientShardingInfoDto.shardName) &&
                Objects.equals(this.storage, clientShardingInfoDto.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roles, routeName, shardName, storage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientShardingInfoDto {\n");

        sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
        sb.append("    routeName: ").append(toIndentedString(routeName)).append("\n");
        sb.append("    shardName: ").append(toIndentedString(shardName)).append("\n");
        sb.append("    storage: ").append(toIndentedString(storage)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

