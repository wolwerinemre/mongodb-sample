package com.insparx.sample.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.insparx.sample.model.DistributedLock;
import com.insparx.sample.repository.DistributedLockRepository;
import com.insparx.sample.service.DistributedLockService;

@Service
public class DistributedLockServiceImpl implements DistributedLockService{
	
	Logger logger = Logger.getLogger(DistributedLockServiceImpl.class.getName());
	
	@Autowired
	private DistributedLockRepository distributedLockRepository;

	@Override
	public boolean tryLock(String key) {
		return getAndSaveActions(key,true);
	}

	@Override
	public boolean releaseLock(String key) {
		return getAndSaveActions(key,false);
	}

	@Transactional
	private boolean getAndSaveActions(String key,boolean locked) {
		DistributedLock distributedLock =  distributedLockRepository.findOneByKey(key);
		if(!ObjectUtils.isEmpty(distributedLock)) {
			distributedLock.setLocked(locked);
			try {
				distributedLockRepository.save(distributedLock);			
				return true;
			} catch (Exception e) {
				logger.warning("Save failed! Something went wrong!");
				return false;
			}
			
		}
		else 
			return false;
	}
	
	
}
