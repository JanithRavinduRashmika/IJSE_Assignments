package lk.royalInstitute.dao;

import lk.royalInstitute.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO <Entity extends SuperEntity, ID extends Serializable> extends SuperDAO{
    public boolean add(Entity entity) throws Exception;
    public boolean delete(ID id) throws Exception;
    public boolean update(Entity entity) throws Exception;
    public Entity get(ID id) throws Exception;
    public List<Entity> getAll() throws Exception;

}
