package com.insparx.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.insparx.sample.model.DistributedLock;

@Repository
public interface DistributedLockRepository extends MongoRepository<DistributedLock, Long>  {
	
	public DistributedLock findOneByKey(String key);

}
