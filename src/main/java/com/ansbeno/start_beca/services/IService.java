package com.ansbeno.start_beca.services;

import com.ansbeno.start_beca.dtos.PagedResultDto;

public interface IService<T, I> {

      public PagedResultDto<T> findAll(int page, String keyword);

      public T findById(I id);

      public T save(T dto);

      public void delete(I id);

}
