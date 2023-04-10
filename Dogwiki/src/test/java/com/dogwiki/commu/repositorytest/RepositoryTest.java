package com.dogwiki.commu.repositorytest;


import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dogwiki.commu.entity.UserEntity;
import com.dogwiki.commu.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	UserRepository userRepository; //memoRepository 주입
	
	// 이후에 test진행 - CRUD 테스트 
	@Test
	public void testDependency() {
		log.info("주입 여부 : "+userRepository.getClass().getName());
	}
	
	//삽입 확인
	@Test
	public void testInsert() {
		IntStream.rangeClosed(3, 10).forEach(i -> {
			UserEntity user = UserEntity.builder()
					.userid("user"+i)
					.pw("pw"+i)
					.username("user"+i)
					.email("sdfdf"+i)
					.phone("123")
					.build();
			userRepository.save(user);
		});
	}
	
//	@Test
//	public void testSelect() {
//		//데이터베이스에 존재하는 mno
//		Long mno = 100L;
//		Optional<Memo> result = memoRepository.findById(mno);
//		log.info("=========================================");
//		if(result.isPresent()) {
//			Memo memo = result.get();
//			log.info(memo);
//		}
//	}
//	
//	//데이터 수정
//	@Test
//	public void testUpdate() {
//		System.out.println(userRepository.updateByUserid("nada", "010", "idon@know", "a"));
//	}
	
//	//데이터 삭제
//	@Test
//	public void testDelete() {
//		Long mno = 100L;
//		memoRepository.deleteById(mno);
//		
//	}
//	
//	// 페이징 테스트
//	@Test
//	public void testPageDefault() {
//		// 1페이지 10개
//		Pageable pageable = PageRequest.of(19, 10);
//		Page<Memo> result = memoRepository.findAll(pageable);
//		System.out.println(result);
//		System.out.println("Total Pages : "+result.getTotalPages()); //전체 페이지 개수
//		System.out.println("Total Count : "+result.getTotalElements()); //전체 데이터 개수
//		System.out.println("Page Number : "+result.getNumber()); //현재 페이지 번호 0부터..
//		System.out.println("Page size : "+result.getSize()); //페이지 당 데이터 개수
//		System.out.println("Has next Page? : "+result.hasNext()); //다음 페이지 여부
//		System.out.println("First Page? : "+result.isFirst()); //시작 페이지 여부
//		System.out.println("----------------------------------------------------");
//		
//		for(Memo memo:result.getContent()) {
//			System.out.println(memo);
//		}
//		
//	}
//	
//	//정렬테스트
//	@Test
//	public void testSort() {
//		Sort sort1 = Sort.by("mno").descending(); // 컬럼 "mno"를 기준으로 내림차순
//		Pageable pageable = PageRequest.of(0, 10, sort1);
//		Page<Memo> result = memoRepository.findAll(pageable);
//		result.get().forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
//	
//	//여러개 정렬 기준을 합치는 경우.. 
//	@Test
//	public void testSortConcate() {
//		Sort sort1 = Sort.by("mno").descending(); // 컬럼 "mno"를 기준으로 내림차순
//		Sort sort2 = Sort.by("memoText").ascending(); 
//		Sort sortAll = sort1.and(sort2); //and를 이용한 연결
//		Pageable pageable = PageRequest.of(0, 10, sortAll);
//		Page<Memo> result = memoRepository.findAll(pageable);
//		result.get().forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
//	
//	//Method Query 테스트 
//	@Test
//	public void testQueryMethod() {
//		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
//		for (Memo memo : list) {
//			System.out.println(memo);
//		}
//	}
//	
//	
//	// mno가 10 ~ 50까지 내림차순으로 정렬하면서 페이징 처리
//	@Test
//	public void testQueryMethodsPaging() {
//		Pageable pageable = PageRequest.of(
//				0, 
//				10, 
//				Sort.by("mno").descending()
//				);
//		Page<Memo> result = memoRepository.findByMnoBetween(
//				10L, 50L, pageable);
//		result.get().forEach(memo -> System.out.println(memo));
//		
//		
//	}
//	
//	//작업 완료를 위해서 설정이 필요
//	@Commit  //작업 완료
//	@Transactional  //설정 없을시 에러가 발생
//	@Test
//	public void testDeleteQueryMethod() {
//		memoRepository.deleteMemoByMnoLessThan(10L);
//	}
//	
//	//@Query를 이용한 파라미터 바인딩 
//	@Test
//	public void testUpdateQuery(){
//		log.info(memoRepository.updateMemoText(99L,"@Query를 이용한 수정"));
//		log.info(memoRepository.updateMemoText(
//				Memo.builder()
//				.mno(101L)
//				.memoText("@Query를 이용한 수정2")
//				.build()				
//				));
//		
//	
//	}
//	
//	//@Query를 이용한 Paging처리... 
//	@Test
//	public void testSelectQuery() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
//		Page<Memo> page = memoRepository.getListWithQuery(50L,pageable);
//		for(Memo memo : page) {
//			log.info(memo);
//		}
//	}
//	
//	@Test
//	public void testSelectQueryObjectReturn() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
//		Page<Object[]> page = memoRepository.getListWithQueryObject(50L, pageable);
//		for(Object[] arr:page) {
//			log.info(Arrays.toString(arr));
//		}
//	}
//	
//	
//	//Native SQL을 사용한 예제
//	@Test
//	public void testSelectNativeQuery() {
//		List<Object[]> list = memoRepository.getNativeResult();
//		for(Object[] arr : list) {
//			System.out.println(Arrays.toString(arr));
//		}
//	}
	
}
