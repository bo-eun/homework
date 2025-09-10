package it.korea.app_boot.common.files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {
    @Value("${server.file.upload.path}")
    private String filePath;

    /*
     * 파일 업로드 기능
     */
    // 클라이언트가 업로드한 파일을 @Value("${server.file.upload.path}") application.yml에서 설정한 path에 저장
    public Map<String, Object> uploadFiles(MultipartFile file, String type) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        if(file == null || file.isEmpty()) {
            return null;
        }

        String fileName = file.getOriginalFilename(); // 클라이언트가 올린 파일 이름
        String extention = fileName.substring(fileName.lastIndexOf(".") +1); // 파일 확장자
        String randName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16); // 랜덤한 이름 생성
        String storedFileName = randName + "." + extention; // 저장할 파일 이름

        filePath = filePath + type + File.separator;
        String fullPath = filePath + storedFileName;
        File newFile = new File(fullPath);

        // 경로 없으면 만들어주기
        if(!newFile.getParentFile().exists()) {
            // 경로 만들어줌
            newFile.getParentFile().mkdirs();
        }

        newFile.createNewFile(); // 빈 파일
        file.transferTo(newFile);

        resultMap.put("fileName", fileName);
        resultMap.put("storedFileName", storedFileName);
        resultMap.put("filePath", filePath);

        return resultMap;
    }

    /*
     * 파일 삭제 기능
     */
    public void deleteFIle(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
        }
    }
}
