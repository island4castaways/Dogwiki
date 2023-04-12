package com.dogwiki.commu.service;


import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dogwiki.commu.entity.PictureEntity;
import com.dogwiki.commu.repository.PictureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PictureService {
	@Autowired
	PictureRepository picRepository;
	
	public void write(PictureEntity picEntity , MultipartFile file) throws Exception{
        /*우리의 프로젝트경로를 담아주게 된다 - 저장할 경로를 지정*/
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid = UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName = uuid + "_" + file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        /*디비에 파일 넣기*/
        picEntity.setFilename(fileName);
        /*저장되는 경로*/
        picEntity.setFilepath("/files/" + fileName); /*저장된파일의이름,저장된파일의경로*/
        
        /*파일 저장*/
        picRepository.save(picEntity);
    }
	
	public Page<PictureEntity> picBoardList(Pageable pageable, int category){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return picRepository.findALLByCategory(category, pageable);
	}
	
	public Optional<PictureEntity> selectOnePicture(Integer id) {
		return picRepository.findById(id);
	}
	
	public void deletePicture(Integer id) {
		picRepository.deleteById(id);
	}
	
	public PictureEntity updatePicture(PictureEntity entity) {
		return picRepository.save(entity);
	}
	
	public Page<PictureEntity> searchPicBoard(String search, Pageable pageable){
		return picRepository.getListWithQuery(search, pageable);
	}

}
