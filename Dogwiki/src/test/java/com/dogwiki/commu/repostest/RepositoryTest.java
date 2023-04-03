package com.dogwiki.commu.repostest;

import java.util.List;
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
					.trUrl("#")
					.build();
			tr.save(en);
		});
	}
	
	@Test
	public void testSelect() {
		int trId = 100;
		Optional<TrainingEntity> result = tr.findById(trId);
		log.info("==================");
		if(result.isPresent()) {
			TrainingEntity en = result.get();
			log.info(en);
		}
	}
	
	@Test
	public void testDelete() {
		TrainingEntity te = TrainingEntity.builder()
				.trProf("test1")
				.trTitle("test101")
				.trUrl("#")
				.build();
		int trId = 101;
		tr.deleteById(trId);
	}
	
	@Test
	public void testPaging() {
		//page1, 10개
		Sort sort = Sort.by("trId").descending();
		Pageable pageable = PageRequest.of(1, 10, sort);
		Page<TrainingEntity> result = tr.findAll(pageable);
		result.get().forEach(en -> {
			System.out.println(en);
		});
	}
	
	@Test
	public void testUpdate() {
		TrainingEntity modified = TrainingEntity.builder()
				.trId(101)
				.trProf("test")
				.trTitle("test101modified")
				.trUrl("https://www.youtube.com/embed/3pp-RUaUhvY")
				.build();
		tr.save(modified);
	}
	
	@Test
	public void testSelectByTrProf() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("trId").descending());
		List<TrainingEntity> te = tr.findByTrProf("test", pageable);
		te.stream().forEach(entity -> {
			System.out.println(entity.getTrProf());
		});
	}
	
	@Test
	public void testSelectOne() {
		Optional<TrainingEntity> op = tr.findById(2);
		TrainingEntity te = op.get();
		System.out.println(te.getTrId());
		System.out.println(te.getTrProf());
		System.out.println(te.getTrTitle());
		System.out.println(te.getTrUrl());
		System.out.println(te.getTrDate());
		System.out.println(te.getTrHit());
	}

}
