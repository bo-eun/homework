package it.korea.app_boot.common.files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.MultiStepRescaleOp;
import com.mortennobel.imagescaling.AdvancedResizeOp;

@Component
public class FileUtils {
    /*
     * 파일 업로드 기능
     */
    // 클라이언트가 업로드한 파일을 @Value("${server.file.upload.path}") application.yml에서 설정한 path에 저장
    public Map<String, Object> uploadFiles(MultipartFile file, String filePath) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        if(file == null || file.isEmpty()) {
            return null;
        }

        String fileName = file.getOriginalFilename(); // 클라이언트가 올린 파일 이름
        String extention = fileName.substring(fileName.lastIndexOf(".") +1); // 파일 확장자
        String randName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16); // 랜덤한 이름 생성
        String storedFileName = randName + "." + extention; // 저장할 파일 이름

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
    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
        }
    }

    /**
     * 썸네일 만들기
     */
    public String thumbNailFile(int width, int height, File originFile, String thumbPath) throws Exception {
        String thumbFileName = "";

        String fileName = originFile.getName();
        String extention = fileName.substring(fileName.lastIndexOf(".") +1); // 파일 확장자
        String randName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16); // 랜덤한 이름 생성
        thumbFileName = randName + "." + extention; // 저장할 파일 이름

        try(
            InputStream in = new FileInputStream(originFile);
            BufferedInputStream bf = new BufferedInputStream(in);
        ) { 
            // 원본 이미지 파일 뜨기
            BufferedImage originImage  =  ImageIO.read(originFile);
            // 이미지 사이즈 줄이기
            MultiStepRescaleOp scaleImage = new MultiStepRescaleOp(width, height);
            // 마스킹 처리
            scaleImage.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
            // 리사이즈 이미지 생성
            BufferedImage resizeImage = scaleImage.filter(originImage, null);

            String thumbFilePath = thumbPath + thumbFileName;

            File resizeFile = new File(thumbFilePath);

            if(!resizeFile.getParentFile().exists()) {
                // 경로 없으면 만들어주기
                resizeFile.getParentFile().mkdirs();
            }

            // 리사이즈한 파일 실제 경로에 생성. 결과를 리턴
            boolean isWrite = ImageIO.write(resizeImage, extention, resizeFile);

            if(!isWrite) {
                throw new RuntimeException("썸네일 생성 오류");
            }


        } catch(Exception e) {
            thumbFileName = null;
            e.printStackTrace();
            throw new RuntimeException("썸네일");
        }

        return thumbFileName;
    }
}
