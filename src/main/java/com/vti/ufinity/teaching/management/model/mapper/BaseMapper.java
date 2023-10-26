package com.vti.ufinity.teaching.management.model.mapper;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface BaseMapper<Entity, Dto> {

    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);
}
