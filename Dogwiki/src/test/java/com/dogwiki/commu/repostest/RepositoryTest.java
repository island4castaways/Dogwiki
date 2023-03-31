package com.dogwiki.commu.repostest;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.repository.TrainingRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	TrainingRepository tr;
	
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			TrainingEntity en = TrainingEntity.builder()
					.trProf("test")
					.trTitle("test" + i)
					.build();
			tr.save(en);
		});
	}
	
	@Test
	public void testSelect() {
		int trId = 103;
		Optional<TrainingEntity> result = tr.findById(trId);
		log.info("==================");
		if(result.isPresent()) {
			TrainingEntity en = result.get();
			log.info(en);
		}
	}
	
	@Test
	public void testDelete() {
		int trId = 103;
		tr.deleteById(trId);
	}
	
	@Test
	public void testPageDefault() {
		//page1, 10개
		Sort sort = Sort.by("trId").descending();
		Pageable pageable = PageRequest.of(1, 10, sort);
		Page<TrainingEntity> result = tr.findAll(pageable);
		result.get().forEach(en -> {
			System.out.println(en);
		});
	}

}
