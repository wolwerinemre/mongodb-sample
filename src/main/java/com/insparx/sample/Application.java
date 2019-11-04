package com.insparx.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.insparx.sample.model.DistributedLock;
import com.insparx.sample.repository.DistributedLockRepository;
import com.insparx.sample.service.DistributedLockService;


@EnableAutoConfiguration
@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private DistributedLockService distributedLockService;
	
	@Autowired
	private DistributedLockRepository distributedLockRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		distributedLockRepository.deleteAll();

		// save a couple of distributedLock
		DistributedLock distributedLock = distributedLockRepository.save(new DistributedLock(1,"subject1",false));
		System.out.println(distributedLock.toString());
		
		DistributedLock distributedLock2 = distributedLockRepository.save(new DistributedLock(2,"subject2",true));
		System.out.println(distributedLock2.toString());

		// fetch all distributedLock
		System.out.println("-------------------------------");
		for (DistributedLock distLock : distributedLockRepository.findAll()) {
			System.out.println(distLock.toString());
		}
		System.out.println("-------------------------------");
		
		//trylock and releaselock actions
		boolean value = distributedLockService.tryLock("subject1");
		System.out.println("subject1" + value);
		
		boolean value2 = distributedLockService.releaseLock("subject2");
		System.out.println("subject2" + value2);

		// fetch all distributedLock
		System.out.println("-------------------------------");
		for (DistributedLock distLock : distributedLockRepository.findAll()) {
			System.out.println(distLock.toString());
		}
		System.out.println("-------------------------------");
	}
}
