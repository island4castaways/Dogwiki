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

import com.dogwiki.commu.entity.BoardEntity;
import com.dogwiki.commu.entity.CommentEntity;
import com.dogwiki.commu.entity.TrainingEntity;
import com.dogwiki.commu.entity.UserEntity;
import com.dogwiki.commu.repository.BoardRepository;
import com.dogwiki.commu.repository.CommentRepository;
import com.dogwiki.commu.repository.TrainingRepository;
import com.dogwiki.commu.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	TrainingRepository tr;
	
	@Autowired
	BoardRepository bd;
	
	@Autowired
	CommentRepository cmt;
	
	@Autowired
	UserRepository us;
	
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
				.trUrl("https://www.youtube.com/embed/3h7S65Bu-jE")
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
	
	@Test
	public void testGetByTrTitle() {
		String search = "강아지";
		Pageable pageable = PageRequest.of(1,  10, Sort.by("trId").descending());
		Page<TrainingEntity> page = tr.getByTrTitle(search, pageable);
		List<TrainingEntity> list = page.getContent();
		for(TrainingEntity en : list) {
			System.out.println(en.getTrTitle());
		}
	}
	
	@Test
	public void testGetByTrProfAndTrTitle() {
		String trProf = "설채현";
		String search = "강아지";
		Pageable pageable = PageRequest.of(1, 10, Sort.by("trId").descending());
		Page<TrainingEntity> page = tr.getByTrProfAndTrTitle(trProf, search, pageable);
		List<TrainingEntity> list = page.getContent();
		for(TrainingEntity en : list) {
			System.out.println(en.getTrTitle());
		}
	}
	
	@Test
	public void testGetCountByTrTitle() {
		String search = "강아지";
		long count = tr.getCountByTrTitle(search);
		System.out.println(count);
	}
	
	@Test
	public void testGetCountByTrProfAndTrTitle() {
		String trProf = "설채현";
		String search = "강아지";
		long count = tr.getCountByTrProfAndTrTitle(trProf, search);
		System.out.println(count);
	}
	
	@Test
	public void testUpdateOne() {
		TrainingEntity testEntity = TrainingEntity.builder()
			.trProf("test")
			.trTitle("testTitle")
			.trUrl("#")
			.trHit(0)
			.build();
		tr.save(testEntity);
		TrainingEntity updated = tr.save(TrainingEntity.builder()
				.trId(testEntity.getTrId())
				.trProf("testProf")
				.trTitle("testTitleChanged")
				.trUrl("#")
				.trHit(1)
				.build());
		System.out.println(testEntity.getTrId() + " -> " + updated.getTrId());
		System.out.println(testEntity.getTrProf() + " -> " + updated.getTrProf());
		System.out.println(testEntity.getTrTitle() + " -> " + updated.getTrTitle());
		System.out.println(testEntity.getTrUrl() + " -> " + updated.getTrUrl());
		System.out.println(testEntity.getTrHit() + " -> " + updated.getTrHit());
		System.out.println(testEntity.getTrDate() + " -> " + updated.getTrDate());
	}
	
	@Test
	public void testBoardInsert() {
		UserEntity entity = UserEntity.builder()
				.userid("testuser")
				.pw("1234")
				.username("testname")
				.phone("1234")
				.email("test@test.com")
				.build();
		us.save(entity);
		IntStream.rangeClosed(1, 100).forEach(i -> {
			BoardEntity en = BoardEntity.builder()
					.category(2)
					.user(entity)
					.title("test" + i)
					.content("testContent" + i)
					.build();
			bd.save(en);
		});
	}
	
	@Test
	public void testCommentInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			CommentEntity en = CommentEntity.builder()
					.board_basic(BoardEntity.builder()
							.num(i)
							.build())
					.user(UserEntity.builder()
							.userid("testuser")
							.build())
					.cmtContent("testComment")
					.build();
			cmt.save(en);
		});
	}
	
	@Test
	public void testCommentDelete() {
		cmt.deleteById(8);
	}

}
