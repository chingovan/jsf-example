package com.blogspot.chingovan.jsf_example.trainer.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by ChiNV on 11/26/2017.
 */
@MappedSuperclass
public class AbstractEntity implements PersistentEntity<Long>{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }
}
